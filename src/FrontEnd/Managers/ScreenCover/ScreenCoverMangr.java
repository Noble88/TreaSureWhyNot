package FrontEnd.Managers.ScreenCover;

import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Window;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

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

  public static SimpleAttributeSet highlight(String color){

    SimpleAttributeSet TEMP = new SimpleAttributeSet();
    Color colorObj;
    switch(color) {
      case "GRAY"->{colorObj = new Color(133, 133, 133);}
      default -> {colorObj = new Color(0, 0, 0);}
    }
    StyleConstants.setForeground(TEMP, colorObj);
    return TEMP;
  }
  //endregion
  public static void colorCell(String color, byte row, byte col){
    Window.screenCoverCells[row][col].getStyledDocument().setCharacterAttributes
        (0,1, highlight(color), true);
  }
  public static void highlightLine(String color, int row, int colSt, int colFin){
    for(byte i = (byte) colSt; i<colFin; i++){colorCell(color, (byte) row,i);}
  }

  public static void clearColoredText(){
    for(byte row=0;row<yB-1;row++){
      for(byte col=1;col<xB-1;col++){
        colorCell("default",row,col);
      }
    }
  }

  //endregion
}
