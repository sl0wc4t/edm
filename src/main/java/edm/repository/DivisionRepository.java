package edm.repository;

import edm.model.entity.Division;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DivisionRepository extends JpaRepository<Division, Long>, JpaSpecificationExecutor<Division> {

    static Specification<Division> defaultCriteria() {
        return (division, cq, cb) -> cb.isNotNull(division.get("id"));
    }

    static Specification<Division> hasId(Long id) {
        return (division, cq, cb) -> cb.equal(division.get("id"), id);
    }

    static Specification<Division> nameStartsWith(String namePart) {
        return (division, cq, cb) -> cb.like(division.get("name"), namePart + "%");
    }

    static Specification<Division> contactDetailsStartsWith(String contactDetailsPart) {
        return (division, cq, cb) -> cb.like(division.get("contactDetails"), contactDetailsPart + "%");
    }

    static Specification<Division> headStartsWith(String headPart) {
        return (division, cq, cb) -> cb.like(division.get("head"), headPart + "%");
    }

}
