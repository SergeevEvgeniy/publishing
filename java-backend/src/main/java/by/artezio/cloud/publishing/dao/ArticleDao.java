package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

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
    private ArticleService service;

    @Autowired
    private TopicDao topicDao;

    private RowMapper<Article> rowMapper = (rs, i) -> {
        Article article = new Article();
        Publishing p = service.getPublishingById(rs.getInt("publishing_id"));
        Topic t = topicDao.getTopicById(rs.getInt("topic_id"));
        Employee e = service.getAuthorById(rs.getInt("author_id"));

        article.setId(rs.getInt("id"));
        article.setPublishing(p);
        article.setTopic(t);
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setAuthor(e);

        return article;
    };

    private RowMapper<Topic> topicRowMapper = (rs, rowNum) -> {
        Topic t = new Topic();

        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));

        return t;
    };

//    /**
//     * Конструктор с параметрами.
//     *
//     * @param jdbcTemplate объект класса NamedParameterJdbcTemplate
//     * @param service      объект класса ArticleService
//     * @param topicDao     объект класса TopicDao
//     */
//    @Autowired
//    public ArticleDao(final NamedParameterJdbcTemplate jdbcTemplate, final ArticleService service, final TopicDao topicDao) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.service = service;
//        this.topicDao = topicDao;
//    }

    /**
     * Получение списка статей по идентификатору автора.
     *
     * @param authorId int
     * @return список объектов статей {@link Article}, где указанный пользователь является автором.
     */
    public List<Article> getArticleListByJournalistId(final int authorId) {
        return jdbcTemplate.query("SELECT * FROM article\n"
                + "WHERE author_id = :authorId",
            Collections.singletonMap("authorId", authorId), rowMapper);
    }

    /**
     * Получение списка всех статей.
     *
     * @return список объектов статей {@link Article}.
     */
    public List<Article> getAllArticles() {
        return jdbcTemplate.query("SELECT * FROM article", rowMapper);
    }

    /**
     * Получение списка соавторов по идентификатору статьи.
     *
     * @param id идентификатор статьи
     * @return список объектов класса {@link ArticleCoauthor}
     */
    public List<ArticleCoauthor> getArticleCoauthorsByArticleId(final int id) {
        return null;
    }
}