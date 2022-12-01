package FrontEnd.Colors;


import BackEnd.GameBehaviors.LevelBhvr;
import BackEnd.GlobalInfo.GlobData;
import FrontEnd.Window;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;

import static BackEnd.GlobalInfo.GlobData.*;

public class LevColors {
  public static SimpleAttributeSet levCellColor(char e){ //sets forground
    SimpleAttributeSet TEMP = new SimpleAttributeSet();
    if(txrColors.containsKey(tilesChar.get(e)+"00")){
      StyleConstants.setForeground(TEMP, txrColors.get(tilesChar.get(e)+"00"));
    }
    else{StyleConstants.setForeground(TEMP, txrColors.get("error00"));}
    return TEMP;
  }

  //region weather & time

  public static void repaintWorldTint(){
    if(LevelBhvr.curLev.levelBg!=null){
      worldTint = LevelBhvr.curLev.levelBg;windowTint.repaint(); return;}
    int r=0,g=0,b=0,a=0; //adjusters
    switch(GlobData.weather){
      case"clear"->{
        if(time<5){worldTint =weatherColor.get("night");}
        else if(time==5){worldTint =weatherColor.get("golden hour 1");a=-20;}
        else if(time==6){worldTint =weatherColor.get("golden hour 2");a=-10;}
        else if(time<12){worldTint =weatherColor.get("day clear");a=-20;}
        else if(time<14){worldTint =weatherColor.get("afternoon clear");a=-20;}
        else if(time<16){worldTint =weatherColor.get("evening");}
        else if(time<20){worldTint =weatherColor.get("night");}
        else if(time<=24){worldTint =weatherColor.get("dark");}
        else{worldTint =weatherColor.get("spooky");}
      }
      case"rainy"->{
        if(time<5){worldTint =weatherColor.get("night");}
        else if(time<6){worldTint =weatherColor.get("evening");a=10;}
        else if(time<10){worldTint =weatherColor.get("day rainy");}
        else if(time<14){worldTint =weatherColor.get("afternoon rainy");a=10;}
        else if(time<16){worldTint =weatherColor.get("evening");r=-20;g=-20;a=20;}//4
        else if(time<20){worldTint =weatherColor.get("night");r=-20;g=-20;}
        else if(time<=24){worldTint =weatherColor.get("dark");}
        else{worldTint =weatherColor.get("spooky");}
      }
    }
    worldTint = new Color(worldTint.getRed()+r, worldTint.getGreen()+g, worldTint.getBlue()+b, 50+a);
  }

  public static JPanel windowTint = new JPanel(){
    public void paintComponent(Graphics g) {
      g.setColor(worldTint);
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
  //endregion

  //endregion

}
