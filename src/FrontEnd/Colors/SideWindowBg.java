package FrontEnd.Colors;

import FrontEnd.Window;

import javax.swing.*;
import java.awt.*;

public class SideWindowBg {
  public static int height= FrontEnd.Window.swBnds[3]/FrontEnd.Window.yBsw;
  public static int width = FrontEnd.Window.swBnds[2]/FrontEnd.Window.xBsw;
  public static boolean[][] gridChecker = new boolean[FrontEnd.Window.yBsw][FrontEnd.Window.xBsw];
  public static boolean checkAutomatedConditions=true; //makes it so doesn't run calculation unnecessarily
  public static int num;
  public static JPanel makeSideWndwBgTileBttm(byte row, byte col){
    return new JPanel(){
      public final byte[] pos = new byte[]{row, col};
      public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(gridChecker[pos[0]][pos[1]]){g2d.setColor(Color.gray);}
        else{g2d.setColor(Color.white);}
        g2d.fillRect(0,0,width,height);
        num++;
      }};

  }

  public static void warmUpSideWindowBackground(){ //PRECONDITION: curLevel has already been assigned
    for(byte row = 0; row< FrontEnd.Window.yBsw; row++){
      for(byte col = 0; col< FrontEnd.Window.xBsw; col++){
        FrontEnd.Window.swBgCellsBG[row][col]=(makeSideWndwBgTileBttm(row,col));
        FrontEnd.Window.swBgCellsBG[row][col].setSize(width,height);
        FrontEnd.Window.swBgCellsBG[row][col].setOpaque(false);
        FrontEnd.Window.swBg.add(FrontEnd.Window.swBgCellsBG[row][col]);
        gridChecker[row][col]=false;
      }
    }
  }
  public static void printGridChecker(){
    for(byte row = 0; row< FrontEnd.Window.yBsw; row++){
      for(byte col = 0; col< FrontEnd.Window.xBsw; col++){
        if(gridChecker[row][col]){System.out.print("T ");}
        else{System.out.print("F ");}
      }
      System.out.println();
    }
  }

  public static void repaintAll(){
    for(byte r = 0; r< FrontEnd.Window.yBsw; r++){
      for(byte c = 0; c< FrontEnd.Window.xBsw; c++){
        Window.swBgCellsBG[r][c].repaint();
      }
    }
    //LevColors.repaintWorldTint();
  }





}
