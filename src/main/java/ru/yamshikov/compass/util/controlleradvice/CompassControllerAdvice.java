package ru.yamshikov.compass.util.controlleradvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yamshikov.compass.model.ErrorResponse;
import ru.yamshikov.compass.util.errors.NoSuchRequestParamException;
import ru.yamshikov.compass.util.errors.SettingsDtoValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CompassControllerAdvice {

    private static String formatInstantNowIntoString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    @ExceptionHandler(SettingsDtoValidationException.class)
    public ResponseEntity<Object> handleException(SettingsDtoValidationException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверно введены данные в JSON'e",
                exception.getErrors(),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        ErrorResponse response = new ErrorResponse(
                "Неизвестная ошибка",
                List.of(exception.getLocalizedMessage()),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверный формат принятого JSON'a",
                List.of(exception.getLocalizedMessage()),
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleException(NoSuchElementException exception) {
        ErrorResponse response = new ErrorResponse(
                "Направление с введенным градусом отсутствует. Проверьте введенные интервалы для направлений",
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchRequestParamException.class)
    public ResponseEntity<Object> handleException(NoSuchRequestParamException exception) {
        ErrorResponse response = new ErrorResponse(
                "Неверный формат параметра запроса",
                formatInstantNowIntoString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }




}
