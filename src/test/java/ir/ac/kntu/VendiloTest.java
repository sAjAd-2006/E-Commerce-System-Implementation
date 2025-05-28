// package ir.ac.kntu;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.util.List;
// import java.util.Scanner;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import ir.ac.kntu.*;
// import org.junit.jupiter.api.*;
// import java.util.*;
// import java.io.*;
// import static org.junit.jupiter.api.Assertions.*;

// public class VendiloTest {
//     private Vendilo vendilo;
//     private ChekFild chekFild;

//     @BeforeEach
//     void setUp() {
//         vendilo = new Vendilo();
//         chekFild = new ChekFild(Vendilo.getSellers(), Vendilo.getCustomers());
        
//         // پاک کردن لیست‌های استاتیک قبل از هر تست
//         Vendilo.getCustomers().clear();
//         Vendilo.getSellers().clear();
//         Vendilo.getSellersVerification().clear();
//         Vendilo.getSupporters().clear();
//     }

//     @Test
//     void testGetAndSetCustomers() {
//         List<Customer> testCustomers = List.of(
//             new Customer("John", "Doe", "john@example.com", "09123456789", "Password123!")
//         );
        
//         vendilo.setCustomers(testCustomers);
//         assertEquals(testCustomers, Vendilo.getCustomers());
//     }

//     @Test
//     void testGetAndSetSellers() {
//         List<Seller> testSellers = List.of(
//             new Seller("Jane", "Doe", "Jane's Store", "1234567890", "09123456789", "Password123!", "Tehran")
//         );
        
//         vendilo.setSellers(testSellers);
//         assertEquals(testSellers, Vendilo.getSellers());
//     }

//     @Test
//     void testGetAndSetSupporters() {
//         List<Supporter> testSupporters = List.of(
//             new Supporter("Support", "User", "support1", "Password123!")
//         );
        
//         vendilo.setSupporters(testSupporters);
//         assertEquals(testSupporters, Vendilo.getSupporters());
//     }

//     @Test
//     void testCustomerLoginSuccess() {
//         Customer testCustomer = new Customer("Test", "User", "test@example.com", "09123456789", "Password123!");
//         Vendilo.getCustomers().add(testCustomer);
        
//         // تست لاگین موفق
//         vendilo.customerLogin2("test@example.com", "Password123!", new Scanner("3")); // 3 برای ادامه
//         // اگر لاگین موفق باشد، پیام موفقیت آمیز چاپ می‌شود
//         // این تست بیشتر برای بررسی عدم پرتاب استثناست
//     }

//     @Test
//     void testCustomerLoginFailure() {
//         Customer testCustomer = new Customer("Test", "User", "test@example.com", "09123456789", "Password123!");
//         Vendilo.getCustomers().add(testCustomer);
        
//         // تست لاگین ناموفق با ایمیل اشتباه
//         vendilo.customerLogin2("wrong@example.com", "Password123!", new Scanner("3"));
        
//         // تست لاگین ناموفق با رمز اشتباه
//         vendilo.customerLogin2("test@example.com", "WrongPassword!", new Scanner("3"));
//     }

//     @Test
//     void testSellerLoginSuccess() {
//         Seller testSeller = new Seller("Seller", "User", "Seller Store", "1234567890", "09123456789", "Password123!", "Tehran");
//         testSeller.setAgencyCode("AG123");
//         Vendilo.getSellers().add(testSeller);
        
//         // تست لاگین موفق
//         vendilo.sellerLogin2("AG123", "Password123!", new Scanner("3"));
//     }

//     @Test
//     void testSellerLoginPendingVerification() {
//         Seller testSeller = new Seller("Seller", "User", "Seller Store", "1234567890", "09123456789", "Password123!", "Tehran");
//         testSeller.setAgencyCode("AG123");
//         Vendilo.getSellersVerification().add(testSeller);
        
//         // تست لاگین فروشنده در حال انتظار برای تایید
//         vendilo.sellerLogin2("AG123", "Password123!", new Scanner("3"));
//     }

//     @Test
//     void testCustomerRegister() {
//         // تست ثبت نام موفق مشتری
//         Customer customer = vendilo.customerRegister2(
//             new Scanner("test@example.com\n09123456789\nPassword123!\n3"), 
//             chekFild, 
//             "Test", 
//             "User"
//         );
        
//         assertNotNull(customer);
//         assertEquals("Test", customer.getFirstname());
//         assertEquals("User", customer.getLastname());
//         assertEquals("test@example.com", customer.getEmail());
//         assertEquals("09123456789", customer.getPhonenumber());
//     }

//     @Test
//     void testSellerRegister() {
//         // تست ثبت نام موفق فروشنده
//         Seller seller = vendilo.sellerRegister2(
//             new Scanner("1234567890\n09123456789\nPassword123!\nTehran\n3"), 
//             chekFild, 
//             "Seller", 
//             "User", 
//             "Test Store"
//         );
        
//         assertNotNull(seller);
//         assertEquals("Seller", seller.getFirstname());
//         assertEquals("User", seller.getLastname());
//         assertEquals("Test Store", seller.getStoreTitle());
//         assertEquals("1234567890", seller.getCodeMely());
//         assertEquals("09123456789", seller.getPhonenumber());
//         assertEquals("Tehran", seller.getProvinceOfSale());
//     }

//     @Test
//     void testSupporterLoginSuccess() {
//         Supporter testSupporter = new Supporter("Support", "User", "support1", "Password123!");
//         Vendilo.getSupporters().add(testSupporter);
        
//         // تست لاگین موفق پشتیبان
//         vendilo.supporterLogin2("support1", "Password123!", new Scanner("3"));
//     }

//     @Test
//     void testRunBackOption() {
//         // تست گزینه بازگشت
//         assertEquals(1, vendilo.runBack(new Scanner("1")));
        
//         // تست گزینه ادامه
//         assertEquals(3, vendilo.runBack(new Scanner("3")));
//     }
// }
