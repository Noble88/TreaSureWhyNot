package BackEnd.GameBehaviors;

import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.GameLoop;
import FrontEnd.Debugger;
import FrontEnd.Managers.SideWndwMangr;

import java.io.IOException;

public class SideWndwBhvr {
  public static String curTab="TREASURE";
  //TODO LATER: make a combine status that combines the top half of the side window & bottom half and an array
  // Then use that array and update all cells using SideWndwMangr.updateCells(arr)
  public static void switchTabs(String tab) throws IOException, ClassNotFoundException {
    SideWndwMangr.clearSideWindowBg(true);
    curTab=tab;

    switch(tab){
      case "TREASURE"->{
        SideWndwMangr.changeHighlightState(true,false,TresBag.menu.getNav()[0],TresBag.menu.getNav()[1]);
        SideWndwMangr.updateBtmCells(TresBag.curPage.bagLayout);
        TresBag.refreshBag();
      }
      case "TOOLS"->{
        SideWndwMangr.changeHighlightState(true,true,ToolBag.menu.getNav()[0],ToolBag.menu.getNav()[1]);
        SideWndwMangr.updateBtmCells(ToolBag.curPage.bagLayout);
        ToolBag.updateSubText();
        ToolBag.refreshBag();
      }
      //case "MAP"->{}
    }
  }
  public static void inputDirector() throws InterruptedException, IOException, ClassNotFoundException {
    switch (GameLoop.associatedKey) {
      case "SWAP TO TABS" -> {GameLoop.gameState = "LEVEL";TBoxBhvr.clearTextBox();}
    }
    switch (curTab) {
      case"TREASURE"->{switch (GameLoop.associatedKey) { //When in |LEVEL| state
        case "UP", "DOWN", "LEFT", "RIGHT"-> {
          SideWndwMangr.changeHighlightState(false,false,TresBag.menu.getNav()[0],TresBag.menu.getNav()[1]);
          TBoxBhvr.clearTextBox(); TresBag.menu.move(GameLoop.associatedKey); Debugger.tresDebugger();
          SideWndwMangr.changeHighlightState(true,false,TresBag.menu.getNav()[0],TresBag.menu.getNav()[1]);
        }

        //TODO EFFIENCY check to see if needs to update so if doens't move anywhere wont update for no reason
        case "INTERACT" -> {TresBag.displayItemDescription();}
      }}
      case"TOOLS"->{switch (GameLoop.associatedKey) { //When in |LEVEL| state
        case "UP", "DOWN", "LEFT", "RIGHT"-> {
          SideWndwMangr.changeHighlightState(false,true,ToolBag.menu.getNav()[0],ToolBag.menu.getNav()[1]);
          TBoxBhvr.clearTextBox(); ToolBag.menu.move(GameLoop.associatedKey); ToolBag.updateSubText();Debugger.toolDebugger();
          SideWndwMangr.changeHighlightState(true,true,ToolBag.menu.getNav()[0],ToolBag.menu.getNav()[1]);
        }
        case "INTERACT" -> {ToolBag.displayItemDescription();}
      }}
    }
  }
  public static void updateTop(){

  }
}
