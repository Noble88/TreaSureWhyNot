package BackEnd.LevelObjects;

import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl.Quest;
import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameLoop;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.TresItem;
import BackEnd.PlayerData;
import FrontEnd.Debugger;
import FrontEnd.Managers.LevMangr;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Managers.TBoxMangr;
import FrontEnd.Runner;
import FrontEnd.Window;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Events implements Serializable {

  public static class EventChain extends Events{
    ArrayList<Events> events = new ArrayList<>(); int delay=100;
    public EventChain(ArrayList<Events> eventsArr){
      events.addAll(eventsArr);
    }
    public EventChain(ArrayList<Events> eventsArr, int delay){
      events.addAll(eventsArr); this.delay=delay;
    }
    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      for(byte i=0; i<events.size(); i++){
        events.get(i).triggerEvent();
        Thread.sleep(delay);
      }
      return false;
    }

  }

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
    public String text=""; public int time=10;

    public BscText (String text){this.text = text;}
    public BscText (String text, int speed){this.text = text; time=speed;}

    public boolean triggerEvent() throws InterruptedException {
      TBoxBhvr.createText(text,time);
      return false;
    }

  }

  //TODO make a delete after selection option so can't duplicate text
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

  public static class DelInptObj extends Events implements Serializable{
    public String name="NO NAME";
    public DelInptObj(String name){this.name=name;}

    public void disappear(){LevelBhvr.curLev.getInputObj(name).disappear();}

    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      LevelBhvr.curLev.getInputObj(name).disappear();
      return false;
    }
    //TODO Suggestions: Maybe make a dispear method within InputObjs so paramtere doesn't need a
  }

  public static class GiveQuest extends Events implements Serializable{
    Quest quest;
    public GiveQuest(Quest quest){this.quest= quest;}

    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      Journal.addQuest(quest);quest=null; //makes sure can't give duplicate quests
      return false;
    }
  }

  /*This Obj, Ripped to FullCordMvt But dones't have collition and can't be interacted with
      This is in events because its only serves as visual event   */
  public static class SymScramble extends Events implements Serializable {
    //region Object Creation Variables
    public char[] sym; public ArrayList<byte[]> pos = new ArrayList<>(); public int[] speed;
    public ArrayList<ArrayList<byte[]>> mvtCmds = new ArrayList<>(); //Necessary Creation Vars
    public byte[] stage; //Level Movement Aid

    //endregion
    //region Properties

    public SymScramble(LevelBhvr.Level lev, char[] sym, ArrayList<byte[]> pos, int[] speed, ArrayList<ArrayList<byte[]>> mvtCmds) {
      this.stage = new byte[sym.length];

      for(byte i=0; i<pos.size(); i++){
        this.pos.add(new byte[]{pos.get(i)[0],pos.get(i)[1]});
      }

      this.sym = new char[sym.length];
      for(byte i=0;i<sym.length;i++){
        this.sym[i]=sym[i];
        stage[i]=0;
      }
      System.out.println("STAGE = "+ Arrays.toString(stage));

      this.speed = new int[speed.length];
      for(byte i=0;i<speed.length;i++){
        this.speed[i]=speed[i];
      }

      for(byte i=0; i<mvtCmds.size();i++){
        this.mvtCmds.addAll(Collections.singleton(mvtCmds.get(i)));
        //this.mvtCmds.set(i,mvtCmds.get(i));
        lev.setE(pos.get(i),sym[i],true);
      }
    }

    //region Movement Helper Methods
    public void progressStage(int num){
      if(stage[num]+1== mvtCmds.get(num).size()){
        stage[num]=-1;
      }
      else{stage[num]++;}
    }
    public byte[] getDes(int num){
      byte y=pos.get(num)[0], x=pos.get(num)[1];
      /*
      System.out.println("MVMT CMNDS= "+ Arrays.toString(mvtCommands.get(stage[num]))+"    cur Pos->"
          + Arrays.toString(pos)+"    Stage: "+stage[num]);

       */
      if(mvtCmds.get(num).get(stage[num])[1]!=pos.get(num)[1] && mvtCmds.get(num).get(stage[num])[0]!=pos.get(num)[0]){
        System.out.println("in get Des attempted to move diagonally ");return pos.get(num);}
      if(mvtCmds.get(num).get(stage[num])[1]==pos.get(num)[1] && mvtCmds.get(num).get(stage[num])[0]==pos.get(num)[0]){
        progressStage(num); LevelBhvr.curLev.flipE(new byte[]{y,x},true); return pos.get(num);}

      if(mvtCmds.get(num).get(stage[num])[1]==pos.get(num)[1]){ //if on same x-axis
        if(mvtCmds.get(num).get(stage[num])[0]<pos.get(num)[0]){y--;} //des is above player
        else{y++;}
      }
      if(mvtCmds.get(num).get(stage[num])[0]==pos.get(num)[0]){ //if on same x-axis
        if(mvtCmds.get(num).get(stage[num])[1]<pos.get(num)[1]){x--;} //des is above player
        else{x++;}
      }
      return new byte[]{y,x};
    }
    public void move(byte[] des,int num){
      if(stage[num]==-1){return;}
      LevelBhvr.curLev.flipE(pos.get(num),true);
      LevelBhvr.curLev.setE(des,sym[num],true);
      pos.get(num)[0]=des[0]; pos.get(num)[1]=des[1];
    }
    //endregion
    public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
      boolean stillGoing=true;
      while(stillGoing){
        stillGoing=false;
        System.out.println("STAGE = "+ Arrays.toString(stage));
        System.out.println("Sym = "+ Arrays.toString(sym));
        for(byte i=0; i<sym.length;i++){
          if(stage[i]!=-1){ stillGoing=true;
            byte[] des = getDes(i);
            move(des,i);
            LevMangr.executeDisplayOfGrid();
            Thread.sleep(speed[i]);
          }
        }
      }
      return false;
    }
  }

  public static class MovingFgElement{

  }

  //region Bag Related Events
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
  //endregion

  //!!!!NOTE: false is returned to tell program it has no events need to execute
  public boolean triggerEvent() throws InterruptedException, IOException, ClassNotFoundException {
    System.out.println("RAN SUPER CLASS TRIGGER EVENT"); return false;}
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
