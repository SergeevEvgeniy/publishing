package by.artezio.cloud.publishing.dto;

import java.util.Map;

/**
 * @author Denis Shubin
 */
public class ArticleStatistics {

    private int authorId;
    private int articleCount;
    private Map<String, Integer> articleCountByPublishing;
    private Map<String, Integer> articleCountByTopics;

    /**
     * @return id автора
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId id автора
     */
    public void setAuthorId(final int authorId) {
        this.authorId = authorId;
    }

    /**
     * @return количество статей автора
     */
    public int getArticleCount() {
        return articleCount;
    }

    /**
     * @param articleCount количество статей автора
     */
    public void setArticleCount(final int articleCount) {
        this.articleCount = articleCount;
    }

    /**
     * @return карта, хранящая пары 'Название журнала - количество статей'
     */
    public Map<String, Integer> getArticleCountByPublishing() {
        return articleCountByPublishing;
    }

    /**
     * @param articleCountByPublishing карта, хранящая пары 'Название журнала - количество статей'
     */
    public void setArticleCountByPublishing(final Map<String, Integer> articleCountByPublishing) {
        this.articleCountByPublishing = articleCountByPublishing;
    }

    /**
     * @return карта, хранящая пары 'Название рубрики - количество статей'
     */
    public Map<String, Integer> getArticleCountByTopics() {
        return articleCountByTopics;
    }

    /**
     * @param articleCountByTopics карта, хранящая пары 'Название рубрики - количество статей'
     */
    public void setArticleCountByTopics(final Map<String, Integer> articleCountByTopics) {
        this.articleCountByTopics = articleCountByTopics;
    }
}
