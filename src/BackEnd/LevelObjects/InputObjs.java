package BackEnd.LevelObjects;

import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameLoop;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.PlayerData;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class InputObjs implements Serializable{ //Game Play Object
  public static class visibleObj extends InputObjs implements Serializable {
    byte[] pos;
    Events event=null;
    public visibleObj(byte[] pos, char sym, Events event){
      this.pos = new byte[]{pos[0],pos[1]};
      this.event = event;
      LevelBhvr.curLev.setE(pos,sym,true);
    }
    public visibleObj(LevelBhvr.Level lev, byte[] pos, char sym, Events event){
      this.pos = new byte[]{pos[0],pos[1]};
      this.event = event;
      lev.setE(pos,sym,true);
    }



    public byte[] getPos(){return pos;}

    public void run() throws InterruptedException, IOException, ClassNotFoundException {
      if(plyrIntrCheck(pos) && event!=null){
        if(event.triggerEvent()){Events.initiateEventState(event);}}
    }
  }




  public static boolean plyrIntrCheck(byte[] charPos){
    byte[] temp = GlobMeths.findCords(PlayerData.pos,PlayerData.facing);
    return( (charPos[0]==temp[0]) && (charPos[1]==temp[1]));
  }
  public byte[] getPos(){return new byte[]{-1,-1};}
  public void run() throws InterruptedException, IOException, ClassNotFoundException {}

}



