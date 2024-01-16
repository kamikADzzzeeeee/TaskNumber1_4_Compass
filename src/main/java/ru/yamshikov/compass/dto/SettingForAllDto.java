package ru.yamshikov.compass.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingForAllDto {
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto north;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto northEast;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto east;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto southEast;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto south;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto southWest;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto west;
    @NotNull(message = "Поле не должно быть пустым")
    @Valid
    private SettingsDto northWest;

    public Map<String, SettingsDto> getMap() {
        return Map.of(
                "north", north,
                "north-east", northEast,
                "east", east,
                "south-east", southEast,
                "south", south,
                "south-west", southWest,
                "west", west,
                "north-west", northWest
        );
    }
}
