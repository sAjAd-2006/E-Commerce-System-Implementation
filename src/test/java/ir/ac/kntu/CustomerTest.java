// package ir.ac.kntu;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// class CustomerTest {
//     private Customer customer;
//     private Kala testKala;
//     private Address testAddress;
    
//     @BeforeEach
//     void setUp() {
//         customer = new Customer("John", "Doe", "john@example.com", "1234567890", "password123");
//         testKala = new Book();
//         testKala.setName("Test Book");
//         testKala.setPrice(100);
//         testKala.setInventory(5);
        
//         testAddress = new Address();
//         testAddress.setCity("Test City");
//     }

//     @Test
//     void testAddToWallet() {
//         int initialAmount = customer.getWallet().getCash();
//         customer.addToWallet(500);
//         assertEquals(initialAmount + 500, customer.getWallet().getCash());
//     }

//     @Test
//     void testAddToShoppingCart() {
//         int initialSize = customer.getShoppingCart().getKalas().size();
//         customer.getShoppingCart().getKalas().add(testKala);
//         assertEquals(initialSize + 1, customer.getShoppingCart().getKalas().size());
//     }

//     @Test
//     void testDiscountWhenAllMatch() {
//         testKala.setSelerCity("Test City");
//         customer.getShoppingCart().getKalas().add(testKala);
//         assertEquals(1, customer.discount(testAddress));
//     }

//     @Test
//     void testDiscountWhenNotAllMatch() {
//         testKala.setSelerCity("Different City");
//         customer.getShoppingCart().getKalas().add(testKala);
//         assertEquals(-1, customer.discount(testAddress));
//     }

//     @Test
//     void testCreateOrder() {
//         customer.getShoppingCart().getKalas().add(testKala);
//         customer.getAddresses().add(testAddress);
        
//         int initialOrderCount = customer.getOrders().size();
//         customer.crOrder(testAddress, 100, 10);
        
//         assertEquals(initialOrderCount + 1, customer.getOrders().size());
//         assertEquals(100, customer.getOrders().get(0).getTotalPrice());
//     }
// }