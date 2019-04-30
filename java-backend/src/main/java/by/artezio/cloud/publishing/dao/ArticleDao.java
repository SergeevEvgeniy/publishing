package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, использующийся для взаимодействия с базой данных.
 *
 * @author Denis Shubin
 */
@Repository
public class ArticleDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    private RowMapper<Article> articleRowMapper = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setPublishingId(rs.getInt("publishing_id"));
        article.setTopicId(rs.getInt("topic_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setAuthorId(rs.getInt("author_id"));

        return article;
    };

    private RowMapper<ArticleCoauthor> articleCoauthorRowMapper = (rs, rowNum) -> {
        ArticleCoauthor ac = new ArticleCoauthor();
        ac.setEmployeeId(rs.getInt("employee_id"));
        ac.setArticleId(rs.getInt("article_id"));
        return ac;
    };


    /**
     * Получение списка статей по идентификатору автора.
     *
     * @param authorId int
     * @return список объектов статей {@link Article}, где указанный пользователь является автором.
     */
    public List<Article> getArticleListByJournalistId(final int authorId) {
        return jdbcTemplate.query("SELECT * FROM article\n"
                + "WHERE author_id = :authorId",
            Collections.singletonMap("authorId", authorId), articleRowMapper);
    }

    /**
     * Получение списка всех статей.
     *
     * @return список объектов статей {@link Article}.
     */
    public List<Article> getAllArticles() {
        return jdbcTemplate.query("SELECT * FROM article", articleRowMapper);
    }

    /**
     * Получение списка соавторов по идентификатору статьи.
     *
     * @param id идентификатор статьи
     * @return список объектов класса {@link ArticleCoauthor}
     */
    public List<ArticleCoauthor> getArticleCoauthorsByArticleId(final int id) {
        return jdbcTemplate.query("SELECT * FROM article_coauthors WHERE article_id = :articleId",
            Collections.singletonMap("articleId", id), articleCoauthorRowMapper);
    }

    /**
     * Возвращает статью {@link Article} по её идентификатору.
     *
     * @param id идентификатор статьи
     * @return {@link Article}
     */
    public Article getArticleByArticleId(final int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = :id",
            Collections.singletonMap("id", id), articleRowMapper);
    }


    /**
     * @param articleId id статьи
     * @return {@code true}, если статья опубликована, иначе - {@code false}
     */
    public boolean isPublished(final Integer articleId) {
        Integer id = jdbcTemplate.queryForObject("SELECT COUNT(*) "
                + "FROM article a "
                + "INNER JOIN issue_article ia "
                + "ON ia.article_id = a.id "
                + "WHERE a.id = :articleId",
            Collections.singletonMap("articleId", articleId),
            Integer.class);
        return id > 0;
    }

    /**
     * Метод для удаления статьи.
     *
     * @param articleId id статьи, которую нужно удалить
     */
    @Transactional
    public void deleteArticleById(final Integer articleId) {
        Map<String, Integer> param = new HashMap<>();
        param.put("articleId", articleId);

        jdbcTemplate.update("DELETE FROM issue_article WHERE article_id = :articleId",
            param);
        jdbcTemplate.update("DELETE FROM review WHERE article_id = :articleId",
            param);
        jdbcTemplate.update("DELETE FROM article_coauthors WHERE article_id = :articleId",
            param);
        jdbcTemplate.update("DELETE FROM article WHERE id = :articleId",
            param);
    }

    /**
     * Получение статей по id рубрики и журнала.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    public List<Article> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("publishingId", publishingId);
        params.put("topicId", topicId);
        return jdbcTemplate.query("SELECT * FROM article "
                + "WHERE topic_id = :topicId "
                + "AND publishing_id = :publishingId",
            params, articleRowMapper);
    }

    /**
     * Получение статей по id рубрики, журнала и автора.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @param authorId     id автора
     * @return {@link List} of {@link Article}
     */
    public List<Article> getArticleByTopicAndPublishingAndAuthorId(final int topicId,
                                                                   final int publishingId,
                                                                   final int authorId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("publishingId", publishingId);
        params.put("topicId", topicId);
        params.put("authorId", authorId);
        return jdbcTemplate.query("SELECT * FROM article "
                + "WHERE topic_id = :topicId "
                + "AND publishing_id = :publishingId "
                + "AND author_id = :authorId",
            params, articleRowMapper);
    }

    /**
     * @param article {@link Article}
     * @return id созданной статьи
     */
    public Integer save(final Article article) {
        Map<String, Object> params = new HashMap<>();
        params.put("publishingId", article.getPublishingId());
        params.put("topicId", article.getTopicId());
        params.put("title", article.getTitle());
        params.put("content", article.getContent());
        params.put("authorId", article.getAuthorId());

        jdbcTemplate.update("INSERT INTO article(publishing_id, topic_id, title, content, author_id) "
                + "VALUES(:publishingId, :topicId, :title, :content, :authorId)",
            params);
        return jdbcTemplate.queryForObject("SELECT MAX(id) as newId FROM article", Collections.singletonMap("", ""),
            (rs, rowNum) -> rs.getInt("newId"));
    }

    /**
     * Обновление статьи, и связзанных с ней записей.
     *
     * @param article    {@link Article}
     * @param coauthtors {@link List} of {@link Integer}. Список id соавторов
     */
    public void update(final Article article, final List<Integer> coauthtors) {
        Map<String, Object> params = new HashMap<>();
        params.put("articleId", article.getId());
        params.put("topicId", article.getTopicId());
        params.put("content", article.getContent());
        params.put("title", article.getTitle());
        params.put("publishingId", article.getPublishingId());


        jdbcTemplate.update("UPDATE article SET "
            + "publishing_id = :publishingId, "
            + "topic_id = :topicId, "
            + "title = :title, "
            + "content = :content "
            + "WHERE id = :articleId", params);

        jdbcTemplate.update("DELETE FROM article_coauthors WHERE article_id = :articleId",
            Collections.singletonMap("articleId", article.getId()));

        if (coauthtors != null) {
            for (Integer id : coauthtors) {
                params = new HashMap<>();
                params.put("articleId", article.getId());
                params.put("employeeId", id);
                jdbcTemplate.update("INSERT INTO article_coauthors(article_id, employee_id) "
                        + "VALUES(:articleId, :employeeId)",
                    params);
            }
        }
    }

    /**
     * Получить список неопубликованных статей.
     *
     * @param publishingId id журнала
     * @param topicId      id рубрики
     * @param authorId     id автора
     * @return список статей
     */
    public List<Article> getUnpublishedArticles(final int publishingId,
                                                final int topicId,
                                                final int authorId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("topicId", topicId);
        params.put("publishingId", publishingId);
        params.put("authorId", authorId);
        return jdbcTemplate.query("SELECT a.* "
                + "FROM article a "
                + "WHERE (SELECT COUNT(*) FROM issue_article WHERE article_id = a.id) = 0 "
                + "AND a.topic_id = :topicId "
                + "AND a.publishing_id = :publishingId "
                + "AND a.author_id = :authorId",
            params, articleRowMapper);
    }

    /**
     * Получить количество статей для указанного автора.
     *
     * @param authorId id автора
     * @return количество статей
     */
    public Integer getArticleCountByAuthorId(final int authorId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) "
                + "FROM article "
                + "WHERE author_id = :authorId",
            Collections.singletonMap("authorId", authorId),
            Integer.class);
    }

    /**
     * Получить карту пар 'Название журнала - количество статей'.
     *
     * @param authorId id автора
     * @return карта пар 'Название журнала - количество статей'
     */
    public Map<String, Integer> getCountByPublishingMap(final int authorId) {
        return jdbcTemplate.queryForObject("SELECT p.title, COUNT(*) as count "
                + "FROM publishing p "
                + "INNER JOIN article a "
                + "ON p.id = a.publishing_id "
                + "WHERE a.author_id = :authorId "
                + "GROUP BY p.title",
            Collections.singletonMap("authorId", authorId),
            (rs, rowNum) -> {
                Map<String, Integer> map = new HashMap<>(rowNum);
                while (rs.next()) {
                    map.put(rs.getString("title"), rs.getInt("count"));
                }
                return map;
            });
    }

    /**
     * Получить карту пар 'Название рубрики - количество статей'.
     *
     * @param authorId id автора
     * @return карта пар 'Название рубрики - количество статей'
     */
    public Map<String, Integer> getCountByTopicMap(final int authorId) {
        return jdbcTemplate.queryForObject("SELECT t.name, COUNT(*) as count "
                + "FROM article a "
                + "INNER JOIN topic t ON t.id = a.topic_id "
                + "WHERE a.author_id = :authorId "
                + "GROUP BY t.name",
            Collections.singletonMap("authorId", authorId),
            (rs, rowNum) -> {
                Map<String, Integer> map = new HashMap<>(rowNum);
                while (rs.next()) {
                    map.put(rs.getString("name"), rs.getInt("count"));
                }
                return map;
            });
    }

    /**
     * Метод для проверки, существует ли статья с указанным идентификатором.
     *
     * @param articleId id статьи
     * @return {@code true}, если статья с таким id существует, иначе - {@code false}
     */
    public boolean isArticleExists(final int articleId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM article WHERE id = :articleId",
            Collections.singletonMap("articleId", articleId), Integer.class) == 1;
    }

    /**
     * Получение id авторов, которые проходят указанный фильтр.
     *
     * @param filter фильтр, который уточняет id, которые нужно выбрать из базы данных
     * @return список id авторов
     */
    public List<Integer> getAuthorsIdList(final AuthorFilter filter) {
        Map<String, Integer> params = new HashMap<>();
        params.put("publishingId", filter.getPublishingId());
        params.put("topicId", filter.getTopicId());
        return jdbcTemplate.queryForList(generateQueryForFilter(filter), params, Integer.class);

    }

    /**
     * Метод для генерации запроса на выборку id авторов из базы данных.
     *
     * @param filter фильтр, который уточняет id, которые нужно выбрать из базы данных
     * @return запрос на получение id
     */
    private String generateQueryForFilter(final AuthorFilter filter) {
        StringBuilder bldr = new StringBuilder("SELECT author_id FROM article");
        boolean needsAnd = false;

        if (filter.getTopicId() != null
            || filter.getPublishingId() != null) {
            bldr.append(" WHERE");
        }
        if (filter.getPublishingId() != null) {
            bldr.append(" publishing_id = :publishingId");
            needsAnd = true;
        }
        if (filter.getTopicId() != null) {
            if (needsAnd) {
                bldr.append(" AND");
            }
            bldr.append(" topic_id = :topicId");
        }

        return bldr.toString();
    }

    /**
     * Получение статей, которые проходят указанный фильтр.
     *
     * @param filter фильтр, который уточняет статьи, которые нужно выбрать из базы данных
     * @return список статей
     */
    public List<Article> getArticlesByFilter(final ArticleFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", filter.getAuthorId());
        params.put("publishingId", filter.getPublishingId());
        params.put("topicId", filter.getTopicId());
        return jdbcTemplate.query(generateQueryForFilter(filter),
            params, articleRowMapper);
    }

    /**
     * Метод для генерации запроса на выборку статей из базы данных.
     *
     * @param filter фильтр, который уточняет статьи, которые нужно выбрать из базы данных
     * @return запрос на получение статей
     */
    private String generateQueryForFilter(final ArticleFilter filter) {
        StringBuilder bldr = new StringBuilder("SELECT * FROM article");
        boolean needsAnd = false;
        if (filter.getTopicId() != null
            || filter.getPublishingId() != null
            || filter.getAuthorId() != null) {
            bldr.append(" WHERE");
        }

        if (filter.getAuthorId() != null) {
            bldr.append(" author_id = :authorId");
            needsAnd = true;
        }

        if (filter.getPublishingId() != null) {
            if (needsAnd) {
                bldr.append(" AND");
            }
            bldr.append(" publishing_id = :publishingId");
            needsAnd = true;
        }

        if (filter.getTopicId() != null) {
            if (needsAnd) {
                bldr.append(" AND");
            }
            bldr.append(" topic_id = :topicId");
        }

        return bldr.toString();
    }
}
