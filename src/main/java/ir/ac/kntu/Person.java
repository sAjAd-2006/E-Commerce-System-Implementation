package ir.ac.kntu;

import java.util.Scanner;

abstract class Person {
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String password;
    private int accessLevel = 1;
    private boolean ban = false;

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
    }

    public int runBack(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the desired option : 1)Back 2)Exit 3)Continue");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return 1;
                case "2":
                    System.exit(0);
                case "3":
                    return 3;
                default:
                    System.out.println("The selected option is invalid.");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Role:" + this.getClass().getSimpleName() + " Name: " + firstname + " " + lastname;
    }
}