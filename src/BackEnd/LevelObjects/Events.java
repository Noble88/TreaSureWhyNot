package BackEnd.LevelObjects;

import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameLoop;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.TresItem;
import BackEnd.PlayerData;
import FrontEnd.Debugger;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Managers.TBoxMangr;
import FrontEnd.Runner;
import FrontEnd.Window;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Events implements Serializable {
  public static class KillPlayerToHome extends Events implements Serializable{//when triggered will cover screen and send player to home
    public KillPlayerToHome(){}
    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      if(Debugger.eventDebug){ System.out.println("---EVENT TRIGGERED -> PLAYER WAS KILLED");}
      TBoxBhvr.clearTextBox();
      Window.screenCover.setVisible(true);
      ScreenCoverMangr.fillGrid("!"); //TODO: make the "!" red
      PlayerData.sendPlayerToNewLevel(
          new byte[]{17,12},
          Runner.curFilePath+"/src/BackEnd/FileManagement/SavedData/Assets/Levels/LivelyLeafs/0,0-13,3_0,0.txt");
      Thread.sleep(1500);
      //TODO: make a death audio
      Window.screenCover.setVisible(false);
      return false;
    }
  }

  public static class BscText extends Events implements Serializable{
    public String text="";
    public BscText (String text){this.text = text;}
    public boolean triggerEvent() throws InterruptedException {
      TBoxBhvr.createText(text,10);

      return false;
    }
  }
  //TODO make a delete  after selection option so can't duplicate text
  public static class SelectBox extends Events implements Serializable{
    public ArrayList<Events> options = new ArrayList<>();
    public ArrayList<String> optionNames = new ArrayList<>();
    public ArrayList<String> lines = new ArrayList<>();
    public byte nav=1;

    public SelectBox(ArrayList<Events> options, ArrayList<String> optionNames){
      this.options.addAll(options);
      this.optionNames.addAll(optionNames);
      if(optionNames.size()!=options.size()){System.out.println("SElECT BOX IMBALANCE ERROR!!!");}
      if((TBoxMangr.yB-1)*2<options.size()){System.out.println("SElECT BOX OPTIONS ARE TO MANY ERROR!!!");}

    }
    public void formatLines(){
      StringBuilder debugOrganizer = new StringBuilder();
      byte maxOptions= (byte) options.size();

      if(maxOptions<=TBoxMangr.yB-1){
        //System.out.println("FORMAT LINES -> IF");
        for(byte i=0; i<maxOptions; i++){
          TBoxBhvr.updateLineOfText(i,optionNames.get(i));
          lines.add(optionNames.get(i));
        }
      } else {
        //System.out.println("FORMAT LINES -> ELSE");
        for(byte i=0; i<maxOptions; i++){
          String oName = optionNames.get(i);
          if(oName.length()>((TBoxMangr.xB-2)/2-1)){oName=oName.substring(0,((TBoxMangr.xB-2)/2-1));
          }else{for(byte k = (byte) (((TBoxMangr.xB-2)/2-1)-(oName.length())); k>0; k--){
            oName+=" ";}
          }
          //System.out.println("oName-> "+oName); //oName = original name
          if(i%2==0){lines.add(oName);
            //System.out.println("ADDED LINE-> "+lines);
          }
          else{lines.set((i-1)/2, lines.get((i-1)/2)+" "+oName);
            //System.out.println("EDITED LINE-> "+lines);
          }
        }
        byte lineMax;
        if(maxOptions%2==0){lineMax= (byte) (maxOptions/2);}
        else{lineMax= (byte) ((maxOptions+1)/2);}
        for(byte i=0; i<lineMax; i++){
          TBoxBhvr.updateLineOfText(i,lines.get(i));
        }
      }
    }
    public void moveNav() throws FileNotFoundException {

      byte maxOptions= (byte) options.size();
      if(maxOptions<=TBoxMangr.yB-1){
        //System.out.println("moveNav -> IF");
        switch (GameLoop.associatedKey) { //When in |LEVEL| state
          case "UP"-> {if(nav!=1){nav--;colorNavText();}}
          case "DOWN"-> {if(nav<maxOptions){nav++;colorNavText();}}
        }
      } else {
        //System.out.println("moveNav -> ELSE");
        switch (GameLoop.associatedKey) { //When in |LEVEL| state
          case "UP"-> {if(nav>2){nav-=2;colorNavText();}}
          case "DOWN"-> {if(nav+1<maxOptions){nav+=2;colorNavText();}}
          case "LEFT"-> {if(nav!=1){nav--; colorNavText();}}
          case "RIGHT"-> {if(nav<maxOptions){nav++;colorNavText();}}
        }
      }
    }
    public void colorNavText(){ //I'm having trouble find why this works (scared to touch but should redo if don't understand)
      //TODO UNDERSTAND: find out what this code means/does
      byte maxOptions = (byte) options.size();
      TBoxMangr.clearColoredText();
      if(TBoxBhvr.textIsCleared){TBoxMangr.clearColoredText();}
      if(maxOptions<=TBoxMangr.yB-1){
        for(byte i=0; i<lines.get(nav-1).length()+1;i++){
          TBoxMangr.colorCell("GRAY", (byte) (nav-1),i);
        }//System.out.println("COLOR NAV TEXT -> IF");
      } else {
        //System.out.println("COLOR NAV TEXT -> ELSE");
        byte lineOn;
        if(nav%2==1){lineOn= (byte) ((nav+1)/2);}else{lineOn= (byte) (nav/2);}
        String lineStrOn=lines.get(lineOn-1);
        if(nav%2==1){
          for(byte i=0; i<lineStrOn.substring(0,(TBoxMangr.xB-2)/2-1).length();i++){
            TBoxMangr.colorCell("GRAY", (byte) (lineOn-1), (byte) (i+1));}
        } else{
          for(byte i = (byte) lineStrOn.substring((TBoxMangr.xB-2)/2).length();
              i<lineStrOn.length(); i++){
            TBoxMangr.colorCell("GRAY", (byte) (lineOn-1), (byte) (i+1));}
        }
        //this for loop is for a bug fix as doesn't pain the left boarder of a text box
        for(byte i =0;i<3;i++){TBoxMangr.colorCell("BLACK", i, (byte) 0);}
      }
    }
    public void resertObj(){
      nav=1; lines.clear();
    }
    public boolean triggerEvent() throws IOException, InterruptedException, ClassNotFoundException {
      if(relatedEvent==null){
        formatLines();if(Debugger.selectBoxDebug){System.out.println("---Formatting Lines:"+lines);}
        colorNavText();
        GameLoop.associatedKey="N/A"; GameLoop.key = "N/A"; Window.keyPressedKeyListner="N/A";
      }
      else{
          GameLoop.associatedKeyPressed(Window.keyPressedKeyListner);
          switch (GameLoop.associatedKey) {
            case "UP","DOWN","RIGHT","LEFT"->{moveNav();
              if(Debugger.selectBoxDebug){System.out.println("---Navigator:("+nav+")");}}
            case "INTERACT"->{
              if(Debugger.selectBoxDebug){System.out.println("---Hit Interact Button (Chose an option):");}
              byte getOption= (byte) (nav-1);
              resertObj();
              boolean temp = options.get(getOption).triggerEvent();
              GameLoop.associatedKey="N/A"; GameLoop.key = "N/A"; Window.keyPressedKeyListner="N/A";
              return temp;

            }
            case "PAUSE"->{TBoxBhvr.clearTextBox();
              GameLoop.associatedKey="N/A"; GameLoop.key = "N/A"; Window.keyPressedKeyListner="N/A";
              resertObj(); return false;
            }
          }
          Thread.sleep(20);
      }
      return true;
    }
  }

  public static class GetTool extends Events implements Serializable{
    public String text="";
    public Tools tool;
    public GetTool(String preText, Tools item){ tool = item; text=preText;}
    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      TBoxBhvr.createText(text,10);
      ToolBag.addItem(tool);

      return false;
    }
  }

  public static class GetTres extends Events implements Serializable{
    public String text="";
    public TresItem tres;
    public GetTres(String preText, TresItem item){ tres = item; text=preText;}
    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      TBoxBhvr.createText(text,10);
      TresBag.addItem(tres);

      return false;
    }
  }

  public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {return false;} //NOTE: false is returned to tell program it has no events need to execute
  public static Events relatedEvent=null;
  public static String gameStateHolder="N/A";
  public static void initiateEventState(Events event){
    relatedEvent=event; gameStateHolder=GameLoop.gameState;
    GameLoop.gameState="EVENT"; GameLoop.inPlayableState=false;
  }
  public static void deActivateEventState() throws InterruptedException {
    relatedEvent=null; GameLoop.gameState=gameStateHolder;
    gameStateHolder="N/A";GameLoop.inPlayableState=true;
    Thread.sleep(1000); TBoxBhvr.clearTextBox(); //show text for 1 more second then clear it
  }
}
