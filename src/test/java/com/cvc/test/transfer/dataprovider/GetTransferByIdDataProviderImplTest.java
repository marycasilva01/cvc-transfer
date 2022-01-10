package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class GetTransferByIdDataProviderImplTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransferMapper mapper;

    @InjectMocks
    private GetTransferByIdDataProviderImpl getTransferByIdDataProviderImpl;


    @Test
    public void shouldReturnSuccessTransferById() {
        var transfer = TransferFactory.createTransferComplete();
        Mockito.when(transferRepository.getById(Mockito.any())).thenReturn(transfer);

        Mockito.when(mapper.convertModelToDTO(Mockito.any())).thenReturn(TransferFactory.createTransferResponse());

        var response = getTransferByIdDataProviderImpl.get(1L);
        Assertions.assertTrue(response.isPresent());
    }

    @Test
    public void shouldReturnNotSuccessTransferById() {
        Mockito.when(transferRepository.getById(Mockito.any())).thenReturn(null);

        var response = getTransferByIdDataProviderImpl.get(1L);
        Assertions.assertTrue(response.isEmpty());
    }
}
