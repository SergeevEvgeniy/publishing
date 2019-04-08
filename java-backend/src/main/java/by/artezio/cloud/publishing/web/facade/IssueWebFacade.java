package by.artezio.cloud.publishing.web.facade;


import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс IssueWebFacade в котором размещена основная логика работы.
 * @author Igor Kuzmin
 * */
@Component
public class IssueWebFacade {

    private IssueService issueService;

    private PublishingService publishingService;

    private SecurityService securityService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
    }

    /**
     * Метод для получения списка {@link IssueInfo} который содержит
     * информацию о номерах доступные текущему пользователю.
     * @return список {@link IssueInfo}
     * */
    public List<IssueInfo> getIssueInfoList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        if (user.isChiefEditor()) {
            return issueService.getListOfAllIssueInfo();
        }
        List<Publishing> publishingList =
            publishingService.getPublishingListByEmployeeId(user.getId());
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Publishing p : publishingList) {
            List<IssueInfo> subIssueInfoList =
                issueService.getIssueInfoListByPublishingId(p.getId());
            if (!subIssueInfoList.isEmpty()) {
                issueInfoList.addAll(subIssueInfoList);
            }
        }
        return issueInfoList;
    }

    /**
     * Метод для получения списка {@link Publishing} который содержит
     * информацию о журналах/газетах доступные текущему пользователю.
     * @return список {@link Publishing}
     * */
    public List<Publishing> getPublishingList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        if (user.isChiefEditor()) {
            return publishingService.getPublishingList();
        }
        return publishingService.getPublishingListByEmployeeId(user.getId());
    }

    /**
     * Метод для получения dto {@link IssueInfo}
     * по id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return {@link IssueInfo}
     * */
    public IssueInfo getIssueInfoByIssueId(final int issueId) {
        securityService.checkIsEditor();
        return issueService.getIssueInfoByIssueId(issueId);
    }

}
