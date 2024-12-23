package model.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpacePicture {
    private int pictureId;
    private String imageUrl;
    private String imageDescription;
    private Date captureTimestamp;
    private String photographer;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int userId;
}
