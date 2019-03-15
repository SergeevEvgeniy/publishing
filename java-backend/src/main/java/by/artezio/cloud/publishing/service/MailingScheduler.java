package by.artezio.cloud.publishing.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Планировщик, реализующий инмициализацию действий по расписанию.
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
     * Реализует рассылку писем по расписанию.
     * Вызывается Spring Framework в моменты времени, удовлетворяющие условию cron
     * Документация по Cron(https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm)
     *
     * Фактически, он просто вызывает метод MailingService.sendMail в определенное время.
     */
    @Scheduled(cron = "0 */15 * * * *")
    public void sendMail() {
        mailingService.sendMail(LocalDateTime.now());
    }
}
