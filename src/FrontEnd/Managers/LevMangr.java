package FrontEnd.Managers;
import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Window;


import static FrontEnd.Colors.LevColors.levCellColor;

public class LevMangr {

  //region ---Cell Commands---
  public static void updateCell(byte row, byte col){
    Window.levGridCells[row][col].setText(String.valueOf(LevelBhvr.curLev.levFG[row][col]));
    Window.levGridCells[row][col].getStyledDocument().setCharacterAttributes
        (0,1,levCellColor(LevelBhvr.curLev.levFG[row][col]), true);
  }
  //endregion

  //region ---Grid Commands---
  public static void executeDisplayOfGrid(){
    for(byte row = 0; row< LevelBhvr.yB; row++){
      for(byte col = 0; col< LevelBhvr.xB; col++) {
        if (LevelBhvr.curLev.levFG[row][col] != Window.levGridCells[row][col].getText().charAt(0)) {
          updateCell(row,col);
          /*
          System.out.println("COMPARED -> Lev:"+LevelBhvr.curLev.levFG[row][col]+" == " +
              "Window:"+ Window.levGridCells[row][col].getText().charAt(0)+" --> ON (Y:"+row+", X:"+col+")");
           */
        }
      }
    }
  }

  public static void displayWholeGrid(){
    for(byte row = 0; row< LevelBhvr.yB; row++){
      for(byte col = 0; col< LevelBhvr.xB; col++) {
        updateCell(row,col);
      }
    }
  }
  //endregion

  public static void printGrids(){
    String gridStr="";
    String prevGridStr="";
    for(byte y = 0; y<LevelBhvr.yB;y++){
      for(byte x=0; x<LevelBhvr.xB;x++){
        prevGridStr+=Window.levGridCells[y][x].getText().charAt(0);
      }
      prevGridStr+="\n";
    }
    System.out.println("REAL TIME LEVEL: \n"+LevelBhvr.curLev.toString(true));
    System.out.println("GAME GRID CELLS: \n"+prevGridStr);


  }


}
