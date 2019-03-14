package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для страницы номеров.
 * @author Igor Kuzmin
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    private PublishingService publishingService;

    /**
     * Конструктор с параметрами.
     * @param publishingService {@link PublishingService}
     * */
    public IssueController(final PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    /**
     * @return ModelAndView - содержит представление данных о всех номерах,
     * а также имя главной страницы раздела номера
     * */
    @GetMapping
    public ModelAndView getIssuesPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issues");
        return modelAndView;
    }

    /**
     * @return ModelAndView - содержит представление данных используемые
     * в режиме создания номера, а также имя страницы jsp содержащая
     * форму для создания нового номера
     */
    @GetMapping("/create")
    public ModelAndView openFormInCreationMode() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("method", "POST");
        modelAndView.addObject("publishing", publishingService.getPublishingList());
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @return ModelAndView - содержит представление данных используемые
     * в режиме редактирования номера, а также имя страницы jsp содержащая
     * форму для редактирования номера
     */
    @GetMapping("/edit")
    public ModelAndView openFormInEditingMode() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * @return ModelAndView - содержит представление данных используемые
     * в режиме просмотра номера, а также имя страницы jsp содержащая
     * форму для просмотра номера
     */
    @GetMapping("/view")
    public ModelAndView openFormInViewingMode() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issueForm");
        return modelAndView;
    }

    /**
     * Метод создания и добовления новых номеров.
     * @param issueForm {@link IssueForm} содержит данные
     * полей формы
     * @return перенаправление на главную страницу раздела номеров
     */
    @PostMapping("/issue")
    public String addNewIssue(@ModelAttribute("issueForm") final IssueForm issueForm) {
        return "redirect:/issues";
    }


}
