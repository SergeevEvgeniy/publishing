package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации объекта {@link Topic} в {@link TopicShortInfo}.
 *
 * @author Denis Shubin
 */
@Component
public class TopicToTopicShortInfoConverter implements Converter<Topic, TopicShortInfo> {

    @Override
    public TopicShortInfo convert(final Topic topic) {
        TopicShortInfo info = new TopicShortInfo();
        info.setName(topic.getName());
        info.setId(topic.getId());
        return info;
    }
}
