package edm.controller;

import edm.model.dto.OrganizationDto;
import edm.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/organizations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrganizationController {

    OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<Page<OrganizationDto>> getAllOrganizations(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String physicalAddress,
            @RequestParam(required = false) String legalAddress,
            @RequestParam(required = false) String head) {

        return ResponseEntity.ok(organizationService.getAll(pageNumber, pageSize, sortBy, id, name, physicalAddress, legalAddress, head));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(organizationService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationDto> addNewOrganization(@Valid @RequestBody OrganizationDto organizationDto) {

        return new ResponseEntity<>(organizationService.save(organizationDto), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationDto> updateOrganization(@Valid @RequestBody OrganizationDto patch) {

        return ResponseEntity.ok(organizationService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizationById(@PathVariable("id") Long id) {

        organizationService.deleteById(id);
    }

}
