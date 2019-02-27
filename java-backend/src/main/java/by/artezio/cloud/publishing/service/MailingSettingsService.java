package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailingSettingsService {

    @Autowired
    private PublishingDao publishingDao;

    @Autowired
    private MailingDao mailingDao;

    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    public List<String> getEmailListByPublishingId(int id) {
        return mailingDao.getEmailListByPublishingId(id);
    }
}
