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

  //endregion

}
