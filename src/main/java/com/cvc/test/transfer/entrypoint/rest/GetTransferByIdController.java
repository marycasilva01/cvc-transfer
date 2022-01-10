package com.cvc.test.transfer.entrypoint.rest;

import com.cvc.test.transfer.domain.usecase.GetTransferByIdUseCase;
import com.cvc.test.transfer.entrypoint.exception.ExceptionDTO;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class GetTransferByIdController {

    private final GetTransferByIdUseCase transferByIdUseCase;

    @Operation(summary = "Get one by id transfer")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TransferResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDTO.class)))
            })
    @GetMapping("/{id}")
    ResponseEntity<TransferResponse> get(@PathVariable("id") Long id){
        var transfer = transferByIdUseCase.execute(id);

        if(transfer.isPresent()){
            return ResponseEntity.ok(transfer.get());
        }
        return ResponseEntity.notFound().build();
    }
}
