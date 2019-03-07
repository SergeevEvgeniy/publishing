package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;

import java.util.List;

/**
 * Сервис, содержащий бизнес-логику по обработке журналов/газет.
 */
public interface PublishingService {

    /**
     * Получение списка всех журналов/газет.
     *
     * @return {@link List}&lt;{@link Publishing}&gt;
     */
//    List<Publishing> getAllPublishings();
    List<Publishing> getPublishingList();

    /**
     * Получение журнала/газеты по его идентификатору.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return {@link Publishing}
     */
    Publishing getPublishingById(Integer publishingId);

    /**
     * Получение списка рубрик по идентификатору журнала/газеты.
     *
     * @param id идентификатор журнала/газеты
     * @return {@link List}&lt;{@link Topic}&gt;
     */
    List<Topic> getTopicsByPublishingId(Integer id);

//    List<Topic> getPublishingTopics(int publishingId);
}
