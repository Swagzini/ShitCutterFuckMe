package SwagCutter;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;

public class CheckAxe extends Task{

    public CheckAxe(MethodContext context) {
        super(context);
    }

    @Override
    public boolean validate() {
        int myLevel = getContext().getSkills().getRealLevel(Skill.WOODCUTTING);
         if (Cutzini.noAxe) {
            getContext().log("No axe");
            Cutzini.changingAxe = true;
            return true;
        } else if (myLevel <= 5 && !Cutzini.currentAxe.equals("Iron axe")) {
            getContext().log("Getting iron axe");
            return true;
        } else if ((myLevel >= 6 && myLevel <=10) && !Cutzini.currentAxe.equals("Steel axe")) {
            Cutzini.changingAxe = true;
            return true;
        } else if ((myLevel >= 11 && myLevel <= 20)&& !Cutzini.currentAxe.equals("Black axe")) {
            Cutzini.changingAxe = true;
            return true;
        } else if ((myLevel >= 21 && myLevel <=30) && !Cutzini.currentAxe.equals("Mithril axe")) {
            Cutzini.changingAxe = true;
            return true;
        } else if ((myLevel >= 31 && myLevel <= 40) && !Cutzini.currentAxe.equals("Adamant axe")) {
            Cutzini.changingAxe = true;
            return true;
        } else if (myLevel >= 41 && !Cutzini.currentAxe.equals("Rune axe")) {
            getContext().log("Getting Rune axe");
            Cutzini.changingAxe = true;
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        BankLocation bank = getContext().getBank().getClosestBankLocation();
        Area bankArea = bank.getArea(2);
        Tile bankTile = bankArea.getRandomTile();
        getContext().getWalking().walk(bankTile);
        if(bankArea.contains(getContext().getLocalPlayer())) {
            getContext().getBank().openClosest();
            getContext().sleepUntil(() -> !getContext().getBank().isOpen(), 3000);
            getContext().log("Open Bank");

            getContext().getBank().deposit(Cutzini.currentAxe);
            getContext().log("Deposit current axe");
            getContext().sleepUntil(() -> !getContext().getInventory().contains(Cutzini.currentAxe), 1500);

           Axe newAxe = Axe.getAxe(getContext().getSkills().getRealLevel(Skill.WOODCUTTING));
           getContext().log("Determining new axe");
           String axe = newAxe.getName().toString();
           getContext().log(axe);

           getContext().getBank().withdraw(axe);
           getContext().log("Withdraw new axe");
           getContext().sleepUntil(() -> getContext().getInventory().contains(axe), 1500);

           getContext().getBank().close();
           getContext().log("Close bank");
            getContext().sleepUntil(() -> !getContext().getBank().isOpen(), 1500);


           Cutzini.currentAxe = axe;
           getContext().log("Changing axe to false");
           Cutzini.changingAxe = false;
           if(getContext().getInventory().contains(Cutzini.currentAxe)) {
               Cutzini.noAxe = false;
           }
        }
    }
}
