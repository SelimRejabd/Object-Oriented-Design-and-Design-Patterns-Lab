import java.util.HashMap;
import java.util.Map;

// Interface for the RetailStore
interface RetailStore {
    void purchaseProduct(String product, int quantity);
    void shipProduct(String product, int quantity, String destination);
}

// RealRetailStore represents the actual retail store with global distribution and warehousing
class RealRetailStore implements RetailStore {
    private Map<String, Integer> warehouse;

    public RealRetailStore() {
        warehouse = new HashMap<>();
    }

    @Override
    public void purchaseProduct(String product, int quantity) {
        int availableQuantity = warehouse.getOrDefault(product, 0);

        if (availableQuantity >= quantity) {
            warehouse.put(product, availableQuantity - quantity);
            System.out.println(quantity + " units of " + product + " purchased.");
        } else {
            System.out.println("Sorry, " + product + " is out of stock.");
        }
    }

    @Override
    public void shipProduct(String product, int quantity, String destination) {
        System.out.println(quantity + " units of " + product + " shipped to " + destination);
    }

    // Method to add products to the warehouse
    public void addProductToWarehouse(String product, int quantity) {
        int currentQuantity = warehouse.getOrDefault(product, 0);
        warehouse.put(product, currentQuantity + quantity);
    }
}

// RetailStoreProxy is a proxy for the RetailStore
class RetailStoreProxy implements RetailStore {
    private RealRetailStore realStore;

    public RetailStoreProxy() {
        realStore = new RealRetailStore();
    }

    @Override
    public void purchaseProduct(String product, int quantity) {
        // Simulate some additional functionality such as checking customer's eligibility
        if (isCustomerEligible()) {
            realStore.purchaseProduct(product, quantity);
        } else {
            System.out.println("Sorry, you are not eligible to purchase " + product + ".");
        }
    }

    @Override
    public void shipProduct(String product, int quantity, String destination) {
        realStore.shipProduct(product, quantity, destination);
    }

    // Simulate customer eligibility check
    private boolean isCustomerEligible() {
        // Add your logic to check customer eligibility here
        return true;
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        RetailStore store = new RetailStoreProxy();

        // Add some products to the warehouse
        RealRetailStore realStore = new RealRetailStore();
        realStore.addProductToWarehouse("ProductA", 100);
        realStore.addProductToWarehouse("ProductB", 50);

        // Purchase products using the proxy
        store.purchaseProduct("ProductA", 20);
        store.purchaseProduct("ProductB", 60);

        // Ship products
        store.shipProduct("ProductA", 20, "CustomerA");
    }
}
