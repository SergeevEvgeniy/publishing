package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.dto.UpdateArticleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UpdateArticleController {

    @GetMapping(path = "/update")
    public final ModelAndView updateArticle() {
        ModelAndView mav = new ModelAndView();
        UpdateArticleDTO dto = new UpdateArticleDTO();

        List<String> publishings = new ArrayList<>();
        publishings.add("Рога и копыта");
        publishings.add("Волосы и пальцы");
        publishings.add("Глаза и ногти");
        publishings.add("Внутренности и грязь");
        dto.setPublishings(publishings);

        List<String> topics = new ArrayList<>();
        topics.add("Horror");
        topics.add("Fun");
        topics.add("Comedy");
        topics.add("Pulp Fiction");
        dto.setTopics(topics);

        dto.setTitle("How to make interesting content");

        dto.setContent("Very interesting content!");

        List<String> coauthors = new ArrayList<>();
        coauthors.add("Sanya");
        coauthors.add("Senya");
        coauthors.add("Jora");
        coauthors.add("Valik");
        dto.setAvailableCoauthors(coauthors);

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(1, 1, "content", false));
        reviews.add(new Review(1, 2, "content", true));
        reviews.add(new Review(1, 3, "content", false));
        reviews.add(new Review(1, 4, "content", true));
        dto.setReviews(reviews);

        mav.addObject("model", dto);
        mav.setViewName("update_article");
        return mav;
    }

}
