package com.cvc.test.dataprovider;

import com.cvc.test.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.common.exceptions.DateIntervalInvalidException;
import com.cvc.test.transfer.dataprovider.GetTransferByIdDataProviderImpl;
import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
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
