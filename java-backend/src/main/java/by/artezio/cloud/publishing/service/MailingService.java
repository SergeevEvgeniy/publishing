package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.domain.MailingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vgamezo
 */
@Service
public class MailingService {

    @Autowired
    MailingDao mailingDao;


    public List<MailingInfo> getAllMailingInfo() {
        return mailingDao.getAllMailingInfo();
    }
}
