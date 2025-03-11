package edm.service;

import edm.model.dto.OrganizationDto;
import edm.model.entity.Organization;
import edm.repository.OrganizationRepository;
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

import static edm.repository.OrganizationRepository.*;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrganizationService {

    OrganizationRepository organizationRepository;

    public Page<OrganizationDto> getAll(Integer pageNumber,
                                        Integer pageSize,
                                        String field,
                                        Long id,
                                        String namePart,
                                        String physicalAddressPart,
                                        String legalAddressPart,
                                        String headPart) {

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        Specification<Organization> criteria = defaultCriteria();
        if (id != null) {
            criteria = criteria.and(hasId(id));
        }
        if (namePart != null) {
            criteria = criteria.and(nameStartsWith(namePart));
        }
        if (physicalAddressPart != null) {
            criteria = criteria.and(physicalAddressStartsWith(physicalAddressPart));
        }
        if (legalAddressPart != null) {
            criteria = criteria.and(legalAddressStartsWith(legalAddressPart));
        }
        if (headPart != null) {
            criteria = criteria.and(headStartsWith(headPart));
        }

        return organizationRepository.findAll(criteria, pageRequest)
                .map(this::toDto);
    }

    public OrganizationDto getById(Long id) {

        return toDto(organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Organization found with id = " + id)));
    }

    public OrganizationDto save(OrganizationDto organizationDto) {

        return toDto(organizationRepository.save(toEntity(organizationDto)));
    }

    public OrganizationDto update(OrganizationDto patch) {

        OrganizationDto organizationDto = getById(patch.getId());
        organizationDto.setName(patch.getName());
        organizationDto.setPhysicalAddress(patch.getPhysicalAddress());
        organizationDto.setLegalAddress(patch.getLegalAddress());
        organizationDto.setHead(patch.getHead());

        return save(organizationDto);
    }

    public void deleteById(Long id) {

        organizationRepository.deleteById(id);
    }

    private OrganizationDto toDto(Organization organization) {

        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .physicalAddress(organization.getPhysicalAddress())
                .legalAddress(organization.getLegalAddress())
                .head(organization.getHead())
                .build();
    }

    private Organization toEntity(OrganizationDto organizationDto) {

        return Organization.builder()
                .id(organizationDto.getId())
                .name(organizationDto.getName())
                .physicalAddress(organizationDto.getPhysicalAddress())
                .legalAddress(organizationDto.getLegalAddress())
                .head(organizationDto.getHead())
                .build();
    }

}
