package com.example.springbootaoptodoexample.todo.repo;

import com.example.springbootaoptodoexample.todo.domain.DailyNote;
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

    /**
     * program development plan
     * New features added to the application
     *
     * @version 1.0
     * @author miladrostami
     * */

    /**Update an existing DailyNoteList based on userId and dailyNoteList.*/
    void updateDailyNoteList(final UUID userId,final DailyNoteList dailyNoteList);

    /**Get a list of notes by status (eg done, in progress) for a specific user.*/
    List<DailyNoteList> getDailyNotesByStatus(final UUID userId,final String status);

    /**Get a list of notes based on priority for a specific user.*/
    List<DailyNoteList> getDailyNotesByPriority(final UUID userId,final String priority);

    /**Search list of notes based on tags for a specific user.*/
    List<DailyNoteList> searchDailyNotesByTags(final UUID userId,final List<String> tags);

    /**Add a new DailyNote to an existing DailyNoteList based on userId and todoList*/
    void addNoteToExistingList(final UUID userId,final String todolist,final DailyNote dailyNote);

    /**Update a specific DailyNote in an existing DailyNoteList based on userId and todoList.*/
    void updateNoteInList(final UUID userId,final String todolist,final DailyNote dailyNote);

}
