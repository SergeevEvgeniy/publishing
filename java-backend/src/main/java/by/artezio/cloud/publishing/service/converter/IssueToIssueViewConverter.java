package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс-конвертер для преобразования объекта {@link Issue}
 * в объект dto {@link IssueView}.
 * @author Igor Kuzmin.
 * */
@Component
public class IssueToIssueViewConverter implements Converter<Issue, IssueView> {

    @Override
    public IssueView convert(final Issue issue) {
        IssueView issueView = new IssueView();
        issueView.setLocalDate(issue.getDate());
        issueView.setNumber(issue.getNumber());
        issueView.setPublishingId(issue.getPublishingId());
        return issueView;
    }

}
