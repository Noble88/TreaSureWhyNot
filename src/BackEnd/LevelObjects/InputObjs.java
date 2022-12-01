package BackEnd.LevelObjects;

import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.PlayerData;

import java.io.IOException;
import java.io.Serializable;

public class InputObjs implements Serializable{ //Game Play Object
  //region class objects
  public static class visibleObj extends InputObjs implements Serializable {
    byte[] pos;
    String name="N/A";
    Events event=null;

    //Adding objects in current Level w/Event
    public visibleObj(String name, byte[] pos, char sym, Events event){
      this.name=name;
      this.pos = new byte[]{pos[0],pos[1]};
      this.event = event;
      LevelBhvr.curLev.setE(pos,sym,true);
    }

    //Meant for doing stuff outside of currentl level w/Event
    public visibleObj(String name, LevelBhvr.Level lev, byte[] pos, char sym, Events event){
      this.name=name;
      this.pos = new byte[]{pos[0],pos[1]};
      this.event = event;
      lev.setE(pos,sym,true);
    }

    public void disappear(){
      LevelBhvr.curLev.flipE(pos,true);
      LevelBhvr.curLev.removeInputObj(name);
    }

    public byte[] getPos(){return pos;}
    public String getName(){return name;}


    public void run() throws InterruptedException, IOException, ClassNotFoundException {
      if(plyrIntrCheck(pos) && event!=null){
        if(event.triggerEvent()){Events.initiateEventState(event);}}
    }
  }
  //endregion
  //region Helper Methods
  public static boolean plyrIntrCheck(byte[] charPos){
    byte[] temp = GlobMeths.findCords(PlayerData.pos,PlayerData.facing);
    return( (charPos[0]==temp[0]) && (charPos[1]==temp[1]));
  }
  //endregion
  //region getter and setter methods & super stuff
  public void disappear(){System.out.println("RAN EMPTY DISAPPEAR");}
  public byte[] getPos(){return new byte[]{-1,-1};}
  public String getName(){return "EMPTY NAME";}
  public void run() throws InterruptedException, IOException, ClassNotFoundException {}
  //endregion
}



