package com.example.springbootaoptodoexample.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNoteList {

    private String listName;
    private List<DailyNote> dailyNoteList;
}
