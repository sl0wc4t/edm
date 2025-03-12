package edm.service;

import edm.model.entity.Division;
import edm.repository.DivisionRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionServiceTest {

    @Mock
    DivisionRepository divisionRepository;

    DivisionService divisionService;

    AutoCloseable mockito;

    @BeforeMethod
    public void init() {

        mockito = MockitoAnnotations.openMocks(this);

        divisionService = new DivisionService(
                divisionRepository
        );
    }

    @AfterMethod
    public void finish() throws Exception {

        verifyNoMoreInteractions(
                divisionRepository
        );

        mockito.close();
    }

    @Test
    public void getAll_test() {

    }

    @Test
    public void getById_test() {

        when(divisionRepository.findById(any()))
                .thenReturn(Optional.ofNullable(Division.builder().build()));


        divisionService.getById(1L);


        verify(divisionRepository, times(1))
                .findById(any());
    }

    @Test
    public void save_test() {

    }

    @Test
    public void update_test() {

    }

    @Test
    public void deleteById_test() {

    }
}
