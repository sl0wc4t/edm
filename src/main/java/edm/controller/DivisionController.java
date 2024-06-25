package edm.controller;

import edm.model.dto.DivisionDto;
import edm.service.DivisionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<DivisionDto>> getAllDivisions(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy) {
        return ResponseEntity.ok(divisionService.getAll(pageNumber, pageSize, sortBy));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DivisionDto> getDivisionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(divisionService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DivisionDto> addNewDivision(@Valid @RequestBody DivisionDto divisionDto) {
        return new ResponseEntity<>(divisionService.save(divisionDto), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DivisionDto> updateDivision(@Valid @RequestBody DivisionDto patch) {
        return ResponseEntity.ok(divisionService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDivisionById(@PathVariable("id") Long id) {
        divisionService.deleteById(id);
    }

}
