package ru.yamshikov.compass.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.yamshikov.compass.dto.*;
import ru.yamshikov.compass.model.Compass;
import ru.yamshikov.compass.model.Direction;
import ru.yamshikov.compass.util.errors.SettingsDtoValidationException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/compass")
@RequiredArgsConstructor
public class CompassController {

    private final Compass compass;

    @GetMapping("/about")
    public ResponseEntity<Object> getAllDirections() {
        return new ResponseEntity<>(compass.getAllDirectionsList(), HttpStatus.OK);
    }

    @PostMapping("/settings")
    public ResponseEntity<ConfirmChangeSettingsDirectionDto> setParametersForDirection(
            @RequestParam("direction") String direction,
            @RequestBody @Valid SettingsDto dto, Errors errors) {
        dtoValidation(errors);
        Direction newDirection = new Direction(dto.getName(), getInterval(dto));
        return new ResponseEntity<>(compass.setSettingsDirection(newDirection, direction), HttpStatus.OK);
    }

    @PostMapping("/settings/all-directions")
    public ResponseEntity<HttpStatus> setParametersForAllDirections(
            @RequestBody @Valid SettingForAllDto dto, Errors errors) {
        dtoValidation(errors);
        for (String key : dto.getMap().keySet()) {
            Direction direction = new Direction(dto.getMap().get(key).getName(), getInterval(dto.getMap().get(key)));
            compass.setSettingsDirection(direction, key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/angle")
    public ResponseEntity<GetDirectionDto> getNameDirectionByDegree(
            @RequestBody @Valid DegreeDto dto, Errors errors) {
        dtoValidation(errors);
        return new ResponseEntity<>(compass.getNameDirection(dto.getDegree()), HttpStatus.OK);
    }

    private List<Double> getInterval(SettingsDto dto) {
        List<Double> doubleList = new ArrayList<>();
        for (String s : dto.getInterval().split("-")) {
            Double d = Double.valueOf(s);
            if (d < 0 || d > 360)
                throw new SettingsDtoValidationException(List.of("interval - Значение должно быть от 0 до 360"));
            doubleList.add(d);
        }
        return doubleList;
    }

    private void dtoValidation(Errors errors) {
        if (errors.hasErrors()) {
            List<String> listErrors = errors.getFieldErrors()
                    .stream()
                    .map(field -> field.getField() + " - " + field.getDefaultMessage())
                    .toList();
            throw new SettingsDtoValidationException(listErrors);
        }
    }


}
