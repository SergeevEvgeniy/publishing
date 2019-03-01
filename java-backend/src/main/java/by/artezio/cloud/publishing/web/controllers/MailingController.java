package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private MailingService mailingService;

    /**
     * Возвращает вьюху "mailing".
     * @return Вьюха "mailing"
     */
    @GetMapping
    public String initMailingInfo(final Model model) {
        System.out.println(mailingService.getAllMailingInfo().size());
        model.addAttribute("mailingInfoList", mailingService.getAllMailingInfo());
        return "mailing";
    }
}
