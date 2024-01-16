package ru.yamshikov.compass.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Direction {
    @Setter
    @Getter
    private static String exclusion = "Север";
    private String name;
    private List<Double> interval;

    public Direction(String name, List<Double> interval) {
        this.name = name;
        this.interval = interval;
    }

    public Direction(String name) {
        this.name = name;
        switch (name) {
            case "Север":
                interval = List.of(337.5D, 22.5D);
                break;
            case "Северо-восток":
                interval = List.of(22.5D, 67.5D);
                break;
            case "Восток":
                interval = List.of(67.5D, 112.5D);
                break;
            case "Юго-восток":
                interval = List.of(112.5D, 157.5D);
                break;
            case "Юг":
                interval = List.of(157.5, 202.5D);
                break;
            case "Юго-запад":
                interval = List.of(202.5D, 247.5D);
                break;
            case "Запад":
                interval = List.of(247.5D, 292.5D);
                break;
            case "Северо-запад":
                interval = List.of(292.5D, 337.5D);
                break;
        }
    }

    public Direction(Direction direction) {
        this.name = direction.name;
        this.interval = direction.interval;
    }

    boolean angleInInterval(Integer angles) {
        boolean bool;
        if (!name.equals(exclusion)) {
            bool = angles >= interval.get(0) && angles <= interval.get(1);
        } else {
            bool = (angles >= interval.get(0) && angles <= 360) || (angles >= 0 && angles <= interval.get(1));
        }
        return bool;
    }
}
