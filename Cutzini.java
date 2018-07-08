package SwagCutter;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.items.Item;

import java.util.ArrayList;
import java.util.List;

@ScriptManifest(author = "Swagzini", category = Category.WOODCUTTING, name = "SwagCutter", version = 0.2)

public class Cutzini extends AbstractScript {

    public static Tree currentTree;
    public static String treeName;
    public static Area currentArea;
    public static String currentAxe;
    public static boolean changingAxe = false;
    public static boolean noAxe;
    public static boolean upgradeAvailable;
    public ArrayList<Task> tasks = new ArrayList<Task>();


    @Override
    public void onStart() {
        log("Setting up variables");
        currentTree = Tree.getTree(getSkills().getRealLevel(Skill.WOODCUTTING));
        treeName = currentTree.getName();
        currentArea = currentTree.getChopArea();
        boolean checkInventory = getInventory().contains("Iron axe", "Steel axe", "Black axe", "Mithril axe", "Adamant axe", "Rune axe");
        if (!checkInventory) {
            noAxe = true;
            log("No axe");
        } else if (checkInventory) {
            List<Item> axes = getInventory().all(item -> item != null && item.getName().endsWith("axe"));
            currentAxe = axes.get(0).getName();
            log("Axe Found");


        }

        tasks.add(new Chop(this));
        tasks.add(new Dropping(this));
        tasks.add(new WalkToArea(this));
        tasks.add(new CheckArea(this));
        tasks.add(new CheckAxe(this));

        log("Finished setting up variables");
    }

    @Override
    public int onLoop() {
        log("looping");
        for (Task t : tasks) {
            if (t.validate()) {
                log("task validated");
                t.execute();
                log("task executed");
            }
        }
        sleep(500, 600);
        return 0;
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
