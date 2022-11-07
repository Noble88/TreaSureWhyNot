package FrontEnd.Colors;


import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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

    /*
  public static Color[] grass = new Color[]{
      new Color(7, 165, 7  ),new Color(49, 94, 32 ),
  };
  public static Color[] bush = new Color[]{
      new Color(25, 133, 54),new Color(42, 191, 82),
      new Color(38, 150, 68)

  };
  public static Color[] tree = new Color[]{
      new Color(24, 48, 22 ),new Color(35, 66, 32 ),
      new Color(16, 74, 31)
  };
  public static Color[] deadTree = new Color[]{
      new Color(171, 129, 2),new Color(191, 144, 0),
  };
  //endregion
  //region Rocky Stuff
  public static Color[] dirt = new Color[]{
      new Color(150, 75, 1 ),new Color(156, 125, 54),
      new Color(55, 94, 46) ,new Color(47, 49, 27),
  };
  public static Color[] mud = new Color[]{
      new Color(61, 45, 34),new Color(72, 57, 33),
  };
  public static Color[] roughtDirt = new Color[]{

  };
  public static Color[] mountain = new Color[]{
      new Color(186, 123, 35 ),new Color(179, 72, 11),
  };
  public static Color[] sand = new Color[]{
      new Color(242,209,107),new Color(185, 194, 53),
  };
  public static Color[] rock = new Color[]{
      new Color(184, 191, 190),new Color(104, 130, 126),
  };
  //endregion
  //region water & snow
  public static Color[] snow = new Color[]{
      new Color(161, 191, 187),new Color(235, 247, 247),
  };
  public static Color[] water = new Color[]{
      new Color(62, 68, 246  ),new Color(78, 141, 186),
  };
  //endregion]
  //region Structure related
  public static Color[] woodFloor = new Color[]{
      new Color(145, 108, 1),new Color(84, 65, 12),
      new Color(107, 82, 11),
  };
  public static Color[] rope = new Color[]{
      new Color(77, 53, 19),
  };
  //endregion
  //region UI elements
  public static Color[] warning = new Color[]{
      new Color(179, 0, 37),
  };
  //endregion
  //region Colors
  public static Color white = new Color(255, 255, 255), black = new Color(0,0,0);
  public static Color shinyGold = new Color(255, 215, 0);
  //endregion
  public static Color[] nullCol = new Color[]{
      new Color(140, 31, 243),new Color(81, 11, 84),
  };
  //endregion

   */
  //endregion

}
