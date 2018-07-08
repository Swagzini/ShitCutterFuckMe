package SwagCutter;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.wrappers.items.Item;

public class Dropping extends Task {

    public Dropping(MethodContext context) {
        super(context);
    }

    @Override
    public boolean validate() {

        if (Cutzini.currentArea.contains(getContext().getLocalPlayer()) && getContext().getInventory().isFull()) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        getContext().log(Cutzini.currentAxe);
        getContext().log("Dropping logs");
        getContext().getInventory().dropAllExcept(Cutzini.currentAxe);
        getContext().sleep(500, 1000);

    }
}
