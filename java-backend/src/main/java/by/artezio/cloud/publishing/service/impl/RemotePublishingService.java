package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для получения сведений об изданиях, издаваемых в издательстве.
 * Он непосредственно посылает HTTP-запросы на сервер и получает данные в ответах.
 */
public class RemotePublishingService implements PublishingService {

    /**
     * URL в виде строки, по которому располагается сервер, к которому происходит обращение.
     */
    private static final String URL = "10.99.33.138:3000/api/publishings";

    @Override
    public List<PublishingDTO> getPublishingList() {
        RestTemplate restTemplate = new RestTemplate();
        PublishingDTO[] publishings = restTemplate.getForObject(URL, PublishingDTO[].class);

        return Arrays.asList(publishings);
    }

    @Override
    public PublishingDTO getPublishingById(final Integer publishingId) {
        RestTemplate restTemplate = new RestTemplate();
        PublishingDTO publishing = restTemplate.getForObject(URL + "/" + publishingId, PublishingDTO.class);

        return publishing;
    }

    @Override
    public List<Topic> getTopicsByPublishingId(final Integer id) {
        // todo add implementation
        return new ArrayList<>();
    }

    @Override
    public String getPublishingTitle(final int publishingId) {
        PublishingDTO publishing = this.getPublishingById(publishingId);
        return (publishing == null ? null : publishing.getTitle());
    }

    @Override
    public List<PublishingDTO> getPublishingListByEmployeeId(final int employeeId) {
        // todo add implementation
        return new ArrayList<>();
    }
}
