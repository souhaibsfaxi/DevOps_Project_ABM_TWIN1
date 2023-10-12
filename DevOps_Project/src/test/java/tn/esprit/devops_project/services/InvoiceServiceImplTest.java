package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canRetrieveAllInvoices() {
        // Arrange
        List<Invoice> expectedInvoices = new ArrayList<>();
        Mockito.when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        // Act
        List<Invoice> actualInvoices = invoiceService.retrieveAllInvoices();

        // Assert
        assertEquals(expectedInvoices, actualInvoices);
    }

    @Test
    public void canCancelInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(invoiceId);

        // Assert
        assertEquals(true, invoice.getArchived());
    }

    @Test
    public void canCancelInvoiceNotFound() {
        // Arrange
        Long invoiceId = 1L;
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> invoiceService.cancelInvoice(invoiceId));
    }

    @Test
    public void testRetrieveInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // Act
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Assert
        assertEquals(expectedInvoice, retrievedInvoice);
    }

    @Test
    public void testRetrieveInvoiceNotFound() {
        // Arrange
        Long invoiceId = 1L;
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> invoiceService.retrieveInvoice(invoiceId));
    }



    @Test
    public void canGetTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedTotalAmount = 100.0f;
        Mockito.when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        // Act
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedTotalAmount, totalAmount);
    }

}
