package by.artezio.cloud.publishing.web.security.impl;

import by.artezio.cloud.publishing.web.security.EncoderService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * Имплементация {@link EncoderService}. Добавляет соль к значению пароля и
 * возвращает хэш пароля
 *
 * @author Sergeev Evgeniy
 */
@Service
public class MD5EncoderService implements EncoderService {

    @Override
    public String encode(final String input) {
        return DigestUtils.md5Hex(input + "Strongest salt ever");
    }

}
