package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.service.MailingSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для странпицы настройки рассылок.
 *
 * @author vgamezo
 */
@Controller
@RequestMapping("mailing/settings")
public class MailingSettingsController {

    @Autowired
    private PublishingDao publishingDao;

    @Autowired
    private MailingSettingsService mailingSettingsService;

    /**
     * Обработчик пустой ссылки.
     *
     * @param model модель, с помощью которой можно взаимодейстовать с отображаемым контентом.
     * @return вьюха настроек
     */
    @GetMapping
    public String initListPublishing(final Model model) {
        model.addAttribute("publishingList", mailingSettingsService.getPublishingList());
        return "mailingSettings";
    }

    /**
     * Обработчик ссылки с id публикации.
     *
     * @param id id публикации
     * @param model модель, с помощью которой можно взаимодействовать с отображаемым контентом
     * @return вьюха настроек
     */
    @GetMapping("/{id}")
    public String getEmailList(@PathVariable final int id, final Model model) {
        model.addAttribute("emailList", mailingSettingsService.getEmailListByPublishingId(id));
        model.addAttribute("publishingList", mailingSettingsService.getPublishingList());
        return "mailingSettings";
    }
}
