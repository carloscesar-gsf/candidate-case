package br.com.carloscesargsf.candidatecase.controllers;

import br.com.carloscesargsf.candidatecase.controllers.base.BaseController;
import br.com.carloscesargsf.candidatecase.dtos.CreditCardTypeListItemDTO;
import br.com.carloscesargsf.candidatecase.dtos.PaginatedItemsDTO;
import br.com.carloscesargsf.candidatecase.filters.CreditCardTypeFilter;
import br.com.carloscesargsf.candidatecase.services.CreditCardTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-card-types")
@Tag(name = "Credit Card Type", description = "Type of the credit card")
public class CreditCardTypeController extends BaseController {

    private final CreditCardTypeService creditCardTypeService;

    public CreditCardTypeController(CreditCardTypeService creditCardTypeService) {
        this.creditCardTypeService = creditCardTypeService;
    }

    @GetMapping
    @Operation(summary = "Returns a list of credit card types.",
            description = "Returns a list of credit card types.",
            security = {@SecurityRequirement(name = "basic")}
    )
    @ApiResponse(responseCode = "200", description = "Returns a list of credit card types.")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PaginatedItemsDTO<CreditCardTypeListItemDTO>> findAll(CreditCardTypeFilter filters) {
        logCurrentMethodExecution(filters);

        return new ResponseEntity<>(creditCardTypeService.findAll(filters), HttpStatus.OK);
    }

}
