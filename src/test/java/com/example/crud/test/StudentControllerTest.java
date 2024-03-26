package com.example.crud.test;

import com.example.crud.test.controllers.StudentController;
import com.example.crud.test.entities.Student;
import com.example.crud.test.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads(){
        assertThat(studentController).isNotNull();
    }
    //Test per la creazione di un nuovo studente
    @Test
    @Order(1)
    void createStudent() throws Exception{
        Student student = new Student();
        student.setId(1L);
        student.setNome("Denise");
        student.setSurname("De Leo");
        student.isWorking(true);

        String studentJSON = objectMapper.writeValueAsString(student);

        MvcResult resultActions = this.mockMvc.perform(post("/studente/addStudente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();

    }

    //Test per avere la lista completa degli studenti
    @Test
    @Order(3)
    void getAllStudent() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/studente/getListaStudenti"))
                .andDo(print()).andReturn();

        List<Student> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(),
                List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }
    //Test per cercare uno studente tramite id
    @Test
    @Order(4)
    void getStudenteId()throws Exception{
        Long studenteId = 1L;


        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/studente/getStudenteId/{id}", studenteId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(studenteId)).andReturn();
    }
    //Test per aggiornare uno studente tramite l'id
    @Test
    @Order(2)
    void updateStudenteId()throws Exception{
        Long studenteId = 1L;
        Student updateStudente = new Student(studenteId,"Pippo","Baudo",false);
        String studentJSON = objectMapper.writeValueAsString(updateStudente);

        MvcResult resultUpdate = this.mockMvc.perform(MockMvcRequestBuilders.put("/studente/updateStudenteId/{id}",studenteId)
                .contentType(MediaType.APPLICATION_JSON).content(studentJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = resultUpdate.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }
    //Test per settare isWorking
    @Test
    @Order(5)
    void updateIsWorking()throws Exception{
        Long studenteId = 1L;
        boolean isWorking = false;

        mockMvc.perform(MockMvcRequestBuilders.patch("/studente/setIsWorking/{id}", studenteId)
                        .param("working", String.valueOf(isWorking)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    //Test per l'eliminazione di uno studente
    @Test
    @Order(6)
    void deleteStudente() throws Exception {
        Long studentId = 1L;

        MvcResult result = mockMvc.perform(delete("/studente/deleteStudenteId/{id}",studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


    }

}
