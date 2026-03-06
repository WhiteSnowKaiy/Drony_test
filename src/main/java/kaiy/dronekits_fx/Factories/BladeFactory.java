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
            try {
                makeBlade();
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
            sleep(1000);
            return;
        }
        warehouse.addBlades(1);
    }

    public BladeFactory(String name) {
        this.name = name;
    }
}
