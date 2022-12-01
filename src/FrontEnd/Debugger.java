package FrontEnd;


import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.TresItem;
import BackEnd.GlobalInfo.GlobData;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Managers.TBoxMangr;

import java.util.Arrays;

import static BackEnd.GameBehaviors.LevelBhvr.curLev;
import static BackEnd.GlobalInfo.GlobData.time;
import static BackEnd.GlobalInfo.GlobData.worldTint;


public class Debugger {
  public static boolean showLevelContacts,playerDebug,levelDebug, sideMenuDebug,tBoxDebug,windowDebug,saveDataDebug,
      fileTracerDebug, eventDebug,selectBoxDebug,weatherAndTimeDebug;
  public static void debugGame(boolean player, boolean level,boolean sideMen, boolean tBox,
                               boolean levelContacts, boolean window, boolean saveData, boolean fileTracer,
                               boolean event,boolean selBox,boolean time){
    playerDebug=player; levelDebug=level; sideMenuDebug=sideMen; tBoxDebug=tBox;
    showLevelContacts=levelContacts; windowDebug=window; saveDataDebug=saveData; fileTracerDebug=fileTracer;
    eventDebug =event; selectBoxDebug=selBox; weatherAndTimeDebug=time;}
  public static void showLevelContacts(String name, String[] contacts){if(!showLevelContacts){return;}
    System.out.println("Level:"+name);
    for(int i=0; i<4;i++){
      System.out.println(contacts[i]);
    }
    System.out.println("");
  }
  public static void playerDebugger(){    if(!playerDebug){return;}
    System.out.println("\n-------------PLAYER DEBUGGER-------------\n" +
        "Player Location:(Y:"+ BackEnd.PlayerData.pos[0]+",X:"+BackEnd.PlayerData.pos[1]+")  |  " +
        "Symbol Facing Foreground:("+BackEnd.PlayerData.symFacingFG +")  |  Symbol Facing Background:("+BackEnd.PlayerData.symFacingBG + ")  |  " +
        "Direction Facing:("+BackEnd.PlayerData.facing+")  |  Walkables:("+BackEnd.PlayerData.plyrWalkables+") ");
  }
  public static void levelDebugger(){    if(!levelDebug){return;}
    System.out.println("\n-------------LEVEL DEBUGGER-------------\n" +
        "Name:("+curLev.name+")  |  Path:"+curLev.selfPath+"\n" +
        "Walkable Chars:("+curLev.levelWalkables+")  |  Boarder Collision:("+curLev.swapLevOnBrdrHit +")"
    );
  }
  //TODO LATER SUGGESTION -> make it so displays item info upon reciving the item insead of printing each time nav hover over it (maybe toggle extensive mode)
  //region Bag Debuggers
  public static void toolDebugger(){    if(!sideMenuDebug){return;}
    System.out.print("\n-------------TOOL BAG DEBUGGER-------------\n" +
        "PAGE:("+ToolBag.menu.getCurPg()+")  |  Current Tile:"+ Arrays.toString(ToolBag.menu.getNav())+" |     ");

    Tools tool = ToolBag.curPage.itemGrid[ToolBag.menu.getNav()[0]][ToolBag.menu.getNav()[1]];
    if(tool!=null){
    System.out.println("Current Selection -> Name:("+tool.getName()+")  |  Position:("+Arrays.toString(tool.getPos())+")  |  "+
        "Quantity:("+tool.getQuantity()+")  |  Value:("+tool.getValue()+")");//basic descriptions
      System.out.println("Description:"+tool.getDescr());
      char[][] tempchar = tool.getIcon();
      for(byte i=0; i<tempchar.length; i++){ String line="";
        for(byte k=0;k<tempchar[i].length;k++){line+=" "+tempchar[i][k];}
        System.out.println(line);
      }
    }else{System.out.println("Current Selection -> Null");}
  }
  public static void toolDebugger(boolean add, Tools tool){    if(!sideMenuDebug){return;}
    System.out.print("\n-------------TOOL ITEM DEBUGGER-------------\n");
    if(tool!=null){
      if(add) {System.out.print("Attempting to Add -> ");
      }else{System.out.print("Attempting to delete -> ");}
      System.out.println("Name:("+tool.getName()+")  |  Position:("+Arrays.toString(tool.getPos())+")  |  "+
          "Quantity:("+tool.getQuantity()+")  |  Value:("+tool.getValue()+")");//basic descriptions
      System.out.println("Description:"+tool.getDescr());
      char[][] tempchar = tool.getIcon();
      for(byte i=0; i<tempchar.length; i++){ String line="";
        for(byte k=0;k<tempchar[i].length;k++){line+=" "+tempchar[i][k];}
        System.out.println(line);
      }
    }else{System.out.println("Current Selection -> Null");}
  }
  public static void tresDebugger(){    if(!sideMenuDebug){return;}
    System.out.print("\n-------------TREASURE BAG DEBUGGER-------------\n" +
        "PAGE:("+ TresBag.menu.getCurPg()+")  |  Current Tile:"+ Arrays.toString(TresBag.menu.getNav())+" |     ");

    TresItem tres = TresBag.curPage.itemGrid[TresBag.menu.getNav()[0]][TresBag.menu.getNav()[1]];
    if(tres!=null){
      System.out.println("Current Selection -> Name:("+tres.getName()+")  |  Position:("+Arrays.toString(tres.getPos())+")  |  "+
          "Quantity:("+tres.getQuantity()+")  |  Value:("+tres.getValue()+")");//basic descriptions
      System.out.println("Description:"+tres.getDescr());
      char[][] tempchar = tres.getIcon();
      for(byte i=0; i<tempchar.length; i++){String line="";
        for(byte k=0;k<tempchar[i].length;k++){line+=" "+tempchar[i][k];}
        System.out.println(line);
      }
    }else{System.out.println("Current Selection -> Null");}
  } //For Bag Navigation
  public static void tresDebugger(boolean add, TresItem tres){    if(!sideMenuDebug){return;}
    System.out.print("\n-------------TREASURE ITEM DEBUGGER-------------\n");
    if(tres!=null){
      if(add) {System.out.print("Attempting to Add -> ");
      }else{System.out.print("Attempting to delete -> ");}
      System.out.println("Name:("+tres.getName()+")  |  Position:("+Arrays.toString(tres.getPos())+")  |  "+
          "Quantity:("+tres.getQuantity()+")  |  Value:("+tres.getValue()+")");//basic descriptions
      System.out.println("Description:"+tres.getDescr());
      char[][] tempchar = tres.getIcon();
      for(byte i=0; i<tempchar.length; i++){String line="";
        for(byte k=0;k<tempchar[i].length;k++){line+=" "+tempchar[i][k];}
        System.out.println(line);
      }
    }else{System.out.println("Current Selection -> Null");}
  } //For Item Changing

  //endregion
  public static void JrnlDebugger(){    if(!sideMenuDebug){return;}
    System.out.print("\n-------------JOURNAL BAG DEBUGGER-------------\n" +
        "PAGE:("+ Journal.menu.getCurPg()+")  |  Current Tile:"+ Arrays.toString(Journal.menu.getNav()));
    if(Journal.getHighlightedQuest()!=null){
      System.out.println(
          "HIGHLIGHTED QUEST ->  NAME: ("+Journal.getHighlightedQuest().name+")  |  " +
              "TaskNum: ("+Journal.getHighlightedQuest().taskNum+")  |  "
          //"Is Current Task Completed: ("+Journal.getHighlightedQuest().getCurTask().getIsComplete()+")"
      );}
    else{System.out.println("NO QUEST IN THIS SLOT");}
  }
  //region Text Box Debugger
  public static void createTextDebugger(int speed, String text){if(!tBoxDebug){return;}
    System.out.println("\n-------------Create Text DEBUGGER-------------\n"+
        "Speed:("+speed+")  |  Text:"+text);
  }
  public static void updateTBoxLineDebugger(byte line,String text){if(!tBoxDebug){return;}
    System.out.println("\n-------------Create Text DEBUGGER-------------\n"+
        "Line:("+line+")  |  Text:"+text);
  }

  //endregion
  public static void windowDebugger(){
    System.out.println("\n-------------WINDOW DEBUGGER-------------");
    System.out.println("Window Size -> Width:("+Window.windowSize[0]+")  |  Height:("+Window.windowSize[1]+")");
    System.out.println("Level Grid -> Tile Width:("+ LevelBhvr.xB +")  |  "+"Tile Height:("+LevelBhvr.yB +")  |  "+
        "Window Size -> X:("+Window.levBnds[0]+")  |  "+" Y:("+Window.levBnds[1]+")  |  "+
        "Width:("+Window.levBnds[2]+")  |  "+" Height:("+Window.levBnds[3]+")  |  ");
    System.out.println("Text Box -> Tile Width:("+ TBoxMangr.xB +")  |  "+"Tile Height:("+TBoxMangr.yB +")  |  "+
        "Window Size -> X:("+Window.tbBnds[0]+")  |  "+" Y:("+Window.tbBnds[1]+")  |  "+
        "Width:("+Window.tbBnds[2]+")  |  "+" Height:("+Window.tbBnds[3]+")  |  ");
    System.out.println("Side Window -> Tile Width:("+ SideWndwMangr.xB +")  |  "+"Tile Height:("+SideWndwMangr.yB +")  |  "+
        "Window Size -> X:("+Window.swBnds[0]+")  |  "+" Y:("+Window.swBnds[1]+")  |  "+
        "Width:("+Window.swBnds[2]+")  |  "+" Height:("+Window.swBnds[3]+")  |  ");
    System.out.println("Screen Cover -> Tile Width:("+ ScreenCoverMangr.xB +")  |  "+"Tile Height:("+ScreenCoverMangr.yB +")  |  "+
        "Window Size -> X:("+Window.scBnds[0]+")  |  "+" Y:("+Window.scBnds[1]+")  |  "+
        "Width:("+Window.scBnds[2]+")  |  "+" Height:("+Window.scBnds[3]+")  |  ");
  }
  public static void savedData(byte[] posOfPlyr, String levelSaved){
    System.out.println("\n-------------SAVED DATA DEBUGGER-------------");
    System.out.println("Position Saved -> (Y:"+posOfPlyr[0]+" , X:"+posOfPlyr[1]+")  |  Level File Saved -> "+levelSaved);
  }
  public static void fileTracer(String file){
    System.out.println("\n-------------FILE TRACER/TRACKER DEBUGGER-------------");
    System.out.println("File Pulled -> "+file);
  }
  public static void weatherAndTimeDebugger(){
    System.out.print("\n-------------WEATHER AND TIME DEBUGGER-------------\n" +
        "Current Weather: ("+ GlobData.weather+")  |  ");
    if(time<=12){
      System.out.println("[12h Time: ("+(time)+")AM] | [24 Time: ("+time+")]  & Color RBGA: ("+
          worldTint.getRed()+"/"+ worldTint.getGreen()+"/"+ worldTint.getBlue()+"/"+ worldTint.getAlpha()+")");
    } else {
      System.out.println("[12h Time: ("+(time-12)+")AM] | [24 Time: ("+time+")]  & Color RBGA: ("+
          worldTint.getRed()+"/"+ worldTint.getGreen()+"/"+ worldTint.getBlue()+"/"+ worldTint.getAlpha()+")");
    }
  }

  //TODO: make the debugger so I can teleport to any location I want <-- could be gate way to fast travel mechanic

}
