package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.service.MailingSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mailing/settings")
public class MailingSettingsController {

    @Autowired
    private PublishingDao publishingDao;

    @Autowired
    private MailingSettingsService mailingSettingsService;

    @GetMapping
    public String initListPublishing(Model model) {
        model.addAttribute("publishingList", mailingSettingsService.getPublishingList());
        return "mailingSettings";
    }

    @GetMapping("/{id}")
    public String getEmailList(@PathVariable int id, Model model) {
        model.addAttribute("emailList", mailingSettingsService.getEmailListByPublishingId(id));
        model.addAttribute("publishingList", mailingSettingsService.getPublishingList());
        return "mailingSettings";
    }
}
