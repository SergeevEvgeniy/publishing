package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param model модель, с помощью которой можно взаимодейстовать с отображаемым контентом.
     * @return Вьюха "mailing"
     */
    @GetMapping
    public String initMailingInfo(final Model model) {
        System.out.println(mailingService.getAllMailingInfo().size());
        model.addAttribute("mailingInfoList", mailingService.getAllMailingInfo());
        return "mailing";
    }

    /**
     * Обработчик ссылки с id публикации.
     *
     * @param id id публикации
     * @param model модель, с помощью которой можно взаимодействовать с отображаемым контентом
     * @return вьюха настроек
     */
    @GetMapping("/settings")
    public String getEmailList(@RequestParam(required = false) final Integer id, final Model model) {
        System.out.println("ID == " + id);
        model.addAttribute("publishingList", mailingService.getPublishingList());
        if (id != null) {
            model.addAttribute("id", id);
            model.addAttribute("emailList", mailingService.getEmailListByPublishingId(id));
        }
        return "mailingSettings";
    }
}
