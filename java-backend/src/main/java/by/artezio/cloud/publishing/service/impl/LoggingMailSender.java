package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.domain.MailingResultType;
import by.artezio.cloud.publishing.service.MailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Реализация интерфейса {@link MailSender}.
 * Происходит эмуляция действий реального рассылателя писем.
 * Рельтат каждого действия пишется в консоль.
 *
 * @author vgamezo
 */
@Service
public class LoggingMailSender implements MailSender {

    @Override
    public List<String> sendMail(final List<String> addressees, final String subject, final String message) {
        String addresses = String.join(", ", addressees);
        System.out.printf("Sending email to [%s]. Subject: %s. Message: %s\n", addresses, subject, message);
        return Collections.singletonList(MailingResultType.SUCCESS.getMessage());
    }
}
