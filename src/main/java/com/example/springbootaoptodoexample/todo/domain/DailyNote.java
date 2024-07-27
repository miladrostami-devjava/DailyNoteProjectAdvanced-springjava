package com.example.springbootaoptodoexample.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNote {
    private String noteTask;
    private ZonedDateTime before;
}
