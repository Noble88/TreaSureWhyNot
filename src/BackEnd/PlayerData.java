package BackEnd;

import BackEnd.FileManagement.Loaders.LoadPlayerData;
import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GlobalInfo.GlobData;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.LevelObjects.LiveObjs;
import FrontEnd.Colors.LevColors;
import FrontEnd.Debugger;

import java.io.*;

import static BackEnd.GameBehaviors.LevelBhvr.*;


public class PlayerData {
  public static byte[] pos = new byte[]{0,0};
  public static char symFacingFG =' ';
  public static char symFacingBG =' ';
  public static String facing ="N/A";
  public static String plyrWalkables="";

  public static void setPlyrPos(byte[] des, boolean putOnLev){
    curLev.flipE(pos,true);
    pos = new byte[]{des[0],des[1]}; //NEED TO CHANGE THIS LATER
    if(putOnLev){curLev.setE(pos,'@',true);}
  }
  public static void removePlyrPos(byte[] des){
    curLev.flipE(des,true);
    pos = new byte[]{des[0],des[1]}; //NEED TO CHANGE THIS LATER
  }

  public static void movePlayer(byte[] des) throws IOException, ClassNotFoundException, InterruptedException {
    facing= GlobMeths.findDirection(pos,des);
    /*System.out.println("char facing:"+ curLev.getE(des,false)+" cur walkables"+curLev.levelWalkables+
        "\n Global walkables:"+GlobData.walkables);*/

    //normal collision
    checkIfPlayerHitLiveObject(des);
    if(checkIfPlayerWillWalkOnValidGround(des));

    //level switching collision
    if(checkIfPlayerHitACollidableBorder(des) || checkIfPlayerHitLevelSwitch(des)){//
      LevColors.repaintWorldTint();}

    //Find symbol facing
    byte[] temp = GlobMeths.findCords(pos,facing);
    symFacingFG = curLev.levFG[temp[0]][temp[1]]; //update what symbol player facing
    symFacingBG = curLev.levBG[temp[0]][temp[1]]; //update what symbol player facing

    Debugger.playerDebugger();
  }
  //region Movement Checks & Exicution
  public static void checkIfPlayerHitLiveObject(byte[] des) throws IOException, InterruptedException, ClassNotFoundException {
    //Explination: checks to see if it is about to hit a symbol that represents a
    // live object & if that object is null. if so run the collision code of that live object
    if((curLev.liveObjSyms.contains(Character.toString(curLev.getE(des,true))))&&
        (LiveObjs.findLiveObjWithDes(des)!=null)){
      LiveObjs.findLiveObjWithDes(des).playerCollide();
      //TODO MAKE: maybe live object can play sound (make new method or type of live object that produce sound on hit)
    }
  }
  public static boolean checkIfPlayerWillWalkOnValidGround(byte[] des){
    if(GlobData.walkables.indexOf(curLev.levFG[des[0]][des[1]])!=-1){ //Can player walk on surface
      curLev.flipE(pos,true);//deletePlayer
      curLev.setE(new byte[]{des[0],des[1]},'@',true); //add player
      pos = new byte[]{des[0],des[1]}; //update player location
      //TODO MAKE: can have identify what the current tile player on and make a sound
      return true;
    }
    return false;
  }
  public static boolean checkIfPlayerHitACollidableBorder(byte[] des) throws IOException, ClassNotFoundException {
    if(!(switchLevCheck(des).equals("N/A")) && curLev.swapLevOnBrdrHit){ //Did the player hit a board && is level bhvr allowed to switch on hitboard
      curLev.flipE(pos, true);
      curLev.levBrdrSwitch();
      byte[] newPlyrPos = levShiftPlyrPos();
      setPlyrPos(new byte[]{newPlyrPos[0],newPlyrPos[1]}, true);
      LoadPlayerData.savePlayerData();
      Debugger.levelDebugger();
      return true;
    }
    return false;
  }
  public static boolean checkIfPlayerHitLevelSwitch(byte[] des) throws IOException, ClassNotFoundException {
    for(byte i=0; i< curLev.subLevLoc.size(); i++){
      if(des[0]==curLev.subLevLoc.get(i)[0] && des[1]==curLev.subLevLoc.get(i)[1]){
        setPlyrPos(curLev.spawnPoints.get(i),false);//will delete and ressign pos var
        curLev.levPosSwitch(i);
        curLev.setE(pos,'@',true);
        Debugger.levelDebugger();
        return true;
      }
    }
    return false;
  }
  //endregion
  //region switching level related functions


  public static String switchLevCheck(byte[] des){
    //if destination is the boarder of the level
    if      (des[0]==0)     {return"UP";   }
    else if (des[0]==(yB-1)){return"DOWN"; }
    else if (des[1]==0)     {return"LEFT"; }
    else if (des[1]==(xB-1)){return"RIGHT";}
    else{return "N/A";}
  }
  public static byte[] levShiftPlyrPos(){
    switch(facing){
      case "UP"  ->{return new byte[]{(byte) (pos[0]+(xB-3)),pos[1]};}
      case "DOWN"->{return new byte[]{(byte) (pos[0]-(xB-3)),pos[1]};}
      case "LEFT"    ->{return new byte[]{pos[0],(byte) (pos[1]+(yB-3))};}
      case "RIGHT"  ->{return new byte[]{pos[0],(byte) (pos[1]-(yB-3))};}
      default -> {System.out.println("HUGE ERROR IN (PlayerData->levShiftPlyrPos) BAD DIRECTION");
        return new byte[]{pos[0],pos[1]};}
    }
  }

  public static void sendPlayerToNewLevel(byte[] des, String levFilePath) throws IOException, ClassNotFoundException {
    LevelBhvr.curLev.saveLevel();
    setPlyrPos(des,false);
    curLev.saveLevel();
    curLev.goToNewLevel(levFilePath);
    curLev.setE(pos,'@',true);
    curLev.saveLevel();
    LoadPlayerData.savePlayerData();
    Debugger.levelDebugger();    Debugger.playerDebugger();
  }
  //endregion

}
