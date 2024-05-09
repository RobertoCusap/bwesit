import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Locate the registry.
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

            // Get the references of exported objects from the RMI Registry.
            ProductInterface laptop = (ProductInterface) registry.lookup("laptop");
            ProductInterface mobilePhone = (ProductInterface) registry.lookup("mobilePhone");
            ProductInterface charger = (ProductInterface) registry.lookup("charger");
            ProductInterface powerBank = (ProductInterface) registry.lookup("powerBank");

            // Print details of all products.
            System.out.println("Available Products:");
            printProductDetails(laptop);
            printProductDetails(mobilePhone);
            printProductDetails(charger);
            printProductDetails(powerBank);

            // Get reference to the cart.
            CartInterface cart = (CartInterface) registry.lookup("cart");

            // Prompt user to add products to the cart.
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Enter the names of products you want to add to the cart (separated by commas):");
                String input = scanner.nextLine().trim();

                // Split user input by commas and add products to the cart.
                String[] productNames = input.split(",");
                for (String productName : productNames) {
                    ProductInterface product = null;
                    switch (productName.trim().toLowerCase()) {
                        case "laptop":
                            product = laptop;
                            break;
                        case "mobilephone":
                            product = mobilePhone;
                            break;
                        case "charger":
                            product = charger;
                            break;
                        case "powerbank":
                            product = powerBank;
                            break;
                        default:
                            System.out.println("Invalid product name: " + productName);
                    }
                    if (product != null) {
                        cart.addProduct(product);
                    }
                }

                // View added products in the cart.
                System.out.println("\nProducts in the Cart:");
                cart.viewAddedProducts();
            }

        } catch (Exception e) {
            System.out.println("Client side error: " + e);
        }
    }

    private static void printProductDetails(ProductInterface product) throws RemoteException {
        System.out.println("Name: " + product.getName() + ", Description: " + product.getDescription() + ", Price: "
                + product.getPrice());
    }
}
