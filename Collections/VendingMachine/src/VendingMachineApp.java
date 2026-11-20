import java.util.Scanner;

public class VendingMachineApp {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while(running) {
            machine.displayCatalog();
            System.out.println("\n[1] Select Product  [2] Insert Coin  [3] Insert Note");
            System.out.println("[4] Pay via UPI     [5] Dispense       [6] Cancel");
            System.out.println("[7] Show Stats      [0] Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter product name (Soda/Chips/Candy/Drink/Chocolate): ");
                        machine.selectProduct(sc.nextLine().trim());
                        break;
                    case 2:
                        System.out.print("Coin (1/2/5/10/20): ");
                        int cv = sc.nextInt();
                        sc.nextLine();
                        machine.insertCoin(CoinDenomination.values()[cv == 1 ? 0 : cv == 2 ? 1 : cv == 5 ? 2 : cv == 10 ? 3 : cv == 20 ? 4 : 5]);
                        break;
                    case 3:
                        System.out.print("Note (100/200/500/2000): ");
                        int nv = sc.nextInt();
                        sc.nextLine();
                        machine.insertNote(NoteDenomination.values()[nv == 100 ? 0 : nv == 200 ? 1 : nv == 500 ? 2 : 3]);
                        break;
                    case 4:
                        machine.payViaUPI();
                        break;
                    case 5:
                        machine.dispense();
                        break;
                    case 6:
                        machine.cancelTransaction();
                        break;
                    case 7:
                        machine.printStats();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("  ⚠ " + e.getMessage());
            }

        }
        sc.close();
    }

}
