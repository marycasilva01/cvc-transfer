package com.cvc.test.transfer.domain.dataprovider;

import com.cvc.test.transfer.dataprovider.model.enums.TransferType;

import java.time.LocalDate;

public interface DefineTransferTypeDataProvider {

     TransferType define(LocalDate transferDate);
}
