package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        List<TrelloListDto> trelloListDtoStub = new ArrayList<>();
        trelloListDtoStub.add(new TrelloListDto("1", "name", false));

        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        trelloBoardDtoStub.add(new TrelloBoardDto("1", "name", trelloListDtoStub));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoardDto.size());
        assertEquals("name", fetchedTrelloBoardDto.get(0).getName());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDtoStub = new CreatedTrelloCardDto("1", "name", "test.com/test");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDtoStub);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("test.com/test", createdTrelloCardDto.getShortUrl());
    }

    @Test
    public void fetchEmptyTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedEmptyTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, fetchedEmptyTrelloBoardDto.size());
    }
}