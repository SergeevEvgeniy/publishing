package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.service.MailSender;

import java.util.List;

/**
 * Реализация интерфейса {@link MailSender}.
 * Происходит непосредственная рассылка писем, используя почтовые протоколы.
 *
 * @author vgamezo
 */
public class SendingMailSender implements MailSender {

    @Override
    public void sendMail(final List<String> addressees, final String subject, final String message) {
        //TODO must be implementation.
    }
}
