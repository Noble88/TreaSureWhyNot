package FrontEnd.Managers.ScreenCover.PauseScreen;

import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;

import java.io.FileNotFoundException;

public class PauseMangr {
  public static void displayOptions(){
    ScreenCoverMangr.clearGrid();
    byte i=0;
    ScreenCoverMangr.displayLine(i,"----------Game Paused-----------");
    i++;
    i++; ScreenCoverMangr.displayLine(i,"-----Settings------");
    i++; ScreenCoverMangr.displayLine(i,"  1) Video         |(ND)");
    i++; ScreenCoverMangr.displayLine(i,"  2) Audio         |(ND)");
    i++; ScreenCoverMangr.displayLine(i,"  3) Key Bindings  |");
    i++; ScreenCoverMangr.displayLine(i,"------Other---------");
    i++; ScreenCoverMangr.displayLine(i,"  4) Help          |");
    i++; ScreenCoverMangr.displayLine(i,"  5) Developer Info| (ND)");
    i++; ScreenCoverMangr.displayLine(i,"  6) Save Game     |");
    i++;
    i++; ScreenCoverMangr.displayLine(i,"(press a number)");
    i++; ScreenCoverMangr.displayLine(i,"NOTE: DN = not done");



  }
  public static void displayKeyBindings() throws FileNotFoundException {

  }
}
