package edm.controller;

import edm.entity.Division;
import edm.service.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/divisions")
@AllArgsConstructor
public class DivisionController {

    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<Iterable<Division>> getAllDivisions() {
        return ResponseEntity.ok(divisionService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(divisionService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Division> addNewDivision(@RequestBody Division division) {
        return new ResponseEntity<>(divisionService.save(division), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Division> updateDivision(@RequestBody Division patch) {
        return ResponseEntity.ok(divisionService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDivisionById(@PathVariable("id") Long id) {
        divisionService.deleteById(id);
    }

}
