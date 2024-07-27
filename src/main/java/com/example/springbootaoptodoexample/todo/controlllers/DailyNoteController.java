package com.example.springbootaoptodoexample.todo.controlllers;


import com.example.springbootaoptodoexample.aspects.Restrict;
import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import com.example.springbootaoptodoexample.todo.dto.TotalDailyNoteList;
import com.example.springbootaoptodoexample.todo.repo.DailyNoteRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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





}


