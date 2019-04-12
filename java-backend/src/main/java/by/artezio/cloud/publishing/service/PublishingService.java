package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.PublishingDTO;

import java.util.List;

/**
 * Сервис для получения сведений об изданиях, издаваемых в издательстве.
 */
public interface PublishingService {

    /**
     * Получение списка всех журналов/газет.
     *
     * @return Список всех публикаций, имеющихся в издательстве.
     */
    List<PublishingDTO> getPublishingList();

    /**
     * Получение журнала/газеты по его идентификатору.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return Объект издания, у которого <code>id == publishingId</code>.
     *         Если такого объекта не существует, возвращается <code>null</code>.
     */
    PublishingDTO getPublishingById(Integer publishingId);

    /**
     * Получение списка рубрик по идентификатору журнала/газеты.
     *
     * @param id идентификатор журнала/газеты
     * @return Список рубрик, которые имеет издание
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
     * Возвращает список журналов/газет, к которым сотрудник с <code>id == employeeId</code> имеет доступ.
     *
     * @param employeeId id сотрудника.
     * @return список изданий, в которых задействован данный сотрудник
     * */
    List<PublishingDTO> getPublishingListByEmployeeId(final int employeeId);

    /**
     * Метод получения журналистов журнала/газуты.
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     * @return - список всех {@link Employee}
     * */
    List<Employee> getPublishingJournalist(final int publishingId);
}
