package edm.controller;

import edm.model.dto.OrganizationDto;
import edm.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAll());
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
