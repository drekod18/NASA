package model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class HistoryContent {
    private String id;
    private String className;
    private LocalDateTime createdDate;
    private String actor = "system";
    private String methodName;
    private Map<String, Object> object;
    private Status status;

    public HistoryContent() {
        this.createdDate = LocalDateTime.now();
    }
}