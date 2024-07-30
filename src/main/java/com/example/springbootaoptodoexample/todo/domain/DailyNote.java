package com.example.springbootaoptodoexample.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNote {
    private String noteTask;
    private ZonedDateTime before;
/**
 * program development plan
 * New features added to the application
 *  @param title,status,description,priority,createdTime,updatedTime,tags
 * @version 1.0
 * @author miladrostami
 * */
    private String title;
    private String status;
    private String description;
    private String priority;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private List<String> tags;


}
