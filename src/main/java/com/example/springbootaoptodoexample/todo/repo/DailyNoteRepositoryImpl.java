package com.example.springbootaoptodoexample.todo.repo;

import com.example.springbootaoptodoexample.aspects.Timed;
import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import org.springframework.stereotype.Repository;

import java.util.*;
import static java.lang.String.format;
@Repository
public class DailyNoteRepositoryImpl implements DailyNoteRepository{
    private Map<UUID,List<DailyNoteList>> db = new HashMap<>();

    @Override
    @Timed
    public void addDailyNote(UUID userId, DailyNoteList list) {
  if (getDailyNote(userId,list.getListName()).isPresent()){
      throw new IllegalArgumentException(format("List with name %s already exists",list.getListName()));
  }
  if (list.getDailyNoteList() == null){
      list.setDailyNoteList(new ArrayList<>());
  }
  getDailyNote(userId).add(list);
    }

    @Override
    @Timed
    public List<DailyNoteList> getDailyNote(UUID userId) {
        return db.computeIfAbsent(userId,newUser -> new ArrayList<>());
    }

    @Override
    @Timed
    public Optional<DailyNoteList> getDailyNote(UUID userId, String todoList) {

        return getDailyNote(userId)
                .stream()
                .filter(dailyNoteList -> dailyNoteList.getListName().equalsIgnoreCase(todoList))
                .findAny();
    }

    @Override
    @Timed
    public void deleteDailyNote(UUID userId) {
db.remove(getDailyNote(userId));
    }

    @Override
    @Timed
    public void deleteDailyNote(UUID userId, String todoList) {
List<DailyNoteList> lists = getDailyNote(userId);
lists.removeIf(dailyNoteList -> dailyNoteList.getListName().equals(todoList));
    }

    @Override
    @Timed
    public void deleteAllDailyNoteList() {
db.clear();
    }
}
