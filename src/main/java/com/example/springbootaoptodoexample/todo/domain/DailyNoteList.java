package com.example.springbootaoptodoexample.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNoteList {

    private String listName;
    private List<DailyNote> dailyNoteList;
    /**
     * program development plan
     * New features added to the application in DailyNoteList
     * @param owner,status,description,priority,createdTime,updatedTime,tags,permissions
     * @version 1.0
     * @author miladrostami
     * */
    private String owner;
    private String status;
    private String description;
    private String priority;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private List<String> tags;
    private List<String> permissions;


    public DailyNoteList(String listName) {
        this.listName = listName;
    }
}
