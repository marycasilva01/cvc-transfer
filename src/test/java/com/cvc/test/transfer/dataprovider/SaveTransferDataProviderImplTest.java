package com.cvc.test.dataprovider;

import com.cvc.test.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.common.exceptions.DateIntervalInvalidException;
import com.cvc.test.transfer.common.exceptions.UndefinedFeeException;
import com.cvc.test.transfer.dataprovider.SaveTransferDataProviderImpl;
import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import com.cvc.test.transfer.domain.dataprovider.CalculateFeeDataProvider;
import com.cvc.test.transfer.domain.dataprovider.DefineTransferTypeDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class SaveTransferDataProviderImplTest {

    @Mock
    private DefineTransferTypeDataProvider defineTransferTypeDataProvider;

    @Mock
    private CalculateFeeDataProvider calculateFeeDataProvider;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private SaveTransferDataProviderImpl saveTransferDataProviderImpl;

    @Mock
    private TransferMapper mapper;

    @BeforeEach
    void setUp(){
        Mockito.when(mapper.convertModelToDTO(Mockito.any())).thenReturn(TransferFactory.createTransferResponse());
    }

    @Test
    public void test_save_with_past_date() {
        var currentDate = LocalDate.now();
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(TransferFactory.createTransferComplete());
        var transfer = TransferFactory.createTransferDTODate(currentDate.minusDays(1),0);
        Mockito.when(defineTransferTypeDataProvider.define(Mockito.any())).thenThrow(DateIntervalInvalidException.class);
        Assertions.assertThrows(DateIntervalInvalidException.class, () -> saveTransferDataProviderImpl.save(transfer));
    }

    @Test
    public void scheduleTestInvalidFee() {
        var transfer = TransferFactory.createTransferDate(LocalDate.now(),0);
        var dto =  TransferFactory.createTransferDTODate(LocalDate.now(),0);
        var currentDate = LocalDate.now();
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(transfer);
        Mockito.when(defineTransferTypeDataProvider.define(currentDate)).thenReturn(TransferType.A);
        Mockito.when(calculateFeeDataProvider.execute(transfer)).thenThrow(UndefinedFeeException.class);
        Assertions.assertThrows(UndefinedFeeException.class, () -> saveTransferDataProviderImpl.save(dto));
    }

    @Test
    public void scheduleCaseA() {
        var currentDate = LocalDate.now();
        var dto = TransferFactory.createTransferDTODate(currentDate,0);
        var transfer = TransferFactory.createTransferDate(currentDate,0);
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(transfer);
        Mockito.when(defineTransferTypeDataProvider.define(currentDate)).thenReturn(TransferType.A);
        Mockito.when(calculateFeeDataProvider.execute(transfer)).thenReturn(BigDecimal.valueOf(3.30).setScale(2));
        Mockito.when(transferRepository.save(transfer)).thenReturn(transfer);

        var persistedTransfer = saveTransferDataProviderImpl.save(dto);
        Assertions.assertEquals(BigDecimal.valueOf(3.30).setScale(2),persistedTransfer.getFeeAmount());
        Assertions.assertEquals(TransferType.A, persistedTransfer.getType());
    }

    @Test
    public void scheduleCaseB() {
        var currentDate = LocalDate.now().plusDays(1);
        var dto = TransferFactory.createTransferDTODate(currentDate,9);
        var transfer = TransferFactory.createTransferDate(currentDate,9);
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(transfer);
        Mockito.when(defineTransferTypeDataProvider.define(currentDate)).thenReturn(TransferType.B);
        Mockito.when(calculateFeeDataProvider.execute(transfer)).thenReturn(BigDecimal.valueOf(108));
        Mockito.when(transferRepository.save(transfer)).thenReturn(transfer);

        var persistedTransfer = saveTransferDataProviderImpl.save(dto);
        Assertions.assertEquals(BigDecimal.valueOf(1),persistedTransfer.getFeeAmount());
        Assertions.assertEquals(TransferType.A,persistedTransfer.getType());
    }

    @Test
    public void scheduleCaseBSameDate() {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        var dto = TransferFactory.createTransferDTODate(currentDate,0);
        var transfer = TransferFactory.createTransferDate(currentDate,0);
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(transfer);
        Mockito.when(defineTransferTypeDataProvider.define(currentDate)).thenReturn(TransferType.B);
        Mockito.when(calculateFeeDataProvider.execute(transfer)).thenReturn(BigDecimal.valueOf(12));
        Mockito.when(transferRepository.save(transfer)).thenReturn(transfer);

        var persistedTransfer = saveTransferDataProviderImpl.save(dto);
        Assertions.assertEquals(BigDecimal.valueOf(1),persistedTransfer.getFeeAmount());
        Assertions.assertEquals(TransferType.A,persistedTransfer.getType());
    }

    @Test
    public void scheduleCaseC() {
        var currentDate = LocalDate.now();
        var dto = TransferFactory.createTransferDTODate(currentDate,15);
        var transfer = TransferFactory.createTransferDate(currentDate,15);
        Mockito.when(mapper.convertDTOToModel(Mockito.any())).thenReturn(transfer);
        Mockito.when(defineTransferTypeDataProvider.define(currentDate)).thenReturn(TransferType.C);
        Mockito.when(calculateFeeDataProvider.execute(transfer)).thenReturn(BigDecimal.valueOf(2));
        Mockito.when(transferRepository.save(transfer)).thenReturn(transfer);

        var persistedTransfer = saveTransferDataProviderImpl.save(dto);
        Assertions.assertEquals(BigDecimal.valueOf(1),persistedTransfer.getFeeAmount());
        Assertions.assertEquals(TransferType.A,persistedTransfer.getType());
    }
}
