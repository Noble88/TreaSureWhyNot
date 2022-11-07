package FrontEnd.Managers;

import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Colors.SideWindowBg;
import FrontEnd.Window;

import java.io.IOException;

public class SideWndwMangr {
  public static final byte xB=12,yB=(TBoxMangr.yB+LevelBhvr.yB);
  public static int swArea=(yB-2)*(xB-1);
  public static char[][] header = new char[][]{
      {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
      {'J', 'O', 'U', 'R', 'N', 'A', 'L', ' ', ':', 'J', ' ','|'},
      {'T', 'R', 'E', 'A', 'S', 'U', 'R', 'E', ':', 'B', ' ','|'},
      {'T', 'O', 'O', 'O', 'L', 'S', ' ', ' ', ':', 'T', ' ','|'},
      {'M', 'A', 'P', ' ', ' ', ' ', ' ', ' ', ':', 'M', ' ','|'},
  };

  public static void warmUpSideWndwMangr() throws IOException, ClassNotFoundException {
    for(byte i=0; i<yB; i++){
      Window.sideWndwCells[i][xB-1].setText("|");
    }//make the sides
    for(byte k=0; k<xB; k++){
      Window.sideWndwCells[yB-1][k].setText("-");
      Window.sideWndwCells[0][k].setText("-");
    }
    clearCells();
    updateTopCells(header);
  }
  public static void updateTopCells(char[][] arr){
    for(byte row=1; row<5; row++){
      for(byte col=0; col<SideWndwMangr.xB-1; col++){
        Window.sideWndwCells[row][col].setText(String.valueOf(arr[row][col]));
      }
    }
  }
  public static void updateBtmCells(char[][] arr){
    byte space=5;
    for(byte row=0; row<SideWndwMangr.yB-space; row++){
      for(byte col=0; col<SideWndwMangr.xB-1; col++){
        Window.sideWndwCells[row+space][col].setText(String.valueOf(arr[row][col]));
      }
    }
  }
  public static void updateCell(String e, byte[] pos){
        Window.sideWndwCells[pos[0]][pos[1]].setText(e);
  }
  public static void clearCells(){
    for(byte row=1; row<SideWndwMangr.yB-1; row++){
      for(byte col=0; col<SideWndwMangr.xB-1; col++){
        Window.sideWndwCells[row][col].setText(" ");}
    }
  }

  public static void changeHighlightState(boolean add, boolean isTool, byte row, byte col){
    if(isTool){
      byte y= (byte) (5+1+(4*row)), x= (byte) (6*col);
      for(byte r=0; r<3;r++){
        for(byte c=0; c<5;c++) {
          SideWindowBg.gridChecker[y+r][x+c]=add;}}
    }
    else{
      byte y= (byte) (5+1+(3*row)), x= (byte) (3*col);
      SideWindowBg.gridChecker[y  ][x  ]=add;
      SideWindowBg.gridChecker[y+1][x  ]=add;
      SideWindowBg.gridChecker[y  ][x+1]=add;
      SideWindowBg.gridChecker[y+1][x+1]=add;
    }
    SideWindowBg.repaintAll();
    /* mini debugger
    System.out.println("ADD?("+add+")  |  isTool?("+isTool+")");
    SideWindowBg.printGridChecker();
     */
  } //grey tiles for tool & bag UI


  public static void clearSideWindowBg(boolean onlyBottom){
    if(onlyBottom){
      for(byte row = 5; row< FrontEnd.Window.yBsw; row++){
        for(byte col = 0; col< FrontEnd.Window.xBsw; col++){
          SideWindowBg.gridChecker[row][col]=false;
        }
      }
    }
    else{
      for(byte row = 0; row< FrontEnd.Window.yBsw; row++){
        for(byte col = 0; col< FrontEnd.Window.xBsw; col++){
          SideWindowBg.gridChecker[row][col]=false;
        }
      }
    }
    SideWindowBg.repaintAll();
  }

}
