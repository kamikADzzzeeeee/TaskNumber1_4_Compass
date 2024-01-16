package ru.yamshikov.compass.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yamshikov.compass.dto.ConfirmChangeSettingsDirectionDto;
import ru.yamshikov.compass.dto.GetDirectionDto;
import ru.yamshikov.compass.util.errors.NoSuchRequestParamException;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class Compass {

    private Direction north = new Direction("Север");
    private Direction northEast = new Direction("Северо-восток");
    private Direction east = new Direction("Восток");
    private Direction southEast = new Direction("Юго-восток");
    private Direction south = new Direction("Юг");
    private Direction southWest = new Direction("Юго-запад");
    private Direction west = new Direction("Запад");
    private Direction northWest = new Direction("Северо-запад");

    public List<Direction> getAllDirectionsList(){
        return List.of(north, northEast, east, southEast, south, southWest, west, northWest);
    }

    public ConfirmChangeSettingsDirectionDto setSettingsDirection(Direction newDirection, String nameOldDirection){
        Direction oldDirection = switch (nameOldDirection) {
            case "north" -> {
                Direction.setExclusion(newDirection.getName());
                yield north;
            }
            case "north-east" -> northEast;
            case "east" -> east;
            case "south-east" -> southEast;
            case "south" -> south;
            case "south-west" -> southWest;
            case "west" -> west;
            case "north-west" -> northWest;
            default -> throw new NoSuchRequestParamException();
        };
        Direction oldDir = new Direction(oldDirection);
        oldDirection.setInterval(newDirection.getInterval());
        oldDirection.setName(newDirection.getName());
        Direction newDir = new Direction(oldDirection);
        return new ConfirmChangeSettingsDirectionDto(oldDir,newDir);
    }

    public GetDirectionDto getNameDirection(Integer degree){
        return new GetDirectionDto(getAllDirectionsList().stream()
                .filter(direction -> direction.angleInInterval(degree))
                .findFirst()
                .orElseThrow()
                .getName());
    }

}
