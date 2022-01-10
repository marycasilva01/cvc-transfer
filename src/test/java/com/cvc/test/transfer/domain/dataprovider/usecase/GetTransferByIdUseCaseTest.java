package com.cvc.test.transfer.domain.dataprovider.usecase;

import com.cvc.test.transfer.domain.dataprovider.GetTransferByIdDataProvider;
import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.domain.usecase.GetTransferByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class GetTransferByIdUseCaseTest {

    @Mock
    private GetTransferByIdDataProvider getTransferByIdDataProvider;

    @InjectMocks
    private GetTransferByIdUseCase getTransferByIdUseCase;


    @Test
    public void shouldReturnSuccessTransferById() {
        var transfer = Optional.of(TransferFactory.createTransferResponse());
        Mockito.when(getTransferByIdDataProvider.get(Mockito.any())).thenReturn(transfer);

        var response = getTransferByIdUseCase.execute(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    public void shouldReturnNotSuccessTransferById() {
        Mockito.when(getTransferByIdDataProvider.get(Mockito.any())).thenReturn(Optional.empty());

        var response = getTransferByIdUseCase.execute(1L);
        Assertions.assertTrue(response.isEmpty());
    }
}
