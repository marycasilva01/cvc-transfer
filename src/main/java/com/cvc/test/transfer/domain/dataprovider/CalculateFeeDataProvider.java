package com.cvc.test.transfer.domain.dataprovider;

import com.cvc.test.transfer.dataprovider.model.Transfer;

import java.math.BigDecimal;

public interface CalculateFeeDataProvider {

    BigDecimal execute(Transfer transfer);
}
