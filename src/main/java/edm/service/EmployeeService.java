package edm.service;

import edm.entity.Employee;
import edm.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public Iterable<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Employee found with id = " + id));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee patch) {
        Employee employee = getById(patch.getId());
        employee.setFirstname(patch.getFirstname());
        employee.setLastname(patch.getLastname());
        employee.setMiddlename(patch.getMiddlename());
        return save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
