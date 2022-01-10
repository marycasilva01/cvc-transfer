package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import com.cvc.test.transfer.domain.dataprovider.GetTransferByIdDataProvider;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransferByIdDataProviderImpl implements GetTransferByIdDataProvider {

    private final TransferRepository transferRepository;
    private final TransferMapper mapper;

    @Override
    public Optional<TransferResponse> get(Long id) {
        var transfer = transferRepository.getById(id);
        if(Objects.isNull(transfer)){
            return Optional.empty();
        }
        return Optional.of(mapper.convertModelToDTO(transfer));
    }
}
