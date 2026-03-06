package kaiy.dronekits_fx;

import kaiy.dronekits_fx.Exceptions.NotEnoughMaterial;

public class Warehouse {
    private static Warehouse INSTANCE;

    // Materials
    private int aluminum = 10000;
    private int plastic = 10000;
    private int chips = 1000;

    // Components
    private int frames = 0;
    private int blades = 0;
    private int PCB = 0;

    // Finished kits
    private int kits = 0;

    // Material count controllers
    public synchronized void addAluminum(int amount){
        this.aluminum += amount;
    }
    public synchronized void removeAluminum(int amount){
        if (this.aluminum >= amount) {
            this.aluminum -= amount;
        } else {
            throw new NotEnoughMaterial("Not enough aluminum!");
        }
    }

    public synchronized void addPlastic(int amount){
        this.plastic += amount;
    }
    public synchronized void removePlastic(int amount){
        if (this.plastic >= amount) {
            this.plastic -= amount;
        } else {
            throw new NotEnoughMaterial("Not enough plastic!");
        }
    }

    public synchronized void addChips(int amount){
        this.chips += amount;
    }
    public synchronized void removeChips(int amount){
        if (this.chips >= amount) {
            this.chips -= amount;
        } else {
            throw new NotEnoughMaterial("Not enough chips!");
        }
    }

    // Component count controllers
    public synchronized void addFrames(int amount){
        this.frames += amount;
    }
    public synchronized void removeFrames(int amount){
       if (this.frames >= amount) {
           this.frames -= amount;
       } else {
           throw new NotEnoughMaterial("Not enough frames for kits!");
       }
    }

    public synchronized void addBlades(int amount){
        this.blades += amount;
    }
    public synchronized void removeBlades(int amount){
        if (this.blades >= amount) {
            this.blades -= amount;
        }else {
            throw new NotEnoughMaterial("Not enough blades for kits!");
        }
    }

    public synchronized void addPCB(int amount){
        this.PCB += amount;
    }
    public synchronized void removePCB(int amount){
        if (this.PCB > amount) {
            this.PCB -= amount;
        } else {
            throw new NotEnoughMaterial("Not enough PCBs for kits!");
        }
    }

    public synchronized void addKit(int amount){
        this.kits += amount;
    }
    public synchronized void removeKit(int amount){
        if (this.kits > amount) this.kits -= amount;
    }

    public synchronized int getAluminum() {
        return aluminum;
    }

    public synchronized int getPlastic() {
        return plastic;
    }

    public synchronized int getChips() {
        return chips;
    }

    public synchronized int getFrames() {
        return frames;
    }

    public synchronized int getBlades() {
        return blades;
    }

    public synchronized int getPCB() {
        return PCB;
    }

    public synchronized int getKits() {
        return kits;
    }

    public static Warehouse getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Warehouse();
        }

        return INSTANCE;
    }
}
