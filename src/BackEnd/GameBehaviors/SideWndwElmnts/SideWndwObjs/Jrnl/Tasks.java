package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl;


//NOTE: Task DO NOT reward player. Task are for NPC & other to see if have complete specific tasks (NPC will reward player based on task progression)

import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameLoop;
import BackEnd.PlayerData;
import FrontEnd.Managers.TBoxMangr;

import java.io.Serializable;

public class Tasks implements Serializable {

  //NOTE: nameing of tasks goes a follows -> (condition to meet) + (reward upon complete)

  //region TEST
  public static class IntrSymGetTestTask extends Tasks implements Serializable{
    char npc; String questText;

    public IntrSymGetTestTask(char sym, String questText){
      npc=sym; this.questText =questText;
    }
    public String getQuestText(){return questText;}

    public boolean checkCompletion() throws InterruptedException {
      if(PlayerData.symFacingFG==npc && GameLoop.associatedKey.equals("INTERACT")){
        TBoxBhvr.createText("I COMPLETED A QUEST",10); return true;
      };
      return false;
    }
  }
  //endregion
  //region Interaction Based Tasks
  public static class IntrSymGetText extends Tasks implements Serializable{
    char sym; String questText, finishText;

    public IntrSymGetText(char sym, String questText, String finishText){
      this.sym=sym; this.questText =questText; this.finishText =finishText;
    }
    public String getQuestText(){return questText;}

    public boolean checkCompletion() throws InterruptedException {
      if(PlayerData.symFacingFG==sym && GameLoop.associatedKey.equals("INTERACT") && GameLoop.gameState.equals("LEVEL") ){
        TBoxBhvr.clearTextBox(false);
        TBoxBhvr.createText(finishText,10);
        Thread.sleep(1500); TBoxBhvr.clearTextBox(false); return true;};
      return false;
    }
  }
  //endregion


  public String getQuestText(){return "N/A initialText";}
  public boolean checkCompletion() throws InterruptedException {System.out.println("EMPTY COMPLITION");return false;}
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