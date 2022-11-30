package BackEnd.GameBehaviors.HelpElmnts;

import java.util.Arrays;

public class MenuCus {
  byte maxPg=1, maxRow,maxCol; //NOTE: max page is the inclusive (starts at 1) BUT max row & col is exclusive
  byte curPg=1;
  byte[] nav=new byte[]{0,0}; //Y,X || row,col

  //wrapping makes it so when hit max page and need to go over max page then goes back to first page (note if 1 page then attempts to go to next page will go back around
  boolean horizWrap,vertWrap;
  boolean vertChngPg=false, horizChngPg=false;



  public MenuCus(int maxPages, int row, int col, boolean changeHorizontally, boolean wrapHoriz, boolean wrapVert){
    maxPg= (byte) maxPages; maxRow= (byte) row; maxCol= (byte) col;
    if(changeHorizontally && maxPages!=1){horizChngPg=changeHorizontally; vertChngPg=!changeHorizontally;}
    else {horizChngPg=false; vertChngPg=false;}

    horizWrap=wrapHoriz; vertWrap=wrapVert;
  } //Pages w/left right & up down movement
  
  
  public void move(String dir){
    switch(dir){
      case "UP"->{
        if( (nav[0]-1) !=-1 ){nav[0]-=1;} //IF [WON'T go to a new page]
        else if(vertChngPg){ //IF changes pages vertically
          if(vertWrap && curPg-1==0){curPg=maxPg; nav[0]= (byte) (maxRow-1);}//IF [WILL go to new page] BUT [WILL hit the minimum or maximum page]
          else {curPg--; nav[0]= (byte) (maxRow-1);}//IF [WILL go to new page] AND [WON'T hit the minimum or maximum page]
        }
        else if(vertWrap && maxPg==1){//IF [WILL go to a new page] AND [Wraps Vertically] AND [Wraps Horizontally] BUT [only has 1 page]
          nav[0]= (byte) (maxRow-1);
        }
      }
      case "DOWN"->{
        if( (nav[0]+1) != (maxRow) ){nav[0]+=1;} //IF [WON'T go to a new page]
        else if(vertChngPg){ //IF changes pages vertically
          if(vertWrap && curPg+1>maxPg){curPg=1; nav[0]=0;}//IF [WILL go to new page] BUT [WILL hit the minimum or maximum page]
          else {curPg++; nav[0]=0;}//IF [WILL go to new page] AND [WON'T hit the minimum or maximum page]
        }
        else if(vertWrap && maxPg==1){nav[0]=0;}//IF [WILL go to a new page] AND [Wraps Vertically] AND [Wraps Horizontally] BUT [only has 1 page]
      }
      case "RIGHT"->{
        if( (nav[1]+1) < (maxCol) ){nav[1]+=1;} //IF [WON'T go to a new page]
        else if(horizChngPg){ //IF changes pages vertically
          if(horizWrap && curPg+1>maxPg){curPg=1; nav[1]=0;}//IF [WILL go to new page] BUT [WILL hit the minimum or maximum page]
          else {curPg++; nav[1]=0;}//IF [WILL go to new page] AND [WON'T hit the minimum or maximum page]
        }
        else if(horizWrap && maxPg==1){nav[1]=0;}//IF [WILL go to a new page] AND [Wraps Vertically] AND [Wraps Horizontally] BUT [only has 1 page]
      }
      case "LEFT"->{
        if( (nav[1]-1) != -1){nav[1]-=1;} //IF [WON'T go to a new page]
        else if(horizChngPg){ //IF changes pages vertically
          if(horizWrap && curPg-1==0){curPg=maxPg; nav[1]=(byte) (maxCol-1);}//IF [WILL go to new page] BUT [WILL hit the minimum or maximum page]
          else {curPg--; nav[1]=(byte) (maxCol-1);}//IF [WILL go to new page] AND [WON'T hit the minimum or maximum page]
        }
        else if(horizWrap && maxPg==1){nav[1]=(byte) (maxCol-1);}//IF [WILL go to a new page] AND [Wraps Vertically] AND [Wraps Horizontally] BUT [only has 1 page]
      }
    }
  }


  public byte getMaxPg(){ return maxPg;}
  public byte getCurPg(){ return curPg;}
  public byte[] getNav(){return nav;}

  public void setNav(int y, int x){nav[0]= (byte) y;nav[1]= (byte) x;}
  public void setCurPg(int pg){curPg= (byte) pg;}
  public void setMaxPg(int pg){maxPg= (byte) pg;}

  public String toString(){
    //TODO ADD: add the boolean stuff to this to string method
    return "Page ("+curPg+"/"+maxPg+") on Y:"+nav[0]+"  &  X:"+nav[1];
  }


}
