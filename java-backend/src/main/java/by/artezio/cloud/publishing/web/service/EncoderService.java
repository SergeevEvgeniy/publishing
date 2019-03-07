package by.artezio.cloud.publishing.web.service;

/**
 *
 * @author Sergeev Evgeniy
 */
public interface EncoderService {

    /**
     *
     * @param input значение для хэширования
     * @return захэшированное значение
     */
    String encode(String input);
}
