package edm.service;

import edm.model.dto.EmployeeDto;
import edm.model.entity.Employee;
import edm.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static edm.repository.EmployeeRepository.*;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public Page<EmployeeDto> getAll(Integer pageNumber, Integer pageSize, String field,
                                    Long id, String firstNamePart, String lastnamePart, String middlenamePart) {

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        Specification<Employee> criteria = defaultCriteria();
        if (id != null) {
            criteria = criteria.and(hasId(id));
        }
        if (firstNamePart != null) {
            criteria = criteria.and(firstnameStartsWith(firstNamePart));
        }
        if (lastnamePart != null) {
            criteria = criteria.and(lastnameStartsWith(lastnamePart));
        }
        if (middlenamePart != null) {
            criteria = criteria.and(middlenameStartsWith(middlenamePart));
        }

        return employeeRepository.findAll(criteria, pageRequest)
                .map(this::toDto);
    }

    public EmployeeDto getById(Long id) {

        return toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Employee found with id = " + id)));
    }

    public EmployeeDto save(EmployeeDto employeeDto) {

        return toDto(employeeRepository.save(toEntity(employeeDto)));
    }

    public EmployeeDto update(EmployeeDto patch) {

        EmployeeDto employeeDto = getById(patch.getId());
        employeeDto.setFirstname(patch.getFirstname());
        employeeDto.setLastname(patch.getLastname());
        employeeDto.setMiddlename(patch.getMiddlename());

        return save(employeeDto);
    }

    public void deleteById(Long id) {

        employeeRepository.deleteById(id);
    }

    private EmployeeDto toDto(Employee employee) {

        return EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .middlename(employee.getMiddlename())
                .build();
    }

    private Employee toEntity(EmployeeDto employeeDto) {

        return Employee.builder()
                .id(employeeDto.getId())
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .middlename(employeeDto.getMiddlename())
                .build();
    }

}
