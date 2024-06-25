package edm.controller;

import edm.model.dto.EmployeeDto;
import edm.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String middlename) {
        return ResponseEntity.ok(employeeService.getAll(pageNumber, pageSize, sortBy, id, firstname, lastname, middlename));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> addNewEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto patch) {
        return ResponseEntity.ok(employeeService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }

}
