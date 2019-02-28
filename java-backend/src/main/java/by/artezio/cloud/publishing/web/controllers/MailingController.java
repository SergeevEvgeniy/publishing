package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.MailingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для страницы рассылки.
 * @author vgamezo
 */
@Controller
@RequestMapping("/mailing")
public class MailingController {

    @Autowired
    private MailingDao mailingDao;

    /**
     * Возвращает вьюху "mailing".
     * @return Вьюха "mailing"
     */
    @GetMapping
    public String emptyEmailList() {
         return "mailing";
    }
}
