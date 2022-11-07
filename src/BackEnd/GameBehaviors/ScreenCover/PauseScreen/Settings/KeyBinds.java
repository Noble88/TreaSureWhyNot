package BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.PauseBhvr;
import BackEnd.GameLoop;
import FrontEnd.Managers.ScreenCover.PauseScreen.Settings.KeyBindingsMangr;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;
import FrontEnd.Runner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class KeyBinds {
  //TODO EFFICENCY: change menu navigation

  public static int maxLine=0,nav=1,trueNav=1; public static byte maxPage=1,page=1;
  public static ArrayList<Integer> gaps = new ArrayList<>();

  public static String takenBinds ="";
  public static boolean awaitingInput=false;

  public static void warmUpSettings() throws IOException {
      File keyBinds = new File(fileFinder.get("KeyBinds"));
      Scanner sc = new Scanner(keyBinds);
      while(sc.hasNextLine()) {
        maxLine++;
        String data = sc.nextLine();
        if(!isValidLine(data)){gaps.add(maxLine);}
      }
      maxPage = (byte)(int) (.99999+((double) maxLine)/((double) ScreenCoverMangr.yB-2));
      //System.out.println("GAPS"+gaps); (gaps are the lines the user can not use/interact with)
      //System.out.println("MAX LINE"+maxLine);
      sc.close();
      takenBinds= Files.readAllLines(Paths.get(fileFinder.get("KeyBinds"))).get(maxLine-1);
      updateAssosKeyBinds();
    }

    public static HashMap<String,String> assosToKey = new HashMap<>();

    public static void updateAssosKeyBinds() throws FileNotFoundException {
      assosToKey.clear();
      File keyBinds = new File(fileFinder.get("KeyBinds"));
      Scanner sc = new Scanner(keyBinds);
      while (sc.hasNextLine()) {
        String data = sc.nextLine();
        KeyBinds.isValidLine(data);
        if(KeyBinds.isValidLine(data)){ //the actual check to see if is proper key
          assosToKey.put(getKey(data),getAssocKey(data));
        }
      }
      sc.close();
    }

    //region helper methods
  public static boolean isValidLine(String data){
      return data.length()>0 && data.charAt(0) != ' ' && data.charAt(0) != '-';}
  public static String getKey(String data){ return data.substring(0,data.indexOf(":"));}
  public static String getAssocKey(String data){return data.substring(data.indexOf(":")+1);}
  public static String getLine(int line) throws IOException {
      if(line>=maxLine){
        System.out.println("ERROR: KeyBinds -> getLine: attempted to get line"+line+" thats >= to maxLine"); return null;}
    return Files.readAllLines(Paths.get(fileFinder.get("KeyBinds"))).get(line);
  }
  public static boolean isBindOccupied(String key, File file) throws FileNotFoundException {
    Scanner sc = new Scanner(file);
    int line=1;
    while (sc.hasNextLine() && line<maxLine) {
      line++; String data =sc.nextLine();
      if(isValidLine(data) && key.equals(getKey(data))){sc.close(); return true;}
    }
    sc.close(); return false;
  }
  public static void replaceLine(int line, File file) throws IOException {
    FileReader fr = new FileReader(file);
    StringBuffer sb = new StringBuffer();    Scanner sc = new Scanner(new File(fileFinder.get("KeyBinds")));
    while (sc.hasNextLine()) {
      sb.append(sc.nextLine()+System.lineSeparator());
    }
    String entireFile = sb.toString();
    sc.close();
    String oldData=getLine(line);
    String newData=GameLoop.key+":"+getAssocKey(oldData);
    entireFile = entireFile.replaceAll(oldData, newData);
    System.out.println("OLD DATA:"+oldData);
    System.out.println("NEW DATA:"+newData);

    if(isBindOccupied(getKey(oldData),file)){
      entireFile=entireFile.replaceAll(","+getKey(oldData)+",",","+GameLoop.key+",");}
    else{
      entireFile=entireFile.replaceAll( getLine(maxLine),getLine(maxLine)+","+GameLoop.key+",");

    }
    FileWriter writer = new FileWriter(fileFinder.get("KeyBinds"));
    writer.append(entireFile);
    writer.flush(); fr.close();writer.close();;
  }
  public static void emptyBind(int line, File file) throws IOException {
    FileReader fr = new FileReader(file);
    StringBuffer sb = new StringBuffer();    Scanner sc = new Scanner(new File(fileFinder.get("KeyBinds")));
    while (sc.hasNextLine()) {
      sb.append(sc.nextLine()+System.lineSeparator());
    }
    String entireFile = sb.toString();
    sc.close();

    String oldData=getLine(line);
    String newData="N/A:"+getAssocKey(oldData);
    entireFile = entireFile.replaceAll(oldData, newData);
    if(!getKey(oldData).equals("N/A")){
    entireFile = entireFile.replaceAll(","+getKey(oldData)+",", ",");} //replaces used key

    FileWriter writer = new FileWriter(fileFinder.get("KeyBinds"));
    writer.append(entireFile);
    writer.flush(); fr.close();writer.close();
  }

  //endregion

  //NOTE THE LAST NOR FIRST LINE CAN'T BE A "NON VALID" LINE & cant have 2 non valid lines in a row
  public static void keyBindNav() throws IOException, InterruptedException {
    switch (GameLoop.associatedKey) {
      case"UP"   ->{
        //System.out.println("EQUATION 1"+((nav-1)/ScreenCoverMangr.yB));
        if((trueNav-1)%ScreenCoverMangr.yB == 0 && page!=1) {
          page--;KeyBindingsMangr.formPage(page);}
        if(trueNav-1!=2) {nav--;}
        calcTrueNav(-1);
      }
      case"DOWN" ->{
        if((trueNav+1)%ScreenCoverMangr.yB == 0 && page!=maxPage){
          page++; KeyBindingsMangr.formPage(page);}
        else if(nav+1!=ScreenCoverMangr.yB && nav<maxLine) {nav++;}
        calcTrueNav(1);
      }
      case"PAUSE" ->{
        nav=1;trueNav=1;page=1;PauseBhvr.leaveSubMenu();}
      case "INTERACT"->{keyBindModifier();}
    }
    System.out.println("nav:"+nav+" Page:"+page+" TrueNav:"+trueNav);
  }
  public static void calcTrueNav(int offset){
    if(nav==1){trueNav=3;}
      for(int i=0; i<gaps.size();i++){
        if(nav==gaps.get(i) && nav+offset>2){nav+=offset;}
      }


    trueNav=nav;
  }
  //TODO EFFICIENCY: make menu math and navigation easier/simplier (seems messy & [looks]inefficent)

  public static void keyBindModifier() throws IOException, InterruptedException {
    File keyBindsFile = new File(fileFinder.get("KeyBinds"));
    if(!awaitingInput){awaitingInput=true;
      //TODO MAKE: make a pop-up window to displays these messages
      System.out.println("Press the key you would like to reassign... " +
          "Note: Hit delete/backspace to unbind a key or hit ESC to back out");
    }
    else if(GameLoop.key.equals("DELETE")){awaitingInput=false; emptyBind(trueNav,keyBindsFile); updateAssosKeyBinds();
    } else if(GameLoop.key.equals("ESC")){awaitingInput=false;
      System.out.println("you backed out");
    } else {replaceLine(trueNav-1, keyBindsFile); awaitingInput=false; updateAssosKeyBinds();}

    KeyBindingsMangr.formPage(page);
  }
  //TODO BUG: keybindings might not refresh when the delete all saved files boolean is true

}
