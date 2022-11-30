package BackEnd.GlobalInfo;

import FrontEnd.Audio.Sfx;
import FrontEnd.Runner;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static FrontEnd.Runner.curFilePath;

public class GlobData {
  //Initialization & Declaration
  //region Tiles and Tile Color
  public static HashMap<String, Character> tilesName = new HashMap<>();
  public static HashMap<Character, String> tilesChar = new HashMap<>();
  public static HashMap<Character, Sfx> tilesSounds = new HashMap<>();
  public static HashMap<String, Color> txrColors = new HashMap<>();


  //MAKE HASHMAP FOR AUDIO TILES HERE
  //endregion
  //region File Directories
  public static HashMap<String, String> fileFinder = new HashMap<>();
  //endregion
  //region Weather and Time Color
  public static HashMap<String, Color> weatherColor = new HashMap<>();
  public static byte time=10; //TODO MAKE: make it save the time & weather
  public static String weather="clear";
  public static Color worldTint;
  //endregion
  //region Other
  public static int volume=100;
  //endregion

  public static void warmUpGlobData(){
    //region Tiles Sym, Colors, Sound
    // Background color notation 00 = foreground / 01 = background / 02-49 = Layers / 50-99 Blends (the seem between 2 tiles)
    // Sound sfx notation

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
      //region Grass Sound
    tilesSounds.put(tilesName.get("grass"), new Sfx.NoteNumInstrm(73,20,100,9));

    //tilesSounds.put(',', new Sfx.NoteAdv("5A#",20,50,0));
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
      //region Cattail Colors
    //TODO CHANGE COLORS!
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
      //region Dirt Sound(s)

    //tilesSounds.put('∷', new Sfx.Test(20,100,9));
    //tilesSounds.put('░', new Sfx.Test(20,100,9));
    tilesSounds.put(tilesName.get("dirtVert"), new Sfx.NoteNumInstrm(62,20,100,9));
    tilesSounds.put(tilesName.get("dirtHorz"), new Sfx.NoteNumInstrm(62,20,100,9));

    //endregion
    tilesName.put("sand",':'); tilesChar.put(':',"sand");
      //region sand Color
    txrColors.put("sand00",new Color(242,209,107));
    txrColors.put("sand01",new Color(185, 194, 53));
      //endregion
      //region sand Sound(s)
    tilesSounds.put(':',new Sfx.NoteRngAdv(new byte[]{9,10},new ArrayList<Sfx>(){{
      add(new Sfx.NoteOrder(new ArrayList<Sfx>(){{
        add(new Sfx.NoteNumInstrm(69,0,50,9));
        add(new Sfx.NoteNumInstrm(82,0,50,9));
      }}));
      add(new Sfx.NoteNumInstrm(70,0,50,9));
    }}));
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
    tilesName.put("gravel",'▓'); tilesChar.put('▓',"gravel");
      //region gravel Colors
    txrColors.put("gravel00",new Color(116, 109, 100));
    txrColors.put("gravel01", new Color(80, 77, 75));
      //endregion
      //region Gravel Sound(s)
    tilesSounds.put(tilesName.get("gravel"), new Sfx.NoteNumInstrm(62,20,100,9));
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
    tilesName.put("spruce ",'↟'); tilesChar.put('↟',"spruce");
      //region water Color
    txrColors.put("spruce00",new Color(75, 66, 53));
    txrColors.put("spruce01",new Color(131, 122, 115));
      //endregion

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
        //region -Out In Nature-
    tilesName.put("rope",'ʇ'); tilesChar.put('ʇ',"rope");
      //region rope Colors
    txrColors.put("rope00",txrColors.get("mntn00"));
    txrColors.put("rope01",txrColors.get("mntn01"));
      //endregion
    tilesName.put("signPostL",'⍇'); tilesChar.put('⍇',"signPostL");
      //region rope Colors
    txrColors.put("signPostL00",new Color(115, 89, 32));
    txrColors.put("signPostL01",txrColors.get("woodFlr01"));
      //endregion

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
    //region Weather and Time Colors
    weatherColor.put("golden hour 1",new Color(193, 179, 77));
    weatherColor.put("golden hour 2",new Color(148, 145, 97));

    weatherColor.put("day clear",new Color(183, 189, 135));
    weatherColor.put("day rainy",new Color(70, 127, 183));

    weatherColor.put("afternoon clear",new Color(124, 163, 164));
    weatherColor.put("afternoon rainy",new Color(31, 101, 171));

    weatherColor.put("evening",new Color(84, 113, 126));
    weatherColor.put("night",new Color(33, 46, 112));
    weatherColor.put("dark",new Color(1, 14, 80));
    weatherColor.put("spooky",new Color(0, 4, 31));
    //endregion
  }
  public static boolean hitLevBrdr=false;
  public static String walkables=":";
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