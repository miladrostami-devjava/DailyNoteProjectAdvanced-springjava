package com.example.springbootaoptodoexample.todo.dto;

import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDailyNoteList {
    private List<DailyNoteList> dailyNoteLists;
    /**
     * program development plan
     * New features added to the application
     * @param owner,createdTime,updatedTime,totalLists
     * @version 1.0
     * @author miladrostami
     * */
    private String owner;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    //The total number of daily note lists.
    private Integer totalLists;

    public TotalDailyNoteList(List<DailyNoteList> dailyNoteLists) {
        this.dailyNoteLists = dailyNoteLists;
    }




    /**
     * program development plan
     * This method is used to set daily
     * lists and automatically updates the number
     * of lists to ensure that this value is always correct.
     * */
    public void setDailyNoteLists(List<DailyNoteList> dailyNoteLists) {
        this.dailyNoteLists = dailyNoteLists;
        this.totalLists = dailyNoteLists != null ? dailyNoteLists.size() :  0;
    }
}
