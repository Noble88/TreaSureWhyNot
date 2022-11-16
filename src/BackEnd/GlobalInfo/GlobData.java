package BackEnd.GlobalInfo;

import FrontEnd.Runner;

import java.awt.*;
import java.util.HashMap;

import static FrontEnd.Runner.curFilePath;

public class GlobData {
  //region Tiles and Tile Color Initialization & Declaration
  public static HashMap<String, Character> tilesName = new HashMap<>();
  public static HashMap<Character, String> tilesChar = new HashMap<>();
  public static HashMap<String, Color> txrColors = new HashMap<>();
  //endregion
  //region File Directories Initialization & Declaration
  public static HashMap<String, String> fileFinder = new HashMap<>();
  //endregion

  public static void warmUpGlobData(){
    //region Tiles and Tile Colors
    // NOTE: 00 = foreground / 01 = background / 02-49 = Layers / 50-99 Blends (the seem between 2 tiles)
    //region ->basic tiles<-
      //region ---Borders & Intangibles---
    tilesName.put("vertBrder",'|'); tilesChar.put('|',"vertBrder");
    tilesName.put("horzBrder",'-'); tilesChar.put('-',"horzBrder");
    tilesName.put("intrsctBrder",'+'); tilesChar.put('+',"intrsctBrder");
    tilesName.put("lowHorzBrder",'_'); tilesChar.put('_',"lowHorzBrder");
      //region border & intangible colors
    txrColors.put("vertBrder00",new Color(255, 255, 255));    txrColors.put("vertBrder01",new Color(0, 0, 0));
    txrColors.put("horzBrder00",new Color(255, 255, 255));    txrColors.put("horzBrder01",new Color(0, 0, 0));
    txrColors.put("intrsctBrder00",new Color(255, 255, 255)); txrColors.put("intrsctBrder01",new Color(0, 0, 0));
    txrColors.put("lowHorzBrder00",new Color(255, 255, 255)); txrColors.put("lowHorzBrder01",new Color(0, 0, 0));
    //endregion
      //endregion
      //region ---Nature---
    tilesName.put("grass",','); tilesChar.put(',',"grass");
      //region Grass Colors
    txrColors.put("grass00",new Color(7, 165, 7  ));
    txrColors.put("grass01",new Color(49, 94, 32 ));
      //endregion
    tilesName.put("tree",'⚲'); tilesChar.put('⚲',"tree");
      //region Tree Colors
    txrColors.put("tree00",new Color(24, 48, 22 ));
    txrColors.put("tree01",new Color(35, 66, 32 ));
    txrColors.put("tree02",new Color(16, 74, 31));
      //endregion
    tilesName.put("bush",'ʋ'); tilesChar.put('ʋ',"bush");
      //region Bush Colors
    txrColors.put("bush00",new Color(25, 133, 54));
    txrColors.put("bush01",new Color(42, 191, 82));
    txrColors.put("bush02",new Color(38, 150, 68));
      //endregion
    tilesName.put("deadTree",'Ɏ'); tilesChar.put('Ɏ',"deadTree");
      //region DeadTree Colors
    txrColors.put("deadTree00",new Color(171, 129, 2));
    txrColors.put("deadTree01",new Color(191, 144, 0));
      //endregion
    tilesName.put("cattail",'ľ'); tilesChar.put('ľ',"cattail");
      //region DeadTree Colors
    txrColors.put("cattail00",new Color(54, 120, 65));
    txrColors.put("cattail01",new Color(191, 144, 0));
      //endregion
      //endregion
      //region ---Rocky Stuff---
    tilesName.put("dirtVert",'░'); tilesChar.put('░',"dirtVert");
    tilesName.put("dirtHorz",'∷'); tilesChar.put('∷',"dirtHorz");
      //region dirt Color
    txrColors.put("dirtVert00",new Color(150, 75, 1 ));  txrColors.put("dirtHorz00",new Color(150, 75, 1 ));
    txrColors.put("dirtVert01",new Color(156, 125, 54)); txrColors.put("dirtHorz01",new Color(156, 125, 54));
    txrColors.put("dirtVert50",new Color(55, 94, 46));   txrColors.put("dirtHorz50",new Color(55, 94, 46));//Blend w/Grass
    txrColors.put("dirtVert51",new Color(47, 49, 27));   txrColors.put("dirtHorz51",new Color(47, 49, 27));//Blend w/Tree
      //endregion
    tilesName.put("sand",':'); tilesChar.put(':',"sand");
      //region sand Color
    txrColors.put("sand00",new Color(242,209,107));
    txrColors.put("sand01",new Color(185, 194, 53));
      //endregion
    tilesName.put("mud",'∴'); tilesChar.put('∴',"mud");
      //region mud Colors
    txrColors.put("mud00",new Color(61, 45, 34));
    txrColors.put("mud01",new Color(72, 57, 33));
      //endregion
    tilesName.put("seaRocks",'ᵔ'); tilesChar.put('ᵔ',"seaRocks");
      //region seaRocks Colors
    txrColors.put("seaRocks00",new Color(184, 191, 190));
    txrColors.put("seaRocks01",new Color(104, 130, 126));
      //endregion
    tilesName.put("sharpDirt",'\''); tilesChar.put('\'',"sharpDirt");
      //region sharpDirt Colors
    txrColors.put("sharpDirt00",new Color(224, 142, 27));
    txrColors.put("sharpDirt01", new Color(138, 87, 45));
        //endregion
    tilesName.put("mntn",'^'); tilesChar.put('^',"mntn");
      //region mntn Colors
        txrColors.put("mntn00",new Color(186, 123, 35 ));
        txrColors.put("mntn01",new Color(179, 72, 11));
        //endregion
      //endregion
      //region ---Water---
    //TODO: get still water character
    tilesName.put("water",'~'); tilesChar.put('~',"water");
    tilesName.put("wavyWaterVert",'⧛'); tilesChar.put('⧛',"wavyWaterVert");
      //region water Color
    txrColors.put("water00",new Color(62, 68, 246));
    txrColors.put("water01",new Color(78, 141, 186));
      //endregion
      //endregion
      //region---Snow---
    //TODO add: snow
      //endregion
    //endregion
    //region ->Building/Human Made Tiles<-
      //region ---structure related---
    tilesName.put("woodFlr",'#'); tilesChar.put('#',"woodFlr");
      //region woodFlr Colors
    txrColors.put("woodFlr00",new Color(145, 108, 1));
    txrColors.put("woodFlr01",new Color(84, 65, 12));
    //Not sure what this is for bth
    //txrColors.put("woodFlr", new Color(107, 82, 11));
      //endregion
    tilesName.put("fence",'ỻ'); tilesChar.put('ỻ',"fence");
      //region fence Colors
    //TODO ADD: make fence color
    //txrColors.put("fence", COLOR);
      //endregion
    tilesName.put("divot" ,'˽'); tilesChar.put('˽',"divot" );
      //region divot Colors
    //TODO ADD: make divot color
    //txrColors.put("divot", COLOR);
      //endregion
    tilesName.put("cropDirt",'ʭ'); tilesChar.put('ʭ',"cropDirt");
      //region cropDirt Colors
    //TODO ADD: make cropDirt color
    //txrColors.put("cropDirt", COLOR);
      //endregion
      //endregion
      //region---Human Made---
    tilesName.put("rope",'ʇ'); tilesChar.put('ʇ',"rope");
      //region rope Colors
    txrColors.put("rope00",txrColors.get("mntn00"));
    txrColors.put("rope01",txrColors.get("mntn01"));
      //endregion
      //endregion
    //endregion
    //region ->UI elements<-
    tilesName.put("warning",'!'); tilesChar.put('!',"warning");
      //region warning Colors
    //TODO ADD: make warning color
    //txrColors.put("warning", COLOR);
      //endregion
    //endregion
    //region ->multiuse/Misc<-
    tilesName.put("empty",' '); tilesChar.put(' ',"empty");
    tilesName.put("error",'☹'); tilesChar.put('☹',"error");
      //region error Colors
    txrColors.put("error00",new Color(140, 31, 243));
    txrColors.put("error01",new Color(81, 11, 84));
      //endregion
    //endregion
    //endregion
    //region File Directories
    fileFinder.put("BagPages",curFilePath+"/src/BackEnd/FileManagement/SavedData/Assets/BagPages/");
    fileFinder.put("Levels",curFilePath+"/src/BackEnd/FileManagement/SavedData/Assets/Levels");
    fileFinder.put("PlayerData",curFilePath+"/src/BackEnd/FileManagement/SavedData/PlayerData");
    fileFinder.put("KeyBinds",curFilePath+"/src/BackEnd/FileManagement/SavedData/Settings/Key Bindings.txt");
    //endregion
  }
  public static boolean hitLevBrdr=false;
  public static String walkables="";
}



/*Borders & intangable*/// static char W='|',b='-',B='_',P='+',T='⚲', ʋ='ʋ',ʭ='ʭ',Ɏ='Ɏ', ỻ='ỻ',Ầ='(',Ấ=')',ꝑ='+',ƹ='{',ȝ='}',ᶓ='[',ᶔ=']';
/*walkable surface    */// static char g=',',G='.',E=' ',h='H', c=':', H='#',z= '\'',Ď='˽',ĺ='\\',ľ='/';
/*walkable W/item     */// static char w='~',ᵔ='ᵔ',ʇ='ʇ', Ŵ='⧛';
/*new areas teleporter*/// static char R='»', L='«',m='^',e='=',d='$';
/*treasure icons      */// static char Ꝟ='*';
/*NPC                 */// static char D='&' , M='%', ß='ß', ë='ë',ä='ä', Õ='Õ',N='N';
/*animals             */// static char ᵜ='ᵜ';
/*bubbles*/             // static char o='⋄', O='Օ', Օ='ᵕ';
/*Misc                */// static char p='@',ꝟ='∗';