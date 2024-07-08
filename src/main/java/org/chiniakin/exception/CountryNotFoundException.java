package org.chiniakin.exception;

/**
 * Исключение с сообщением, что страна не найдена.
 *
 * @author ChiniakinD
 */
public class CountryNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public CountryNotFoundException(String message) {
        super(message);
    }

}
