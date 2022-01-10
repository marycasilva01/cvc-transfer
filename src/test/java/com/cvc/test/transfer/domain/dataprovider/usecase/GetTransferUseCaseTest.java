package com.cvc.test.transfer.domain.dataprovider.usecase;

import com.cvc.test.transfer.domain.dataprovider.GetTransferDataProvider;
import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.GetTransferUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class GetTransferUseCaseTest {

    @Mock
    private GetTransferDataProvider getAllTransferDataProvider;

    @InjectMocks
    private GetTransferUseCase getTransferUseCase;

    @Test
    public void shouldReturnSuccessTransferById() {

        var pageRequest = PageRequest.of(0,20);
        var list = List.of(TransferFactory.createTransferResponse());

        Mockito.when(getAllTransferDataProvider.get(Pageable.ofSize(10))).thenReturn(new PageImpl<>(list, pageRequest, list.size()));

        var response = getTransferUseCase.execute(Pageable.ofSize(10));

        Assertions.assertTrue(response.getTotalElements() == 1);
    }

    @Test
    public void shouldReturnNotSuccessTransferById() {
        Mockito.when(getAllTransferDataProvider.get(Mockito.any())).thenReturn(Page.empty());

        var response = getTransferUseCase.execute(Pageable.ofSize(1));
        Assertions.assertTrue(response.isEmpty());
    }
}
