package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GetTransferDataProviderImplTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private GetTransferDataProviderImpl getAllTransferDataProviderImpl;

    @Mock
    private TransferMapper mapper;

    @Test
    public void shouldReturnNotSuccessTransferById() {
        Mockito.when(transferRepository.findAll(Pageable.ofSize(10))).thenReturn(Page.empty());
        Mockito.when(mapper.convertModelToDTO(Mockito.any())).thenReturn(TransferFactory.createTransferResponse());
        var response = getAllTransferDataProviderImpl.get(Pageable.ofSize(10));
        Assertions.assertNotNull(response);
    }
}
