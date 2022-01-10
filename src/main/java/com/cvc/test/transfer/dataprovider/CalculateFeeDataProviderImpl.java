package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.dataprovider.model.Transfer;
import com.cvc.test.transfer.common.exceptions.UndefinedFeeException;
import com.cvc.test.transfer.domain.dataprovider.CalculateFeeDataProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

@Service
public class CalculateFeeDataProviderImpl implements CalculateFeeDataProvider {

    public BigDecimal execute(Transfer transfer){
        long scheduleAndTransferDaysDifference = Duration.between(transfer.getCreatedAt().atStartOfDay(), transfer.getTransferAt().atStartOfDay()).toDays();

        if (Objects.nonNull(transfer.getType())) {
            switch (transfer.getType()) {
                case A:
                    return BigDecimal.valueOf(3L).add(transfer.getAmount().multiply(BigDecimal.valueOf(0.03)));
                case B:
                    if(scheduleAndTransferDaysDifference == 0){
                        return BigDecimal.valueOf(12L);
                    }
                    return BigDecimal.valueOf(12L).multiply(BigDecimal.valueOf(scheduleAndTransferDaysDifference));
                case C:
                    if (scheduleAndTransferDaysDifference > 10 && scheduleAndTransferDaysDifference <= 20) {
                        return transfer.getAmount().multiply(BigDecimal.valueOf(0.08));
                    }
                    if (scheduleAndTransferDaysDifference > 20 && scheduleAndTransferDaysDifference <= 30) {
                        return transfer.getAmount().multiply(BigDecimal.valueOf(0.06));
                    }
                    if (scheduleAndTransferDaysDifference > 30 && scheduleAndTransferDaysDifference <= 40) {
                        return transfer.getAmount().multiply(BigDecimal.valueOf(0.04));
                    }
                    if (scheduleAndTransferDaysDifference > 40 && transfer.getAmount().longValue() > 100000) {
                        return transfer.getAmount().multiply(BigDecimal.valueOf(0.02));
                    }
            }
        }
        throw new UndefinedFeeException("Undefined fee");
    }
}
