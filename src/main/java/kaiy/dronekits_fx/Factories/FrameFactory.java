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
            try {
                makeFrame();
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
            sleep(1000);
            return;
        }
        warehouse.addFrames(1);
    }

    public FrameFactory(String name) {
        this.name = name;
    }
}
