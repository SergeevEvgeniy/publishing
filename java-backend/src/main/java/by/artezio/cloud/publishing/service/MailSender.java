package by.artezio.cloud.publishing.service;

import java.util.List;

/**
 * Общий интерфейс для сервисов, реализующих логику рассылки писам.
 * @author vgamezo
 */
public interface MailSender {

    /**
     * Метод посылает письма каждому email-у списка addressees
     *          с темой subject и текстом внутри message.
     * @param addressees список адресатов.
     * @param subject тема письма.
     * @param message содержание письма.
     */
    void sendMail(final List<String> addressees, final String subject, final String message);
}
