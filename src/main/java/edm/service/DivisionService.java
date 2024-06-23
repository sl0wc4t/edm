package edm.service;

import edm.entity.Division;
import edm.repository.DivisionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DivisionService {

    private DivisionRepository divisionRepository;

    public Iterable<Division> getAll() {
        return divisionRepository.findAll();
    }

    public Division getById(Long id) {
        return divisionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Division found with id = " + id));
    }

    public Division save(Division division) {
        return divisionRepository.save(division);
    }

    public Division update(Division patch) {
        Division division = getById(patch.getId());
        division.setName(patch.getName());
        division.setContactDetails(patch.getContactDetails());
        division.setHead(patch.getHead());
        return save(division);
    }

    public void deleteById(Long id) {
        divisionRepository.deleteById(id);
    }

}
