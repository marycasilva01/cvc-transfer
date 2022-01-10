package com.cvc.test.transfer.domain.usecase;

import com.cvc.test.transfer.domain.dataprovider.GetTransferByIdDataProvider;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransferByIdUseCase {

    private final GetTransferByIdDataProvider provider;

    public Optional<TransferResponse> execute(Long id){
        return provider.get(id);
    }
}
