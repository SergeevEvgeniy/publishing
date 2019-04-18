package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.ArticleForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации {@link ArticleForm} в {@link Article}.
 *
 * @author Denis Shubin
 */
@Component
public class ArticleFormToArticleConverter implements Converter<ArticleForm, Article> {

    @Override
    public Article convert(final ArticleForm articleForm) {
        Article article = new Article();
        article.setAuthorId(articleForm.getAuthorId());
        article.setContent(articleForm.getContent());
        article.setPublishingId(articleForm.getPublishingId());
        article.setTitle(articleForm.getTitle());
        article.setTopicId(articleForm.getTopicId());
        return article;
    }
}
