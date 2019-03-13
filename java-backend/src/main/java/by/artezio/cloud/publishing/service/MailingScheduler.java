package by.artezio.cloud.publishing.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Класс, реализующий инмициализацию действий по расписанию.
 * @author vgamezo
 */
@EnableScheduling
@Component
public class MailingScheduler {

    private MailingService mailingService;

    /**
     * Конструктор с параметром.
     * @param mailingService сервис для взаимодействия по расписанию.
     */
    public MailingScheduler(final MailingService mailingService) {
        this.mailingService = mailingService;
    }

    /**
     * Метод, реализующий рассылку писем по расписанию.
     * Фактически, он просто вызывает метод MailingService.sendMail в определенное время.
     */
    @Scheduled(cron = "* */5 * * * *")
    public void sendMail() {
        mailingService.sendMail();
    }
}
