package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OperateurMockTest {
    @InjectMocks
    private OperatorServiceImpl operateurService;

    @Mock
    private OperatorRepository operateurRepository;



    @Test
    public void testRetrieveAllOperateurs() {
        List<Operator> operateurList = new ArrayList<>();
        operateurList.add(new Operator());
        operateurList.add(new Operator());
        operateurList.add(new Operator());
        operateurList.add(new Operator());

        when(operateurRepository.findAll()).thenReturn(operateurList);

        List<Operator> result = operateurService.retrieveAllOperators();

        assertEquals(4, result.size());
    }

    @Test
    public void testAddOperateur() {
        Operator operateur = new Operator();
        operateur.setFname("raed");
        operateur.setLname("sdiri");
        operateur.setPassword("0");
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operator result = operateurService.addOperator(operateur);

        assertEquals(operateur, result);
        assertEquals("raed", result.getFname());
        assertEquals("sdiri", result.getLname());
        assertEquals("0", result.getPassword());
    }

    @Test
    public void testDeleteOperateur() {
        Long IdToDelete = 1L;

        operateurService.deleteOperator(IdToDelete);

        verify(operateurRepository).deleteById(IdToDelete);
    }

    @Test
    public void testUpdateOperateur() {
        Operator operateur = new Operator();
        operateur.setIdOperateur(1L);
        operateur.setFname("raed");
        operateur.setLname("sdiri");
        operateur.setPassword("00");

        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operator result = operateurService.updateOperator(operateur);

        assertNotNull(result);
        assertEquals("raed", result.getFname());
        assertEquals("sdiri", result.getLname());
        assertEquals("00", result.getPassword());
    }
    @Test
    public void testRetrieveOperateur() {
        Long id = 1L;

        Operator operateur = new Operator();
        operateur.setIdOperateur(1L);
        operateur.setFname("raedd");
        operateur.setLname("sdirii");
        operateur.setPassword("000");

        when(operateurRepository.findById(id)).thenReturn(java.util.Optional.of(operateur));

        Operator result = operateurService.retrieveOperator(id);

        assertEquals(operateur, result);

        assertEquals("raedd", result.getFname());
        assertEquals("sdirii", result.getLname());
        assertEquals("000", result.getPassword());
    }

}
