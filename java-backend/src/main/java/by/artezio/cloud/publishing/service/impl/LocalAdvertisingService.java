package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.AdvertisingDao;
import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.service.AdvertisingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Локальный сервис, содержащий бизнес-логику для работы с рекламой.
 * @author Igor Kuzmin
 */
@Service
public class LocalAdvertisingService implements AdvertisingService {

    private AdvertisingDao advertisingDao;

    /**
     * @param advertisingDao - {@link AdvertisingDao}
     * */
    public LocalAdvertisingService(final AdvertisingDao advertisingDao) {
        this.advertisingDao = advertisingDao;
    }

    @Override
    public List<Advertising> getAdvertisingListByIssueId(final int issueId) {
        return advertisingDao.getAdvertisingListByIssueId(issueId);
    }

}
