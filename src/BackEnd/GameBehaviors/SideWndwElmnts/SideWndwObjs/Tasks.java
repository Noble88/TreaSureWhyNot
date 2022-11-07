package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs;


import java.util.ArrayList;


//NOTE: Task DO NOT reward player. Task are for NPC & other to see if have complete specific tasks (NPC will reward player based on task progression)
public class Tasks {
  //TODO MAKE: NPC/world need to know what type of tasks they should check for (either by name or ID)
  byte taskNum=1;
  ArrayList<Tasks> tasks = new ArrayList<>();
  //TODO MAKE: need to make ways to check certain catagories of tasks ex: need to check "toolGet" type tasks when getting tools
  public static class SpeakToNpcTask extends Tasks {
    boolean complete=false; char npc; String startText,finishText;

    public SpeakToNpcTask(char sym,String initialText, String finishText){
      npc=sym; startText=initialText; this.finishText=finishText;
    }

    public String getInitialText(){return startText;}
    public String getfinishText(){return finishText;}
    public void completeTask(){

    }

    public void checkCompletion(){}

    //TODO ASK TEACHER -> I'm going to have a quest system -> I want the computer to reconizes or call which tasks are ones need check
    // -> use hashmap with same key / ID the task under a name or number / check all of them /
  }

  /*
  public static class GetToolTask extends Tasks {
    boolean complete=false; String item, startText,finishText;

    public GetToolTask(String itemName,String initialText, String finishText){
      item=itemName; startText=initialText; this.finishText=finishText;
    }

    public String getInitialText(){return startText;}
    public String getfinishText(){return finishText;}
    public void checkCompletion(){}

  }

   */



  public String getInitialText(){return "N/A initialText";}
  public String getfinishText(){return "N/A finishText";}
  public void checkCompletion(){}
  //TODO BUG: if player has already met condition then might break so make a checkall method when getting task (maybe even make text exception when this happends)
  //   Maybe can use hashmap to store all active, make sure to delete when inactive, quests and use key to find all relivent data (might not work)
  //   IF NOT can use sort by type vvvvvvvvvvvvvvv
  public String getType(){return "MISSING TYPE!";}

  public void finishTask(){}
  public void displayTask(){}//changes whether the player can see the task or not (off by default)

}
