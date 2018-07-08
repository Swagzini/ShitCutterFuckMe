package SwagCutter;

public enum Axe {


    RUNE("Rune axe", 41, 40),
    ADAMANT("Adamant axe", 31, 30),
    MITHRIL("Mithril axe", 21, 20),
    BLACK("Black axe", 11, 10),
    STEEL("Steel axe", 6, 5),
    IRON("Iron axe", 1, 1);


    private String name;
    private int level;
    private int equipLevel;

    Axe(String name, int level, int equipLevel) {
        this.name = name;
        this.level = level;
        this.equipLevel = equipLevel;
    }

    public static Axe getAxe(int level) {
        for (Axe axe : Axe.values()) {
            if (level >= axe.getLevel()) {
                return axe;
            }
        }
        return IRON;
    }



    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getEquipLevel() {
        return equipLevel;
    }
}
