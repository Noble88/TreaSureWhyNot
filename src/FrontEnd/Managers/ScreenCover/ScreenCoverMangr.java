package FrontEnd.Managers.ScreenCover;

import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Managers.TBoxMangr;
import FrontEnd.Window;

import static BackEnd.GameLoop.gameState;

public class ScreenCoverMangr {
  public static final byte yB=(SideWndwMangr.yB), xB=(SideWndwMangr.xB+LevelBhvr.xB);


  //region keyBindings

  public static void warmUpScreenCover(){
    for(byte r=0; r<yB; r++){
      Window.screenCoverCells[r][xB-1].setText("|");
      Window.screenCoverCells[r][0].setText("|");
    }
    for(byte c=0; c<xB; c++){
      Window.screenCoverCells[0][c].setText("-");
      Window.screenCoverCells[yB-1][c].setText("-");
    }
  }
  //region modify line
  public static void displayLine(byte line, String str){
    for(byte i=1; i<str.length()+1 && i<xB-1; i++){ //NOTE: max space currently is 30 exclusive 32 inclusive
      Window.screenCoverCells[line][i].setText(str.substring(i-1,i));
    }
  }
  public static void clearLine(byte line){
    for(byte i=1; i<xB-1 ; i++){
      Window.screenCoverCells[line][i-1].setText(" ");
    }
  }
  //endregion
  //region modify grid
  public static void clearGrid(){
    for(byte r=1; r<yB-1 ; r++){
      for(byte c=1; c<xB-1 ; c++){Window.screenCoverCells[r][c].setText(" ");}
    }
  }
  public static void fillGrid(String sym){
    for(byte r=1; r<yB-1 ; r++){
      for(byte c=1; c<xB-1 ; c++){Window.screenCoverCells[r][c].setText(sym);}
    }
  }

  //endregion
}
