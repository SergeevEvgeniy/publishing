package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Класс-конвертер для преобразования объекта {@link Issue}
 * в объект dto {@link IssueInfo}.
 * @author Igor Kuzmin.
 * */
@Component
public class IssueToIssueInfoConverter implements Converter<Issue, IssueInfo> {

    @Override
    public IssueInfo convert(final Issue issue) {
        IssueInfo issueInfo = new IssueInfo();
        issueInfo.setIssueId(issue.getId());
        issueInfo.setNumber(issue.getNumber());
        issueInfo.setPublished(issue.isPublished());
        issueInfo.setLocalDate(issue.getDate());
        return issueInfo;
    }

}
