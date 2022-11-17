package BackEnd.GlobalInfo;

import BackEnd.FileManagement.Loaders.LoadPlayerData;
import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.PlayerData;
import FrontEnd.Colors.LevColors;

import java.io.IOException;

import static BackEnd.GameBehaviors.LevelBhvr.*;
import static BackEnd.GlobalInfo.GlobData.time;
import static BackEnd.GlobalInfo.GlobData.worldTint;
import static FrontEnd.Colors.LevColors.windowTint;

public class GlobMeths {
  public static void updateWalkables(){
    GlobData.walkables=curLev.levelWalkables+ PlayerData.plyrWalkables;
  }

  //region Location Based Methods
  public static byte[] findCords(byte[] pos, String key){
    byte mX=0,mY=0;
    switch (key){
      case "LEFT"  -> {mX=-1;} case "RIGHT" -> {mX=1;}
      case "UP"    -> {mY=-1;} case "DOWN"  -> {mY=1;}
    }
    return new byte[]{(byte) (pos[0]+mY), (byte) (pos[1]+mX)};
  }

  public static String findDirection(byte[] pos, byte[] des){
    //NOTE: byte[0][0] is the top left of the grid
    if(pos[0]==des[0] && pos[1]==des[1]){return "N/A";}

    if(pos[0]==des[0]){ //if Y axis are alligned... (must have moved along the X axis)
      if(pos[1]>des[1]){return "LEFT";}
      else{return "RIGHT";}
    }
    else if(pos[1]==des[1]){ //if X axis are alligned... (must have moved along the Y axis)
      if(pos[0]>des[0]){return "UP";}
      else{return "DOWN";}
    }
    return "N/A";
  }
  //endregion

  //region file manipulation methods
  public static void saveAllDataToCurrentFile() throws IOException, ClassNotFoundException {
    LoadPlayerData.savePlayerData(); LevelBhvr.curLev.saveLevel();
  }

  //endregion

  public static void progressTime(){
    time++;
    if(time==25){time=1;}

    if(LevelBhvr.curLev.levelBg!=null && worldTint!=LevelBhvr.curLev.levelBg){
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!DIDNT WENT TO NULL LEVEL");
      worldTint =LevelBhvr.curLev.levelBg;windowTint.repaint();
      System.out.println("DID WENT TO NULL LEVEL!!!!!!!!!!!!!!!!");

      return;}
    else{LevColors.repaintWorldTint();}

    if(time<=12){
      System.out.println("time: ("+(time)+")AM / 24 Time: ("+time+")  & Color: ("+
          worldTint.getRed()+"/"+ worldTint.getGreen()+"/"+ worldTint.getBlue()+"/"+ worldTint.getAlpha()+")");
    } else{
      System.out.println("time: ("+(time-12)+")PM 24 Time: ("+time+") & Color: ("+
          worldTint.getRed()+"/"+ worldTint.getGreen()+"/"+ worldTint.getBlue()+"/"+ worldTint.getAlpha()+")");
    }
    windowTint.repaint();
  }

}
