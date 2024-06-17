package edm.controller;

import edm.entity.Division;
import edm.service.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/divisions")
@AllArgsConstructor
public class DivisionController {

    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<Iterable<Division>> getAll() {
        return new ResponseEntity<>(divisionService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Division> getById(@PathVariable("id") Long id) {
        return divisionService.getById(id)
                .map(division -> ResponseEntity.ok(division))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Division> add(@RequestBody Division division) {
        return new ResponseEntity<>(divisionService.save(division), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        divisionService.deleteById(id);
    }

    @PatchMapping(consumes = "application/json")
    public ResponseEntity<Division> update(@RequestBody Division patch) {
        return divisionService.getById(patch.getId())
                .map(division -> {
                    division.setName(patch.getName());
                    division.setContactDetails(patch.getContactDetails());
                    division.setHead(patch.getHead());
                    return ResponseEntity.ok(divisionService.save(division));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
