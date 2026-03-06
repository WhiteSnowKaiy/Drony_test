package kaiy.dronekits_fx.Factories;

import kaiy.dronekits_fx.Warehouse;

import static java.lang.Thread.sleep;

public class WarehouseWorker extends Factory implements Runnable {
    Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run()
     {
         while (unlocked) {
             int random = (int) (Math.random()*1000);
             if (warehouse.getKits() >= kitsGoal){
                 System.out.println("[" + name + "] Stopping production!");
                 return;
             }
             if (random % 3 == 1) {
                 warehouse.addAluminum(random);
                 System.out.println("["+name+"] Added: " + random + "g of Aluminum");
             } else if (random % 3 == 2) {
                 warehouse.addPlastic(random);
                 System.out.println("["+name+"] Added: " + random + "g of Plastic");
             } else if (random % 3 == 0) {
                 warehouse.addChips(random/10);
                 System.out.println("["+name+"] Added: " + random/10 + "g of Chips");
             }

             try {
                 sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
     }

    public WarehouseWorker(String name, int kitsGoal) {
        this.name = name;
        this.kitsGoal = kitsGoal;
    }
}
