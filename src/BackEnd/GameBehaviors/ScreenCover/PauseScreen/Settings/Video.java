package BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.HelpElmnts.MenuCus;
import BackEnd.GameBehaviors.ScreenCover.PauseScreen.PauseBhvr;
import BackEnd.GameLoop;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Window;

import java.io.IOException;
import java.util.Arrays;

public class Video {
  public static MenuCus menu = new MenuCus(1,3,3,false,false,true);
  public static double wSize =1.5,tSize=1.5;
  public static void warmUpVideoSettings(){

  }

  public static void changer(boolean right){
    switch(menu.getNav()[0]){
      case 0-> {
        if (right) {
          Window.windowSize[0] *= wSize;
          Window.windowSize[1] *= wSize;
          Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize() * tSize));
          Window.resizeAll();
        } else {
          Window.windowSize[0] /= wSize;
          Window.windowSize[1] /= wSize;
          Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize() / tSize));
          Window.resizeAll();
        }
      } //Window Size
      case 1->{
        if(right){Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize()* tSize));}
        else{Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize()/ tSize));}
      } //Text Size
      case 2->{
        Window.windowSize[0]=900;Window.windowSize[1]=700;
        Window.modifyAllCellsFontSize(20);
        Window.resizeAll();
      }
    }
    ScreenCoverMangr.highlightLine("GRAY",menu.getNav()[0]+2,1,31);
  }

  public static void move(){
    ScreenCoverMangr.highlightLine("DEFAULT", menu.getNav()[0] + 2, 1, 31);
    menu.move(GameLoop.associatedKey);
  }
  public static void keyBindNav() throws IOException, InterruptedException {
    if(GameLoop.associatedKey.equals("PAUSE")){ //Leave Video
      ScreenCoverMangr.clearColoredText();
      PauseBhvr.leaveSubMenu();
    }

    switch(GameLoop.associatedKey){
      case "LEFT"->{
        move();
        changer(false);
        menu.setNav(menu.getNav()[0],1);
      }
      case "RIGHT"->{
        move();
        changer(true);
        menu.setNav(menu.getNav()[0],1);
      }
      case "UP", "DOWN"->{
        move();
        ScreenCoverMangr.highlightLine("GRAY",menu.getNav()[0]+2,1,31);
      }
    }
    System.out.println(Arrays.toString(Window.windowSize));
  }
}
