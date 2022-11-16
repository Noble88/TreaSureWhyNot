package FrontEnd;

import BackEnd.FileManagement.Loaders.LoadBag;
import BackEnd.FileManagement.Loaders.LoadPlayerData;
import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import BackEnd.GameBehaviors.SideWndwBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl.Quest;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Jrnl.Tasks;
import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.GameLoop;
import BackEnd.GlobalInfo.GlobData;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import BackEnd.LevelObjects.Events;
import BackEnd.LevelObjects.InputObjs;
import BackEnd.LevelObjects.LiveObjs;
import BackEnd.PlayerData;
import BackEnd.FileManagement.Loaders.LoadLevels;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.TresItem;
import FrontEnd.Colors.LevBg;
import FrontEnd.Colors.SideWindowBg;
import FrontEnd.Managers.LevMangr;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Managers.TBoxMangr;
//import FrontEnd.Managers.TBoxMangr;

import java.io.IOException;
import java.util.ArrayList;
//NOTE: version naming goes as following relase.majorAddition.Patch
//TODO SENDING IT OUT: before sharing final results: toggle saving in settings / make exe file / bug check... / help menu / TBA
//TODO EFFICIENCY: organize imports to reduce loading time
//TODO CODING: make it so all "static finals" are put in all caps
public class Runner {
  public static String curFilePath=System.getProperty("user.dir");
  public static boolean settingUp=true;

  /*
  -----Things to Note before running-----
  -If: Changed Location of a Level Pos Swap (DELETE LEVEL FILES)
  -If: Modifed A level layout or minipulated files (DELETE LEVELS FILES)

   */
  public static boolean deleteFilesOnStart=true;


  public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
    System.out.println("PROGRAM START!!! (inside of: "+curFilePath);

    //region Load Data
    GlobData.warmUpGlobData(); //MOST IMPORTANT
    LoadLevels.loadAndValidateLevels();
    LoadBag.loadBag();
    //endregion

    //region Warm Up Data
    Window.warmUpWindowClass();
    Window.warmUpKeyListener();


    if(!deleteFilesOnStart){LoadLevels.levelSelect(LoadPlayerData.getSavedLevelFileDirectoryPlayerIn());} //grab the level that the player is on
    else{LoadLevels.levelSelect(curFilePath+"/src/BackEnd/FileManagement/SavedData/Assets/Levels/LivelyLeafs/0,0.txt");}

    Debugger.debugGame(false,true,true,true,
        false,false,true,false,false,false);

    ScreenCoverMangr.warmUpScreenCover();
    KeyBinds.warmUpSettings();
    TBoxMangr.warmUpTBoxMangr();
    SideWndwMangr.warmUpSideWndwMangr();
    LevBg.warmUpLevelBackground();
    SideWindowBg.warmUpSideWindowBackground();
    //endregion
    //region Make the screen presentable
    LevMangr.displayWholeGrid();
    LevBg.updateAutoDrawList(LevelBhvr.curLev.autoDrawList);
    GlobMeths.updateWalkables();
    if(!deleteFilesOnStart){ //RESUMED SAVE DATA
      PlayerData.setPlyrPos(LoadPlayerData.getSavedPlayerPosition(),true);//sets player location using PlayerData File
      //region assigned side window player data
      ToolBag.menu.setCurPg(LoadPlayerData.getSavedBagPage(true));
      byte[] toolNav=LoadPlayerData.getSavedBagNav(true);ToolBag.menu.setNav(toolNav[0],toolNav[1]);
      TresBag.menu.setCurPg(LoadPlayerData.getSavedBagPage(false));
      byte[] tresNav=LoadPlayerData.getSavedBagNav(false);TresBag.menu.setNav(tresNav[0],tresNav[1]);
      SideWndwBhvr.switchTabs(LoadPlayerData.getSavedTab());//sets the side window using PlayerDate File (NOTE: done at end so refresh w/relevant data
      //endregion
    }
    else{
      SideWndwBhvr.switchTabs("TOOLS");
      PlayerData.setPlyrPos(new byte[]{14,4},true);
      //region modifyable start

      //region add Quests

      //endregion
      //region test live and input objects

      LiveObjs obj1 = new LiveObjs.FullCordMvt('P',new byte[]{5,4}, (byte) 20,
          new ArrayList<byte[]>(){{ add(new byte[]{2,4}); add(new byte[]{2,5});
            add(new byte[]{5,5}); add(new byte[]{5,4});}});
      obj1.addWalkableProp(true,"");
      obj1.addPlayerCollisionProp(new Events.BscText("PLAYER COLLIDED"));
      LevelBhvr.curLev.liveObjs.add(obj1);

      LiveObjs obj2 = new LiveObjs.FullCordMvt('K',new byte[]{15,6}, (byte) 20,
          new ArrayList<byte[]>(){{ add(new byte[]{17,6}); add(new byte[]{17,8});
            add(new byte[]{15,8}); add(new byte[]{15,6});}});
      obj2.addWalkableProp(true,"");
      obj2.addPlayerCollisionProp(new Events.KillPlayerToHome());
      LevelBhvr.curLev.liveObjs.add(obj2);

      InputObjs obj3 = new InputObjs.visibleObj("obj3",new byte[]{13,5},'k', new Events.KillPlayerToHome());
      LevelBhvr.curLev.inputObjs.add(obj3);

      InputObjs obj4 = new InputObjs.visibleObj("obj4",new byte[]{13,6},'T', new Events.BscText("TEST TEXT"));
      LevelBhvr.curLev.inputObjs.add(obj4);

      InputObjs obj44 = new InputObjs.visibleObj("obj44",new byte[]{16,6},'t', new Events.BscText(
          "THIS IS TO SEE HOW TEXT IS SEPARATED AS WELL AS WHAT I LOOKS LIKE WHEN IT GOES TO THE NEXT PAGE" +
              "AND HAS A WAIT TIME/DELAY TO INSURE THAT THE PLAYER CAN KEEP UP WITH THE TEXT SPEED"

      ));
      LevelBhvr.curLev.inputObjs.add(obj44);

      InputObjs obj5 = new InputObjs.visibleObj("obj5",new byte[]{13,7},'s', new Events.SelectBox(
          new ArrayList<Events>(){{add(new Events.BscText("1!")); add(new Events.BscText("2!"));
            add(new Events.BscText("3!"));}},
          new ArrayList<String>(){{add("choice1"); add("choice2");add("choice3");}}));
      LevelBhvr.curLev.inputObjs.add(obj5);

      InputObjs megaSelectObj = new InputObjs.visibleObj("megaSelectObj",new byte[]{13,8},'S', new Events.SelectBox(
          new ArrayList<Events>(){{
            add(new Events.BscText("This what the is the basic text event looks like"));
            add(new Events.GetTool("This is the pretext notifying u got an tool",
                new Tools.BasicTool("Snow TEST", "SNOOOO SOGVEL", 1, new char[][]{
                    {'|', ' ', ' ', '-', '\\'},
                    {'|', '-', '-', '|', '|' },
                    {'|', ' ', ' ', '-', '/' }})));
            add(new Events.GetTres("This is the pretext to notify player that they have gotten a Treasure",
                new TresItem.BasicTre("TEST THING","THIS IS A TEST",100, new char[][]{
                    {'+','-'},
                    {'-','+'}})));
            add(new Events.KillPlayerToHome());
            add(new Events.BscText("5!"));
            add(new Events.BscText("6!"));}},
          new ArrayList<String>(){{
            add("Test Text");
            add("Get Tool");
            add("Get Treasure");
            add("Kill Player");
            add("Option5");
            add("Option6");}}));
      LevelBhvr.curLev.inputObjs.add(megaSelectObj);

      //endregion
      //region test items
        TresBag.addItem(new TresItem.BasicTre("TEST THING","THIS IS A TEST",100,
            new char[][]{ {'+','-'},{'-','+'}}));
        TresBag.addItem(new TresItem.BasicTre("TEST THING","THIS IS A TEST",100,
            new char[][]{ {'2','2'},{'2','2'}}));
        TresBag.addItem(new TresItem.BasicTre("TEST THING","THIS IS A TEST",100,
            new char[][]{ {'3','3'},{'3','3'}}));


      ToolBag.addItem(new Tools.BasicTool("Shovel", "A trusty shovel", 1,
          new char[][]{
              {' ', ' ', ' ', ' ', ' '},
              {'|', '=', '=', '[', ']' },
              {' ', ' ', ' ', ' ', ' ' }}));

      ToolBag.addItem(new Tools.BasicTool("Stick", "This is a stick", 10,
          new char[][]{
              {' ', ' ', ' ', ' ', '/'},
              {' ', ' ', '-', '-', ' ' },
              {' ', '/', ' ', ' ', ' ' }}));
      ToolBag.addItem(new Tools.BasicTool("Pebble", "Yup it sure is", 100,
          new char[][]{
              {' ', ' ', ' ', ' ', ' '},
              {' ', ' ', ' ', '0', ' ' },
              {' ', ' ', ' ', ' ', ' ' }}));
      //endregion
      //endregion
    }
    if(Debugger.windowDebug){Debugger.windowDebugger();}

    settingUp=false;
    LoadPlayerData.savePlayerData(); //save game
    while(true){
      GameLoop.gameLoop();
      Thread.sleep(25);
    }
  }
}


//TODO LATER: make a one handed option
