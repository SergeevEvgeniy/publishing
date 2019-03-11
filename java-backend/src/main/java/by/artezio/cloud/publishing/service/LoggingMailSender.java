package by.artezio.cloud.publishing.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация интерфейса {@link MailSender}.
 * Непосредственная рассылка писем отсутствует. Только логирование этих действий.
 *
 * @author vgamezo
 */
@Service
public class LoggingMailSender implements MailSender {

    @Override
    public void sendMail(final List<String> addressees, final String subject, final String message) {
        String addresses = String.join(", ", addressees);
        System.out.printf("Sending email to [%s]. Subject: %s. Message: %s", addresses, subject, message);
    }
}
