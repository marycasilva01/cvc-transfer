package com.cvc.test.dataprovider;

import com.cvc.test.domain.dataprovider.model.TransferFactory;
import com.cvc.test.transfer.common.exceptions.UndefinedFeeException;
import com.cvc.test.transfer.dataprovider.CalculateFeeDataProviderImpl;
import com.cvc.test.transfer.dataprovider.model.Transfer;
import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
public class CalculateFeeDataProviderImplTest {

    private CalculateFeeDataProviderImpl feeDataProvider;

    @BeforeEach
    void setUp() {
        feeDataProvider = new CalculateFeeDataProviderImpl();
    }

    @Test
    public void calculateTaxAmountWithTransferTypeA() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10();
        transfer.setType(TransferType.A);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(3.30).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeB() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(9);
        transfer.setType(TransferType.B);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(108),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen10LessOrEqualThen20() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(15);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.80).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCIntervalEqual20() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(20);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.80).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen20LessOrEqualThen30() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(25);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.60).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCIntervalEqual30() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(30);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.60).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen30LessOrEqualThen40() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(35);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.40).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCIntervalEqual40() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(40);
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(0.40).setScale(2),taxAmount);
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen40AndLessThen100000() {
        Transfer transfer = TransferFactory.createTransferDefaultAmount10IntervalDate(41);
        transfer.setType(TransferType.C);
        Assertions.assertThrows(UndefinedFeeException.class, () -> feeDataProvider.execute(transfer));
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen40AndEqual100000() {
        Transfer transfer = TransferFactory.createTransferIntervalDate(41,BigDecimal.valueOf(100000));
        transfer.setType(TransferType.C);
        Assertions.assertThrows(UndefinedFeeException.class, () -> feeDataProvider.execute(transfer));
    }

    @Test
    public void calculateTaxAmountWithTransferTypeCGreaterThen40AndGreaterThen100000() {
        Transfer transfer = TransferFactory.createTransferIntervalDate(41,BigDecimal.valueOf(100001));
        transfer.setType(TransferType.C);
        BigDecimal taxAmount = feeDataProvider.execute(transfer);
        Assertions.assertEquals(BigDecimal.valueOf(2000.02).setScale(2),taxAmount);
    }
}
