package tn.esprit.devops_project.repositories;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.InvoiceRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private SupplierRepository supplierRepository;
    @Test
    public void testRetrieveInvoicesBySupplier() {
        // Create a test Supplier
        Supplier supplier = new Supplier();
        supplier.setCode("TestSupplierCode");
        supplier.setLabel("TestSupplierLabel");
        supplier.setSupplierCategory(SupplierCategory.CONVENTIONNE);  // Replace with the actual category

        // Save the Supplier to the database
        supplier = supplierRepository.save(supplier);

        // Create a test Invoice
        Invoice invoice = new Invoice(100.0f, 500.0f, supplier, false);


        // Save the Invoice to the database
        invoiceRepository.save(invoice);

        // Call the custom query method
        List<Invoice> retrievedInvoices = invoiceRepository.retrieveInvoicesBySupplier(supplier);

        // Perform assertions
        assertEquals(1, retrievedInvoices.size());
        assertTrue(retrievedInvoices.get(0).getSupplier().equals(supplier));
    }
}
