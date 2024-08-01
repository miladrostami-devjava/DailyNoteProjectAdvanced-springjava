package com.example.springbootaoptodoexample.todo.controlllers;

import com.example.springbootaoptodoexample.todo.domain.DailyNote;
import com.example.springbootaoptodoexample.todo.domain.DailyNoteList;
import com.example.springbootaoptodoexample.todo.dto.TotalDailyNoteList;
import com.example.springbootaoptodoexample.todo.repo.DailyNoteRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(DailyNoteController.class)
class DailyNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DailyNoteRepositoryImpl repository;

    private UUID userId;
    private DailyNoteList dailyNoteList;
    private DailyNote dailyNote;




    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userId = UUID.randomUUID();
        dailyNoteList = new DailyNoteList("Test List");
        dailyNote = new DailyNote();
        dailyNote.setTitle("Test Note");
    }

    @AfterEach
    void tearDown() {
reset(repository);
    }

    /* @Test
    void getUsersDailyNoteList() throws Exception {
        TotalDailyNoteList totalDailyNoteList = new TotalDailyNoteList(Arrays.asList(dailyNoteList));
        when(repository.getDailyNote(any(UUID.class))).thenReturn(Arrays.asList(dailyNoteList));

        mockMvc.perform(get("/dailynote/me")
                .header("user-id", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dailyNotes", hasSize(1)))
                .andExpect(jsonPath("$.dailyNotes[0].name", is(dailyNoteList.getName())));

        verify(repository, times(1)).getDailyNote(userId);
    }*/

    @Test
    void getUsersDailyNoteList() throws Exception{
        TotalDailyNoteList totalDailyNoteList = new TotalDailyNoteList(Collections.singletonList(dailyNoteList));
        when(repository.getDailyNote(any(UUID.class))).thenReturn(Collections.singletonList(dailyNoteList));
      mockMvc.perform(get("/dailynote/")
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.dailyNotes.size()",hasSize(2)))
              .andExpect(jsonPath("$.dailyNotes[0].name",is(dailyNoteList.getListName())));
      verify(repository,times(1)).getDailyNote(userId);
    }

    @Test
    void getUserDailyNoteList() throws InstantiationException, IllegalAccessException {
    }

    @Test
    void deleteUserDailyNoteList() {
    }

    @Test
    void deleteUserDailyNote() {
    }

    @Test
    void getUUID() {
    }

    @Test
    void createDailyNote() {
    }

    @Test
    void deleteUsersTotalDailyNotes() {
    }

    @Test
    void updateDailyNoteList() {
    }

    @Test
    void getDailyNotesByStatus() {
    }

    @Test
    void getDailyNotesByPriority() {
    }

    @Test
    void searchDailyNotesByTags() {
    }

    @Test
    void addNoteToExistingList() {
    }

    @Test
    void updateNoteInList() {
    }
}



