package BackEnd.GameBehaviors;

import BackEnd.GameBehaviors.SideWndwElmnts.Journal;
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
    if(tab.equals(curTab)){return;}//Will make it so wont refresh stuff if only attempted to switch to a page that player is already on
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
      case "JOURNAL"->{
        SideWndwMangr.changeJournalHighlightState(true);
        Journal.displayQuests(); Journal.updateSubText();

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
          TBoxBhvr.clearTextBox(); TresBag.move(); Debugger.tresDebugger();
          SideWndwMangr.changeHighlightState(true,false,TresBag.menu.getNav()[0],TresBag.menu.getNav()[1]);
        }

        //TODO EFFIENCY check to see if needs to update so if doens't move anywhere wont update for no reason
        case "INTERACT" -> {TresBag.displayItemDescription();}
      }}
      case"TOOLS"->{switch (GameLoop.associatedKey) { //When in |LEVEL| state
        case "UP", "DOWN", "LEFT", "RIGHT"-> {
          SideWndwMangr.changeHighlightState(false,true,ToolBag.menu.getNav()[0],ToolBag.menu.getNav()[1]);
          TBoxBhvr.clearTextBox(); ToolBag.move(); ToolBag.updateSubText();Debugger.toolDebugger();
          SideWndwMangr.changeHighlightState(true,true,ToolBag.menu.getNav()[0],ToolBag.menu.getNav()[1]);
        }
        case "INTERACT" -> {ToolBag.displayItemDescription();}
      }}
      case"JOURNAL"->{switch (GameLoop.associatedKey) { //When in |LEVEL| state
        case "UP", "DOWN", "LEFT", "RIGHT"-> {
          SideWndwMangr.changeJournalHighlightState(false);
          TBoxBhvr.clearTextBox(); Journal.menu.move(GameLoop.associatedKey); Journal.updateSubText(); Debugger.JrnlDebugger();
          SideWndwMangr.changeJournalHighlightState(true);

        }
        case "INTERACT" -> {if(Journal.getHighlightedQuest() != null){Journal.displayInteractText();}}
      }}
    }
  }
  public static void updateTop(){

  }
}
