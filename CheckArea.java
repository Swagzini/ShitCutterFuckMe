package SwagCutter;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.skills.Skill;

public class CheckArea extends Task {
    public CheckArea(MethodContext context) {
        super(context);
    }

    @Override
    public boolean validate() {
        int myLevel = getContext().getSkills().getRealLevel(Skill.WOODCUTTING);
        if (myLevel == 15 && Cutzini.treeName != Tree.OAK.getName()) {
            return true;
        } else if (myLevel == 30 && Cutzini.treeName != Tree.WILLOW.getName()) {
            return true;
        } else if (myLevel == 60 && Cutzini.treeName != Tree.YEW.getName()) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        Cutzini.currentTree = Tree.getTree(getContext().getSkills().getRealLevel(Skill.WOODCUTTING));
        Cutzini.treeName = Cutzini.currentTree.getName();
        Cutzini.currentArea = Cutzini.currentTree.getChopArea();
    }
}
