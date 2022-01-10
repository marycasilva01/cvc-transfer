package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.common.exceptions.DateIntervalInvalidException;
import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class DefineTransferTypeDataProviderImplTest {

    private DefineTransferTypeDataProviderImpl dataProvider;

    @BeforeEach
    void setUp() {
        dataProvider = new DefineTransferTypeDataProviderImpl();
    }

    @Test
    public void defineTaxDateIntervalInvalidException() {
        Assertions.assertThrows(DateIntervalInvalidException.class, () -> dataProvider.define(LocalDate.now().plusDays(-1)));
    }

    @Test
    public void defineTaxTypeA() {
        Assertions.assertEquals(TransferType.A, dataProvider.define(LocalDate.now()));
    }

    @Test
    public void defineTaxTypeB() {
        Assertions.assertEquals(TransferType.B, dataProvider.define(LocalDate.now().plusDays(1)));
    }

    @Test
    public void defineTaxTypeBLimit10() {
        Assertions.assertEquals(TransferType.B, dataProvider.define(LocalDate.now().plusDays(10)));
    }

    @Test
    public void defineTaxTypeC() {
        Assertions.assertEquals(TransferType.C, dataProvider.define(LocalDate.now().plusDays(11)));
    }
}
