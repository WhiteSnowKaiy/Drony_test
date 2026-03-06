package kaiy.dronekits_fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import kaiy.dronekits_fx.Builders.DroneBuilder;
import kaiy.dronekits_fx.Factories.BladeFactory;
import kaiy.dronekits_fx.Factories.FrameFactory;
import kaiy.dronekits_fx.Factories.PCBFactory;
import kaiy.dronekits_fx.Factories.WarehouseWorker;

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
    @FXML
    private TextField kitsGoal;

    Warehouse warehouse = Warehouse.getInstance();

    private Thread frameThread;
    private FrameFactory frames;
    private Thread bladeThread;
    private BladeFactory blades;
    private Thread pcbThread;
    private PCBFactory PCB;
    private Thread WarehouseThread;
    private WarehouseWorker warehouseWorker;

    private Thread builderThread1;
    private DroneBuilder builder1;
    private Thread builderThread2;
    private DroneBuilder builder2;

    @FXML
    protected void onStart() {
        if (kitsGoal.getCharacters().toString().isEmpty()) {
            System.out.println("Please first select how many kits you'd like to be made");
            return;
        }

        System.out.println("Creating threads!");
        int goal = Integer.parseInt(kitsGoal.getCharacters().toString());
        // Factories
        frames = new FrameFactory("Frames", goal);
        frameThread = new Thread(frames);

        blades = new BladeFactory("Blades", goal);
        bladeThread = new Thread(blades);

        PCB = new PCBFactory("PCBs", goal);
        pcbThread = new Thread(PCB);

        builder1 = new DroneBuilder("Builder Alpha", goal);
        builder2 = new DroneBuilder("Builder Beta", goal);

        builderThread1 = new Thread(builder1);
        builderThread2 = new Thread(builder2);

        warehouseWorker = new WarehouseWorker("WarehouseWorker", goal);
        WarehouseThread = new Thread(warehouseWorker);

        // Started factories
        pcbThread.start();
        bladeThread.start();
        frameThread.start();
        WarehouseThread.start();

        builderThread1.start();
        builderThread2.start();
        updater();
    }

    @FXML
    protected void onStop(){
        // Todo: Fix this...
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
}
