package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl;


//NOTE: Task DO NOT reward player. Task are for NPC & other to see if have complete specific tasks (NPC will reward player based on task progression)

import BackEnd.GameLoop;
import BackEnd.PlayerData;

public class Tasks {

  //NOTE: nameing of tasks goes a follows -> (condition to meet) + (reward upon complete)
  //TODO MAKE: NPC/world need to know what type of tasks they should check for (either by name or ID)

  //TODO MAKE: need to make ways to check certain catagories of tasks ex: need to check "toolGet" type tasks when getting tools
  //region Speak to NPC TASK
  public static class SpeakToNpcGetTestTask extends Tasks {
    boolean isComplete =false; char npc; String startText,finishText;

    public SpeakToNpcGetTestTask(char sym, String initialText, String finishText){
      npc=sym; startText=initialText; this.finishText=finishText;
    }

    public String getInitialText(){return startText;}
    public String getFinishText(){return finishText;}
    public boolean getIsComplete(){return isComplete;} public void setIsComplete(boolean val){isComplete=val;}

    public void completeTask(){ System.out.println("TASK HAS BEEN COMPLETED");}

    public void checkCompletion(){
      if(PlayerData.symFacingFG==npc && GameLoop.associatedKey.equals("INTERACT")){isComplete =true;};
    }
  }
  //endregion

  public String getInitialText(){return "N/A initialText";}
  public String getFinishText(){return "N/A finishText";}
  public boolean getIsComplete(){System.out.println("EMPTY ISCOMPLETED"); return false;} public void setIsComplete(boolean val){}


  public void checkCompletion(){System.out.println("EMPTY COMPLITION");}
  //TODO: MAKE CHECK ALL METHOD AND PUT IT WHERE IT IS NEEDED
  public void completeTask(){System.out.println("EMPTY COMPLETED TASKS");}
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