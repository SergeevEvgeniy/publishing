package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.ArticleCoauthorsDao;
import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.converter.ArticleFormToArticleConverter;
import by.artezio.cloud.publishing.service.converter.ArticleToArticleDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис, содержащий бизнес-логику по обработке статей.
 */
@Service
public class LocalArticleService implements by.artezio.cloud.publishing.service.ArticleService {

    private ArticleDao articleDao;
    private ArticleCoauthorsDao coauthorsDao;
    private ArticleFormToArticleConverter articleConverter;
    private ArticleToArticleDtoConverter articleToArticleDtoConverter;

    /**
     * @param articleDao класс для взаимодействия с таблицей article
     */
    @Autowired
    public void setArticleDao(final ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    /**
     * @param coauthorsDao класс для взаимодействия с таблицей article_coauthors
     */
    @Autowired
    public void setCoauthorsDao(final ArticleCoauthorsDao coauthorsDao) {
        this.coauthorsDao = coauthorsDao;
    }

    /**
     * @param articleConverter {@link ArticleFormToArticleConverter}
     */
    @Autowired
    public void setArticleConverter(final ArticleFormToArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
    }

    /**
     * @param articleToArticleDtoConverter {@link ArticleToArticleDtoConverter}
     */
    @Autowired
    public void setArticleToArticleDtoConverter(final ArticleToArticleDtoConverter articleToArticleDtoConverter) {
        this.articleToArticleDtoConverter = articleToArticleDtoConverter;
    }

    @Override
    public List<ArticleInfo> getArticleInfoList(final User user) {

        List<Article> articles;
        if (user.getType().equals('J')) {
            articles = articleDao.getArticleListByJournalistId(user.getId());
        } else {
            articles = articleDao.getAllArticles();
        }

        List<ArticleInfo> articleLists = new ArrayList<>(articles.size());
        for (Article a : articles) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setArticleId(a.getId());
            articleInfo.setTitle(a.getTitle());
            articleInfo.setPublished(articleDao.isPublished(a.getId()));
            articleLists.add(articleInfo);
        }

        return articleLists;
    }

    @Override
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        ArticleForm form = new ArticleForm();

        Article article = articleDao.getArticleByArticleId(articleId);

        List<ArticleCoauthor> articleCoauthors = articleDao.getArticleCoauthorsByArticleId(articleId);
        List<Integer> coauthors = new ArrayList<>(articleCoauthors.size());
        for (ArticleCoauthor ac : articleCoauthors) {
            coauthors.add(ac.getEmployeeId());
        }

        form.setPublishingId(article.getPublishingId());
        form.setTitle(article.getTitle());
        form.setTopicId(article.getTopicId());
        form.setContent(article.getContent());
        form.setCoauthors(coauthors);
        return form;
    }

    @Override
    public ArticleDto getArticleDtoById(final int articleId) {
        Article article = articleDao.getArticleByArticleId(articleId);
        return articleToArticleDtoConverter.convert(article);
    }

    @Override
    public void deleteArticle(final Integer articleId) {
        articleDao.deleteArticleById(articleId);
    }

    @Override
    public List<ArticleDto> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
        List<Article> articles = articleDao.getArticleByTopicAndPublishingId(topicId, publishingId);
        List<ArticleDto> articleDtos = new ArrayList<>(articles.size());
        for (Article a : articles) {
            articleDtos.add(articleToArticleDtoConverter.convert(a));
        }
        return articleDtos;
    }

    @Override
    public List<Article> getArticlesBytopicAndPublishingAndAuthorId(final int topicId,
                                                                    final int publishingId,
                                                                    final int authorId) {
        return articleDao.getArticleByTopicAndPublishingAndAuthorId(topicId, publishingId, authorId);
    }

    @Override
    public List<ArticleCoauthor> getCoauthorsByArticleId(final int articleId) {
        return articleDao.getArticleCoauthorsByArticleId(articleId);
    }

    @Override
    public void save(final ArticleForm articleForm) {
        Article article = articleConverter.convert(articleForm);
        List<Integer> coauthors = articleForm.getCoauthors();
        if (article != null) {
            Integer articleId = articleDao.save(article);

            if (coauthors != null) {
                for (Integer coauthorId : coauthors) {
                    coauthorsDao.save(articleId, coauthorId);
                }
            }
        }
    }

    @Override
    public void update(final ArticleForm articleForm, final Integer articleId) {
        Article article = articleConverter.convert(articleForm);
        article.setId(articleId);
        articleDao.update(article, articleForm.getCoauthors());
    }

    @Override
    public List<ArticleDto> getUnpublishedArticles(final int publishingId,
                                                   final int topicId,
                                                   final int authorId) {
        List<Article> unpublishedArticles = articleDao.getUnpublishedArticles(publishingId, topicId, authorId);
        List<ArticleDto> unpublishedList = new ArrayList<>(unpublishedArticles.size());
        for (Article a : unpublishedArticles) {
            unpublishedList.add(articleToArticleDtoConverter.convert(a));
        }
        return unpublishedList;
    }

    @Override
    public int getArticleCountByAuthorId(final int authorId) {
        return articleDao.getArticleCountByAuthorId(authorId);
    }

    @Override
    public Map<String, Integer> getArticleCountByPublishingMap(final int authorId) {
        return articleDao.getCountByPublishingMap(authorId);
    }

    @Override
    public Map<String, Integer> getArticleCountByTopicMap(final int authorId) {
        return articleDao.getCountByTopicMap(authorId);
    }

    @Override
    public List<ArticleDto> getArticleDtoList(final ArticleFilter filter) {

        List<Article> list = articleDao.getArticlesByFilter(filter);
        List<ArticleDto> articleDtoList = new ArrayList<>(list.size());

        for (Article a : list) {
            articleDtoList.add(articleToArticleDtoConverter.convert(a));
        }

        return articleDtoList;
    }

    @Override
    public ArticleDto getArticleDto(final int articleId) {
        return articleToArticleDtoConverter.convert(
            articleDao.getArticleByArticleId(articleId));
    }

    @Override
    public boolean isArticleExists(final int articleId) {
        return articleDao.isArticleExists(articleId);
    }

    @Override
    public List<Integer> getAuthorsIdList(final AuthorFilter filter) {
        return articleDao.getAuthorsIdList(filter);
    }
}
