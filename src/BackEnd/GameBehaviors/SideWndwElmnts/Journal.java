package BackEnd.GameBehaviors.SideWndwElmnts;

import BackEnd.GameBehaviors.HelpElmnts.MenuCus;
import BackEnd.GameBehaviors.SideWndwBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl.Quest;
import BackEnd.GameBehaviors.TBoxBhvr;
import FrontEnd.Debugger;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Managers.TBoxMangr;

import java.util.ArrayList;

import static BackEnd.GameLoop.gameState;

public class Journal {
  public static MenuCus menu = new MenuCus(1,15,1,false,false,true);

  //region Create Quest and Quest Managment
  public static ArrayList<Quest> quests = new ArrayList<>();

  //region Quest Mangament
  public static void displayQuests(){
    //TODO Qauility of life: could make it so 2 lines desplays 1 quest for more creative naming
    byte pageOffset= (byte) (15*(menu.getCurPg()-1));
    for(byte i = pageOffset; (i<15); i++){
      if(i>=quests.size()){SideWndwMangr.updateLine("             ",i-pageOffset);}
      else{SideWndwMangr.updateLine(quests.get(i).name,i-pageOffset);}
    }
    Debugger.JrnlDebugger();
    menu.setMaxPg((quests.size()/15)+1);
    SideWndwMangr.updatePageNumHeader(menu.getCurPg(),menu.getMaxPg());
  }

  public static void displayInteractText() throws InterruptedException {
    TBoxBhvr.clearTextBox(false);
    TBoxBhvr.createText(quests.get(menu.getNav()[0]).getInteractText(),10);
  }

  //TODO ADD: but checkQuestForCompletion methods in areas where quest will be need to be complete
  // DO NOTE if check for quests or a certain quest is intesive; resort to making methods that check for only
  // specified ID lables ex: checkTools will only check tool related qeusts and trigger if you have obtained a tool
  public static void checkQuestsForCompletion() throws InterruptedException {
    for(byte i=0; i<quests.size(); i++){
      quests.get(i).checkCompletion();
      if(quests.get(i).done){quests.remove(i); i--;}
    }
    if(SideWndwBhvr.curTab.equals("JOURNAL") && gameState.equals("SIDE WINDOW")){displayQuests(); updateSubText();}
  }

  public static void addQuest(Quest quest){quests.add(quest);}
  public static Quest findQuest(String name){
    for(byte i=0; i<quests.size(); i++){
      if(quests.get(i).name.equals(name)){return quests.get(i);}
    }
    System.out.println("ERROR -> CANT FIND QUEST CALLED ("+name+") RETURN NULL");
    return null;
  }
  //endregion



  //region other
  public static Quest getHighlightedQuest(){
    if(quests.size()>menu.getNav()[0]) return quests.get(menu.getNav()[0]);
    return null;
  }
  //endregion
  //endregion

  public static void updateSubText() {
    //TODO BUG/ADD/MAKE: Need to do something with the subtext as leaves ify part at the bottom when switch tabs
    /*
    SideWndwMangr.updateLine("------------", 15);
    if (getHighlightedQuest() != null) {
      if (getHighlightedQuest().getCurTask().getIsComplete()) {SideWndwMangr.updateLine("COMPLETED!", 16);}
      else {SideWndwMangr.updateLine("NOT DONE", 16);}
    }
    else{SideWndwMangr.updateLine("            ",16);}

     */
  }

}
