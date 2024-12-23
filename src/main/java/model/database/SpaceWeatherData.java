package model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceWeatherData {
    private int spaceWeatherId;
    private Date timestamp;
    private int solarActivityLevel;
    private int magneticFieldStrength;
    private String observationLocation;
    private float cosmicRadiation;
    private int solarWindSpeed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int userId;
}
