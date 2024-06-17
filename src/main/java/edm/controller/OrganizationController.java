package edm.controller;

import edm.entity.Organization;
import edm.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<Iterable<Organization>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(organizationService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> addNewOrganization(@RequestBody Organization organization) {
        return new ResponseEntity<>(organizationService.save(organization), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> updateOrganization(@RequestBody Organization patch) {
        return ResponseEntity.ok(organizationService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizationById(@PathVariable("id") Long id) {
        organizationService.deleteById(id);
    }

}
