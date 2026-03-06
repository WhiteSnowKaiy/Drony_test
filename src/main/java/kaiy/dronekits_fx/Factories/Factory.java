package kaiy.dronekits_fx.Factories;

import static java.lang.Thread.sleep;

public abstract class Factory {
    protected String name;
    protected boolean unlocked = true;

    protected void printState(String state) {
        System.out.println("["+name+"] " + state);
    }

    public void sleepThread() throws InterruptedException {
        sleep(1000);
    }

}
