package ru.yamshikov.compass.util.errors;

import lombok.Getter;

import java.util.List;

@Getter
public class SettingsDtoValidationException extends RuntimeException{
    List<String> errors;
    public SettingsDtoValidationException(List<String> errors) {
        super();
        this.errors = errors;
    }
}