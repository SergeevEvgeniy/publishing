package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.Subscribers;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.web.facade.MailingFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Контроллер для страницы рассылки.
 * @author vgamezo
 */
@Controller
@RequestMapping("/mailing")
public class MailingController {

    private MailingFacade mailingFacade;
    private PublishingService publishingService;
    private MailingService mailingService;

    /**
     * Конструктор с параметрами.
     * @param mailingFacade фасад для рассылок
     * @param publishingService сервис изданий
     * @param mailingService сервис рассылок
     */
    public MailingController(final MailingFacade mailingFacade,
                             final PublishingService publishingService,
                             final MailingService mailingService) {
        this.mailingFacade = mailingFacade;
        this.publishingService = publishingService;
        this.mailingService = mailingService;
    }

    /**
     * Возвращает вьюху "mailing".
     * @param model модель, с помощью которой можно взаимодейстовать с отображаемым контентом.
     * @return Вьюха "mailing"
     */
    @GetMapping
    public String initMailingInfo(final Model model) {
        model.addAttribute("mailingInfoList", mailingFacade.getAllMailingInfo());
        return "mailing";
    }

    /**
     * Обработчик перехода на страницу настроек рассылок из вне, а так же обрабатывает внутренние перехода по этому странице
     *      путем выбора издания, подписчиков которых планируется изменять.
     *
     * @param publishingId id издания
     * @param model модель, с помощью которой можно взаимодействовать с отображаемым контентом
     * @return вьюха настроек
     */
    @GetMapping("/settings")
    public String getEmailList(@RequestParam(required = false, name = "id") final Integer publishingId, final Model model) {
        model.addAttribute("publishingList", publishingService.getPublishingList());
        if (publishingId != null) {
            model.addAttribute("id", publishingId);
            model.addAttribute("emailList", mailingFacade.getEmailList(publishingId));
        }
        return "mailingSettings";
    }

    /**
     * Изменяет список подписчиков, подписанных на издание с <code>id == subscribers.getPublishingTitle()</code>.
     *
     * @param subscribers Объект подписчиков рассылки издания с <code>id == subscribers.getPublishingTitle()</code>,
     *                    в котором хранятся email-адреса, на которые произойдет рассылка в следующий раз.
     * @return страница настроек.
     */
    @PostMapping("/settings")
    public String addNewSubscribers(@ModelAttribute final Subscribers subscribers) {
        System.out.println(subscribers);
        boolean isSuccessUpdated = mailingFacade.updateEmailList(subscribers.getPublishingId(), subscribers.getEmails());

        System.out.print("Email-subscribers was success updated? --> ");
        System.out.println(isSuccessUpdated ? "YES" : "NO");

        return "redirect:/mailing/settings";
    }

    /**
     * Временное решение для инициализации рассылок.
     * Будет в будущем УДАЛЕНО.
     * Кнопочки на странице Рассылок также не будет.
     *
     * Инициирует рассылку.
     * @return вьюха рассылок.
     */
    @GetMapping("/do_it")
    public String doIt() {
        this.mailingService.sendMail(LocalDateTime.now());
//        mailSender.sendMail(
//            Arrays.asList("team00_10@mail.ru", "team00_11@mail.ru"),
//            "Рассылка на русском",
//            "Это тело письма. Оно на руском языке. Пробуем кодировки на вкус..."
//        );
        return "redirect:/mailing";
    }
}
