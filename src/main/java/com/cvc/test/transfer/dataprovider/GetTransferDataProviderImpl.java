package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import com.cvc.test.transfer.domain.dataprovider.GetTransferDataProvider;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransferDataProviderImpl implements GetTransferDataProvider {

    private final TransferRepository transferRepository;
    private final TransferMapper mapper;

    @Override
    public Page<TransferResponse> get(Pageable pageable) {
        return transferRepository.findAll(pageable).map(transfer -> {
            var dto = mapper.convertModelToDTO(transfer);
            return dto;
        });
    }


}
