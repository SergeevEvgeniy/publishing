package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeRemotePublishingService implements PublishingService {

    private PublishingDao publishingDao;

    public FakeRemotePublishingService(PublishingDao publishingDao) {
        this.publishingDao = publishingDao;
    }

    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    public List<Topic> getPublishingTopics(int publishingId) {
        return publishingDao.getPublishingTopics(publishingId);
    }

}
