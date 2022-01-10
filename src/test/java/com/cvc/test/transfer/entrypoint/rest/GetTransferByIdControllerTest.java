package com.cvc.test.transfer.entrypoint.rest;

import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.GetTransferByIdUseCase;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetTransferByIdController.class)
class GetTransferByIdControllerTest {

    @MockBean
    private GetTransferByIdUseCase useCase;

    @Autowired MockMvc mvc;

    private static Long ID = 1l;

    @Test
    void test_get_transfer() throws Exception {
        var transfer = Optional.of(TransferFactory.createTransferResponse());

        when(useCase.execute(ID)).thenReturn(transfer);

        mvc.perform(get("/transfers/{id}", ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void test_get_transfer_empty() throws Exception {
        when(useCase.execute(ID)).thenReturn(Optional.empty());

        mvc.perform(get("/transfers/{id}", ID))
                .andExpect(status().isNotFound());
    }
}
