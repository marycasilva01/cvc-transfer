package com.cvc.test.transfer.domain.dataprovider.dto;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {

    @NotNull
    private BigDecimal amount;

    @FutureOrPresent
    private LocalDate transferAt;

    @NotBlank
    private String accountOrigin;

    @NotBlank
    private String accountDestination;
}
