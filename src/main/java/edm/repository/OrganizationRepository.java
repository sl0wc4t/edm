package edm.repository;

import edm.model.entity.Organization;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    static Specification<Organization> defaultCriteria() {

        return (organization, cq, cb) -> cb.isNotNull(organization.get("id"));
    }

    static Specification<Organization> hasId(Long id) {

        return (organization, cq, cb) -> cb.equal(organization.get("id"), id);
    }

    static Specification<Organization> nameStartsWith(String namePart) {

        return (organization, cq, cb) -> cb.like(organization.get("name"), namePart + "%");
    }

    static Specification<Organization> physicalAddressStartsWith(String physicalAddressPart) {

        return (organization, cq, cb) -> cb.like(organization.get("physicalAddress"), physicalAddressPart + "%");
    }

    static Specification<Organization> legalAddressStartsWith(String legalAddressPart) {

        return (organization, cq, cb) -> cb.like(organization.get("legalAddress"), legalAddressPart + "%");
    }

    static Specification<Organization> headStartsWith(String headPart) {

        return (organization, cq, cb) -> cb.like(organization.get("head"), headPart + "%");
    }

}
