package BackEnd.GameBehaviors;

import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.LevelObjects.InputObjs;
import BackEnd.LevelObjects.LiveObjs;
import BackEnd.PlayerData;
import BackEnd.FileManagement.Loaders.LoadLevels;
import FrontEnd.Colors.LevBg;
import FrontEnd.Colors.LevColors;
import FrontEnd.Debugger;
import FrontEnd.Managers.LevMangr;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class LevelBhvr {
  public static final byte xB=20,yB=20;
  public static Level curLev = null;

  //public static ArrayList<byte[]> levLayer = new ArrayList<>(){new byte{0,0.txt}}
  static public class Level implements Serializable {
    //region declare and initialize variables
    //region ---Basic Level Components---
    public String name=""; public String[]contacts = new String[4]; public String selfPath="";
    public char[][] levFG = new char[yB][xB], levBG = new char[yB][xB];
    public ArrayList<byte[]> pos = new ArrayList<>();
    public String levelWalkables="";
    public boolean swapLevOnBrdrHit = true;
    public ArrayList<String> autoDrawList= new ArrayList<>();
    public Color levelBg = null;
    //endregion
    //region ---Sub Level / Switch Components---
    public ArrayList<byte[]> subLevLoc = new ArrayList<>();
    public ArrayList<String> subLevPath = new ArrayList<>();
    public ArrayList<byte[]> spawnPoints = new ArrayList<>();
    //endregion
    //region ---Objects Related---
    public ArrayList<InputObjs> inputObjs = new ArrayList<>(); //Make this a 2D array

    public String liveObjSyms="";
    public ArrayList<LiveObjs> liveObjs = new ArrayList<>();
    //endregion

    //region Level Construction
    public Level(ArrayList<byte[]> pos, String name, char[][] arr){//declaration for OverWorld Leve
      //region Basic Level assignment
      this.name=name;
      for(byte i=0; i<pos.size(); i++){
        this.pos.add(new byte[]{pos.get(i)[0],pos.get(i)[1]});
      }
      for(byte y = 0; y<yB;y++){
        for(byte x=0; x<xB;x++){
          levFG[y][x]=arr[y][x]; //Seems inefficient to make the bg to fg when its not needed but idk how to check if 2d arr pos is empty
          levBG[y][x]=arr[y][x];
        }
      }
      //endregion
      //region adding file properties
      selfPath= LoadLevels.getLevelPath(pos,0,0);
      contacts[0]=LoadLevels.getLevelPath(pos,0,-1); //LEFT LEVEL PATH
      contacts[1]=LoadLevels.getLevelPath(pos,-1,0); //UP LEVEL PATH
      contacts[2]=LoadLevels.getLevelPath(pos,1,0);//DOWN LEVEL PATH
      contacts[3]=LoadLevels.getLevelPath(pos,0,1); //RIGHT LEVEL PATH
      Debugger.showLevelContacts(name,contacts);

      //endregion
    }
    public void assignUniqueBhvr(String levWlk, boolean chngOnBrdCol){
      levelWalkables=levWlk;
      //System.out.println("LEVEL: "+name+"   walkables:"+levelWalkables);
      swapLevOnBrdrHit =chngOnBrdCol;
    }
    public void addBackgroundColor(Color col){levelBg=col;}
    public void assignLevelSwitch(byte[] subLevLoc, byte[] spawn, ArrayList<byte[]> path){
      this.subLevLoc.add(new byte[]{subLevLoc[0],subLevLoc[1]});
      subLevPath.add(LoadLevels.getLevelPath(path,0,0));
      spawnPoints.add(new byte[]{spawn[0],spawn[1]});

      //Mini debugger for method
      /*
      System.out.print("\nLEVEL SWITCH METHOD DEBUG-----------------------\nLevel:"+name+"\nthis.subLevLoc");
      for(byte i=0; i<this.subLevLoc.size();i++){
        System.out.print(Arrays.toString(this.subLevLoc.get(i)));
      }
      System.out.println("\nsubLevLoc: "+Arrays.toString(subLevLoc)+"\nsubLevPath: "+subLevPath+"\nSpawn: ");
      for(byte i=0; i<spawnPoints.size();i++){
        System.out.print(Arrays.toString(spawnPoints.get(i)));
      }
      */
    }
    public void assignAutoDrawing(ArrayList<String> arr){autoDrawList.addAll(arr);}
    //endregion

    //region Level Switching & Management
    //region Level Swapping
    public void levBrdrSwitch() throws IOException, ClassNotFoundException {
      if(!swapLevOnBrdrHit){return;}
      saveLevel();
      goToNewLevel(contacts[directionTonNum(PlayerData.facing)]);
      LevColors.repaintWorldTint();
    }

    public void levPosSwitch(byte loc) throws IOException, ClassNotFoundException {
      saveLevel();
      goToNewLevel(subLevPath.get(loc));
      LevColors.repaintWorldTint();
    }
    //endregion
    //region Level Management
    public void goToNewLevel(String filePath) throws IOException, ClassNotFoundException {
      if(Debugger.fileTracerDebug){Debugger.fileTracer(filePath);}
      ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath));
      curLev = (LevelBhvr.Level)input.readObject();
      input.close();
      GlobMeths.updateWalkables();
      LevMangr.displayWholeGrid();
      LevBg.updateAutoDrawList(autoDrawList);
      LevBg.repaintAll();
    }
    public void saveLevel () throws IOException, ClassNotFoundException{
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(selfPath));
      output.writeObject(curLev);
      output.close();
    }
    //endregion
    //endregion

    //region Methods for Manipulating & Locating Elements in Arrays
    //region Foreground and Background Array Elements
    public byte[] posE  (char e){ byte y,x;
      for(y=1;y<yB;y++){
        for(x=0; x<xB;x++){
          if(levFG[y][x]==e){return new byte[]{y,x};}}}
      return new byte[]{-1,-1};
    }
    public char   getE  (byte[] yx, boolean getFG){
      if(getFG){return levFG[yx[0]][yx[1]];}
      else      {return levBG[yx[0]][yx[1]];}
    }
    public void   setE  (byte[] yx, char e, boolean editFG){
      if(editFG){levFG[yx[0]][yx[1]]=e;}
      else      {levBG[yx[0]][yx[1]]=e;}
    }
    public void   flipE (byte[] yx,boolean flipFG){
      if(flipFG){levFG[yx[0]][yx[1]]=levBG[yx[0]][yx[1]];}
      else      {levBG[yx[0]][yx[1]]=levFG[yx[0]][yx[1]];}
    }
    //endregion
    //region Input Obj Methods
    public InputObjs getInputObj(String name){
      for(byte i=0; i<inputObjs.size(); i++){
        System.out.println("NAME INPUT OBJ matching -> "+name+" to "+ inputObjs.get(i).getName());
        if(inputObjs.get(i).getName().equals(name)){
          System.out.println("MATCH FOUND");
          return inputObjs.get(i);

        }
      }
      System.out.println("MATCH NOTTTT FOUND");
      return null;
    }
    public void removeInputObj(String name){//can delete multiple obj with same name
      for(byte i=0; i<inputObjs.size(); i++){
        if(inputObjs.get(i).getName().equals(name)){
          inputObjs.remove(i); i--;
        }
      }
    }
    //endregion
    //endregion


    public String toString(boolean printFG){
      String tempStr="";
      for(byte y = 0; y<yB;y++){
        for(byte x=0; x<xB;x++){
          if(printFG){tempStr+=levFG[y][x];}
          else       {tempStr+=levBG[y][x];}
        }tempStr+="\n";}
      return tempStr;
    }

    //region Running objects in level
    public void runInputObjects() throws InterruptedException, IOException, ClassNotFoundException {
      for(int i = 0; i< inputObjs.size(); i++){
        inputObjs.get(i).run();}
    }
    public void runLiveObjects() throws InterruptedException, IOException, ClassNotFoundException {
      for(int i = 0; i< liveObjs.size(); i++){
        liveObjs.get(i).run();}
    }
    //endregion
 }

  //region Methods To Help Inside of Level Class Object
  public static byte directionTonNum(String direction){
    switch(direction){
      case "LEFT" ->{return 0;}
      case "UP"   ->{return 1;}
      case "DOWN" ->{return 2;}
      case "RIGHT"->{return 3;}
      default -> {System.out.println("error iN directioN to NUM FUNCTION IN LEVEL BHVR"); return -1;}
    }
  }

  public static String getLevelPos(String filePath){
    return filePath.substring(
         filePath.indexOf("/", filePath.indexOf("Levels")+2)+1);
  }


  //endregion
  //For switch levels could make a 2D array 3 by 3 and move contents to make player always in the middle



/*


*/


}
