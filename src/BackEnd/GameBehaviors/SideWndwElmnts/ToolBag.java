package BackEnd.GameBehaviors.SideWndwElmnts;

import BackEnd.GameBehaviors.HelpElmnts.MenuCus;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import BackEnd.GameLoop;
import FrontEnd.Debugger;
import FrontEnd.Managers.SideWndwMangr;

import java.io.*;
import java.util.Arrays;

import static BackEnd.GameBehaviors.SideWndwElmnts.TresBag.emptyIcon;
//TODO OVERHAUL!!!: make a new class called bag that takes the common aspects of both bag classes and merges it (ex: navv method) (might not work tho)
//      How Would I Do It: Need to make a items class and inside have tool & tres extend item (class). tool & tres could
//      share: name/if tool or bag boolean/value/Icon/page position/qauntity/description
public class ToolBag {
  public static MenuCus menu = new MenuCus(11,4,2,true,true,false);

  public static ToolPage curPage = null;
  public static ToolPage quickStor = null;
  static boolean[] isPageFull = new boolean[menu.getMaxPg()];
  public static String[] pagePaths = new String[menu.getMaxPg()];
  static final byte[] itemBrdrs= new byte[]{4,2};

  //region bag manipulator
  public static void addItem(Tools item) throws IOException, ClassNotFoundException {
    for(byte i = 0; i< isPageFull.length; i++){
      if(!isPageFull[i]){
        if(i==(menu.getCurPg()-1)){
          quickStor = curPage;
        }//if the empty page found is current page
        else{
          ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(pagePaths[i])));
          quickStor = (ToolPage) input.readObject();
          input.close();
        }
        for(byte r=0;r<itemBrdrs[0];r++){ for(byte c=0;c<itemBrdrs[1];c++){
          if(quickStor.itemGrid[r][c]==null){
            quickStor.itemGrid[r][c]=item;
            item.setPos(new byte[]{r,c});
            writeIcon(quickStor,item);
            Debugger.toolDebugger(true,item);
            savePage(quickStor,i);
            SideWndwMangr.updateBtmCells(quickStor.bagLayout);
            quickStor.checkIfPageFull();
            refreshBag();
            quickStor = null;
            return;
          }
        }}
      }
    }
  }
  public static void deleteItem(Tools item) throws IOException, ClassNotFoundException {
    boolean itemIsOnCurPage=false;

    for (byte r=0; r<itemBrdrs[0]; r++){
      for (byte c=0; c<itemBrdrs[1]; c++){
        if(curPage.itemGrid[r][c]==item){
          itemIsOnCurPage=true;
          quickStor=curPage;
        }
      }
    }

    if(!itemIsOnCurPage){ for (byte i=0; i<menu.getMaxPg(); i++){
      ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(pagePaths[i])));
      quickStor = (ToolPage) input.readObject();
      input.close();
      for (byte r=0; r<itemBrdrs[0]; r++){
        for (byte c=0; c<itemBrdrs[1]; c++){
          if(quickStor.itemGrid[r][c]==item){i=menu.getMaxPg();}
        }
      }
    }}

    byte[] pos = item.getPos();
    item.setIcon(emptyIcon);
    writeIcon(quickStor, item);
    quickStor.itemGrid[pos[0]][pos[1]]=null;
    quickStor.checkIfPageFull();
    refreshBag();
    quickStor=null;
  }
  //TODO CREATE METHOD: make method that takes in item's name and searches bag for that item
  //TODO CREATE: Make a hasmap that contains name,object in where the object are items that can be called from
  //TODO Check: are items even saved if not make it some items are saved over

  public static void writeIcon(ToolPage page, Tools item){
    byte[] pos = item.getPos();
    byte y= (byte) (1+(4*pos[0])), x= (byte) (6*pos[1]);
    for(byte r=0; r<3;r++){
      for(byte c=0; c<5;c++) {
        page.bagLayout[y+r][x+c]=item.getIcon()[r][c];
      }
    }
  }

  //endregion

  //region file management
  public static void savePage(ToolPage page, byte pgNum) throws IOException {
    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(pagePaths[pgNum])));
    output.writeObject(page);
    output.close();
  }
  public static void switchPage(byte pgNum) throws IOException, ClassNotFoundException {
    ObjectInputStream input = new ObjectInputStream(new FileInputStream(
        new File(pagePaths[pgNum])));
    curPage = (ToolPage) input.readObject();
    input.close();
    menu.setCurPg(pgNum+1);
  }

  //endregion


  public static void refreshBag() throws IOException, ClassNotFoundException {
    switchPage((byte) (menu.getCurPg()-1));
    SideWndwMangr.updateBtmCells(curPage.bagLayout);
    SideWndwMangr.updatePageNumHeader(menu.getCurPg(),menu.getMaxPg());
  }


  public static class ToolPage implements Serializable {
    public char[][] bagLayout; public static byte pgNum=-1;
    public Tools[][] itemGrid = new Tools[itemBrdrs[0]][itemBrdrs[1]];

    public ToolPage(byte pos){
      pgNum=pos;
      isPageFull[pos]=false;
      //region bagLayout Declaration
      bagLayout = new char[][]{
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
      };
      //endregion
    }
    public void checkIfPageFull(){
      for(byte r=0; r<itemGrid.length; r++){
        for(byte c=0; c<itemGrid[0].length; c++){
          if(itemGrid[r][c]==null){
            isPageFull[menu.getCurPg()-1]=false; return;
          }}}
      isPageFull[menu.getCurPg()-1]=true;
    }
  }
  public static void updateSubText(){
    for(byte i=0; i<11; i++){
      SideWndwMangr.updateCell(" ",new byte[]{22,i});
    }
    if(curPage.itemGrid[menu.getNav()[0]][menu.getNav()[1]]==null){return;}

    String name=curPage.itemGrid[menu.getNav()[0]][menu.getNav()[1]].getName();
    String amount=""+curPage.itemGrid[menu.getNav()[0]][menu.getNav()[1]].getQuantity();
    for(byte i=0; i<amount.length(); i++){
      SideWndwMangr.updateCell(amount.substring(i,i+1),new byte[]{22,i});
    }
    for(byte i=0;i<name.length() && i<11-3;i++){
      SideWndwMangr.updateCell(name.substring(i,i+1),new byte[]{22, (byte) (i+3)});
    }
  }


  public static void displayItemDescription() throws InterruptedException {
    if(curPage.itemGrid[menu.getNav()[0]][menu.getNav()[1]]!=null) {
      TBoxBhvr.createText(curPage.itemGrid[menu.getNav()[0]][menu.getNav()[1]].getDescr(), 15);
    }
    else{TBoxBhvr.createText("An empty space to put my next item",15);}
  }

  public static void move() throws IOException, ClassNotFoundException {
    int prevCurPage=menu.getCurPg();
    menu.move(GameLoop.associatedKey);
    if(prevCurPage!=menu.getCurPg()){refreshBag();}//updates accordingly upon switching page
  }
  //endregion
}
