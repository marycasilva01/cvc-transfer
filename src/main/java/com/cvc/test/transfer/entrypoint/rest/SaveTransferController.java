package com.cvc.test.transfer.entrypoint.rest;


import com.cvc.test.transfer.domain.usecase.SaveTransferUseCase;
import com.cvc.test.transfer.domain.dataprovider.dto.TransferDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transfers")
public class SaveTransferController {

    private final SaveTransferUseCase saveTransferUseCase;

    SaveTransferController(SaveTransferUseCase saveTransferUseCase){
        this.saveTransferUseCase = saveTransferUseCase;
    }

    @Operation(summary = "Create a new schedule transfer")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TransferResponse.class)))
            })
    @PostMapping
    public ResponseEntity<TransferResponse> save(@RequestBody TransferDTO transferDTO){
        var saved = saveTransferUseCase.save(transferDTO);
        return ResponseEntity.created(URI.create(String.format("/transfers/%s", saved.getId()))).body(saved);
    }

}
