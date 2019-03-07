package by.artezio.cloud.publishing.service.publishing;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class FakeRemotePublishingService implements PublishingService {

    private PublishingDao publishingDao;

    public FakeRemotePublishingService(PublishingDao publishingDao) {
        this.publishingDao = publishingDao;
    }

    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    @Override
    public Publishing getPublishingById(int publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }

    @Override
    public List<Topic> getTopicsByPublishingId(Integer publishingId) {
        return publishingDao.getPublishingTopics(publishingId);
    }

}
