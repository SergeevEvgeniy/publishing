package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Advertising;
import java.util.List;

/**
 * Сервис, содержащий бизнес-логику для работы с рекламой.
 * @author Igor Kuzmin
 */
public interface AdvertisingService {

    /**
     * Метод для получения списка реклам по id номера.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return список {@link Advertising}
     * */
    List<Advertising> getAdvertisingListByIssueId(final int issueId);

}
