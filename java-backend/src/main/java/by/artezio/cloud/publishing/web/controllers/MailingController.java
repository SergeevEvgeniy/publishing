package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.Subscribers;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для страницы рассылки.
 * @author vgamezo
 */
@Controller
@RequestMapping("/mailing")
public class MailingController {

    private MailingService mailingService;
    private PublishingService publishingService;

    /**
     * Конструктор с параметрами.
     * @param mailingService mailingService
     * @param publishingService publishingService
     */
    public MailingController(final MailingService mailingService, final PublishingService publishingService) {
        this.mailingService = mailingService;
        this.publishingService = publishingService;
    }

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
        model.addAttribute("publishingList", publishingService.getPublishingList());
        if (id != null) {
            model.addAttribute("id", id);
            model.addAttribute("emailList", mailingService.getEmailListByPublishingId(id));
        }
        return "mailingSettings";
    }

    /**
     * @param subscribers Объект подписчиков рассылки издания с <code>id == subscribers.getPublishingId()</code>,
     *                    в котором хранятся email-адреса, на которые произойдет рассылка в следующий раз.
     * @return страница настроек.
     */
    @PostMapping("/settings")
    public String addNewSubscribers(@ModelAttribute final Subscribers subscribers) {
        System.out.println(subscribers);
        boolean isSuccessUpdated = mailingService
            .updateSubscribersListByPublishingId(subscribers.getPublishingId(), subscribers.getEmails());
        System.out.println("Email-subscribers was success updated?");
        System.out.println(isSuccessUpdated ? "YES" : "NO");
        return "redirect:../mailing/settings";
    }
}
