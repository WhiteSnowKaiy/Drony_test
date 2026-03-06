package kaiy.dronekits_fx.Builders;

import kaiy.dronekits_fx.Exceptions.NotEnoughMaterial;
import kaiy.dronekits_fx.Factories.Factory;
import kaiy.dronekits_fx.Warehouse;

import static java.lang.Thread.sleep;

public class DroneBuilder extends Factory implements Runnable{
    Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run() {
        System.out.println("Builder " + name + " has started working on new drones!");
        while (unlocked){
            if (warehouse.getKits() >= kitsGoal){
                System.out.println("[" + name + "] Finished working on new drones!");
                System.out.println("[" + name + "] Total kits made: " + componentsMade);
                return;
            }
            try {
                makeDrone();
                componentsMade++;
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[" + name + "] Thread stopped!");
            }
        }
    }

    void makeDrone() throws InterruptedException {
        try {
            warehouse.removeFrames(1);
            warehouse.removeBlades(1);
            warehouse.removePCB(1);
        } catch (NotEnoughMaterial e) {
            printState(e.getMessage());
            return;
        }
        warehouse.addKit(1);
    }

    public DroneBuilder(String name, int goal) {
        this.name = name;
        this.kitsGoal = goal;
    }
}
