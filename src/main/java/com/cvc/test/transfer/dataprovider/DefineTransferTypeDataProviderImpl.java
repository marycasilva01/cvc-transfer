package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.domain.dataprovider.DefineTransferTypeDataProvider;
import com.cvc.test.transfer.common.exceptions.DateIntervalInvalidException;
import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class DefineTransferTypeDataProviderImpl implements DefineTransferTypeDataProvider {

    public TransferType define(LocalDate transferDate){
        if(transferDate.isBefore(LocalDate.now())){
            throw new DateIntervalInvalidException("Invalid Date");
        }
        if(transferDate.isEqual(LocalDate.now())){
            return TransferType.A;
        }
        if(Duration.between(LocalDate.now().atStartOfDay(), transferDate.atStartOfDay()).toDays() <= 10){
            return TransferType.B;
        }
        if(Duration.between(LocalDate.now().atStartOfDay(), transferDate.atStartOfDay()).toDays() > 10){
            return TransferType.C;
        }
        throw new DateIntervalInvalidException("Invalid Date interval");
    }
}
