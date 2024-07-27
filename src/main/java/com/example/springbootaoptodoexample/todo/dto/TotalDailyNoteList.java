package com.example.springbootaoptodoexample.todo.dto;

import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDailyNoteList {
    private List<DailyNoteList> dailyNoteLists;
}
