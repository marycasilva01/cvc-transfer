package com.cvc.test.transfer.dataprovider.mapper;

import com.cvc.test.transfer.dataprovider.model.Transfer;
import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TransferMapper {

    public Transfer convertDTOToModel(TransferDTO transferDTO){
        return Transfer.builder()
                .transferAt(transferDTO.getTransferAt())
                .createdAt(LocalDate.now())
                .amount(transferDTO.getAmount())
                .destination(transferDTO.getAccountDestination())
                .origin(transferDTO.getAccountOrigin())
                .build();
    }

    public TransferResponse convertModelToDTO(Transfer transfer){
        TransferResponse transferResponse =  new TransferResponse(
                transfer.getId(),
                transfer.getAmount(),
                transfer.getFeeAmount(),
                transfer.getType(),
                transfer.getCreatedAt(),
                transfer.getTransferAt(),
                transfer.getOrigin(),
                transfer.getDestination()
        );
        return transferResponse;
    }
}
