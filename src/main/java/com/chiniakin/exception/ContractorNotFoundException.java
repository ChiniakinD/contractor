package com.chiniakin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение с сообщением, что контрагент не найден.
 *
 * @author ChiniakinD
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContractorNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public ContractorNotFoundException(String message) {
        super(message);
    }

}
