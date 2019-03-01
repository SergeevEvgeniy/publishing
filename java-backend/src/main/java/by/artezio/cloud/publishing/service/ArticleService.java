package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.ArticleListDTO;
import by.artezio.cloud.publishing.dto.UpdateArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<ArticleListDTO> getArticleListDto(HttpServletRequest request) {
        List<ArticleListDTO> articleLists = new ArrayList<>();

        String userEmail = (String) request.getAttribute("userMail");
        int id = getUserIdByEmail(userEmail);


        List<Article> articles = articleDao.getArticleListByJournalistId(id);

        for (Article a : articles) {
            ArticleListDTO aListDto = new ArticleListDTO();
            aListDto.setTitle(a.getTitle());

            String publishing = getPublishingById(a.getPublishingId());
            aListDto.setPublishing(publishing);

            String topic = getTopicById(a.getTopicId());
            aListDto.setTopic(topic);

            String authorFullName = getAuthorFullNameById(a.getAuthorId());
            aListDto.setAuthorFullName(authorFullName);

            List<String> coauthors = getCoauthorsByArticleId(a.getId());
            aListDto.setCoauthors(coauthors);
        }

        return articleLists;
    }


    private List<String> getCoauthorsByArticleId(int id) {
        // Тут будут запросы к таблицам article_coauthors, employee
        return null;
    }

    private String getAuthorFullNameById(int authorId) {
        // Тут будут запросы к сервисам Employee
        return null;
    }

    private String getTopicById(int topicId) {
        // Тут будут запросы к сервисам Topics
        return null;
    }

    private String getPublishingById(int publishingId) {
        // Тут будут запросы к сервисам Publishing
        return null;
    }

    private int getUserIdByEmail(String userEmail) {
        // Тут будут запросы к сервисам Employee
        return 0;
    }

    public UpdateArticleDTO getUpdateArticleDto(HttpServletRequest request) {

        String action = request.getParameter("action");
        UpdateArticleDTO dto = new UpdateArticleDTO();


        if ("create".equalsIgnoreCase(action)) {
            dto = createNewArticleDto();
        } else {
            dto = prepareArticleDtoForUpdate(request);
        }


        return null;
    }

    private UpdateArticleDTO prepareArticleDtoForUpdate(HttpServletRequest request) {
        return null;
    }

    private UpdateArticleDTO createNewArticleDto() {
        UpdateArticleDTO dto = new UpdateArticleDTO();
        List<Publishing> publishings = getAllPublishings();
        List<String> publishingNames = new ArrayList<>();

        for (Publishing p : publishings) {
            publishingNames.add(p.getTitle());
        }

        dto.setPublishings(publishingNames);
        return dto;
    }

    private List<Publishing> getAllPublishings() {
        // Запросы к сервисам с Publishing
        return null;
    }
}
