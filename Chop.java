package SwagCutter;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.wrappers.interactive.GameObject;

public class Chop extends Task {

    public Chop(MethodContext context) {
        super(context);
    }

    @Override
    public boolean validate() {
        if (!getContext().getLocalPlayer().isAnimating() && Cutzini.currentArea.contains(getContext().getLocalPlayer()) && !Cutzini.noAxe) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        getContext().log("Chopping tree");
        GameObject tree = getContext().getGameObjects().closest(Cutzini.treeName);
        if (tree != null) {
            tree.interact("Chop down");
            getContext().sleep(500, 600);
            getContext().sleepUntil(() -> !getContext().getLocalPlayer().isAnimating(), 10000);
        }

    }
}
