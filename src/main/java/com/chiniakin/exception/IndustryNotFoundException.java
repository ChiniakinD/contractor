package com.chiniakin.exception;

/**
 * Исключение с сообщением, что страна не отрасль.
 *
 * @author ChiniakinD
 */
public class IndustryNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public IndustryNotFoundException(String message) {
        super(message);
    }

}
