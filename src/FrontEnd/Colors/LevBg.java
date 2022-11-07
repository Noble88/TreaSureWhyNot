package FrontEnd.Colors;

import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static BackEnd.GlobalInfo.GlobData.*;

public class LevBg {
  public static final int yMin=0;
  public static final int xMin=0;
  public static int height= FrontEnd.Window.levBnds[3]/ FrontEnd.Window.yBlev;
  public static int width = FrontEnd.Window.levBnds[2]/ FrontEnd.Window.xBlev;
  public static ArrayList<String> autoDrawList= new ArrayList<>();

  public static boolean checkAutomatedConditions=true; //makes it so doesn't run calculation unnecessarily

  public static JPanel makeBackgroundTile(byte row, byte col){
    return new JPanel(){
      public final byte[] pos = new byte[]{row, col};
      public char sym = '?';

      //TODO EFFICENCY: make filters that are atteched to the level bhvr (ex: level contains dirt and/or foliage) so when
      // go through switch case doesn't need to go through all the options. think of it like importing sections of what it could look for.

      //region Color Data & Methods
      public Color colorTile(){
        if(txrColors.containsKey(tilesChar.get(sym)+"01")){
          return txrColors.get(tilesChar.get(sym)+"01");
        }
        else {return txrColors.get("error01");}


      }
      //endregion

      //region automated details helper method
      public boolean adjacentSymCompare(int y, int x, char symCompared, boolean useFG){
        return LevelBhvr.curLev.getE(new byte[]{(byte) (pos[0]+y), (byte) (pos[1]+x)},useFG) == symCompared;
      }

      //endregion
      //paint runs on 3 conditions
      /*
      -The component is first made visible on the screen.
      -The component is resized.
      -The component has damage that needs to be repaired. (For example, something that previously obscured the
        component has moved, and a previously obscured portion of the component has become exposed).
      */
    public void paint(Graphics g) {
      sym=LevelBhvr.curLev.getE(pos,false);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(colorTile());
      g2d.fillRect(0,0,width,height);
      //System.out.println("Updated Tile -> (Y:"+pos[0]+", X:"+pos[1]+")");
      //region ALL AutoDrawList Commands
      /*
      -"PLAYER GRASS RUFFLE" = adds a tuff when player is on a grass icon
      -"BUSH GRASS TUF"
      -"DIRT GRASS BLEND"= adds a blend between dirt and grass
      */

      //TODO efficency: make a class or methods that does this automattacly cuz can be simplfied
      for(byte i=0; i<autoDrawList.size(); i++){
        switch(autoDrawList.get(i)){
          case "BUSH GRASS TUF"->{
            if(sym == tilesName.get("grass")){
              g2d.setColor(txrColors.get("bush02"));
              if(adjacentSymCompare(-1,0, tilesName.get("bush"),false)) {//above
                g2d.fillRect(0,0,width,2);
              }
              if(adjacentSymCompare(1,0, tilesName.get("bush"),false)) {//below
                g2d.fillRect(0,height-2,width,2);
              }
              if(adjacentSymCompare(0,1, tilesName.get("bush"),false)) {//right
                g2d.fillRect(width-2,0,2,height);
              }
              if (adjacentSymCompare(0,-1, tilesName.get("bush"),false)){//left
                g2d.fillRect(0,0,2,height);
              }
            }
          }
          case "DIRT GRASS BLEND"->{
            if(sym == tilesName.get("dirtVert") || sym == tilesName.get("dirtHorz")){
              g2d.setColor(txrColors.get("dirt50"));
              if(adjacentSymCompare(-1,0, tilesName.get("grass"),false)) {//above
                g2d.fillRect(0,0,width,2);
              }
              if(adjacentSymCompare(1,0, tilesName.get("grass"),false)) {//below
                g2d.fillRect(0,height-2,width,2);
              }
              if(adjacentSymCompare(0,1, tilesName.get("grass"),false)) {//right
                g2d.fillRect(width-2,0,2,height);
              }
              if (adjacentSymCompare(0,-1, tilesName.get("grass"),false)){//left
                g2d.fillRect(0,0,2,height);
              }
            }
          }
          case "DIRT TREE BLEND"->{
            if(sym == tilesName.get("dirtVert") || sym == tilesName.get("dirtHorz")){
              g2d.setColor(txrColors.get("dirt51"));
              if(adjacentSymCompare(-1,0, tilesName.get("tree"),false)) {//above
                g2d.fillRect(0,0,width,2);
              }
              if(adjacentSymCompare(1,0, tilesName.get("tree"),false)) {//below
                g2d.fillRect(0,height-2,width,2);
              }
              if(adjacentSymCompare(0,1, tilesName.get("tree"),false)) {//right
                g2d.fillRect(width-2,0,2,height);
              }
              if (adjacentSymCompare(0,-1, tilesName.get("tree"),false)){//left
                g2d.fillRect(0,0,2,height);
              }
            }
          }
          case "PLAYER GRASS RUFFLE"->{}
        }
      }
      //endregion
    }};
  }

  public static void warmUpLevelBackground(){ //PRECONDITION: curLevel has already been assigned
    for(byte row = 0; row< FrontEnd.Window.yBlev; row++){
      for(byte col = 0; col< FrontEnd.Window.xBlev; col++){
        FrontEnd.Window.levBgCells[row][col]=(makeBackgroundTile(row,col));
        FrontEnd.Window.levBgCells[row][col].setSize(width,height);
        FrontEnd.Window.levBgCells[row][col].setOpaque(false);
        FrontEnd.Window.levBg.add(FrontEnd.Window.levBgCells[row][col]);
      }
    }

    /*
    Window.levBg.remove(18+18);
    Window.levBg.add(Window.levBgCells[18][18]);
    Window.levBg.remove(19+19);
    Window.levBg.add(Window.levBgCells[19][19]);

     */
  }

  public static void updateAutoDrawList(ArrayList<String> arr){autoDrawList.clear();autoDrawList.addAll(arr);}
  public static void repaintAll(){
    for(byte r = 0; r< FrontEnd.Window.yBlev; r++){
      for(byte c = 0; c< FrontEnd.Window.xBlev; c++){
        Window.levBgCells[r][c].repaint();
      }
    }
  }





}
