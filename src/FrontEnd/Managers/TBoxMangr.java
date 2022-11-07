package FrontEnd.Managers;

import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Window;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;


public class TBoxMangr {
  public static final byte xB=LevelBhvr.xB,yB=4;
  public static boolean editedTBox=false;

  public static int tBoxArea=(yB-1)*(xB-2);

  public static void warmUpTBoxMangr(){
    for(byte i=0; i<4; i++){
      Window.textBoxCells[i][0].setText("|");
      Window.textBoxCells[i][xB-1].setText("|");
    }//make the sides
    for(byte k=0; k<xB; k++){
      Window.textBoxCells[yB-1][k].setText("-");
    }
    for(byte r=0;r<yB-1;r++){
      for(byte c=1;c<xB-1;c++){
        Window.textBoxCells[r][c].setText(" ");
      }
    }
  }
  public static void updateTextCell(String text,int speed) throws InterruptedException {
    for(byte row=0; row<yB-1; row++){
      for(byte col=1; col<xB-1; col++){
        Window.textBoxCells[row][col].setText(text.substring(0,1));
        text=text.substring(1);
        if(text.length()==0){return;}
        Thread.sleep(speed);
      }
    }
  }


  //region foreground colors
  //region declaration of colors

  //endregion
  public static SimpleAttributeSet colorText(String color){

    SimpleAttributeSet TEMP = new SimpleAttributeSet();
    Color colorObj;
    switch(color) {
      case "GRAY"->{colorObj = new Color(133, 133, 133);}
      default -> {colorObj = new Color(0,0,0);}
    }
    StyleConstants.setForeground(TEMP, colorObj);
    return TEMP;
  }
  //endregion
  public static void colorCell(String color, byte row, byte col){
    Window.textBoxCells[row][col].getStyledDocument().setCharacterAttributes
        (0,1,colorText(color), true);
  }
  public static void clearColoredText(){
    for(byte row=0;row<yB-1;row++){
      for(byte col=1;col<xB-1;col++){
        colorCell("BLACK",row,col);
      }
    }
  }
}
