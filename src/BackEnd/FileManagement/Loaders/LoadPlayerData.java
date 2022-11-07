package BackEnd.FileManagement.Loaders;

import BackEnd.GameBehaviors.SideWndwBhvr;
import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import BackEnd.PlayerData;
import FrontEnd.Debugger;
import FrontEnd.Runner;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static BackEnd.GameBehaviors.LevelBhvr.curLev;
import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class LoadPlayerData {

  //region Save Player Data

  //region helper methods
  public static String getData(String data){ return data.substring(0,data.indexOf(":"));}
  public static String getAssocData(String data){return data.substring(data.indexOf(":")+1);}
  public static String getLineData(int line) throws IOException {
    return Files.readAllLines(Paths.get(fileFinder.get("PlayerData"))).get(line);}

  //endregion

  public static void savePlayerData() throws IOException {
    String formatedForFilePath= curLev.selfPath;

    while(formatedForFilePath.indexOf('\\')!=-1){
      formatedForFilePath=formatedForFilePath.substring(0,formatedForFilePath.indexOf('\\'))+"/"+
          formatedForFilePath.substring(formatedForFilePath.indexOf('\\')+1);
    } //need to be done so when saving filepath doesn't leave out the slashes

    File playerData = new File(fileFinder.get("PlayerData"));
    FileReader fr = new FileReader(playerData);
    StringBuffer sb = new StringBuffer();
    Scanner sc = new Scanner(new File(fileFinder.get("PlayerData")));
    while (sc.hasNextLine()) {
      sb.append(sc.nextLine()+System.lineSeparator());
    }
    String entireFile = sb.toString();

    String line1=getLineData(1), line2=getLineData(2),
        line3=getLineData(3),line4=getLineData(4),line5=getLineData(5),line6=getLineData(6),line7=getLineData(7);
    //region Player Location (Lines 1-2)
    entireFile = entireFile.replaceAll(line1, line1.substring(0,line1.indexOf(':')+1)+formatedForFilePath); //Line 1 = What Lev Player is in
    entireFile = entireFile.replaceAll(line2, line2.substring(0,line2.indexOf(':')+1)+PlayerData.pos[0]+","+PlayerData.pos[1]); //Line 2 = What Position Player
    //endregion
    //region Side Window Elements (Lines 3-7)
    entireFile = entireFile.replaceAll(line3, line3.substring(0,line3.indexOf(':')+1)+SideWndwBhvr.curTab); //Line 3 = what tab player on
    entireFile = entireFile.replaceAll(line4, line4.substring(0,line4.indexOf(':')+1)+ToolBag.menu.getCurPg()); //Line 4 = What Tool Page on
    entireFile = entireFile.replaceAll(line5, line5.substring(0,line5.indexOf(':')+1)+ToolBag.menu.getNav()[0]+","+ToolBag.menu.getNav()[1]); //Line 5 = What Tool Selection on
    entireFile = entireFile.replaceAll(line6, line6.substring(0,line6.indexOf(':')+1)+TresBag.menu.getCurPg()); //Line 6 = What Treasure Page on
    entireFile = entireFile.replaceAll(line7, line7.substring(0,line7.indexOf(':')+1)+TresBag.menu.getNav()[0]+","+ToolBag.menu.getNav()[1]); //Line 7 = What Treasure Selection on
    //endregion

    FileWriter writer = new FileWriter(fileFinder.get("PlayerData"));

    writer.append(entireFile);
    writer.flush(); fr.close();writer.close();sc.close();
    if(Debugger.saveDataDebug){Debugger.savedData(getSavedPlayerPosition(),getLineData(1));}//line 1 folder to level player was saved in
  }

  public static byte[] getSavedPlayerPosition() throws IOException {
    String cords = getLineData(2);
    byte y = Byte.parseByte(cords.substring(cords.indexOf(":")+1,cords.indexOf(",")));//"y,x"
    byte x = Byte.parseByte(cords.substring(cords.indexOf(",")+1));//"y,x"
    return new byte[]{y,x};
  }
  public static String getSavedLevelFileDirectoryPlayerIn() throws IOException {
    return getLineData(1).substring(getLineData(1).indexOf(':')+1);
  }

  public static String getSavedTab() throws IOException {
    return getLineData(3).substring(getLineData(3).indexOf(':')+1);
  }
  public static byte getSavedBagPage(boolean getTool) throws IOException {
    if(getTool){return Byte.parseByte(getLineData(4).substring(getLineData(4).indexOf(':')+1));}
    else{return Byte.parseByte(getLineData(6).substring(getLineData(6).indexOf(':')+1));}
  }
  public static byte[] getSavedBagNav(boolean getToolNav) throws IOException {
    String line="";
    if(getToolNav){line=getLineData(5);}  else{line=getLineData(7);}

    byte y = Byte.parseByte(line.substring(line.indexOf(":")+1,line.indexOf(",")));//"y,x"
    byte x = Byte.parseByte(line.substring(line.indexOf(",")+1));//"y,x"
   return new byte[]{y,x};
  }
  //endregion
}
