package br.com.carloscesargsf.candidatecase.controllers;

import br.com.carloscesargsf.candidatecase.controllers.base.BaseController;
import br.com.carloscesargsf.candidatecase.dtos.*;
import br.com.carloscesargsf.candidatecase.filters.CandidateFilter;
import br.com.carloscesargsf.candidatecase.filters.CreditCardFilter;
import br.com.carloscesargsf.candidatecase.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/candidates")
@Tag(name = "Candidate", description = "Candidate")
public class CandidateController extends BaseController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @Operation(summary = "Returns a list of candidates.",
            description = "Returns a list of candidates."
    )
    @ApiResponse(responseCode = "200", description = "Returned the list of candidates.")
    public ResponseEntity<PaginatedItemsDTO<CandidateListItemDTO>> findAll(@Valid CandidateFilter filters) {
        logCurrentMethodExecution(filters);

        return ResponseEntity.ok(candidateService.findAll(filters));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a candidate.",
            description = "Returns a candidate."
    )
    @ApiResponse(responseCode = "200", description = "Returned the candidate.")
    public ResponseEntity<CandidateDTO> findById(@Parameter(description = "ID of a candidate", required = true, example = "1", in = ParameterIn.PATH)
                                                 @PathVariable Long id) {
        logCurrentMethodExecution(id);

        return ResponseEntity.ok(candidateService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creates a candidate.",
            description = "Creates a candidate and return it."
    )
    @ApiResponse(responseCode = "201", description = "Candidate created.")
    public ResponseEntity<CandidateDTO> create(@RequestBody @Valid CandidateCreateDTO candidateCreateDTO) {
        logCurrentMethodExecution(candidateCreateDTO);

        CandidateDTO candidateDTO = candidateService.create(candidateCreateDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(candidateDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(candidateDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a candidate.",
            description = "Updates a candidate."
    )
    @ApiResponse(responseCode = "204", description = "Candidate updated.")
    public ResponseEntity update(@Parameter(description = "ID of the candidate to be updated.", required = true, example = "1", in = ParameterIn.PATH)
                                 @PathVariable Long id,
                                 @RequestBody @Valid CandidateUpdateDTO candidateUpdateDTO) {
        logCurrentMethodExecution(id, candidateUpdateDTO);

        candidateService.update(id, candidateUpdateDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a candidate.",
            description = "Deletes a candidate."
    )
    @ApiResponse(responseCode = "204", description = "Candidate deleted.")
    public ResponseEntity deleteById(@Parameter(description = "ID of the candidate to be deleted.", required = true, example = "1", in = ParameterIn.PATH)
                                     @PathVariable Long id) {
        logCurrentMethodExecution(id);

        candidateService.deleteByID(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/credit-cards")
    @Operation(summary = "Returns a list of candidate's credit cards.",
            description = "Returns a list of candidate's credit cards."
    )
    @ApiResponse(responseCode = "200", description = "Returned the list of candidate's credit cards.")
    public ResponseEntity<PaginatedItemsDTO<CreditCardListItemDTO>> findAllCreditCards(@Parameter(description = "ID of the candidate.", required = true, example = "1", in = ParameterIn.PATH)
                                                                                       @PathVariable Long id,
                                                                                       @Valid CreditCardFilter filters) {
        logCurrentMethodExecution(id);

        return ResponseEntity.ok(candidateService.findAllCreditCards(id, filters));
    }

    @GetMapping("/{id}/credit-cards/{creditCardId}")
    @Operation(summary = "Returns a credit card for a candidate.",
            description = "Returns a credit card for a candidate."
    )
    @ApiResponse(responseCode = "200", description = "Returned a credit cards for a candidate.")
    public ResponseEntity<CreditCardDTO> findCreditCardById(@Parameter(description = "ID of the candidate.", required = true, example = "1", in = ParameterIn.PATH)
                                                            @PathVariable Long id,
                                                            @Parameter(description = "ID of the candidate's credit card.", required = true, example = "1", in = ParameterIn.PATH)
                                                            @PathVariable Long creditCardId) {
        logCurrentMethodExecution(id, creditCardId);

        return ResponseEntity.ok(candidateService.findCreditCardById(id, creditCardId));
    }

    @PostMapping("/{id}/credit-cards")
    @Operation(summary = "Creates a credit card for a candidate.",
            description = "Creates a credit card for a candidate and return it."
    )
    @ApiResponse(responseCode = "201", description = "Credit card created.")
    public ResponseEntity<CreditCardDTO> createCreditCard(@Parameter(description = "ID of the candidate.", required = true, example = "1", in = ParameterIn.PATH)
                                                          @PathVariable Long id,
                                                          @RequestBody @Valid CandidateCreditCardCreateDTO candidateCreditCardCreateDTO) {
        logCurrentMethodExecution(candidateCreditCardCreateDTO);

        CreditCardDTO creditCardDTO = candidateService.createCreditCard(id, candidateCreditCardCreateDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/credit-cards/{creditCardId}")
                .buildAndExpand(creditCardDTO.getCandidateId(), creditCardDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(creditCardDTO);
    }

    @DeleteMapping("/{id}/credit-cards/{creditCardId}")
    @Operation(summary = "Deletes a candidate's credit card.",
            description = "Deletes a candidate's credit card."
    )
    @ApiResponse(responseCode = "204", description = "Credit card deleted.")
    public ResponseEntity deleteCreditCardById(@Parameter(description = "ID of the candidate.", required = true, example = "1", in = ParameterIn.PATH)
                                               @PathVariable Long id,
                                               @Parameter(description = "ID of the credit card to be deleted.", required = true, example = "1", in = ParameterIn.PATH)
                                               @PathVariable Long creditCardId) {
        logCurrentMethodExecution(id, creditCardId);

        candidateService.deleteCreditCardById(id, creditCardId);

        return ResponseEntity.noContent().build();
    }

}
