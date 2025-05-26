package ir.ac.kntu;

public class Test {
    public static void main(String[] args) {
        Vendilo vendilo = new Vendilo();
        vendilo.getCustomers().add(new Customer("sajad", "teymoori", "sajad.t@gmail.com", "09391838534", "12345Ss!"));
        vendilo.getSellers().add(new Seller("saaad", "jrjj", "Dino", "123456789", "09191838534", null, null));
        vendilo.getSellers().get(0).setAgencyCode("AASS12");
        vendilo.getSellers().get(0).setPassword("12345Bb?");
        // vendilo.customerLogin(null);
        vendilo.menu();
    }
}
