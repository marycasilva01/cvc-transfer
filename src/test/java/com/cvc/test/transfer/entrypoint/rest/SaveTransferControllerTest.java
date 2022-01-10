package com.cvc.test.transfer.entrypoint.rest;

import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.SaveTransferUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

@WebMvcTest(SaveTransferController.class)
class SaveTransferControllerTest {

    @MockBean
    private SaveTransferUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired MockMvc mvc;

    @Test
    void test_create_transfer_error() throws Exception {

        when(useCase.save(any())).thenThrow(RuntimeException.class);
        var request= TransferFactory.createTransferDTORequest();
        mvc.perform(post("/transfers").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void test_create_transfer() throws Exception {

        when(useCase.save(any())).thenReturn(TransferFactory.createTransferResponse());
        var request= TransferFactory.createTransferDTORequest();
        mvc.perform(post("/transfers").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.feeAmount").value(BigDecimal.ONE));
    }
}
