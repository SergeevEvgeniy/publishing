package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;

import java.util.List;

public interface PublishingService {

    List<Publishing> getPublishingList();

    List<Topic> getPublishingTopics(int publishingId);

}

