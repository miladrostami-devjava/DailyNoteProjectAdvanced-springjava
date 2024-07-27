package com.example.springbootaoptodoexample.todo.repo;

import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface DailyNoteRepository {


    void addDailyNote(final UUID userId, final DailyNoteList dailyNoteList);

    List<DailyNoteList> getDailyNote(final  UUID userId);

    Optional<DailyNoteList> getDailyNote(final UUID userId,final String todoList);

    void deleteDailyNote(final UUID userId);

    void deleteDailyNote(final UUID userId,final String todoList);

    void deleteAllDailyNoteList();

}
