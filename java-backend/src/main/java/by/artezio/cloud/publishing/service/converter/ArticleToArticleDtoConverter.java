package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.dto.ArticleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации {@link Article} в {@link ArticleDto}.
 *
 * @author Denis Shubin
 */
@Component
public class ArticleToArticleDtoConverter implements Converter<Article, ArticleDto> {

    @Override
    public ArticleDto convert(final Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setAuthorId(article.getAuthorId());
        articleDto.setPublishingId(article.getPublishingId());
        articleDto.setTopicId(article.getTopicId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }
}
