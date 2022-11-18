package BackEnd.GameBehaviors;

import BackEnd.GameLoop;
import BackEnd.GlobalInfo.GlobMeths;
import BackEnd.PlayerData;

import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;

public class GridBhvr {
  public static void inputDirector() throws InterruptedException, IOException, ClassNotFoundException, MidiUnavailableException {
    switch (GameLoop.associatedKey) { //When in |LEVEL| state
      case "UP", "DOWN", "LEFT", "RIGHT"-> {
        PlayerData.movePlayer(GlobMeths.findCords(PlayerData.pos,GameLoop.associatedKey));
        TBoxBhvr.clearTextBox();
      }
      case "INTERACT" -> {LevelBhvr.curLev.runInputObjects();}
      case "SWAP TO TABS" -> {TBoxBhvr.clearTextBox(); GameLoop.gameState="SIDE WINDOW";}
    }
  }
}
