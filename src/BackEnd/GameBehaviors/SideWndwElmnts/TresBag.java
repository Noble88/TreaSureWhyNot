package BackEnd.GameBehaviors.SideWndwElmnts;

import BackEnd.GameBehaviors.HelpElmnts.MenuCus;
import BackEnd.GameBehaviors.TBoxBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs.TresItem;
import BackEnd.GameLoop;
import FrontEnd.Debugger;
import FrontEnd.Managers.SideWndwMangr;

import java.io.*;

public class TresBag implements Serializable {
  public static MenuCus menu = new MenuCus(11,6,4,true,true,false);
  public static boolean saveOnPageSwitch=true;
  public static TrePage curPage = null;
  static TrePage quickStor = null;
  static final byte[] itemBrdrs= new byte[]{6,4};
  static boolean[] isPageFull = new boolean[menu.getMaxPg()];
  public static String[] pagePaths = new String[menu.getMaxPg()];
  public static char[][] emptyIcon= new char[][]{ {' ',' '},{' ',' '}};

  //region Bag Manipulation
  public static void addItem(TresItem item) throws IOException, ClassNotFoundException {
    for(byte i = 0; i< isPageFull.length; i++){
      if(!isPageFull[i]){
        if(i==(menu.getCurPg()-1)){
         quickStor=curPage;
        }//if the empty page found is current page
        else{
          ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(pagePaths[i])));
          quickStor = (TrePage)input.readObject();
          input.close();
        }
        for(byte r=0;r<itemBrdrs[0];r++){ for(byte c=0;c<itemBrdrs[1];c++){
          if(quickStor.itemGrid[r][c]==null){
            quickStor.itemGrid[r][c]=item;
            item.setPos(new byte[]{r,c});
            writeIcon(quickStor,item);
            Debugger.tresDebugger(true,item);
            savePage(quickStor,i);
            SideWndwMangr.updateBtmCells(quickStor.bagLayout);
            quickStor.checkIfPageFull();
            refreshBag();
            quickStor =null;
            return;
          }
        }}}
    }
  }
  public static void deleteItem(TresItem item) throws IOException, ClassNotFoundException {
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
      quickStor = (TrePage)input.readObject();
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
  public static void writeIcon(TrePage page, TresItem item){
    byte[] pos = item.getPos();
    byte y= (byte) (1+(3*pos[0])), x= (byte) (3*pos[1]);
    page.bagLayout[y  ][x  ]=item.getIcon()[0][0];
    page.bagLayout[y+1][x  ]=item.getIcon()[1][0];
    page.bagLayout[y  ][x+1]=item.getIcon()[0][1];
    page.bagLayout[y+1][x+1]=item.getIcon()[1][1];
  }
  //endregion

  //region file management
  public static void savePage(TrePage obj, byte pgNum) throws IOException {
    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(pagePaths[pgNum])));
    output.writeObject(obj);
    output.close();
  }

  public static void switchPage(byte pgNum) throws IOException, ClassNotFoundException {
    ObjectInputStream input = new ObjectInputStream(new FileInputStream(
        new File(pagePaths[pgNum])));
    curPage = (TrePage)input.readObject();
    input.close();
    menu.setCurPg(pgNum+1);
  }
  //endregion

  public static void refreshBag() throws IOException, ClassNotFoundException {
    switchPage((byte) (menu.getCurPg()-1));
    SideWndwMangr.updateBtmCells(curPage.bagLayout);
    SideWndwMangr.updatePageNumHeader(menu.getCurPg(),menu.getMaxPg());
  }

  public static class TrePage implements Serializable {
    public char[][] bagLayout; public static byte pgNum=-1;
    public TresItem[][] itemGrid = new TresItem[itemBrdrs[0]][itemBrdrs[1]];
    
    public TrePage(byte pgNum){
      TrePage.pgNum =pgNum;
      isPageFull[pgNum]=false;
      //region bagLayout Declaration
      bagLayout = new char[][]{
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '|', '-', '-', '|', '-', '-', '|', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '|', '-', '-', '|', '-', '-', '|', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '|', '-', '-', '|', '-', '-', '|', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '|', '-', '-', '|', '-', '-', '|', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '|', '-', '-', '|', '-', '-', '|', '-', '-','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ','|'},
          {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},
      };
      //endregion
    }
    public void checkIfPageFull(){
      for(byte r=0; r<itemGrid.length; r++){
        for(byte c=0; c<itemGrid[0].length; c++){
          if(itemGrid[r][c]==null){
            isPageFull[(menu.getCurPg()-1)]=false; return;}
        }
      }
      isPageFull[(menu.getCurPg()-1)]=true;
    }
  }

  //region bag navigation

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


