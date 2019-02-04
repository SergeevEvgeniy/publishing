package by.artezio.cloud.publishing.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * REST-сервис, позволяющий работать со списком дел.
 */
@RestController
@RequestMapping(path = "api/todo/list")
public class TodoListRestService {

    /**
     * Метод возвращает список активных дел.
     *
     * @return Список активных дел
     */
    @GetMapping(path = "/todos")
    public final List<String> getTodos() {
        return Arrays.asList("Хлеб", "Сметана", "Колбаса");
    }
}
