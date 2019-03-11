package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.PublishingService;

import java.util.List;

/**
 * КАКОЙ-ТАААА СЕРВИС.
 *
 * @author rezerv
 */
public class FakeRemotePublishingService implements PublishingService {

    private PublishingDao publishingDao;

    /**
     *
     * @param publishingDao publishingDao
     */
    public FakeRemotePublishingService(final PublishingDao publishingDao) {
        this.publishingDao = publishingDao;
    }

    @Override
    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    @Override
    public Publishing getPublishingById(final Integer publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }

    @Override
    public List<Topic> getTopicsByPublishingId(final Integer publishingId) {
        return publishingDao.getPublishingTopics(publishingId);
    }

}
