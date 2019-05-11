package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования сущности {@link Issue}
 * в объект DTO {@link IssueForm}.
 *
 * @author Igor Kuzmin.
 * */
@Component
public class IssueToIssueFormConverter implements Converter<Issue, IssueForm> {

    @Override
    public IssueForm convert(final Issue issue) {
        IssueForm issueForm = new IssueForm();
        issueForm.setPublishingId(issue.getPublishingId());
        issueForm.setLocalDate(issue.getDate());
        issueForm.setNumber(issue.getNumber());
        issueForm.setPublished(issue.isPublished());
        return issueForm;
    }
}
