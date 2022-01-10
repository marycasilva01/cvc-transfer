package com.cvc.test.transfer.dataprovider;

import com.cvc.test.transfer.dataprovider.mapper.TransferMapper;
import com.cvc.test.transfer.dataprovider.repository.TransferRepository;
import com.cvc.test.transfer.domain.dataprovider.CalculateFeeDataProvider;
import com.cvc.test.transfer.domain.dataprovider.SaveTransferDataProvider;
import com.cvc.test.transfer.domain.dataprovider.DefineTransferTypeDataProvider;
import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveTransferDataProviderImpl implements SaveTransferDataProvider {

    private final DefineTransferTypeDataProvider defineTransferTypeDataProvider;
    private final CalculateFeeDataProvider calculateFeeDataProvider;
    private final TransferRepository transferRepository;
    private final TransferMapper mapper;

    @Override
    @Transactional
    public TransferResponse save(TransferDTO dto) {
        var transfer = mapper.convertDTOToModel(dto);
        transfer.setType(defineTransferTypeDataProvider.define(transfer.getCreatedAt()));
        transfer.setFeeAmount(calculateFeeDataProvider.execute(transfer));

        var saved = transferRepository.save(transfer);
        return mapper.convertModelToDTO(saved);
    }
}
