package kaiy.dronekits_fx.Factories;

import kaiy.dronekits_fx.Exceptions.NotEnoughMaterial;
import kaiy.dronekits_fx.Warehouse;

import static java.lang.Thread.sleep;

public class BladeFactory extends Factory implements Runnable {
    Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run() {
        System.out.println("Factory " + name + " has started producing");
        while (unlocked){
            if (warehouse.getKits() >= kitsGoal){
                System.out.println("[" + name + "] Stopping production!");
                System.out.println("[" + name + "] Total blades made: " + componentsMade);
                return;
            }
            try {
                makeBlade();
                componentsMade++;
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[" + name + "] Thread stopped!");
            }
        }
    }

    void makeBlade() throws InterruptedException {
        try {
            warehouse.removePlastic(30);
        } catch (NotEnoughMaterial e) {
            printState(e.getMessage());
            return;
        }
        warehouse.addBlades(1);
    }

    public BladeFactory(String name, int kitsGoal) {
        this.name = name;
        this.kitsGoal = kitsGoal;
    }
}
