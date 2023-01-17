package com.teste.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.demo.controller.FamiliaController;
import com.teste.demo.model.Familia;
import com.teste.demo.repository.FamiliaRepository;
import com.teste.demo.service.CriterioService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FamiliaController.class)
@AutoConfigureMockMvc
public class FamiliaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriterioService criterioService;

    @MockBean
    private FamiliaRepository familiaRepository;

    @Test
    public void testCreateFamily() throws Exception {
        Familia familia = new Familia();
        familia.setDependentes(0);
        familia.setRenda(800);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(familia);

        Mockito.when(familiaRepository.save(ArgumentMatchers.any(Familia.class))).thenReturn(familia);

        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/familia").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        final String response = mvcResult.getResponse().getContentAsString();
        MatcherAssert.assertThat(response, Matchers.notNullValue());
    }

    @Test
    public void testavaliableFamily() throws  Exception {
        Familia familia = new Familia();
        familia.setDependentes(0);
        familia.setRenda(800);

        List<Familia> list = new ArrayList<>();
        list.add(familia);
        Mockito.when(familiaRepository.findAll()).thenReturn(list);
        Mockito.when(criterioService.calcularPontuacaoDependentes(ArgumentMatchers.any(Familia.class))).thenReturn(0);
        Mockito.when(criterioService.calcularPontuacaoRenda(ArgumentMatchers.any(Familia.class))).thenReturn(5);

        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/familia/avaliar")).andExpect(status().isOk()).andReturn();

        final String response = mvcResult.getResponse().getContentAsString();
        MatcherAssert.assertThat(response, Matchers.notNullValue());

        int pont = list.get(0).getPontuacao();
        assertEquals(5, pont);



    }


}
