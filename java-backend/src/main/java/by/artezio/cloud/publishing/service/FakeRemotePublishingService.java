package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
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

}
