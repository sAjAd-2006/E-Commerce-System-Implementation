package ir.ac.kntu;

import java.util.Scanner;

// import java.util.LinkedHashMap;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Vendilo vendilo = new Vendilo();
        vendilo.getCustomers().add(new Customer("sajad", "teymoori", "sajad.t@gmail.com", "09391838534", "12345Ss!"));
        vendilo.getCustomers().getFirst().getWallet().setCash(100000);
        DiscountCode discountCode1 = new DiscountCode("12003", Kind.Percentage, 10, 1);
        DiscountCode discountCode2 = new DiscountCode("12003", Kind.Numeric, 100, 2);
        vendilo.getCustomers().getFirst().getDiscountCodes().add(discountCode2);
        vendilo.getCustomers().getFirst().getDiscountCodes().add(discountCode1);
        Address address = new Address();
        address.setTitle("home");
        address.setProvince("alborz");
        address.setCity("tehran");
        address.setDescription("iewinwevkc,mcmme");
        vendilo.getCustomers().getFirst().getAddresses().add(address);
        vendilo.getSellers().add(new Seller("saaad", "jrjj", "Dino", "123456789", "09191838534", null, null));
        vendilo.getSellers().get(0).setProvinceOfSale("tehran");
        vendilo.getSellers().get(0).setAgencyCode("AASS12");
        vendilo.getSellers().get(0).setPassword("12345Bb?");
        // Scanner scanner = new Scanner(System.in);
        // Manager manager = new Manager("sajad", "teymoory", "momilo", "12345Pk?", Integer.MAX_VALUE);
        // vendilo.getManagers().add(manager);
        // vendilo.getManagers().getFirst().sendingAPublicMessage(scanner, vendilo.getCustomers(), vendilo.getSellers());
        // vendilo.getSellersVerification().get(0).setReasonForRejection("Your
        // authentication has not been confirmed yet.");
        // // Book book = new Book();
        // Book book2 = new Book();
        // book.setSelerName("sajjjjjjjjad");
        // book.setSelerName("sajad");
        // // List<Kala> kalas = new ArrayList<>();
        // LinkedHashMap<Kala, Integer> bookMap = new LinkedHashMap<>();
        // bookMap.put(book, 2);
        // // kalas.add(book2);
        // // List<Boolean> booleans = new ArrayList<>();
        // LinkedHashMap<Kala, Boolean> voteMap = new LinkedHashMap<>();
        // voteMap.put(book, false);
        // List<String> name = new ArrayList<>();
        // name.add("soooooooo1");
        // name.add("soooooooo2");
        // name.add("soooooooo3");
        // // order.getIsVoted().add(false);
        // Order order = new Order(bookMap, LocalDateTime.now(), name, null, 10000, 100,
        // null, null);
        // // order.setIsVoted(booleans);
        // order.setKalasVoteMap(voteMap);
        // vendilo.getCustomers().getFirst().getOrders().add(order);
        // // vendilo.getCustomers().getFirst().getShoppingCart().setKalasMap(bookMap);
        // Scanner scanner = new Scanner(System.in);
        // order.showOrderCustomer(scanner);
        // // vendilo.getCustomers().getFirst().getShoppingCart().seeCart(scanner);

        // Seller.getKalas().add(new )
        // vendilo.customerLogin(null);
        vendilo.menu();
    }
}
