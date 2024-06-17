package edm.service;

import edm.entity.Organization;
import edm.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    public Iterable<Organization> getAll() {
        return organizationRepository.findAll();
    }

    public Organization getById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Organization found with id = " + id));
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization update(Organization patch) {
        Organization organization = getById(patch.getId());
        organization.setName(patch.getName());
        organization.setPhysicalAddress(patch.getPhysicalAddress());
        organization.setLegalAddress(patch.getLegalAddress());
        organization.setHead(patch.getHead());
        return save(organization);
    }

    public void deleteById(Long id) {
        organizationRepository.deleteById(id);
    }

}
