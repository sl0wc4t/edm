package edm.service;

import edm.model.dto.DivisionDto;
import edm.model.entity.Division;
import edm.repository.DivisionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DivisionService {

    private DivisionRepository divisionRepository;

    public List<DivisionDto> getAll() {
        return divisionRepository.findAll().stream()
                .map(this::toDto)
                .toList();
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
