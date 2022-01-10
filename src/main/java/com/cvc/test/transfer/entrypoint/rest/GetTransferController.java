package com.cvc.test.transfer.entrypoint.rest;

import com.cvc.test.transfer.domain.usecase.GetTransferUseCase;
import com.cvc.test.transfer.entrypoint.rest.model.TransferResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class GetTransferController {

    private final GetTransferUseCase getTransferUseCase;

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)))
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<TransferResponse>> get(@RequestParam(value = "page",defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "20") int size){
        
        var listSchedules = getTransferUseCase.execute(PageRequest.of(page, size));

        if(listSchedules != null && !listSchedules.isEmpty()){
            return ResponseEntity.ok(listSchedules);
        }
        return ResponseEntity.noContent().build();
    }

}
