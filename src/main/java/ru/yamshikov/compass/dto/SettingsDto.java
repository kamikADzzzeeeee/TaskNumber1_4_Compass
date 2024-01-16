package ru.yamshikov.compass.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SettingsDto {
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "(([0-9]*\\.?[0-9]*))(\\-)(([0-9]*\\.?[0-9]+))", message = "Формат поля должен быть 'start-end'")
    private String interval;
}
