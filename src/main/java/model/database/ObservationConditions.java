package model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationConditions {
    private int observationId;
    private int cloudCoveragePercentage;
    private int atmosphericSeeingQuality;
    private String observationLocation;
    private Date observationTimestamp;
    private float temperature;
    private float humidity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int spaceWeatherId;
}
