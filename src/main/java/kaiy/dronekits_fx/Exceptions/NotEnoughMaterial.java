package kaiy.dronekits_fx.Exceptions;

public class NotEnoughMaterial extends RuntimeException {
    public NotEnoughMaterial(String message) {
        super("Not Enough Material: " + message);
    }
}
