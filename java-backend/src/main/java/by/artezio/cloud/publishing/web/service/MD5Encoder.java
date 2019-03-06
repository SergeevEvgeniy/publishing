package by.artezio.cloud.publishing.web.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sergeev Evgeniy
 */
@Service
public class MD5Encoder implements EncoderService {

    @Override
    public String encode(final String input) {
        return DigestUtils.md5Hex(input);
    }

}
