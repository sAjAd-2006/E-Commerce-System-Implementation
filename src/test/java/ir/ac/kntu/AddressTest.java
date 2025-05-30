// package ir.ac.kntu;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// public class AddressTest {
//     private Address address;

//     @BeforeEach
//     public void setUp() {
//         address = new Address("Home", "Tehran", "Tehran", "No. 123, Main St");
//     }

//     @Test
//     public void testGetTitle() {
//         assertEquals("Home", address.getTitle());
//         System.out.println("testGetTitle: PASSED");
//     }

//     @Test
//     public void testGetProvince() {
//         assertEquals("Tehran", address.getProvince());
//         System.out.println("testGetProvince: PASSED");
//     }

//     @Test
//     public void testGetCity() {
//         assertEquals("Tehran", address.getCity());
//         System.out.println("testGetCity: PASSED");
//     }

//     @Test
//     public void testGetDescription() {
//         assertEquals("No. 123, Main St", address.getDescription());
//         System.out.println("testGetDescription: PASSED");
//     }

//     @Test
//     public void testSetTitle() {
//         address.setTitle("Office");
//         assertEquals("Office", address.getTitle());
//         System.out.println("testSetTitle: PASSED");
//     }

//     @Test
//     public void testSetProvince() {
//         address.setProvince("Isfahan");
//         assertEquals("Isfahan", address.getProvince());
//         System.out.println("testSetProvince: PASSED");
//     }

//     @Test
//     public void testSetCity() {
//         address.setCity("Shiraz");
//         assertEquals("Shiraz", address.getCity());
//         System.out.println("testSetCity: PASSED");
//     }

//     @Test
//     public void testSetDescription() {
//         address.setDescription("New address");
//         assertEquals("New address", address.getDescription());
//         System.out.println("testSetDescription: PASSED");
//     }

//     @Test
//     public void testToString() {
//         String expected = "Title:Home - Province:Tehran - City:Tehran - Description:No. 123, Main St";
//         assertEquals(expected, address.toString());
//         System.out.println("testToString: PASSED");
//     }

// }