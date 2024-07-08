package com.chiniakin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений.
 *
 * @author ChiniakinD
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывет исключение {@link CountryNotFoundException} .
     *
     * @param e исключение {@link CountryNotFoundException} .
     * @return ответ с сообщением об ошибке и код статуса 404.
     */
    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Object> handleCountryNotFoundException(CountryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Обрабатывет исключение {@link IndustryNotFoundException} .
     *
     * @param e исключение {@link IndustryNotFoundException} .
     * @return ответ с сообщением об ошибке и код статуса 404.
     */
    @ExceptionHandler(IndustryNotFoundException.class)
    public ResponseEntity<Object> industryNotFoundException(IndustryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Обрабатывет исключение {@link OrgFormNotFoundException} .
     *
     * @param e исключение {@link OrgFormNotFoundException} .
     * @return ответ с сообщением об ошибке и код статуса 404.
     */
    @ExceptionHandler(OrgFormNotFoundException.class)
    public ResponseEntity<Object> handleOrgFormNotFoundException(OrgFormNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Обрабатывет исключение {@link ContractorNotFoundException} .
     *
     * @param e исключение {@link ContractorNotFoundException} .
     * @return ответ с сообщением об ошибке и код статуса 404.
     */
    @ExceptionHandler(ContractorNotFoundException.class)
    public ResponseEntity<Object> handleContractorNotFoundException(ContractorNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
