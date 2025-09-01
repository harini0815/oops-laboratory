//Harini M
//2117240070105
import java.util.Scanner;

// Abstract Class
abstract class Payment {
    double amount;
    Payment(double amount) { this.amount = amount; }
    abstract void pay();   // abstract method
}

// Card Payment
class CardPayment extends Payment {
    String card, cvv;
    CardPayment(double amt, String card, String cvv) {
        super(amt); this.card = card; this.cvv = cvv;
    }
    void pay() {
        if (card.matches("\\d{16}") && cvv.matches("\\d{3}"))
            System.out.println("Card Payment of Rs." + amount + " successful!");
        else
            System.out.println("Invalid Card details!");
    }
}

// UPI Payment
class UpiPayment extends Payment {
    String upi;
    UpiPayment(double amt, String upi) {
        super(amt); this.upi = upi;
    }
    void pay() {
        if (upi.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z]+$"))
            System.out.println("UPI Payment of Rs." + amount + " successful!");
        else
            System.out.println("Invalid UPI ID!");
    }
}

// Main Class
public class PaymentDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Card  2. UPI");
        int choice = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amt = sc.nextDouble();
        sc.nextLine(); // clear buffer

        Payment p;
        if (choice == 1) {
            System.out.print("Enter Card Number (16 digits): ");
            String card = sc.nextLine();
            System.out.print("Enter CVV (3 digits): ");
            String cvv = sc.nextLine();
            p = new CardPayment(amt, card, cvv);
        } else {
            System.out.print("Enter UPI ID (e.g. user@bank): ");
            String upi = sc.nextLine();
            p = new UpiPayment(amt, upi);
        }

        p.pay();
        sc.close();
    }
}
