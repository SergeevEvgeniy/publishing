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
        String md5Hex = DigestUtils.md5Hex(input).toUpperCase();
        md5Hex = DigestUtils.md5Hex("cloud" + md5Hex + "publishing").toUpperCase();
        return md5Hex;
    }
}
