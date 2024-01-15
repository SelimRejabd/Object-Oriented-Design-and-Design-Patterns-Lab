// State interface
interface VendingMachineState {
    void selectItem(String item);
    void insertMoney(double amount);
    void dispenseItem();
}

// Concrete state 1: NoSelectionState
class NoSelectionState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public NoSelectionState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String item) {
        System.out.println("Item selected: " + item);
        vendingMachine.setItemSelected(item);
        vendingMachine.changeState(new HasSelectionState(vendingMachine));
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Please select an item first.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please select an item first.");
    }
}

// Concrete state 2: HasSelectionState
class HasSelectionState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public HasSelectionState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String item) {
        System.out.println("Item changed to: " + item);
        vendingMachine.setItemSelected(item);
    }

    @Override
    public void insertMoney(double amount) {
        vendingMachine.setMoneyInserted(amount);
        System.out.println("Money inserted: " + amount + " Taka");
        vendingMachine.changeState(new DispensingState(vendingMachine));
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please insert money first.");
    }
}

// Concrete state 3: DispensingState
class DispensingState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String item) {
        System.out.println("Please wait, dispensing " + vendingMachine.getItemSelected() + "...");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Already processing your request. Please wait.");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Item dispensed: " + vendingMachine.getItemSelected());
        vendingMachine.setItemSelected(null);
        vendingMachine.setMoneyInserted(0);
        vendingMachine.changeState(new NoSelectionState(vendingMachine));
    }
}

// Context class: VendingMachine
class VendingMachine {
    private VendingMachineState state;
    private String itemSelected;
    private double moneyInserted;

    public VendingMachine() {
        this.state = new NoSelectionState(this);
    }

    public void changeState(VendingMachineState state) {
        this.state = state;
    }

    public void selectItem(String item) {
        state.selectItem(item);
    }

    public void insertMoney(double amount) {
        state.insertMoney(amount);
    }

    public void dispenseItem() {
        state.dispenseItem();
    }

    public String getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(String itemSelected) {
        this.itemSelected = itemSelected;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(double moneyInserted) {
        this.moneyInserted = moneyInserted;
    }
}

// Client code
public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.selectItem("Ice Cream");
        vendingMachine.insertMoney(200.0);
        vendingMachine.dispenseItem();

        vendingMachine.selectItem("Chocolate");
        vendingMachine.insertMoney(100.0);
        vendingMachine.dispenseItem();
    }
}
