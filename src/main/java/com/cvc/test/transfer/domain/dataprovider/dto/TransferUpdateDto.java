package com.cvc.test.transfer.domain.dataprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class TransferUpdateDto {

    @NotNull
    private BigDecimal amount;

    private BigDecimal taxAmount;

    @FutureOrPresent
    private LocalDate scheduleDate;

}
