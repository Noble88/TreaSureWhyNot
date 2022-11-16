package BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.PauseBhvr;
import BackEnd.GameLoop;
import FrontEnd.Window;

import java.io.IOException;
import java.util.Arrays;

public class Video {
  public static double wSize =1.5,tSize=1.5;
  public static void keyBindNav() throws IOException, InterruptedException {
    if(GameLoop.associatedKey.equals("PAUSE")){PauseBhvr.leaveSubMenu();}
    switch (GameLoop.key) {
      case"1" ->{Window.windowSize[0]*= wSize;Window.windowSize[1]*= wSize; Window.resizeAll();}
      case"2" ->{Window.windowSize[0]/= wSize;Window.windowSize[1]/= wSize;Window.resizeAll();}
      case"3" ->{Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize()* tSize));}
      case"4" ->{Window.modifyAllCellsFontSize((int) (Window.getCurretFontSize()/ tSize));}
      case"5" ->{//RESET TO DEFAULT
        Window.windowSize[0]=900;Window.windowSize[1]=700;
        Window.modifyAllCellsFontSize(20);
        Window.resizeAll();}
    }
    System.out.println(Arrays.toString(Window.windowSize));
  }
}
