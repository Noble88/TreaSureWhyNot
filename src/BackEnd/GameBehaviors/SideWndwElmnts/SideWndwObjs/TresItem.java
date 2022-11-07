package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs;

import java.io.Serializable;
public class TresItem implements Serializable {

  public static class BasicTre extends TresItem implements Serializable{
    String name="", description=""; int value=0,quantity=1;
    char[][] icon= new char[2][2];
    byte[] pos = new byte[2];
    public BasicTre(String name, String descr, int val, char[][] icon){
      this.name=name; description=descr; value=val;
      for(byte r=0;r<2;r++){ for(byte c=0;c<2;c++){ this.icon[r][c]=icon[r][c];}}
    }
    //region Pos Methods
    public void setPos(byte[] pos){this.pos[0]=pos[0];this.pos[1]=pos[1];}
    public byte[] getPos(){return pos;}
    //endregion
    //region Icon Methods
    public char[][] getIcon(){return icon;}
    public void setIcon(char[][] icon){for(byte r=0;r<2;r++){ for(byte c=0;c<2;c++){
      this.icon[r][c]=icon[r][c];}}}

    public int getQuantity(){return quantity;}
//make a set or add to quantity or sumthing


    //endregion
  }


  public static class AdvTre extends TresItem implements Serializable{
    String name="", description=""; int value=0;

    public AdvTre(String name, String descr, int val){
      this.name=name; description=descr; value=val;
    }
  }

  public void setDescr(String text){}
  public String getDescr(){return "EMPTY SET TEXT";}

  public void setValue(int num){}
  public int getValue(){return -1;} //NOTE: value of -1=unsellable

  public void setName(String text){}
  public String getName(){return "EMPTY SET NAME";}

  public void setIcon(char[][] icon){}
  public char[][] getIcon(){return new char[][]{};}

  public void setPos(byte[] pos){}
  public byte[] getPos(){return new byte[]{-1,-1};}

  public void setQuantity(byte[] pos){}
  public int getQuantity(){return -1;}

  public void setPlayerHasItem(boolean hasItem){}
  public boolean getPlayerHasItem(){return false;}



  //COULD add a quantity of item

}
