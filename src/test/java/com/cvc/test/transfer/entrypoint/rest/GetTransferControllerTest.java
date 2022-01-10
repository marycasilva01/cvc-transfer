package com.cvc.test.transfer.entrypoint.rest;

import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.GetTransferUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetTransferController.class)
class GetTransferControllerTest {

    @MockBean
    private GetTransferUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired MockMvc mvc;

    @Test
    void test_get_transfer() throws Exception {

        var pageRequest = PageRequest.of(0,20);
        var list = List.of(TransferFactory.createTransferResponse());

        when(useCase.execute(pageRequest)).thenReturn(new PageImpl<>(list, pageRequest, list.size()));

        mvc.perform(get("/transfers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content[*].id").isNotEmpty());
    }

    @Test
    void test_get_transfer_empty() throws Exception {
        var pageRequest = PageRequest.of(0,20);
        when(useCase.execute(pageRequest)).thenReturn(Page.empty());

        mvc.perform(get("/transfers"))
                .andExpect(status().isNoContent());
    }
}
