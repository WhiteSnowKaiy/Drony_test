package kaiy.dronekits_fx.Factories;

import kaiy.dronekits_fx.Exceptions.NotEnoughMaterial;
import kaiy.dronekits_fx.Warehouse;

import static java.lang.Thread.sleep;

public class PCBFactory extends Factory implements Runnable {
    Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run() {
        System.out.println("Factory " + name + " has started producing");
        while (unlocked){
            try {
                makePCB();
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[" + name + "] Thread stopped!");
            }
        }
    }

    void makePCB() throws InterruptedException {
        try {
            warehouse.removeChips(1);
            warehouse.removeAluminum(10);
        } catch (NotEnoughMaterial e) {
            printState(e.getMessage());
            sleep(1000);
            return;
        }
        warehouse.addPCB(1);
    }

    public PCBFactory(String name) {
        this.name = name;
    }
}
