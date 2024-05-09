import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Server {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(9100);
			System.setProperty("java.rmi.server.hostname", "127.0.0.1");
			System.out.println("Server has been started...");

			Product Laptop = new Product("Laptop", "Lenovo", 3740.91);
			Product MobilePhone = new Product("MobilePhone", "iPhone", 441.72);
			Product Charger = new Product("Charger", "Lenovo Charger", 841.55);
			Product powerBank = new Product("PowerBank", "Panasonic", 44.12);

			ProductInterface stub_laptop = (ProductInterface) UnicastRemoteObject.exportObject(Laptop, 0);
			ProductInterface stub_mobilePhone = (ProductInterface) UnicastRemoteObject.exportObject(MobilePhone, 0);
			ProductInterface stub_charger = (ProductInterface) UnicastRemoteObject.exportObject(Charger, 0);
			ProductInterface stub_powerBank = (ProductInterface) UnicastRemoteObject.exportObject(powerBank, 0);

			Cart cart = new Cart();
			CartInterface stub_cart = (CartInterface) UnicastRemoteObject.exportObject(cart, 0);

			// Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

			registry.rebind("laptop", stub_laptop);
			registry.rebind("mobilePhone", stub_mobilePhone);
			registry.rebind("charger", stub_charger);
			registry.rebind("powerBank", stub_powerBank);
			registry.rebind("cart", stub_cart);

			System.out.println("Exporting and binding of Objects has been completed...");
		} catch (Exception e) {
			System.out.println("Some server error..." + e);
		}
	}
}
