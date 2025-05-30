// package ir.ac.kntu;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import java.util.List;

// class CustomerTest {
//     private Customer customer;
//     private Address address;
//     private Kala kala;
//     private Seller seller;

//     @BeforeEach
//     void setUp() {
//         customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "password123");
//         customer.getAddresses().add(address);

//         List<Seller> sellers = new ArrayList<>();
//         sellers.add(seller);
//         customer.setSellers(sellers);
//         Seller.getKalas().add(kala);
//     }

//     @Test
//     void testAddToWallet() {
//         customer.addToWallet(500);
//         assertEquals(500, customer.getWallet().getCash(), "Adding to wallet failed");
//         System.out.println("testAddToWallet: PASSED");
//     }

//     @Test
//     void testDiscount_SameCity() {
//         int result = customer.discount(address);
//         assertEquals(1, result, "Discount should be applied for same city");
//         System.out.println("testDiscount_SameCity: PASSED");
//     }

//     @Test
//     void testCrOrder() {
//         customer.getShoppingCart().getKalas().add(kala);
//         customer.addToWallet(1500);
//         customer.crOrder(address, 1000, 10);
//         assertEquals(1, customer.getOrders().size(), "Order creation failed");
//         System.out.println("testCrOrder: PASSED");
//     }

//     @Test
//     void testIsInteger() {
//         assertTrue(customer.isInteger("123"), "Integer check failed for valid number");
//         assertFalse(customer.isInteger("abc"), "Integer check failed for invalid input");
//         System.out.println("testIsInteger: PASSED");
//     }
// }