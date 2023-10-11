package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canRetrieveAllSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = new ArrayList<>();
        Mockito.when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        // Assert
        assertEquals(expectedSuppliers, actualSuppliers);
    }

    @Test
    public void canAddSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        Mockito.when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Assert
        assertEquals(supplier, addedSupplier);
    }

    @Test
    public void canUpdateSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        Mockito.when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        // Assert
        assertEquals(supplier, updatedSupplier);
    }

    @Test
    public void canDeleteSupplier() {
        // Arrange
        Long supplierId = 1L;

        // Act
        supplierService.deleteSupplier(supplierId);

        // Assert
        Mockito.verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    public void canRetrieveSupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier expectedSupplier = new Supplier();
        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(expectedSupplier));

        // Act
        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        // Assert
        assertEquals(expectedSupplier, retrievedSupplier);
    }

    @Test
    public void testRetrieveSupplierInvalidId() {
        // Arrange
        Long supplierId = 1L;
        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> supplierService.retrieveSupplier(supplierId));
    }
}
