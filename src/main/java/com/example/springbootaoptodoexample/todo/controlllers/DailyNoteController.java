package com.example.springbootaoptodoexample.todo.controlllers;


import com.example.springbootaoptodoexample.aspects.Restrict;
import com.example.springbootaoptodoexample.todo.domain.DailyNote;
import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import com.example.springbootaoptodoexample.todo.dto.TotalDailyNoteList;
import com.example.springbootaoptodoexample.todo.repo.DailyNoteRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/dailynote")

public class DailyNoteController {

private final DailyNoteRepositoryImpl repository;
    private static final Logger log = LoggerFactory.getLogger(DailyNoteController.class);

    @Autowired
    public DailyNoteController(DailyNoteRepositoryImpl repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET,produces = "application/json")
    public Callable<TotalDailyNoteList> getUsersDailyNoteList(@RequestHeader("user-id") final UUID userId){
log.info("GET all daily notes for user {}",userId);
        return ()-> new TotalDailyNoteList(repository.getDailyNote(userId));
//return ()-> new TotalDailyNoteList(repository.getDailyNote(userId));
    }


    @RequestMapping(value = "/me/{note}",method = RequestMethod.GET,produces = "application/json")
    public Callable<ResponseEntity<DailyNoteList>> getUserDailyNoteList
            (@RequestHeader("user-id") final UUID userId,@PathVariable("note") final String todoList){
        log.info("Get All daily notes for user {} with Id {}",todoList,userId);
        return ()-> repository.getDailyNote(userId, todoList)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/me" ,method = RequestMethod.DELETE)
    public Callable<ResponseEntity<?>> deleteUserDailyNoteList(@RequestHeader("user-id") final UUID userId){
        log.info("Delete daily notes for user {}",userId);
        return ()-> {
          repository.deleteDailyNote(userId);
       return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/me/{note}"  , method = RequestMethod.DELETE)
    public Callable<ResponseEntity<?>> deleteUserDailyNote
            (@RequestHeader("user-id") final UUID userId,@PathVariable("note") final String dailyNote){
        log.info("Delete daily note for user {} with Id {}", dailyNote, userId);
        return ()-> {
             repository.deleteDailyNote(userId,dailyNote);
             return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/uuid",method = RequestMethod.GET)
    public UUID getUUID(){
        return UUID.randomUUID();
    }


    @RequestMapping(value = "/me",method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> createDailyNote
            (@RequestHeader("user-id") final UUID userId,@RequestBody DailyNoteList dailyNoteList){
        return ()-> {
            repository.addDailyNote(userId, dailyNoteList);
            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    @Restrict
    public Callable<ResponseEntity<?>> deleteUsersTotalDailyNotes(){
        log.info("Delete All Daily Note for all users in all application!");
        return ()->{
            repository.deleteAllDailyNoteList();
            return ResponseEntity.accepted().build();
        };
    }


    // new methods added to controller for additional functionalities

    //region newMethods

    @RequestMapping(value = "/me", method = RequestMethod.PUT)
    public Callable<ResponseEntity<?>> updateDailyNoteList(
            @RequestHeader("user-id") final UUID userId,@RequestBody DailyNoteList dailyNoteList
    ){
        return ()-> {
            repository.updateDailyNoteList(userId, dailyNoteList);
            return ResponseEntity.ok().build();
        };
    }

    @RequestMapping(value = "/me/status",method = RequestMethod.GET,produces = "application/json")
    public Callable<List<DailyNoteList>> getDailyNotesByStatus(
            @RequestHeader("user-id") final UUID userId,
            @RequestParam(value = "status") final String status
    ){
        return ()-> repository.getDailyNotesByStatus(userId, status);
    }

    @RequestMapping(value = "/me/priority", method = RequestMethod.GET, produces = "application/json")
    public Callable<List<DailyNoteList>> getDailyNotesByPriority
            (@RequestHeader("user-id") final UUID userId, @RequestParam("priority") final String priority) {
        return () -> repository.getDailyNotesByPriority(userId, priority);
    }

    @RequestMapping(value = "/me/tags", method = RequestMethod.GET, produces = "application/json")
    public Callable<List<DailyNoteList>> searchDailyNotesByTags
            (@RequestHeader("user-id") final UUID userId, @RequestParam("tags") final List<String> tags) {
        return () -> repository.searchDailyNotesByTags(userId, tags);
    }

    @RequestMapping(value = "/me/{note}/add", method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> addNoteToExistingList
            (@RequestHeader("user-id") final UUID userId,
             @PathVariable("note") final String todoList, @RequestBody DailyNote dailyNote) {
        return () -> {
            repository.addNoteToExistingList(userId, todoList, dailyNote);
            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/me/{note}/update", method = RequestMethod.PUT)
    public Callable<ResponseEntity<Void>> updateNoteInList
            (@RequestHeader("user-id") final UUID userId,
             @PathVariable("note") final String todoList, @RequestBody DailyNote dailyNote) {
        return () -> {
            repository.updateNoteInList(userId, todoList, dailyNote);
            return ResponseEntity.ok().build();
        };
    }

    //endregion







}


