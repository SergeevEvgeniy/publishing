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

    /**
     * Возвращает название издательства по его id.
     *
     * @param publishingId Id издательства
     * @return Название издательства, если издательство с таким id существует, иначе null
     */
    String getPublishingTitle(int publishingId);

    /**
     * Метод получения списка журналов/газет по id сотрудника данных журналов/газет.
     * @param employeeId - id {@link by.artezio.cloud.publishing.domain.Employee}
     * @return список {@link Publishing} - все журналы/газуты в которых задействован сотрудник
     * */
    List<Publishing> getPublishingListByEmployeeId(final int employeeId);
}
