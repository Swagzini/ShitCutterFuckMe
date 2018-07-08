package SwagCutter;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.map.Tile;

public class WalkToArea extends Task {

    public WalkToArea(MethodContext context) {
        super(context);
    }

    @Override
    public boolean validate() {
        if (!Cutzini.currentArea.contains(getContext().getLocalPlayer()) && !getContext().getInventory().isFull() && Cutzini.changingAxe != true) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        getContext().log("Walking to area");
        Tile tile = Cutzini.currentArea.getRandomTile();
        getContext().getWalking().setRunThreshold(20);
        getContext().getWalking().walk(tile);


    }
}
