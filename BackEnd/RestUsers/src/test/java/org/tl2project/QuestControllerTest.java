package org.tl2project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tl2project.controller.QuestController;
import org.tl2project.model.Quest;
import org.tl2project.service.QuestService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestControllerTest {
  
  @Mock
  private QuestService questService;

  @InjectMocks
  QuestController questController;

  private MockMvc mockMvc;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    
    mockMvc = MockMvcBuilders.standaloneSetup(questController).build();
  }
  
  @Test
  public void getQueststest() throws Exception{
    
    BigDecimal lat = new BigDecimal(1); 
    BigDecimal lng = new BigDecimal(1); 
    long score = (long) 5;
    Quest quest = new Quest();
    List<Quest> quests = new ArrayList<>();
    quests.add(quest);
    
    when(questService.produceQuests(lat, lng, score)).thenReturn(quests);
    
    mockMvc.perform(get("/quest/getQuests?latitude=1&longitude=1&score=5"))
    .andExpect(status().isOk());
  }
 

  
  
}
