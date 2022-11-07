package BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.PauseBhvr;
import BackEnd.GameLoop;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.HelpManagr;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.KeyBindingsMangr;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Help {
  public static byte page=1;
  public static byte maxPage=2;
  public static void keyBindNav() throws IOException, InterruptedException {
    switch (GameLoop.associatedKey) {
      case"PAUSE" ->{page=1; PauseBhvr.leaveSubMenu();}
      case "LEFT"->{pageNav(false);}
      case "RIGHT"->{pageNav(true);}

    }
  }
  public static void pageNav(boolean addToPage) throws IOException {
    if(addToPage){
      if(page+1>maxPage){page=1;}
      else{page++;}
    }
    else{
      if(page-1==0){page=maxPage;}
      else{page--;}
    }
    pageShower();
  }
  public static void pageShower() throws IOException {
    switch(page){
      case 1 ->{HelpManagr.showPage1();}
      case 2 ->{HelpManagr.showPage2();}
      //case 3 ->{HelpManagr.showPage3();}
    }
  }

}
