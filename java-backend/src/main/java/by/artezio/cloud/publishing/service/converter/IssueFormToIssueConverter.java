package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс-конвертер для преобразования объекта dto {@link IssueForm}
 * в объект {@link Issue}.
 * @author Igor Kuzmin.
 * */
@Component
public class IssueFormToIssueConverter implements Converter<IssueForm, Issue> {

    @Override
    public Issue convert(final IssueForm issueForm) {
        Issue issue = new Issue();
        issue.setPublished(issueForm.isPublished());
        issue.setDate(issueForm.getLocalDate());
        issue.setNumber(issueForm.getNumber());
        issue.setPublishingId(issueForm.getPublishingId());
        return issue;
    }

}
