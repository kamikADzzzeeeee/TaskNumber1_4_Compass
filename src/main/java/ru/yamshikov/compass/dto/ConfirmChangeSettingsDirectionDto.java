package ru.yamshikov.compass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yamshikov.compass.model.Direction;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmChangeSettingsDirectionDto {
    private Direction oldValue;
    private Direction newValue;
}
