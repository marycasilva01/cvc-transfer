package com.cvc.test.transfer.domain.dataprovider.model;

import com.cvc.test.transfer.dataprovider.model.Transfer;
import com.cvc.test.transfer.dataprovider.model.enums.TransferType;
import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public final class TransferFactory {

    private TransferFactory(){
    }

    public static TransferDTO createTransferDTORequest(){
        return new TransferDTO(BigDecimal.TEN, LocalDate.now().plusDays(2), "42342442", "423424421212");
    }

    public static Transfer createTransferDefaultAmount10SemId(){
        return new Transfer(null, BigDecimal.valueOf(10),null,LocalDate.now(), LocalDate.now().plusDays(2), "42342442", "423424421212",null);
    }

    public static TransferResponse createTransferResponse(){
        return new TransferResponse(new Random().nextLong(), BigDecimal.valueOf(10), BigDecimal.ONE, TransferType.A, LocalDate.now(), LocalDate.now().plusDays(2), "42342442", "423424421212");
    }

    public static Transfer createTransferComplete(){
        return new Transfer(new Random().nextLong(), BigDecimal.valueOf(10), BigDecimal.ONE,  LocalDate.now(), LocalDate.now().plusDays(2), "42342442", "423424421212", TransferType.A);
    }

    public static Transfer createTransferDefaultAmount10(){
        return new Transfer(new Random().nextLong(),BigDecimal.valueOf(10),null,LocalDate.now(),LocalDate.now().plusDays(2), "42342442", "423424421212",null);
    }

    public static TransferDTO createTransferSameOriginAndDestination(){
        return new TransferDTO(BigDecimal.valueOf(10), LocalDate.now().plusDays(2),"42342442", "42342442");
    }

    public static Transfer createTransferDate(LocalDate transferDate, Integer interval){
        return new Transfer(new Random().nextLong(),BigDecimal.valueOf(10),null,transferDate,transferDate.plusDays(interval), "42342442", "423424421212",null);
    }

    public static TransferDTO createTransferDTODate(LocalDate transferDate, Integer interval){
        return new TransferDTO(BigDecimal.valueOf(10), transferDate.plusDays(interval), "42342442", "423424421212");
    }

    public static Transfer createTransferDefaultAmount10IntervalDate(Integer differenceDate){
        LocalDate today = LocalDate.now();
        return new Transfer(new Random().nextLong(),BigDecimal.valueOf(10),null,today,today.plusDays(differenceDate),"42342442", "423424421212",null);
    }

    public static Transfer createTransferIntervalDate(Integer differenceDate, BigDecimal amount){
        LocalDate today = LocalDate.now();
        return new Transfer(new Random().nextLong(),amount,null,today,today.plusDays(differenceDate),"42342442", "423424421212",null);
    }
}
