package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Kala> shoppingCart;
    private Wallet wallet;
    private List<Address> addresses;

    public Customer() {
        shoppingCart = new ArrayList<>();
        wallet = new Wallet();
        addresses = new ArrayList<>();
    }

    public Customer(String firstname, String lastname, String email, String phonenumber, String password) {
        shoppingCart = new ArrayList<>();
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
        setPassword(password);
        wallet = new Wallet();
        addresses = new ArrayList<>();
    }

    public List<Kala> getShoppingCarts() {
        return shoppingCart;
    }

    public void setShoppingCarts(List<Kala> shoppingCarts) {
        this.shoppingCart = shoppingCart;
    }

    public void addToShoppingCart(Kala kala) {
        this.shoppingCart.add(kala);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addToWallet(int plus) {
        wallet.setCash(wallet.getCash() + plus);
    }
}
