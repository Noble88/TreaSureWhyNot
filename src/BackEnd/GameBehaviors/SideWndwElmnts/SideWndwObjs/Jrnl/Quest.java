package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl;

import BackEnd.GameBehaviors.SideWndwBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Managers.SideWndwMangr;

import java.util.ArrayList;

public class Quest {
  public String name="";
  public byte taskNum=0;
  private ArrayList<Tasks> tasks = new ArrayList<>();

  public Quest(String name, ArrayList<Tasks> tasks){
    if(name.length()> ScreenCoverMangr.xB-1){System.out.println("NAME IS TO BIT FOR QUEST: ("+name+")");}
    else{this.name=name;}

    this.tasks.addAll(tasks);
    if(SideWndwBhvr.curTab.equals("JOURNAL")){
      Journal.displayQuests();} //refreshes quest if obtain quest when on journal
  }
  public void checkComplition(){getCurTask().checkCompletion();}

  public String getInteractText(){
    if( (getCurTask().getIsComplete()) ){return getCurTask().getFinishText();}
    else{return getCurTask().getInitialText();}
  }

  public Tasks getCurTask(){return tasks.get(taskNum);}
}
