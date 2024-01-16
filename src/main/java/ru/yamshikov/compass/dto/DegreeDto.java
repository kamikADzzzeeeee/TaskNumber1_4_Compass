package ru.yamshikov.compass.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DegreeDto {
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0,message = "Минимальное значение 0")
    @Max(value = 360, message = "Максимальное значение 360")
    private Integer degree;
}
