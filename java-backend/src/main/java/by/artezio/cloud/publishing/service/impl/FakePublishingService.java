package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Фейковый сервис, содержащий бизнес-логику по обработке журналов/газет.
 */
@Service
public class FakePublishingService implements PublishingService {

    @Autowired
    private PublishingDao publishingDao;

    @Override
    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    @Override
    public List<Topic> getTopicsByPublishingId(final Integer id) {
        return publishingDao.getTopicsByPublishingId(id);
    }

    @Override
    public Publishing getPublishingById(final Integer publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }
}
