package kaiy.dronekits_fx.Factories;

import kaiy.dronekits_fx.Exceptions.NotEnoughMaterial;
import kaiy.dronekits_fx.Warehouse;

import static java.lang.Thread.sleep;

public class FrameFactory extends Factory implements Runnable {
    Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run() {
        System.out.println("Factory " + name + " has started producing");
        while (unlocked){
            if (warehouse.getKits() >= kitsGoal){
                System.out.println("[" + name + "] Stopping production!");
                System.out.println("[" + name + "] Total frames made: " + componentsMade);
                return;
            }
            try {
                makeFrame();
                componentsMade++;
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[" + name + "] Thread stopped!");
            }
        }
    }

    void makeFrame() throws InterruptedException {
        try {
            warehouse.removeAluminum(60);
        } catch (NotEnoughMaterial e) {
            printState(e.getMessage());
            return;
        }
        warehouse.addFrames(1);
    }

    public FrameFactory(String name, int kitsGoal) {
        this.name = name;
        this.kitsGoal = kitsGoal;
    }
}
