package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Denis Shubin
 */
@Service
public class
LocalTopicService implements TopicService {

    private TopicDao topicDao;

    /**
     * @param topicDao {@link TopicDao}
     */
    public LocalTopicService(final TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public Topic getTopicById(final Integer topicId) {
        return topicDao.getTopicById(topicId);
    }

    @Override
    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return topicDao.getTopicsByPublishingId(publishingId);
    }
}
