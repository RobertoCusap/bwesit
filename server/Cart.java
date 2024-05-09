import java.util.ArrayList;
import java.rmi.RemoteException;

public class Cart implements CartInterface {
    private ArrayList<ProductInterface> addedProducts;

    public Cart() {
        addedProducts = new ArrayList<>();
    }

    @Override
    public void addProduct(ProductInterface product) throws RemoteException {
        addedProducts.add(product);
        System.out.println(product.getName() + " has been added to the cart.");
    }

    @Override
    public void viewAddedProducts() throws RemoteException {
        if (addedProducts.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Products in the cart:");
            for (ProductInterface product : addedProducts) {
                System.out.println("Name: " + product.getName() + ", Price: " + product.getPrice());
            }
        }
    }
}
