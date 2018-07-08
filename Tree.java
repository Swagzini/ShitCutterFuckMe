package SwagCutter;

import org.dreambot.api.methods.map.Area;

public enum Tree {
    YEW("Yew", 60, new Area(3016, 3318, 3023, 3314)),
    WILLOW("Willow", 30, new Area(2993, 3170, 3003, 3162)),
    OAK("Oak", 15, new Area(2978, 3209, 2993, 3199)),
    NORMAL("Tree", 1, new Area(3193, 3249, 3206, 3238));

    private String name;
    private int level;
    private Area chopArea;


    Tree(String tree, int level, Area area) {
        this.name = tree;
        this.level = level;
        this.chopArea = area;
    }

    public static Tree getTree(int level) {
        for (Tree tree : Tree.values()) {
            if (level >= tree.getLevel()) {
                return tree;
            }
        }
        return NORMAL;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Area getChopArea() {
        return chopArea;
    }
}
