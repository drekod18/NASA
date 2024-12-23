package ru.sfedu.NASA;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryContentTest {
    private String id;
    private String actor;
    private String action;
    private String content;
}