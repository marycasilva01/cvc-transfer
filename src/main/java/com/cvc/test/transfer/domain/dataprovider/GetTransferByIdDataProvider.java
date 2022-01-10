package com.cvc.test.transfer.domain.dataprovider;

import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;

import java.util.Optional;

public interface GetTransferByIdDataProvider {

   Optional<TransferResponse> get(Long id);
}
