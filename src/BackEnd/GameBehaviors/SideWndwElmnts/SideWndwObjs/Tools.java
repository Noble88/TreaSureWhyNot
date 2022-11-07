package BackEnd.GameBehaviors.SideWndwElmnts.SideWndwObjs;

import java.io.Serializable;

public class Tools {
  public static class BasicTool extends Tools implements Serializable {
    String name="", description=""; int quantity;
    char[][] icon= new char[3][5];
    byte[] pos = new byte[2];
    public BasicTool(String name, String descr, int amount ,char[][] icon){
      this.name=name; description=descr; quantity=amount;
      for(byte r=0;r<3;r++){ for(byte c=0;c<5;c++){ this.icon[r][c]=icon[r][c];}}
    }
    //region Pos Methods
    public void setPos(byte[] pos){this.pos[0]=pos[0];this.pos[1]=pos[1];}
    public byte[] getPos(){return pos;}
    //endregion
    //region Name Methods
    public void setName(String text){name=text;}
    public String getName(){return name;}
    //endregion
    //region Icon Methods
    public char[][] getIcon(){return icon;}
    public void setIcon(char[][] icon){for(byte r=0;r<3;r++){ for(byte c=0;c<5;c++){ this.icon[r][c]=icon[r][c];}}}
    //endregion
    //region quantity methods
    public void addQuantity(int num){quantity+=num;}
    public void setQuantity(int num){quantity=num;}
    public int getQuantity(){return quantity;}
    //endregion
    //region description methods
    public String getDescr(){return description;}
    //endregion
  }

  public void setDescr(String text){}
  public String getDescr(){return "EMPTY get DEScr";}

  public void setValue(int num){}
  public int getValue(){return -1;} //NOTE: value of -1=unsellable

  public void setName(String text){}
  public String getName(){return "EMPTY SET NAME";}

  public void setIcon(char[][] icon){}
  public char[][] getIcon(){return new char[][]{};}

  public void setPos(byte[] pos){}
  public byte[] getPos(){return new byte[]{-1,-1};}

  public void addQuantity(int num){}
  public void setQuantity(int num){}
  public int getQuantity(){return -1;}

}
