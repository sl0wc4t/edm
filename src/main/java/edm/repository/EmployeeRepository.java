package edm.repository;

import edm.model.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    static Specification<Employee> defaultCriteria() {

        return (employee, cq, cb) -> cb.isNotNull(employee.get("id"));
    }

    static Specification<Employee> hasId(Long id) {

        return (employee, cq, cb) -> cb.equal(employee.get("id"), id);
    }

    static Specification<Employee> firstnameStartsWith(String firstnamePart) {

        return (employee, cq, cb) -> cb.like(employee.get("firstname"), firstnamePart + "%");
    }

    static Specification<Employee> lastnameStartsWith(String lastnamePart) {

        return (employee, cq, cb) -> cb.like(employee.get("lastname"), lastnamePart + "%");
    }

    static Specification<Employee> middlenameStartsWith(String middlenamePart) {

        return (employee, cq, cb) -> cb.like(employee.get("middlename"), middlenamePart + "%");
    }

}
