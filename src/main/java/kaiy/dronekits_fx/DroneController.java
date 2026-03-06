package kaiy.dronekits_fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import kaiy.dronekits_fx.Builders.DroneBuilder;
import kaiy.dronekits_fx.Factories.BladeFactory;
import kaiy.dronekits_fx.Factories.FrameFactory;
import kaiy.dronekits_fx.Factories.PCBFactory;

public class DroneController {
    @FXML
    private Label aluminumCount;
    @FXML
    private Label plasticCount;
    @FXML
    private Label chipsCount;
    @FXML
    private Label framesCount;
    @FXML
    private Label bladesCount;
    @FXML
    private Label pcbCount;
    @FXML
    private Label kitsCount;

    Warehouse warehouse = Warehouse.getInstance();

    private Thread frameThread;
    private FrameFactory frames;
    private Thread bladeThread;
    private BladeFactory blades;
    private Thread pcbThread;
    private PCBFactory PCB;

    private Thread builderThread1;
    private DroneBuilder builder1;
    private Thread builderThread2;
    private DroneBuilder builder2;

    boolean isBladeThreadInterrupted = false;
    boolean isPcbThreadInterrupted = false;
    boolean isFrameThreadInterrupted = false;

    boolean isBuilder2Interrupted = false;
    boolean isBuilder1Interrupted = false;

    @FXML
    protected void onStart() {
        System.out.println("Creating threads!");

        // Factories
        frames = new FrameFactory("Frames");
        frameThread = new Thread(frames);

        blades = new BladeFactory("Blades");
        bladeThread = new Thread(blades);

        PCB = new PCBFactory("PCBs");
        pcbThread = new Thread(PCB);

        builder1 = new DroneBuilder("Builder Alpha");
        builder2 = new DroneBuilder("Builder Beta");

        builderThread1 = new Thread(builder1);
        builderThread2 = new Thread(builder2);


        // Started factories
        pcbThread.start();
        bladeThread.start();
        frameThread.start();

        builderThread1.start();
        builderThread2.start();
        updater();
    }

    @FXML
    protected void onStop(){
        try {
            while (!isFrameThreadInterrupted){
                isFrameThreadInterrupted = isFrameThreadInterrupted();
            }
            while (!isPcbThreadInterrupted){
                isPcbThreadInterrupted = isPcbThreadInterrupted();
            }
            while (!isBladeThreadInterrupted){
                isBladeThreadInterrupted = isBladeThreadInterrupted();
            }
            while (!isBuilder2Interrupted){
                isBuilder2Interrupted = isBuilder2Interrupted();
            }
            while (!isBuilder1Interrupted){
                isBuilder1Interrupted = isBuilder1Interrupted();
            }
        } catch (InterruptedException e) {
            System.out.println("Exception");
        }
    }

    void updater(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), e -> {
                    aluminumCount.setText(String.valueOf(warehouse.getAluminum()));
                    plasticCount.setText(String.valueOf(warehouse.getPlastic()));
                    chipsCount.setText(String.valueOf(warehouse.getChips()));
                    framesCount.setText(String.valueOf(warehouse.getFrames()));
                    bladesCount.setText(String.valueOf(warehouse.getBlades()));
                    pcbCount.setText(String.valueOf(warehouse.getPCB()));
                    kitsCount.setText(String.valueOf(warehouse.getKits()));
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    boolean isPcbThreadInterrupted() throws InterruptedException {
        PCB.sleepThread();
        pcbThread.interrupt();

        return pcbThread.isInterrupted();
    }

    boolean isBladeThreadInterrupted() throws InterruptedException {
        blades.sleepThread();
        bladeThread.interrupt();

        return bladeThread.isInterrupted();
    }

    boolean isFrameThreadInterrupted() throws InterruptedException {
        frames.sleepThread();
        frameThread.interrupt();

        return frameThread.isInterrupted();
    }

    boolean isBuilder1Interrupted() throws InterruptedException {
        builder1.sleepThread();
        builderThread1.interrupt();

        return builderThread1.isInterrupted();
    }

    boolean isBuilder2Interrupted() throws InterruptedException {
        builder2.sleepThread();
        builderThread2.interrupt();

        return builderThread2.isInterrupted();
    }
}
