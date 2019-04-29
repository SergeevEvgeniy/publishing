package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Topic;

import java.util.List;

/**
 * Сервис, содержащий бизнес-логику для работы с рубриками.
 *
 * @author Denis Shubin
 */
public interface TopicService {

    /**
     * @param topicId id рубрики
     * @return {@link Topic}
     */
    Topic getTopicById(final Integer topicId);

    /**
     * @param publishingId id журнала
     * @return все рубрики указанного журнала
     */
    List<Topic> getTopicsByPublishingId(final int publishingId);

    /**
     * @param topicId id рубрики
     * @return название рубрики
     */
    String getTopicName(Integer topicId);
}
