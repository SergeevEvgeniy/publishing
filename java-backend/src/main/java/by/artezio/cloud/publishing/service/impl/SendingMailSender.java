package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.domain.MailingResultType;
import by.artezio.cloud.publishing.service.MailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link MailSender}.
 * Происходит непосредственная рассылка писем, используя почтовые протоколы.
 *
 * @author vgamezo
 */
//@Service
public class SendingMailSender implements MailSender {

    private JavaMailSenderImpl javaMailSender;

    /**
     * Конструктор с параметром.
     *
     * @param javaMailSender javaMailSender
     */
    public SendingMailSender(final JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<String> sendMail(final List<String> addressees, final String subject, final String message) {
        List<String> results = new ArrayList<>();
        for (String address : addressees) {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            try {
                mimeMessage.setText(message, "UTF-8");
                mimeMessage.setSubject(subject, "UTF-8");
                mimeMessage.setFrom(javaMailSender.getUsername());
                mimeMessage.setRecipients(Message.RecipientType.TO, address);
                javaMailSender.send(mimeMessage);
            } catch (MailException | MessagingException e) {
                System.out.println(e.getMessage());
                results.add(MailingResultType.BAD_SUBSCRIBER.getMessage() + " " + address);
            }
        }

        if (results.size() == 0) {
            results.add(MailingResultType.SUCCESS.getMessage());
        }

        return results;
    }
}
