// package ir.ac.kntu;

// public class Address {
//     private String title;
//     private String province;
//     private String city;
//     private String description;
// }
// public enum Age {
//     CHILD,ADOLESCENT,ADULT;
// }
// public class Book extends Kala {
//     private String authorsName;
//     private String numberOfPages;
//     private Age ageCategory;
//     private String idISBN;
// }
// public enum Check {
//     Registered,Pending,Closed,ALL;
// }
// public class Customer extends Person {
//     private ShoppingCart shoppingCart;
//     private Wallet wallet;
//     private List<Address> addresses;
//     private List<Order> orders;
//     private List<Reportage> reportages;
//     private List<Seller> sellers;
// }
// public class CustomerHelper {
//     private Customer customer;
//     private Vendilo vendilo;
// }
// abstract class DigitalGoods extends Kala {
//     private String Brand;
//     private String interMemoSize;
//     private String amountOfRAM;
// }
// abstract class Kala {
//     private String name;
//     private int Inventory;
//     private int Price;
//     private Type type;
//     private Model model;
//     private int voteNum;
//     private int vote;
//     private boolean voted;
//     private double averageScore = 0;
//     private String agencyCoSeler;
//     private String selerCity;
//     private String selerName;
// }
// public class Laptop extends DigitalGoods {
//     private String graphicsProcessor;
//     private boolean bluetooth;
//     private boolean webcam;
// }
// public class Mobile extends DigitalGoods {
//     private String rearCamReso;
//     private String frontCamReso;
//     private String internetNetwork;
// }
// public enum Model {
//     MOBILE,LAPTOP,BOOK;
// }
// public class Order extends Timeable {
//     private List<Kala> kalas;
//     private List<Boolean> isVoted;
//     private LocalDateTime localDateTime;
//     private List<String> sellersNames;
//     private String customerName;
//     private String customerEmail;
//     private Address address;
//     private int totalPrice;
//     private int shippingCost;
// }
// abstract class Person {
//     private String firstname;
//     private String lastname;
//     private String email;
//     private String phonenumber;
//     private String password;
// }
// public enum Report {
//     Product_qualit,Discrepancy_between_order_and_delivered_product,Settings,Not_receiving_order,ALL;
// }
// public class Reportage {
//     private Report reportTopic;
//     private String text;
//     private Check check;
//     private String answer;
// }
// public class Seller extends Person {
//     private String codeMely;
//     private String storeTitle;
//     private String provinceOfSale;
//     private String agencyCode;
//     private static List<Kala> kalas = new ArrayList<>();
//     private List<Kala> sellerKala;
//     private Wallet wallet;
//     private List<Order> orders;
//     private String reasonForReject;
// }
// public class SellerHelper {
//     private Seller seller;
// }
// public class ShoppingCart {
//     private List<Kala> kalas = new ArrayList<>();
//     private int totalPrice;
// }
// public class Supporter extends Person {
//     private static List<Seller> verification;
//     private static List<Customer> customersReport;
//     private String agencyName;
// }
// public class SupporterHelper {
//     private Supporter supporter;
//     private Vendilo vendilo;
// }
// public class Transaction extends Timeable{
//     private LocalDateTime localDateTime;
//     private int price;
//     private String why;
// }
// public enum Type {
//     DIGITALGOODS,BOOK;
// }
// public class UserSettings {
//     private List<Customer> customers;
// }
// public class Wallet {
//     private int cash;
//     private List<Transaction> transactions;
// }
// public class Vendilo {
//     private List<Customer> customers = new ArrayList<>();
//     private List<Seller> sellers = new ArrayList<>();
//     private List<Seller> verification = new ArrayList<>();
//     private List<Supporter> supporters = new ArrayList<>();
// }
