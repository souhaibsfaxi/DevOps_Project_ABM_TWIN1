package tn.esprit.devops_project;


import lombok.extern.slf4j.Slf4j;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OperateurJunitTest {


    @Autowired
    OperatorServiceImpl operateurServiceImpl;

    @Test
    public void testRetrieveAllOperateurs() {
        List<Operator> operateurList = operateurServiceImpl.retrieveAllOperators();
        int expected= operateurList.size();
        Operator newOperateur = new Operator();
        newOperateur.setFname("Raed1");
        newOperateur.setLname("Sdiri");
        newOperateur.setPassword("1");
        operateurServiceImpl.addOperator(newOperateur);
        assertEquals(expected+1,operateurServiceImpl.retrieveAllOperators().size());


    }
    @Test
    public void testAddOperateur() {

        Operator newOperateur = new Operator();
        newOperateur.setFname("Raed1");
        newOperateur.setLname("Sdiri");
        newOperateur.setPassword("1");

         operateurServiceImpl.addOperator(newOperateur);

        assertNotNull(newOperateur.getIdOperateur());
        assertNotNull(newOperateur.getLname());
        operateurServiceImpl.deleteOperator(newOperateur.getIdOperateur());



    }

    @Test
    public void testDeleteOperateur() {
        // Create a new Operateur object
        Operator newOperateur = new Operator();
        newOperateur.setFname("Raed1");
        newOperateur.setLname("Sdiri1");
        newOperateur.setPassword("2");
        operateurServiceImpl.addOperator(newOperateur);

        assertEquals("Raed1",newOperateur.getFname());

        operateurServiceImpl.deleteOperator(newOperateur.getIdOperateur());
        assertNull(operateurServiceImpl.retrieveOperator(newOperateur.getIdOperateur()));
    }


    @Test
    public void testUpdateOperateur() {

        Operator newOperateur = new Operator();
        newOperateur.setFname("Raed2");
        newOperateur.setLname("Sdiri2");
        newOperateur.setPassword("3");

        operateurServiceImpl.addOperator(newOperateur);
        Operator operateurs = operateurServiceImpl.retrieveOperator(newOperateur.getIdOperateur());
        operateurs.setLname("Sdiriii");
        operateurServiceImpl.updateOperator(operateurs);

        assertEquals("Sdiriii", operateurs.getLname());
    }
    @Test
    public void testRetrieveOperateur() {

        Operator newOperateur = new Operator();
        newOperateur.setFname("Raed3");
        newOperateur.setLname("Sdiri3");
        newOperateur.setPassword("4");

        operateurServiceImpl.addOperator(newOperateur);
        Operator op=operateurServiceImpl.retrieveOperator(newOperateur.getIdOperateur());


        assertEquals("Sdiri3", op.getLname());
    }
}


