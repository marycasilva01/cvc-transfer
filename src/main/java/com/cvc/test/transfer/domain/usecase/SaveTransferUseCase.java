package com.cvc.test.transfer.domain.usecase;

import com.cvc.test.transfer.common.exceptions.InvalidTransferException;
import com.cvc.test.transfer.domain.dataprovider.SaveTransferDataProvider;
import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTransferUseCase {

    private final SaveTransferDataProvider provider;

    public TransferResponse save(TransferDTO transfer) {
        checkIfOriginDifferentFromDestination(transfer);

        return provider.save(transfer);
    }

    private void checkIfOriginDifferentFromDestination(TransferDTO transfer){
        if(transfer.getAccountOrigin().equals(transfer.getAccountDestination())) {
            throw new InvalidTransferException("Account origin and destination cannot be the same");
        }
    }

}
