package com.cvc.test.transfer.entrypoint.rest.model;

import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransferResponse {

    @Schema(description = "Transfer Id")
    private Long id;

    @Schema(description = "Transfer Amount ")
    private BigDecimal amount;

    @Schema(description = "Transfer fee amount ")
    private BigDecimal feeAmount;

    @Schema(description = "Transfer type ")
    private TransferType type;

    @Schema(description = "Transfer created at")
    private LocalDate createdAt;

    @Schema(description = "Transfer at")
    private LocalDate transferAt;

    @Schema(description = "Transfer account origin ")
    private String accountOrigin;

    @Schema(description = "Transfer account destination ")
    private String accountDestination;

}
