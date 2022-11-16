package FrontEnd;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

//packages
import BackEnd.GameLoop;
import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Colors.LevBg;
import FrontEnd.Colors.SideWindowBg;
import FrontEnd.Managers.SideWndwMangr;
import FrontEnd.Managers.TBoxMangr;

import static FrontEnd.Colors.LevColors.windowTint;


//NOTE: make a dominant layer that goes over all other window elemenets (useful for making main menu & settings)
public class Window {
  public static int windowSizeMultiplier = 1;


  //region Window Stuff
  //NOTE: the space for each row is gBnds[3]/yBlev --> current number: 30px

  //region ---Window declaration and Initialization---

  public static JFrame window = new JFrame();
  public static JLayeredPane elementHolder = new JLayeredPane(); //Element Holder -> holds everything assigned window size will be the largest

  public static int[] windowSize = new int[]{900,700}; //width, height
  //static byte gameSizeMultiplyer



  //region --Level Grid Elements--
  static JPanel levGrid = new JPanel();
  public static final byte xBlev = LevelBhvr.xB;
  public static final byte yBlev = LevelBhvr.yB;
  static public JTextPane[][] levGridCells = new JTextPane[yBlev][xBlev];
  public static int[] levBnds = new int[]{0,0,getWinSizeX(xBlev),getWinSizeY(yBlev)};
  //endregion
  //region --Level Background--
  public static JPanel levBg = new JPanel();
  static public JPanel[][] levBgCells = new JPanel[yBlev][xBlev];
  //endregion

  //region --Text Box Element--
  static JPanel textBox = new JPanel();
  static final byte xBtbox = TBoxMangr.xB, yBtbox = TBoxMangr.yB;
  public static JTextPane[][] textBoxCells = new JTextPane[yBlev][xBlev];
  static int[] tbBnds = new int[]{levBnds[0], levBnds[3],levBnds[2],getWinSizeY(yBtbox)};
  //endregion
  //region ---Side Window Elements---
  static JPanel sideWindow = new JPanel();
  public static final byte xBsw = SideWndwMangr.xB;
  public static final byte yBsw = SideWndwMangr.yB;
  public static JTextPane[][] sideWndwCells = new JTextPane[yBsw][xBsw];
  public static int[] swBnds = new int[]{levBnds[2], levBnds[1],getWinSizeX(xBsw),levBnds[3]+tbBnds[3]};

  //endregion
  //region --Side Window Elements Background--
  public static JPanel swBg = new JPanel();
  public static JPanel[][] swBgCellsBG = new JPanel[yBsw][xBsw];
  //endregion

  //region ---Screen Cover Elements---
  public static JPanel screenCover = new JPanel();
  static final byte xBsc = (byte) (xBlev+xBsw+1), yBsc = (byte)(yBsw+1);
  public static JTextPane[][] screenCoverCells = new JTextPane[yBsc][xBsc];
  static int[] scBnds = new int[]{0,0, windowSize[0], windowSize[1]};
  //endregion

  //region tesst

  //endregion

  public static int getWinSizeX(int row){ return ((windowSize[0])/xBsc)*row;}
  public static int getWinSizeY(int col){ return ((windowSize[1])/yBsc)*col;}

  //endregion
  public static void createWindow(){
    //region ---JFrame---
    //frame.setLayout(new GridBagLayout());
    window.setSize(windowSize[0], windowSize[1]);
    window.setUndecorated(false);
    window.setResizable(true);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //endregion

    //region ---Screen Cover---
    window.add(screenCover);
    screenCover.setBounds(0,0,windowSize[0],windowSize[1]);
    screenCover.setLayout(new GridLayout(yBsc,xBsc));
    screenCover.setOpaque(true);
    screenCover.setFocusable(false);
    screenCover.setVisible(false);
    //endregion
    //region ---Element Holder Layered Pane---
    window.add(elementHolder);
    elementHolder.setBounds(0,0, windowSize[0], windowSize[1]);
    elementHolder.setOpaque(false);
    elementHolder.setVisible(true);
    //endregion

    //region Window Tint
    elementHolder.add(windowTint);
    windowTint.setBounds(0,0,windowSize[0],windowSize[1]);
    windowTint.setOpaque(false);
    windowTint.setFocusable(false);
    windowTint.setVisible(true);

    //endregion

    //region ---Playable Area---

    //region ---Level Grid---
    elementHolder.add(levGrid);
    levGrid.setBounds(levBnds[0], levBnds[1], levBnds[2], levBnds[3]);
    levGrid.setLayout(new GridLayout(yBlev, xBlev));
    levGrid.setOpaque(false);
    levGrid.setFocusable(false);
    //endregion

    //region ---Level Grid Background---
    elementHolder.add(levBg);
    levBg.setBounds(levBnds[0], levBnds[1], levBnds[2], levBnds[3]);
    levBg.setLayout(new GridLayout(yBlev, xBlev));
    levBg.setOpaque(false);
    levBg.setFocusable(false);
    //endregion

    //endregion

    //region ---Text Box Element---
    elementHolder.add(textBox);
    textBox.setBounds(tbBnds[0],tbBnds[1],tbBnds[2],tbBnds[3]);
    textBox.setLayout(new GridLayout(yBtbox, xBtbox));
    textBox.setOpaque(false);
    textBox.setFocusable(false);
    //endregion

    //region ---Side Window---
    elementHolder.add(sideWindow);
    sideWindow.setBounds(swBnds[0],swBnds[1],swBnds[2],swBnds[3]);
    sideWindow.setLayout(new GridLayout(yBsw, xBsw));
    sideWindow.setOpaque(false);
    sideWindow.setFocusable(false);


    //region ---Background---
    elementHolder.add(swBg);
    swBg.setBounds(swBnds[0], swBnds[1], swBnds[2], swBnds[3]);
    swBg.setLayout(new GridLayout(yBsw, xBsw));
    swBg.setOpaque(false);
    swBg.setFocusable(false);
    //endregion
    //endregion

  }
  public static void warmUpWindowClass(){
    createWindow();
    createCells(levGrid, levGridCells, yBlev,xBlev);
    createCells(textBox, textBoxCells, yBtbox, xBtbox);
    createCells(sideWindow, sideWndwCells, yBsw, xBsw);
    createCells(screenCover, screenCoverCells, yBsc, xBsc);
    window.addKeyListener(listener);
  }

  //region Changing Window Attributes and Other Stuff
  public static void resizeAll(){
    window.setSize(windowSize[0], windowSize[1]);
    screenCover.setBounds(0,0,windowSize[0],windowSize[1]);
    elementHolder.setBounds(0,0, windowSize[0], windowSize[1]);
    scBnds = new int[]{0,0, windowSize[0], windowSize[1]};
    levBnds = new int[]{0,0,getWinSizeX(xBlev),getWinSizeY(yBlev)};


    swBnds = new int[]{levBnds[2], levBnds[1],getWinSizeX(xBsw),levBnds[3]+tbBnds[3]};
    tbBnds = new int[]{levBnds[0], levBnds[3],levBnds[2],getWinSizeY(yBtbox)};
    levGrid.setBounds(levBnds[0], levBnds[1], levBnds[2], levBnds[3]);
    levBg.setBounds(levBnds[0], levBnds[1], levBnds[2], levBnds[3]); //Level Background
    textBox.setBounds(tbBnds[0],tbBnds[1],tbBnds[2],tbBnds[3]);
    sideWindow.setBounds(swBnds[0],swBnds[1],swBnds[2],swBnds[3]);
    swBg.setBounds(swBnds[0], swBnds[1], swBnds[2], swBnds[3]);

    LevBg.height=FrontEnd.Window.levBnds[3]/ FrontEnd.Window.yBlev;
    LevBg.width =FrontEnd.Window.levBnds[2]/ FrontEnd.Window.xBlev;
    SideWindowBg.height=FrontEnd.Window.swBnds[3]/FrontEnd.Window.yBsw;
    SideWindowBg.width=FrontEnd.Window.levBnds[2]/ FrontEnd.Window.xBlev;
    LevBg.repaintAll();
    SideWindowBg.repaintAll();

  }
  public static int getCurretFontSize(){return levGridCells[0][0].getFont().getSize();}
  public static void modifyAllCellsFontSize(int fontSize) {
    Font font = new Font("Monospaced", Font.CENTER_BASELINE, fontSize);
    modifyCellsFontSize(levGridCells, yBlev, xBlev,font);
    modifyCellsFontSize(textBoxCells, yBtbox, xBtbox,font);
    modifyCellsFontSize(sideWndwCells, yBsw, xBsw,font);
    modifyCellsFontSize(screenCoverCells, yBsc, xBsc,font);
  }
  public static void modifyCellsFontSize(JTextPane[][] grid, byte rMax, byte cMax, Font font){
    for(byte row = 0; row< rMax; row++){
      for(byte col = 0; col< cMax; col++){
        grid[row][col].setFont(font);
      }
    }
  }
  //endregion
  //endregion

  //region grid layout & formating
  static SimpleAttributeSet cellFormat = new SimpleAttributeSet();
  static Font font = new Font("Monospaced", Font.CENTER_BASELINE, 20);


  public static void createCells(JPanel element, JTextPane[][] grid, byte rMax, byte cMax){
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    for(byte row = 0; row< rMax; row++){
      for(byte col = 0; col< cMax; col++){
        grid[row][col]=(new JTextPane());
        StyledDocument doc = grid[row][col].getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        grid[row][col].setFont(font);
        grid[row][col].setMargin(new Insets(2,0,0,0));
        grid[row][col].setEditable(false);
        grid[row][col].setOpaque(false);
        grid[row][col].getCaret().deinstall(grid[row][col]);
        grid[row][col].setCharacterAttributes(cellFormat,true);

        element.add(grid[row][col]);
      }
    }
  }



  //endregion

  //region keyboard stuff

  public static HashMap<Integer, String> KbdToAssosKey = new HashMap<>(); //Keyboard to assosiated key
  public static void warmUpKeyListener(){
    //region Core Default Keys
    KbdToAssosKey.put(KeyEvent.VK_LEFT,  "◀");
    KbdToAssosKey.put(KeyEvent.VK_RIGHT, "▶");
    KbdToAssosKey.put(KeyEvent.VK_UP,    "▲");
    KbdToAssosKey.put(KeyEvent.VK_DOWN,  "▼");
    KbdToAssosKey.put(KeyEvent.VK_SPACE, "SPACE");
    KbdToAssosKey.put(KeyEvent.VK_ENTER, "ENTER");
    KbdToAssosKey.put(KeyEvent.VK_SHIFT, "SHIFT");
    KbdToAssosKey.put(KeyEvent.VK_ESCAPE,"ESC");
    KbdToAssosKey.put(KeyEvent.VK_DELETE,"DELETE");
    KbdToAssosKey.put(KeyEvent.VK_BACK_SPACE,"DELETE");
    //endregion
    //region alphabet
    KbdToAssosKey.put(KeyEvent.VK_A,"A");
    KbdToAssosKey.put(KeyEvent.VK_B,"B");
    KbdToAssosKey.put(KeyEvent.VK_C,"C");
    KbdToAssosKey.put(KeyEvent.VK_D,"D");
    KbdToAssosKey.put(KeyEvent.VK_E,"E");
    KbdToAssosKey.put(KeyEvent.VK_F,"F");
    KbdToAssosKey.put(KeyEvent.VK_G,"G");
    KbdToAssosKey.put(KeyEvent.VK_H,"H");
    KbdToAssosKey.put(KeyEvent.VK_I,"I");
    KbdToAssosKey.put(KeyEvent.VK_J,"J");
    KbdToAssosKey.put(KeyEvent.VK_K,"K");
    KbdToAssosKey.put(KeyEvent.VK_L,"L");
    KbdToAssosKey.put(KeyEvent.VK_M,"M");
    KbdToAssosKey.put(KeyEvent.VK_N,"N");
    KbdToAssosKey.put(KeyEvent.VK_O,"O");
    KbdToAssosKey.put(KeyEvent.VK_P,"P");
    KbdToAssosKey.put(KeyEvent.VK_Q,"Q");
    KbdToAssosKey.put(KeyEvent.VK_R,"R");
    KbdToAssosKey.put(KeyEvent.VK_S,"S");
    KbdToAssosKey.put(KeyEvent.VK_T,"T");
    KbdToAssosKey.put(KeyEvent.VK_U,"U");
    KbdToAssosKey.put(KeyEvent.VK_V,"V");
    KbdToAssosKey.put(KeyEvent.VK_W,"W");
    KbdToAssosKey.put(KeyEvent.VK_X,"X");
    KbdToAssosKey.put(KeyEvent.VK_Y,"Y");
    KbdToAssosKey.put(KeyEvent.VK_Z,"Z");
    //endregion
    //region numbers
    KbdToAssosKey.put(KeyEvent.VK_0,"0");
    KbdToAssosKey.put(KeyEvent.VK_1,"1");
    KbdToAssosKey.put(KeyEvent.VK_2,"2");
    KbdToAssosKey.put(KeyEvent.VK_3,"3");
    KbdToAssosKey.put(KeyEvent.VK_4,"4");
    KbdToAssosKey.put(KeyEvent.VK_5,"5");
    KbdToAssosKey.put(KeyEvent.VK_6,"6");
    KbdToAssosKey.put(KeyEvent.VK_7,"7");
    KbdToAssosKey.put(KeyEvent.VK_8,"8");
    KbdToAssosKey.put(KeyEvent.VK_9,"9");
    //endregion
  }

  public static String keyPressedKeyListner ="N/A";
  public static KeyListener listener = new KeyListener() {
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e){int keycode = e.getKeyCode();
      if(Runner.settingUp){return;}
      //region 2)---Assigning Keys---
      if(!GameLoop.newKeyPressed){
        keyPressedKeyListner = KbdToAssosKey.getOrDefault(keycode,"N/A");
        GameLoop.newKeyPressed=true;
      }
    }@Override public void keyReleased(KeyEvent e) {}
  };
  //endregion
}
