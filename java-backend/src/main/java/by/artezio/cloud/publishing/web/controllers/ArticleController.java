package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.ArticleView;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.web.facade.ArticleWebFacade;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер, обрабатывающий запросы, связанные со статьями.
 *
 * @author Denis Shubin
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleWebFacade articleFacade;
    private final SecurityService securityService;


    /**
     * @param articleFacade   {@link ArticleWebFacade}
     * @param securityService {@link SecurityService}
     */
    public ArticleController(final ArticleWebFacade articleFacade,
                             final SecurityService securityService) {
        this.articleFacade = articleFacade;
        this.securityService = securityService;
    }

    /**
     * Возвращает пользователю страницу со списком статей.
     *
     * @param model Model
     * @return имя представления со списком статей
     */
    @GetMapping
    public final String articleList(final Model model) {
        boolean isJournalist = articleFacade.isJournalist();
        boolean isChiefEditor = articleFacade.isChiefEditor();
        List<ArticleInfo> data = articleFacade.getArticleInfoList();
        model.addAttribute("data", data);
        model.addAttribute("isJournalist", isJournalist);
        model.addAttribute("isChiefEditor", isChiefEditor);
        return "articleList";
    }

    /**
     * Возвращает пользователю страницу для создания статьи.
     *
     * @param model Model
     * @return String название jsp страницы
     */
    @GetMapping(path = "/new")
    public final String createArticle(final Model model) {
        articleFacade.isJournalist();
        ArticleForm articleForm = new ArticleForm();
        model.addAttribute("articleForm", articleForm);
        List<PublishingDTO> publishingDtoList = articleFacade.getPublishingDtoList();

        model.addAttribute("publishingDtoList", publishingDtoList);
        model.addAttribute("isEditMode", false);
        model.addAttribute("availableCoauthors", null);
        model.addAttribute("topicShortInfos", null);
        model.addAttribute("currentCoauthors", null);
        model.addAttribute("reviewShortInfos", null);

        return "updateArticle";
    }

    /**
     * Сохранение статьи и открытие списка статей.
     *
     * @param articleForm   {@link ArticleForm}
     * @param bindingResult {@link BindingResult}
     * @return страница со списком статей
     */
    @PostMapping(path = "/new")
    public final String saveArticle(@ModelAttribute("articleForm") @Valid final ArticleForm articleForm,
                                    final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Ошибка привязки ArticleForm в методе ArticleController.saveArticle()");
        } else {
            articleFacade.save(articleForm);
        }
        return "redirect:/article";

    }

    /**
     * Получение формы для редактирования статьи.
     *
     * @param articleId идентификатор статьи (берётся из URI)
     * @param model     модель, в которую будет положена форма.
     * @return имя представления
     */
    @GetMapping(path = "/update/{articleId}")
    public final String updateArticle(@PathVariable("articleId") final int articleId, final Model model) {
        articleFacade.isAuthorized();
        ArticleForm form = articleFacade.getUpdateArticleFormByArticleId(articleId);
        List<PublishingDTO> publishingDtoList = articleFacade.getPublishingDtoList();
        List<TopicShortInfo> topicShortInfos = articleFacade.getTopicsShortInfoList(form.getPublishingId());
        List<EmployeeShortInfo> availableCoauthors = articleFacade.getAvailableCoauthors(form.getPublishingId());
        List<EmployeeShortInfo> currentCoauthors = articleFacade.getCurrentCoauthors(articleId);
        List<ReviewShortInfo> reviewShortInfos = articleFacade.getReviewShortInfos(articleId);


        for (int i = 0; i < availableCoauthors.size(); i++) {
            for (EmployeeShortInfo currentCoauthor : currentCoauthors) {
                if (availableCoauthors.get(i).getId() == currentCoauthor.getId()) {
                    availableCoauthors.remove(i);
                }
            }
        }
        User current = securityService.getCurrentUser();
        availableCoauthors.remove(articleFacade.getShortEmployeeById(current.getId()));

        model.addAttribute("articleForm", form);
        model.addAttribute("isEditMode", true);
        model.addAttribute("publishingDtoList", publishingDtoList);
        model.addAttribute("topicShortInfos", topicShortInfos);
        model.addAttribute("availableCoauthors", availableCoauthors);
        model.addAttribute("currentCoauthors", currentCoauthors);
        model.addAttribute("reviewShortInfos", reviewShortInfos);
        return "updateArticle";
    }

    /**
     * @param articleId     id статьи
     * @param articleForm   {@link ArticleForm}
     * @param bindingResult {@link BindingResult}
     * @return страница со списком статей
     */
    @PostMapping(path = "/update/{articleId}")
    public final String updateArticle(@PathVariable("articleId") final Integer articleId,
                                      @Valid final ArticleForm articleForm,
                                      final BindingResult bindingResult) {
        articleFacade.update(articleForm, articleId);
        return "redirect:/article";
    }

    /**
     * Вернёт страницу со статьёй в режиме просмотра.
     *
     * @param articleId id статьи
     * @param model     {@link Model}
     * @return имя представления
     */
    @GetMapping(path = "/get/{articleId}")
    public final String getArticleView(@PathVariable("articleId") final int articleId,
                                       final Model model) {
        ArticleView articleView = articleFacade.getArticleViewById(articleId);
        model.addAttribute("model", articleView);
        return "articleView";
    }


    /**
     * Получение страницы для удаления статьи.
     *
     * @param articleId id статьи
     * @param model     {@link Model}
     * @return название представления для удаления статьи
     */
    @GetMapping(path = "/delete/{articleId}")
    public final String getDeleteArticlePage(@PathVariable final int articleId,
                                             final Model model) {
        if (articleFacade.isArticleExists(articleId)) {
            ArticleView view = articleFacade.getArticleViewById(articleId);
            model.addAttribute("article", view);
            model.addAttribute("articleExists", true);
        } else {
            model.addAttribute("articleExists", false);
        }
        return "deleteArticle";
    }

    /**
     * Удаление статьи по её идентификатору.
     *
     * @param articleId id статьи
     * @return название представления со списком статей
     */
    @PostMapping(path = "/delete/{articleId}")
    public final String deleteArticle(@PathVariable("articleId") final int articleId) {
        articleFacade.deleteArticleById(articleId);
        return "redirect:/article";
    }


    /**
     * @param publishingId id журнала
     * @return список рубрик для указанного журнала
     */
    @GetMapping(value = "/topicsByPublishing/{publishingId}")
    @ResponseBody
    public List<Topic> getTopicsByPublishing(@PathVariable("publishingId") final int publishingId) {
        return articleFacade.getTopicsByPublishingId(publishingId);
    }

    /**
     * @param publishingId id журнала
     * @return список сотрудников для указанного журнала
     */
    @GetMapping(value = "/employeesByPublishing/{publishingId}")
    @ResponseBody
    public List<EmployeeShortInfo> getEmployeeByPublishing(@PathVariable("publishingId") final int publishingId) {
        return articleFacade.getAvailableCoauthors(publishingId);
    }

    /**
     * @param articleId  id статьи
     * @param reviewerId id рецензента
     * @return json рецензия {@link ReviewShortInfo}
     */
    @GetMapping(value = "/review/{articleId}/{reviewerId}")
    @ResponseBody
    public ReviewShortInfo getReview(@PathVariable("articleId") final int articleId,
                                     @PathVariable("reviewerId") final int reviewerId) {
        return articleFacade.getReviewShortInfo(articleId, reviewerId);
    }
}
