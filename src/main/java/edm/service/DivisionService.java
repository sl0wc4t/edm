package edm.service;

import edm.model.dto.DivisionDto;
import edm.model.entity.Division;
import edm.repository.DivisionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DivisionService {

    private DivisionRepository divisionRepository;

    public Page<DivisionDto> getAll(Integer pageNumber, Integer pageSize, String field,
                                    Long id, String namePart, String contactDetailsPart, String headPart) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        Specification<Division> specification = DivisionRepository.defaultCriteria();
        if (id != null) {
            specification = specification.and(DivisionRepository.hasId(id));
        }
        if (namePart != null) {
            specification = specification.and(DivisionRepository.nameStartsWith(namePart));
        }
        if (contactDetailsPart != null) {
            specification = specification.and(DivisionRepository.contactDetailsStartsWith(contactDetailsPart));
        }
        if (headPart != null) {
            specification = specification.and(DivisionRepository.headStartsWith(headPart));
        }
        return divisionRepository.findAll(specification, pageRequest)
                .map(this::toDto);
    }

    public DivisionDto getById(Long id) {
        return toDto(divisionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Division found with id = " + id)));
    }

    public DivisionDto save(DivisionDto divisionDto) {
        return toDto(divisionRepository.save(toEntity(divisionDto)));
    }

    public DivisionDto update(DivisionDto patch) {
        DivisionDto divisionDto = getById(patch.getId());
        divisionDto.setName(patch.getName());
        divisionDto.setContactDetails(patch.getContactDetails());
        divisionDto.setHead(patch.getHead());
        return save(divisionDto);
    }

    public void deleteById(Long id) {
        divisionRepository.deleteById(id);
    }

    private DivisionDto toDto(Division division) {
        return DivisionDto.builder()
                .id(division.getId())
                .name(division.getName())
                .contactDetails(division.getContactDetails())
                .head(division.getHead())
                .build();
    }

    private Division toEntity(DivisionDto divisionDto) {
        return Division.builder()
                .id(divisionDto.getId())
                .name(divisionDto.getName())
                .contactDetails(divisionDto.getContactDetails())
                .head(divisionDto.getHead())
                .build();
    }

}
