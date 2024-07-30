package com.example.springbootaoptodoexample.todo.repo;

import com.example.springbootaoptodoexample.aspects.Timed;
import com.example.springbootaoptodoexample.todo.domain.DailyNote;
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

    /**
     * The following methods have been added to the program in the development plan
     * @author miladrostami
     * */

    @Override
    @Timed
    public void updateDailyNoteList(UUID userId, DailyNoteList dailyNoteList) {
        List<DailyNoteList> lists = getDailyNote(userId);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getListName().equalsIgnoreCase(dailyNoteList.getListName())){
                lists.set(i,dailyNoteList);
                return;
            }
        }
        throw new NoSuchElementException(format("List with name %s not found!",dailyNoteList.getListName()));
    }

    @Override
    @Timed
    public List<DailyNoteList> getDailyNotesByStatus(UUID userId, String status) {
        List<DailyNoteList> lists = getDailyNote(userId);
        List<DailyNoteList> result = new ArrayList<>();
        for (DailyNoteList list : lists){
            if (list.getStatus().equalsIgnoreCase(status)){
                result.add(list);
            }
        }
        return result;
    }

    @Override
    @Timed
    public List<DailyNoteList> getDailyNotesByPriority(UUID userId, String priority) {
        List<DailyNoteList> lists = getDailyNote(userId);
        List<DailyNoteList> result = new ArrayList<>();
        for (DailyNoteList list:lists){
            if (list.getPriority().equalsIgnoreCase(priority)){
                result.add(list);
            }
        }
        return result;
    }

    @Override
    @Timed
    public List<DailyNoteList> searchDailyNotesByTags(UUID userId, List<String> tags) {
        List<DailyNoteList> lists = getDailyNote(userId);
        List<DailyNoteList> result = new ArrayList<>();
        for (DailyNoteList list:lists){
            //other  solution method
            /*if (list.getTags() != null && list.getTags().containsAll(tags)){
                result.add(list);
            }*/


            if (list.getTags() != null && new HashSet<>(list.getTags()).containsAll(tags)){
                result.add(list);
            }
        }
        return result;
    }

    /**This method should add a new DailyNote to an existing DailyNoteList.*/
    @Override
    @Timed
    public void addNoteToExistingList(UUID userId, String todolist, DailyNote dailyNote) {
        List<DailyNoteList> lists = getDailyNote(userId);
        for (DailyNoteList list:lists){
            if (list.getListName().equalsIgnoreCase(todolist)){
                list.getDailyNoteList().add(dailyNote);
                return;
            }
        }
        throw new NoSuchElementException(format("List with name %s not found !",todolist));
    }

    /**This method should update a specific DailyNote in an existing DailyNoteList.*/
    @Override
    @Timed
    public void updateNoteInList(UUID userId, String todolist, DailyNote dailyNote) {
        List<DailyNoteList> lists = getDailyNote(userId);
        for (DailyNoteList list: lists){
            if (list.getListName().equalsIgnoreCase(todolist)){
                List<DailyNote> notes = list.getDailyNoteList();
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getNoteTask().equalsIgnoreCase(todolist)){
                        notes.set(i,dailyNote);
                        return;
                    }
                }
                //other  solution method
    /*    for (DailyNote note: notes){
            if (note.getNoteTask().equalsIgnoreCase(dailyNote.getNoteTask())){
                notes.add(note);
                return;
            }
        }*/
                throw new NoSuchElementException(format("Note with task %s not found in list %s ",
                        dailyNote.getNoteTask(),todolist));
            }
        }
        throw new NoSuchElementException(format("List with name %s not found",todolist));


    }
}
