package by.artezio.cloud.publishing.web.converters;

import by.artezio.cloud.publishing.domain.MailingResult;
import by.artezio.cloud.publishing.dto.MailingInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Класс-конвертор, реализующий интерфейс org.springframework.core.convert.converter.Converter&lt;S, T&gt;
 *     и служит для конвертации объектов класса MailingResult(source) в объекты класса MailingInfo(target).
 * За основу берётся множество значений полей source и мапятся на поля target, возможно, с какими-то преобразованиями.
 * <br>
 * Основным методом является <code>MailingInfo convert(MailingResult result)</code>,
 *    в котором и происходит вся логика преобразования без использования <strong>магии</strong>.
 */
@Component
public class MailingResultToMailingInfoConverter implements Converter<MailingResult, MailingInfo> {

    @Override
    public MailingInfo convert(final MailingResult source) {
        MailingInfo info = new MailingInfo();
        info.setMailingId(source.getMailingId());
        info.setDate(Timestamp.valueOf(source.getDateTime()));
        info.setResult(source.getResult());

        return info;
    }
}
