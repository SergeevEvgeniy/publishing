package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.domain.PublishingEmployee;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.converter.TopicToTopicShortInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый сервис, содержащий бизнес-логику по обработке журналов/газет.
 */
@Service
public class FakePublishingService implements PublishingService {

    @Autowired
    private PublishingDao publishingDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TopicToTopicShortInfoConverter topicShortInfoConverter;

    @Override
    public List<PublishingDTO> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    @Override
    public List<TopicShortInfo> getTopicsByPublishingId(final Integer id) {
        List<Topic> topics = publishingDao.getTopicsByPublishingId(id);
        List<TopicShortInfo> topicShortInfos = new ArrayList<>();
        for (Topic t : topics) {
            topicShortInfos.add(topicShortInfoConverter.convert(t));
        }
        return topicShortInfos;
    }

    @Override
    public PublishingDTO getPublishingById(final Integer publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }

    @Override
    public List<PublishingDTO> getPublishingListByEmployeeId(final int employeeId) {
        return publishingDao.getPublishingListByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getPublishingJournalistByPublishingId(final int publishingId) {
        List<Employee> publishingJournalist = new ArrayList<>();
        List<PublishingEmployee> publishingEmployees =
            publishingDao.getPublishingEmployeeList(publishingId);
        for (PublishingEmployee pe : publishingEmployees) {
            Employee employee = employeeService.getEmployeeById(pe.getEmployeeId());
            if (employee.getType().equals('J')) {
                publishingJournalist.add(employee);
            }
        }
        return publishingJournalist;
    }

    @Override
    public String getPublishingTitle(final int publishingId) {
        return this.publishingDao.getPublishingTitle(publishingId);
    }

    @Override
    public List<Integer> getPublishingIdList() {
        List<PublishingDTO> list = getPublishingList();
        List<Integer> idList = new ArrayList<>(list.size());
        for (PublishingDTO publishing : list) {
            idList.add(publishing.getId());
        }
        return idList;
    }
}
