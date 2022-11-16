package BackEnd.LevelObjects;

import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameLoop;
import BackEnd.PlayerData;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LiveObjs implements Serializable{

  //TODO BUG & EFFICENCY: MAKE IT SO instead of having remvoe self command make it can have a version
  // that can take event that triggers when collition and could delete self
  //This Obj Means its fully interactable & has collition
  public static class FullCordMvt extends LiveObjs implements Serializable {
    //region Object Creation Variables
    public byte[] pos; public byte speed;
    public ArrayList<byte[]> mvtCommands = new ArrayList<>(); public char sym; //Necessary Creation Vars
    public byte stage=0; //Level Movement Aid
    //endregion
    //region Properties
    public boolean walkablesOnly=true; public String objWalkables ="";//walkable properties
    public Events event=null;

    public FullCordMvt(char sym, byte[] pos, byte speed, ArrayList<byte[]> mvtCmds) {
      this.pos = new byte[]{pos[0],pos[1]};    this.sym = sym;    this.speed=speed;
      LevelBhvr.curLev.liveObjSyms+=sym;
      this.mvtCommands.addAll(mvtCmds);
      LevelBhvr.curLev.setE(pos,sym,true);//add symbol to level
    }

    //region walkable stuff
    public void addWalkableProp(boolean walkablesOnly, String addWalkables){
      this.walkablesOnly=walkablesOnly; objWalkables=addWalkables;
    }
    public boolean canWalk(byte[] des){
      /*System.out.println("WALKABLES:"+objWalkables+LevelBhvr.curLev.levelWalkables+"    compairing"+
          Character.toString(LevelBhvr.curLev.getE(des,true)));*/
      return (objWalkables+LevelBhvr.curLev.levelWalkables).contains(
          Character.toString(LevelBhvr.curLev.getE(des,true)));

    }
    //endregion
    //region Collision stuff
    public void addPlayerCollisionProp(Events event){
      this.event = event;
    }
    public void playerCollide() throws InterruptedException, IOException, ClassNotFoundException {
      if(event!=null){if(event.triggerEvent()){Events.initiateEventState(event);}
      }
      else{System.out.println("NULL EVENT");}
    }
    //endregion
    //region Other overRided Methods
    public void removeSelf() {
      for(int i=0; i<LevelBhvr.curLev.liveObjs.size(); i++){
        byte[] objPos=LevelBhvr.curLev.liveObjs.get(i).getPos();
        if(objPos[0]==pos[0] && objPos[1] == pos[1]){
          LevelBhvr.curLev.flipE(pos,true);
          LevelBhvr.curLev.liveObjs.remove(i);
          String liveSyms=LevelBhvr.curLev.liveObjSyms;
          LevelBhvr.curLev.liveObjSyms=
              liveSyms.substring(0,liveSyms.indexOf(sym))+liveSyms.substring(liveSyms.indexOf(sym)+1);
          return;
        }
      }
    }
    public byte[] getPos(){return pos;}
    //endregion

    //region Movement Helper Methods
    public void progressStage(){
      if(stage+1==mvtCommands.size()){stage=0;}
      else{stage++;}
    }
    public byte[] getDes(){
      byte y=pos[0], x=pos[1];
      /*
      System.out.println("MVMT CMNDS= "+ Arrays.toString(mvtCommands.get(stage))+"    cur Pos->"
          + Arrays.toString(pos)+"    Stage: "+stage);

       */
      if(mvtCommands.get(stage)[1]!=pos[1] && mvtCommands.get(stage)[0]!=pos[0]){
        System.out.println("in get Des attempted to move diagonally ");return pos;}
      if(mvtCommands.get(stage)[1]==pos[1] && mvtCommands.get(stage)[0]==pos[0]){progressStage();}

      if(mvtCommands.get(stage)[1]==pos[1]){ //if on same x-axis
        if(mvtCommands.get(stage)[0]<pos[0]){y--;} //des is above player
        else{y++;}
      }
      if(mvtCommands.get(stage)[0]==pos[0]){ //if on same x-axis
        if(mvtCommands.get(stage)[1]<pos[1]){x--;} //des is above player
        else{x++;}
      }
      return new byte[]{y,x};
    }
    public void move(byte[] des){
      LevelBhvr.curLev.flipE(pos,true);
      LevelBhvr.curLev.setE(des,sym,true);
      pos[0]=des[0]; pos[1]=des[1];
    }
    //endregion
    public void run() throws InterruptedException, IOException, ClassNotFoundException {
      if(GameLoop.tick%speed!=0){return;}
      byte[] des = getDes();
      if(des[0] == PlayerData.pos[0] && des[1] == PlayerData.pos[1]){playerCollide();}
      else if(canWalk(des)){move(des);}
    }

  }

  //region Object Helper Methods
  public static LiveObjs findLiveObjWithDes(byte[] pos){
    for(int i=0; i<LevelBhvr.curLev.liveObjs.size(); i++){
      byte[] objPos=LevelBhvr.curLev.liveObjs.get(i).getPos();
      if(objPos[0]==pos[0] && objPos[1] == pos[1]){
        return LevelBhvr.curLev.liveObjs.get(i);
      }
    }
    return null;
  }
  //endregion

  //region Important Exicution Methods
  public void run() throws InterruptedException, IOException, ClassNotFoundException {}
  public void playerCollide() throws InterruptedException, IOException, ClassNotFoundException {}
  //endregion

  //region adding properties
  public void addWalkableProp(boolean walkablesOnly, String addWalkables){}
  public void addPlayerCollisionProp(Events event){}
  //endregion

  //region Other Overridden Methods
  public byte[] getPos(){return new byte[]{-1,-1};}
  public void removeSelf() {}
  //endregion
}
