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
            if (warehouse.getKits() >= kitsGoal){
                System.out.println("[" + name + "] Stopping production!");
                System.out.println("[" + name + "] Total PCBs made: " + componentsMade);
                return;
            }
            try {
                makePCB();
                componentsMade++;
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[" + name + "] Thread stopped!");
            }
        }
    }

    void makePCB() throws InterruptedException {
        if (warehouse.getChips() <= 0){
            System.out.println("[" + name + "] Not enough chips!");
            return;
        } else if (warehouse.getAluminum() <= 10){
            System.out.println("[" + name + "] Not enough aluminum!");
            return;
        } else if (warehouse.getPlastic() <= 5){
            System.out.println("[" + name + "] Not enough plastic!");
            return;
        }
        try {
            warehouse.removeChips(1);
            warehouse.removeAluminum(10);
            warehouse.removePlastic(5);
        } catch (NotEnoughMaterial e) {
            printState(e.getMessage());
            return;
        }
        warehouse.addPCB(1);
        sleep(2000);
    }

    public PCBFactory(String name, int kitsGoal) {
        this.name = name;
        this.kitsGoal = kitsGoal;
    }
}
