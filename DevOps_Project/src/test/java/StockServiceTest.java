import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }
    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Test Stock");
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        //Stock savedStock = stockService.addStock(stock);
        Stock savedStock = stockService.addStock(stock);
        assertEquals(savedStock,stock);
    }

    @Test
    public void testRetrieveStock() {
        Long id = 1L;
        Stock stock = new Stock();
        stock.setIdStock(id);
        stock.setTitle("Test Stock");

        Mockito.when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(id);

        assertEquals(stock, retrievedStock);
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1L, "Stock 1",null));
        stockList.add(new Stock(2L, "Stock 2",null));

        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        assertEquals(stockList, retrievedStockList);
    }

}
