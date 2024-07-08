package org.chiniakin.exception;

/**
 * Исключение с сообщением, что форма организации не найдена.
 *
 * @author ChiniakinD
 */
public class OrgFormNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public OrgFormNotFoundException(String message) {
        super(message);
    }

}
