package com.cvc.test.transfer.domain.dataprovider;

import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;

public interface SaveTransferDataProvider {

   TransferResponse save(TransferDTO transfer);

}
