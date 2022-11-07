package FrontEnd.Managers.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class KeyBindingsMangr {
  public static void displayKeyBindings() throws IOException {
    formPage((byte) 1);
  }
  public static void formPage(byte page) throws IOException {
    ScreenCoverMangr.clearGrid();
    File keyBinds = new File(fileFinder.get("KeyBinds"));
    Scanner sc = new Scanner(keyBinds);
    for(byte i=0; sc.hasNextLine() && (i<ScreenCoverMangr.yB-2) && i<KeyBinds.maxLine; i++){
      ScreenCoverMangr.displayLine((byte) (i+1),KeyBinds.getLine((page-1)*(ScreenCoverMangr.yB-2)+i));
    }
  }
}
