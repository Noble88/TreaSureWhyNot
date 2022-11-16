package FrontEnd.Colors;


import FrontEnd.Window;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;

import static BackEnd.GlobalInfo.GlobData.*;

public class LevColors {
  /*
  TODO WORK ON: Make it so when load a level it will request the colors it will need
    -(could make it scan the level before hand and checks to see what colors are needed
  */
  public static SimpleAttributeSet levCellColor(char e){ //sets forground
    SimpleAttributeSet TEMP = new SimpleAttributeSet();
    if(txrColors.containsKey(tilesChar.get(e)+"00")){
      StyleConstants.setForeground(TEMP, txrColors.get(tilesChar.get(e)+"00"));
    }
    else{StyleConstants.setForeground(TEMP, txrColors.get("error00"));}
    return TEMP;
  }

  //region weather & time
  public static void warmUpWindowTint(){

  }

  public static Color overWorldTint;
  public static void progressTime(){
    time++;
    if(time==25){time=1;}
    System.out.println(time+"AND OUTPUT"+ (50+ (int) ((((double)(time+1)%4*15))*.8)));
    if(time<4){overWorldTint=weatherColor.get("spooky");}
    else if(time<8){overWorldTint=weatherColor.get("day");}
    else if(time<12){overWorldTint=weatherColor.get("afternoon");}
    else if(time<16){overWorldTint=weatherColor.get("evening");}
    else if(time<20){overWorldTint=weatherColor.get("night");}
    else if(time<=24){overWorldTint=weatherColor.get("dark");}
    else{overWorldTint=weatherColor.get("dark");}

    overWorldTint = new Color(overWorldTint.getRed(), overWorldTint.getGreen(), overWorldTint.getBlue(), 20+ (int) ((((double)(time+1)/4*15))*.8) );
    repaintAll();
    /*
     */
  }

  public static JPanel windowTint = new JPanel(){
    public void paintComponent(Graphics g) {
      g.setColor(overWorldTint);
      g.fillRect(0,0, Window.windowSize[0],Window.windowSize[1]);
      super.paintComponent(g);
    }
    /*
    public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(overWorldTint);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 10 * 0.1f));
        g2d.fillRect(0,0, Window.windowSize[0],Window.windowSize[1]);
      System.out.println("PAINTED !!!!");
    }

     */



    /*
    public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(overWorldTint);
      g2d.clearRect(0,0, Window.windowSize[0],Window.windowSize[1]);
      System.out.println("PAINTED !!!!");
    }

     */



  };

  public static void repaintAll(){
    windowTint.repaint();
  }
  //endregion

  //endregion

}
