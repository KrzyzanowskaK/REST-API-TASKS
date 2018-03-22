package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        List<Task> taskList = Arrays.asList(task);

        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> theList = dbService.getAllTasks();

        //Then
        assertNotNull(theList);
        assertEquals(1, theList.size());
    }

    @Test
    public void getTaskByIdTest() {
        //Given
        Task taskStub = new Task(1L,"title", "content");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskStub));

        //When
        Task fetchedTaskById = dbService.getTaskById(1L);

        //Then
        assertEquals(taskStub.getId(), fetchedTaskById.getId());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task taskStub = new Task(1l, "title", "content");
        when(taskRepository.save(taskStub)).thenReturn(taskStub);

        //When
        Task testTask = dbService.saveTask(taskStub);

        //Then
        assertEquals(taskStub.getId(), testTask.getId());
        assertEquals(taskStub.getTitle(), testTask.getTitle());
        assertEquals(taskStub.getContent(), testTask.getContent());
    }

    @Test
    public void getTaskTest() {
        //Given
        Task taskStub = new Task(1L, "title", "content");

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(taskStub));

        //When
        Optional<Task> fetchedTaskById = dbService.getTask(1L);

        //Then
        assertTrue(fetchedTaskById.isPresent());
        assertEquals(taskStub.getId(), fetchedTaskById.get().getId());
    }
}