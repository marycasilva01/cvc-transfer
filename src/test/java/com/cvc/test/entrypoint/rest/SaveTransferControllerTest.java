package com.cvc.test.entrypoint.rest;

import com.cvc.test.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.SaveTransferUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SaveTransferControllerTest {

    @MockBean
    private SaveTransferUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_create_transfer_error(@Autowired MockMvc mvc) throws Exception {

        given(useCase.save(any())).willThrow(RuntimeException.class);
        var request= TransferFactory.createTransferDTORequest();
        mvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(request))).andExpect(status().is5xxServerError()).andExpect(content().contentType("application/json"));
    }

    @Test
    void test_create_transfer(@Autowired MockMvc mvc) throws Exception {

        given(useCase.save(any())).willReturn(TransferFactory.createTransferResponse());
        var request= TransferFactory.createTransferDTORequest();
        mvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.taxAmount").value(BigDecimal.ONE));
    }
}
