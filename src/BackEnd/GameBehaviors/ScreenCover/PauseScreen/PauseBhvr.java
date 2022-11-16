package BackEnd.GameBehaviors.ScreenCover.PauseScreen;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.Help;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.Video;
import BackEnd.GlobalInfo.GlobMeths;
import FrontEnd.Managers.ScreenCover.PauseScreen.PauseMangr;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.HelpManagr;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.KeyBindingsMangr;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.VideoMangr;
import FrontEnd.Window;

import java.io.IOException;

import static BackEnd.GameLoop.*;

public class PauseBhvr {
  public static String menuIn="N/A";
  public static boolean paused=false, justedPaused=false, inSubMenu=false;
  public static String gameStateHolder="N/A";

  public static void inputDirector() throws IOException, InterruptedException, ClassNotFoundException {
    if(KeyBinds.awaitingInput){KeyBinds.keyBindModifier(); return;}

    if(!justedPaused && PauseBhvr.paused && !inSubMenu && associatedKey.equals("PAUSE")){
      togglePauseScreen();}

    if(inSubMenu) {
      switch (menuIn) {
        case "Video" -> {Video.keyBindNav();}
        //case "Audio" -> {}
        case "Key Bindings"->{KeyBinds.keyBindNav();}
        case "Help"->{Help.keyBindNav();}
        //case "Dev" -> {}

      }
    }

    else{
      inSubMenu=true;
      switch (key) {
        case "1" -> {VideoMangr.showPage();menuIn="Video";}
        case "2" -> {/*AUDIO SETTINGS*/}
        case "3" -> {KeyBindingsMangr.displayKeyBindings();menuIn="Key Bindings";}
        case "4" -> {HelpManagr.showPage1();menuIn="Help";}
        case "5" -> {/*DEV STUFF*/}
        case "6" -> {GlobMeths.saveAllDataToCurrentFile(); inSubMenu=false;}
        default ->  {inSubMenu=false;}
      }
    }
    //help / settings / custom / dev info
    justedPaused=false;
  }
  public static void togglePauseScreen(){
    if(paused){gameState=gameStateHolder;}
    else {gameStateHolder=gameState; gameState="PAUSE"; PauseMangr.displayOptions();}
    inPlayableState=!inPlayableState; // Note: can't pause when not in a playable state cause precondition in gameloop
    runWorld=paused;
    paused=!paused;
    Window.screenCover.setVisible(paused);
  }
  public static void leaveSubMenu(){
    PauseMangr.displayOptions();
    PauseBhvr.inSubMenu=false; PauseBhvr.justedPaused=true;
  }

}
