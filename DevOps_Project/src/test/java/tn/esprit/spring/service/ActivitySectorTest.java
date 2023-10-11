package tn.esprit.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.ActivitySectorImpl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivitySectorTest {

    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    private ActivitySectorImpl activitySectorImpl;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }
    @Test
    public void testRetrieveAllActivitySectors() {

        List<ActivitySector> activitySectors = new ArrayList<>();
        activitySectors.add(new ActivitySector(1L,"dd", "de"));
        //activitySectors.add(new ActivitySector());

        Mockito.when(activitySectorRepository.findAll()).thenReturn(activitySectors);

        List<ActivitySector> result = activitySectorImpl.retrieveAllActivitySectors();

        assertEquals(activitySectors, result);

    }

    @Test
    public void testAddActivitySector() {
        ActivitySector newActivitySector = new ActivitySector(1L, "New Sector", "ff");

        Mockito.when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(newActivitySector);

        ActivitySector savedActivitySector = activitySectorImpl.addActivitySector(newActivitySector);

        assertEquals(newActivitySector, savedActivitySector);
    }

    @Test
    public void testDeleteActivitySector() {
        Long activitySectorId = 1L;

        activitySectorImpl.deleteActivitySector(activitySectorId);

        verify(activitySectorRepository).deleteById(activitySectorId);
    }

    @Test
    public void testUpdateActivitySector() {
        ActivitySector updatedActivitySector = new ActivitySector(1L, "Updated Sector", "ff");

        Mockito.when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(updatedActivitySector);

        ActivitySector result = activitySectorImpl.updateActivitySector(updatedActivitySector);

        assertEquals(updatedActivitySector, result);
    }

    @Test
    public void testRetrieveActivitySector() {
        Long activitySectorId = 1L;
        ActivitySector activitySector = new ActivitySector(activitySectorId, "Sector 1", "secteur 3");

        Mockito.when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.of(activitySector));

        ActivitySector result = activitySectorImpl.retrieveActivitySector(activitySectorId);

        assertEquals(activitySector, result);
    }
}






