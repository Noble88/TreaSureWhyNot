package BackEnd;

import BackEnd.GameBehaviors.GridBhvr;
import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.PauseBhvr;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import BackEnd.GameBehaviors.SideWndwBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.LevelObjects.Events;
import FrontEnd.Colors.LevColors;
import FrontEnd.Window;
import FrontEnd.Managers.LevMangr;

import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds.assosToKey;
import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class GameLoop {
  public static boolean inPlayableState =true, runWorld=true;
  public static String gameState = "LEVEL";
  public static boolean newKeyPressed = false;
  public static String key = "N/A", associatedKey="N/A";
  public static byte tick=0, tickLoop;

  //TODO QAULITY OF LIFE: Could use the global counter and make methods that say in 40 ticks & 5 tickLoops do this action
  // would add the value to the current time stats so can trigger long time based events
  public static void gameLoop() throws IOException, ClassNotFoundException, InterruptedException, MidiUnavailableException {
    if(runWorld){
      if(tick==40){tick=0;
        if(tickLoop==10){tickLoop=0;
          GlobMeths.progressTime();
      }} //In game time
      tick++;
      LevelBhvr.curLev.runLiveObjects();
    }

    if (newKeyPressed) {
      key = Window.keyPressedKeyListner; GameLoop.associatedKeyPressed(key);
      inputDirector();
      associatedKey="N/A"; key = "N/A"; newKeyPressed = false;
      //reset key input variables (prevents repeating unwanted inputs)
    }
    LevMangr.executeDisplayOfGrid();
  }

  public static void inputDirector() throws IOException, ClassNotFoundException, InterruptedException, MidiUnavailableException {
    Journal.checkQuestsForCompletion(); //DONT DO THIS THIS IS A TEST
    if(inPlayableState){
      switch(associatedKey){ //GLOBAL KEY BINDINGS
        case "MAP"     ->{}
        case "JOURNAL" ->{SideWndwBhvr.switchTabs("JOURNAL"); }
        case "TREASURE"->{SideWndwBhvr.switchTabs("TREASURE");}
        case "TOOL"    ->{SideWndwBhvr.switchTabs("TOOLS");   }
        case "PAUSE"   ->{PauseBhvr.togglePauseScreen(); PauseBhvr.justedPaused=true;}
      }
    }
    switch (gameState) {
      case "LEVEL"->{GridBhvr.inputDirector();}
      case "SIDE WINDOW"->{SideWndwBhvr.inputDirector();}
      case "PAUSE"-> {PauseBhvr.inputDirector();}
      case "EVENT"->{if(!Events.relatedEvent.triggerEvent()){Events.deActivateEventState();}}
    }
  }
  //region Get key stuff
  public static void associatedKeyPressed(String key) throws FileNotFoundException {
    associatedKey=assosToKey.getOrDefault(key,"N/A");
  }

  //TODO efficiency: when making hash map for key bindings try figure out a way where can change
  public static String getKeyFromAssociatedKey(String associatedKey) throws FileNotFoundException { //Currently only used in 1 place (is not efficent)
    File keyBinds = new File(fileFinder.get("KeyBinds"));
    Scanner sc = new Scanner(keyBinds);
    while (sc.hasNextLine()) {
      String data = sc.nextLine();
      KeyBinds.isValidLine(data);
      if(KeyBinds.isValidLine(data) && KeyBinds.getAssocKey(data).equals(associatedKey)){ //the actual check to see if is proper key
        sc.close();
        return KeyBinds.getKey(data);
      }
    }
    sc.close();
    return "N/A";
  }
  //endregion
}
