package BackEnd.GameBehaviors.SideWndwElmnts;

import BackEnd.GameBehaviors.HelpElmnts.MenuCus;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.Tools;
import FrontEnd.Managers.SideWndwMangr;

import java.io.*;

import static BackEnd.GameBehaviors.SideWndwElmnts.TresBag.emptyIcon;
//TODO EFFIENCY: make a new class called bag that takes the common aspects of both bag classes and merges it (ex: navv method) (might not work tho)
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
        if(i==menu.getCurPg()){
          quickStor =curPage;
        }
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
            savePage(quickStor,i);
            SideWndwMangr.updateBtmCells(quickStor.bagLayout);
            quickStor.checkIfPageFull();
            refreshBag();
            quickStor =null;
            return;
            //TODO EFFICIENCY: Make boolean of if player has item list as to not search entire backpack everytime check if have an item. (needs disable has item when selling item)
          }//TODO BUG: might mess up if the first empty page is curPage FIX THIS
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
  //TODO CREATE FILE: create a file in fresh assests that contains all the possible items a player can get so auto creates it

  //TODO CREATE: make hashmap that contains name to object (make sure to save it somehow)
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
    menu.setCurPg(pgNum);
  }

  //endregion


  public static void refreshBag() throws IOException, ClassNotFoundException {
    switchPage(menu.getCurPg());
    SideWndwMangr.updateBtmCells(curPage.bagLayout);
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
            isPageFull[menu.getCurPg()]=false; return;}
        }
      }
      isPageFull[menu.getCurPg()]=true;
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


//TODO BUG: display text is gone upon pressing T again



  //endregion
}
