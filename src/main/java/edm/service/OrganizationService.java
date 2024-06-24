package edm.service;

import edm.model.dto.OrganizationDto;
import edm.model.entity.Organization;
import edm.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    public List<OrganizationDto> getAll(Integer pageNumber, Integer pageSize, String field) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        return organizationRepository.findAll(pageRequest).stream()
                .map(this::toDto)
                .toList();
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
