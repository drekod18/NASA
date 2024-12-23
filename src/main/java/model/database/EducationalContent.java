package model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationalContent {
    private int contentId;
    private String contentTitle;
    private String contentType;
    private String difficultyLevel;
    private int estimatedReadingTime;
    private boolean isRecommended;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int userId;
}
