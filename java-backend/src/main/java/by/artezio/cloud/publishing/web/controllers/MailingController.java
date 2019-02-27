package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.MailingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gamezovladislav
 * @date 22.02.19
 */
@Controller
@RequestMapping("/mailing")
public class MailingController {

    @Autowired
    private MailingDao mailingDao;

    @GetMapping
    public String emptyEmailList() {
         return "mailing";
    }
}
