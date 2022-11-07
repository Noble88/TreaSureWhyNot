package FrontEnd.Managers.ScreenCover.TitleScreen;

import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;

public class TitleScreenMangr {
  public static void displayTitleScreen(){
    ScreenCoverMangr.clearGrid();
    byte i=0;
    ScreenCoverMangr.displayLine(i,"TreaSureWhyNot-----------");
    i++;
    i++; ScreenCoverMangr.displayLine(i,"-----Select an options!------");
    i++; ScreenCoverMangr.displayLine(i,"  Start New Game     |");
    i++; ScreenCoverMangr.displayLine(i,"  Load A Game        |");
    i++; ScreenCoverMangr.displayLine(i,"  Exit               |");
  }
}
