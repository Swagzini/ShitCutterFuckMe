package SwagCutter;

import org.dreambot.api.methods.MethodContext;

public abstract class Task {
    private MethodContext context;

    public Task(MethodContext context) {
        this.context = context;
    }

    public abstract boolean validate();

    public abstract void execute();

    public MethodContext getContext() {

        return context;
    }
}
