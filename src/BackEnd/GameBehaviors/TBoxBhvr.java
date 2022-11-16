package BackEnd.GameBehaviors;

import FrontEnd.Debugger;
import FrontEnd.Managers.TBoxMangr;
import FrontEnd.Window;

import java.util.ArrayList;
import java.util.LinkedList;

public class TBoxBhvr {
  //TODO MAKE: maybe make a color text request that makes it so can say want this word to be in this color
  public static boolean textIsCleared=false;
  public static void createText(String text, int speed) throws InterruptedException {
    //TODO BUG: make it so won't cut off in the mide
    clearTextBox(false);
    textIsCleared=false;
    LinkedList<String> list = new LinkedList<>();

    int space=0;
    String grab="";
    if( !(text.length()<18)) {
      while(text.length()>18){
        grab=text.substring(0,18);
        space=grab.lastIndexOf(" ");
        list.add(text.substring(0,space));
        text=text.substring(space+1);
      }
    }
    list.add(text);
      for(byte i=0; i<list.size(); i++){
        TBoxMangr.updateTextCell(list.get(i),(i)%3,speed);
        if((i)%3==2){Thread.sleep(1500);clearTextBox(false);}
        else{Thread.sleep(200);}
      }
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
