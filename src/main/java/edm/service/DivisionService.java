package edm.service;

import edm.entity.Division;
import edm.repository.DivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DivisionService {

    private DivisionRepository divisionRepository;

    public Iterable<Division> getAll() {
        return divisionRepository.findAll();
    }

    public Optional<Division> getById(Long id) {
        return divisionRepository.findById(id);
    }

    public Division save(Division division) {
        return divisionRepository.save(division);
    }

    public void deleteById(Long id) {
        divisionRepository.deleteById(id);
    }

}
