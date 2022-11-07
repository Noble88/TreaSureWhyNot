package BackEnd.GameBehaviors;

import FrontEnd.Debugger;
import FrontEnd.Managers.TBoxMangr;
import FrontEnd.Window;

import java.util.ArrayList;

public class TBoxBhvr {
  //TODO MAKE: maybe make a color text request that makes it so can say want this word to be in this color
  public static boolean textIsCleared=false;
  public static void createText(String text, int speed) throws InterruptedException {
    clearTextBox(false);
    textIsCleared=false;
    if(text.length()> TBoxMangr.tBoxArea){
      ArrayList<String> boxesOfText = new ArrayList<>(){};
      for(int i=text.length(); i>0; i-=TBoxMangr.tBoxArea){
        if(text.length()>TBoxMangr.tBoxArea){
          boxesOfText.add(text.substring(0,TBoxMangr.tBoxArea));
          text=text.substring(TBoxMangr.tBoxArea);
        }
        else{boxesOfText.add(text.substring(0));}
      }
      for(byte i=0; i<boxesOfText.size(); i++){
        TBoxMangr.updateTextCell(boxesOfText.get(i),speed);
        Thread.sleep(1500);
        clearTextBox();
        Thread.sleep(200);
      }
    }
    else{TBoxMangr.updateTextCell(text,speed);}
    Debugger.createTextDebugger(speed,text);
  }
  public static void updateLineOfText(byte line, String text){
    for(byte i=1; i<TBoxMangr.xB-1 && i<text.length()+1;i++){
      Window.textBoxCells[line][i].setText(text.substring(i-1,i));
    }
    textIsCleared=false;
    Debugger.updateTBoxLineDebugger(line,text);
  }

  public static void clearTextBox(){
    if(textIsCleared){return;}
    textIsCleared=true;
    for(byte row=0; row<TBoxMangr.yB-1; row++){
      for(byte col=1; col<TBoxMangr.xB-1; col++){
      Window.textBoxCells[row][col].setText(" ");}
    }
  }
  public static void clearTextBox(boolean check){
    if(check){
      if(textIsCleared){return;}
      textIsCleared=true;
    }
    for(byte row=0; row<TBoxMangr.yB-1; row++){
      for(byte col=1; col<TBoxMangr.xB-1; col++){
        Window.textBoxCells[row][col].setText(" ");}
    }
  }
}
