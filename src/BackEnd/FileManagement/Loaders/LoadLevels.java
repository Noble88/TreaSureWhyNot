package BackEnd.FileManagement.Loaders;
import BackEnd.GameBehaviors.LevelBhvr;
import FrontEnd.Runner;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static BackEnd.GlobalInfo.GlobData.*;
import static FrontEnd.Runner.curFilePath;



public class LoadLevels {

  public static void loadFarmenTump() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (0,-1) "temp1"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,-1});}};
    LevelBhvr.Level temp1 = new LevelBhvr.Level(pos1,
        "temp1",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/FarmenTump/0,-1.txt"));
    //endregion
    //region Level Behaviors
    temp1.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert") +
        tilesName.get("divot"),true);
    temp1.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (0,-2) "Boxed Cattle"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,-2});}};
    LevelBhvr.Level boxedCattle = new LevelBhvr.Level(pos1,
        "Boxed Cattle",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/FarmenTump/0,-2.txt"));
    //endregion
    //region Level Behaviors
    boxedCattle.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert") +
        tilesName.get("divot"),true);
    boxedCattle.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (1,-1) "Crop Yard"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,-1});}};
    LevelBhvr.Level cropYard = new LevelBhvr.Level(pos1,
        "Crop Yard",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/FarmenTump/1,-1.txt"));
    //endregion
    //region Level Behaviors
    cropYard.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert") +
        tilesName.get("divot"),true);
    cropYard.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (1,-2) "Crop Field"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,-2});}};
    LevelBhvr.Level cropField = new LevelBhvr.Level(pos1,
        "Crop Yard",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/FarmenTump/1,-2.txt"));
    //endregion
    //region Level Behaviors
    cropField.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert") +
        tilesName.get("divot"),true);
    cropField.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(temp1);
    createLevelFile(boxedCattle);
    createLevelFile(cropYard);
    createLevelFile(cropField);
  }
  public static void loadLivelyGreens() throws InterruptedException, IOException, ClassNotFoundException {
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (0,0) "Shiny Greens"
      //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,0});}};
    LevelBhvr.Level shinyGreen = new LevelBhvr.Level(pos1,
        "Shiny Greens",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/LivelyLeafs/0,0.txt"));
      //endregion
      //region Level Behaviors
    shinyGreen.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    shinyGreen.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
      //endregion
      //region Add GamePlay Objects

      //endregion
    //endregion
      //region (0,0) subLevels
      //region suLevel Declaration
    levChngParent1= new byte[]{13,3};// where enterance into sublevel
    childSpawn1= new byte[]{18,10};// where player spawn in sublevel
    levChngChild1= new byte[]{19,10};//where in sublevel is exit to level
    parentSpawn1=new byte[]{14,3};//where spawn upon leaving sublevel
      //region Home
      //region Make Level Object
    pos2=new ArrayList<>(){{add(new byte[]{0,0});add(levChngParent1);add(new byte[]{0,0});}};
    LevelBhvr.Level home = new LevelBhvr.Level(pos2,
        "Home",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/LivelyLeafs/" +
                "0,0-"+levChngParent1[0]+","+levChngParent1[1]+"_0,0"+".txt"));
      //endregion
      //region add other behaviors
    home.assignUniqueBhvr(""+
        tilesName.get("empty")+ tilesName.get("lowHorzBrder"),false);
      //endregion
      //region Add Level Switch
    home.assignLevelSwitch(levChngChild1,parentSpawn1,pos1); //EXIT to shinyGreen
      //endregion
      //endregion
      //endregion
      //region subLevel Adding
    shinyGreen.assignLevelSwitch(levChngParent1,childSpawn1,pos2);//Enter Home
      //endregion
      //endregion
    //region (0,1) "Nestled Nuz"
      //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,1});}};
    LevelBhvr.Level nestledNuz = new LevelBhvr.Level(pos1,
        "Nestled Nuz",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/LivelyLeafs/0,1.txt"));
    //endregion
      //region Modify Level Behaviors
    nestledNuz.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    nestledNuz.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
      //endregion
    //endregion
    //region (1,0) "Shrubbed Shrubbery"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,0});}};
    LevelBhvr.Level shrubbedShrubbery = new LevelBhvr.Level(pos1,
        "Shrubbed Shrubbery",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/LivelyLeafs/1,0.txt"));
    //endregion
    //region Modify Level Behaviors
    shrubbedShrubbery.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    shrubbedShrubbery.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //endregion
    //region (1,1) "Plainned Out Grass"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,1});}};
    LevelBhvr.Level plainnedOutGrass = new LevelBhvr.Level(pos1,
        "Plainned Out Grass",
        fileTranslator(curFilePath+"/src/BackEnd/FileManagement/FreshAssets/LevelLayout/LivelyLeafs/1,1.txt"));
    //endregion
    //region Modify Level Behaviors
    plainnedOutGrass.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    plainnedOutGrass.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //endregion

    //region Write Levels
    createLevelFile(shinyGreen);
      createLevelFile(home);
    createLevelFile(nestledNuz);
    createLevelFile(shrubbedShrubbery);
    createLevelFile(plainnedOutGrass);

    //endregion
  }
  public static void loadKnotCross() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (0,2) "temp7"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,2});}};
    LevelBhvr.Level temp7 = new LevelBhvr.Level(pos1,
        "temp7",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/KnotCross/0,2.txt"));
    //endregion
    //region Level Behaviors
    temp7.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp7.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (0,3) "temp8"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{0,3});}};
    LevelBhvr.Level temp8 = new LevelBhvr.Level(pos1,
        "temp8",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/KnotCross/0,3.txt"));
    //endregion
    //region Level Behaviors
    temp8.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp8.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (1,2) "River Bulge"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,2});}};
    LevelBhvr.Level riverBulge = new LevelBhvr.Level(pos1,
        "River Bulge",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/KnotCross/1,2.txt"));
    //endregion
    //region Level Behaviors
    riverBulge.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    riverBulge.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (1,3) "Water Falling"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{1,3});}};
    LevelBhvr.Level waterFalling = new LevelBhvr.Level(pos1,
        "Water Falling",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/KnotCross/1,3.txt"));
    //endregion
    //region Level Behaviors
    waterFalling.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    waterFalling.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(temp7);
    createLevelFile(temp8);
    createLevelFile(riverBulge);
    createLevelFile(waterFalling);
  }
  public static void loadWetLands() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (2,-1) "Muddy Putty"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,-1});}};
    LevelBhvr.Level muddyPutty = new LevelBhvr.Level(pos1,
        "Muddy Putty",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/WetLands/2,-1.txt"));
    //endregion
    //region Level Behaviors
    muddyPutty.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    muddyPutty.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (2,-2) "temp2"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,-2});}};
    LevelBhvr.Level temp2 = new LevelBhvr.Level(pos1,
        "temp2",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/WetLands/2,-2.txt"));
    //endregion
    //region Level Behaviors
    temp2.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp2.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,-1) "temp3"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,-1});}};
    LevelBhvr.Level temp3 = new LevelBhvr.Level(pos1,
        "temp3",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/WetLands/3,-1.txt"));
    //endregion
    //region Level Behaviors
    temp3.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp3.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,-2) "Melty Snow"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,-2});}};
    LevelBhvr.Level meltySnow = new LevelBhvr.Level(pos1,
        "Melty Snow",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/WetLands/3,-2.txt"));
    //endregion
    //region Level Behaviors
    meltySnow.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    meltySnow.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion


    createLevelFile(muddyPutty);
    createLevelFile(temp2);
    createLevelFile(temp3);
    createLevelFile(meltySnow);
  }
  public static void loadRiverSide() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (2,0) "Cattail Marsh"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,0});}};
    LevelBhvr.Level cattailMarsh = new LevelBhvr.Level(pos1,
        "Cattail Marsh",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/RiverSide/2,0.txt"));
    //endregion
    //region Level Behaviors
    cattailMarsh.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    cattailMarsh.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (2,1) "River Leg"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,1});}};
    LevelBhvr.Level riverLeg = new LevelBhvr.Level(pos1,
        "River Leg",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/RiverSide/2,1.txt"));
    //endregion
    //region Level Behaviors
    riverLeg.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    riverLeg.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,0) "temp4"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,0});}};
    LevelBhvr.Level temp4 = new LevelBhvr.Level(pos1,
        "temp4",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/RiverSide/3,0.txt"));
    //endregion
    //region Level Behaviors
    temp4.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp4.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,1) "temp5"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,1});}};
    LevelBhvr.Level temp5 = new LevelBhvr.Level(pos1,
        "temp5",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/RiverSide/3,1.txt"));
    //endregion
    //region Level Behaviors
    temp5.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp5.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(cattailMarsh);
    createLevelFile(riverLeg);
    createLevelFile(temp4);
    createLevelFile(temp5);
  }
  public static void loadMountainSide() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (2,2) "Riverous Mountain Base"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,2});}};
    LevelBhvr.Level riverousMountainBase = new LevelBhvr.Level(pos1,
        "Riverous Mountain Base",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/MountainSide/2,2.txt"));
    //endregion
    //region Level Behaviors
    riverousMountainBase.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    riverousMountainBase.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (2,3) "insideMountain1"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{2,3});}};
    LevelBhvr.Level insideMountain1 = new LevelBhvr.Level(pos1,
        "insideMountain1",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/MountainSide/2,3.txt"));
    //endregion
    //region Level Behaviors
    insideMountain1.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    insideMountain1.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,2) "Gazed Mountain Sided"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,2});}};
    LevelBhvr.Level gazedMountainSided = new LevelBhvr.Level(pos1,
        "Gazed Mountain Sided",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/MountainSide/3,2.txt"));
    //endregion
    //region Level Behaviors
    gazedMountainSided.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    gazedMountainSided.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (3,3) "temp6"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{3,3});}};
    LevelBhvr.Level temp6 = new LevelBhvr.Level(pos1,
        "temp6",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/MountainSide/3,3.txt"));
    //endregion
    //region Level Behaviors
    temp6.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp6.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(riverousMountainBase);
    createLevelFile(insideMountain1);
    createLevelFile(gazedMountainSided);
    createLevelFile(temp6);
  }
  public static void loadChowderLake() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (-1,0) "Lake Cliff Side"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-1,0});}};
    LevelBhvr.Level lakeCliffSide = new LevelBhvr.Level(pos1,
        "Lake Cliff Side",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ChowderLake/-1,0.txt"));
    //endregion
    //region Level Behaviors
    lakeCliffSide.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    lakeCliffSide.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-1,1) "Center of Chowder Lake"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-1,1});}};
    LevelBhvr.Level centerOfChowderLake = new LevelBhvr.Level(pos1,
        "Center of Chowder Lake",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ChowderLake/-1,1.txt"));
    //endregion
    //region Level Behaviors
    centerOfChowderLake.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    centerOfChowderLake.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-2,0) "temp9"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-2,0});}};
    LevelBhvr.Level temp9 = new LevelBhvr.Level(pos1,
        "temp9",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ChowderLake/-2,0.txt"));
    //endregion
    //region Level Behaviors
    temp9.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp9.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-2,1) "Fisherman's Dock"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-2,1});}};
    LevelBhvr.Level fishermansDock = new LevelBhvr.Level(pos1,
        "Fisherman's Dock",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ChowderLake/-2,1.txt"));
    //endregion
    //region Level Behaviors
    fishermansDock.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    fishermansDock.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(lakeCliffSide);
    createLevelFile(centerOfChowderLake);
    createLevelFile(temp9);
    createLevelFile(fishermansDock);
  }
  public static void loadScatteredSides() throws InterruptedException, IOException, ClassNotFoundException{
    ArrayList<byte[]> pos1, pos2, pos3, pos4 = new ArrayList<>(){};
    byte[] levChngParent1,levChngParent2,levChngParent3,levChngParent4 = new byte[]{}; // pos to enter child level
    byte[] childSpawn1,childSpawn2,childSpawn3,childSpawn4 = new byte[]{}; // where the plyr ends up after enter child

    byte[] levChngChild1,levChngChild2,levChngChild3,levChngChild4 = new byte[]{}; // Child Level exit to parent
    byte[] parentSpawn1,parentSpawn2,parentSpawn3,parentSpawn4 = new byte[]{}; // where plyr ends up exit child
    //region (-1,2) "Eroded Rocks"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-1,2});}};
    LevelBhvr.Level erodedRocks = new LevelBhvr.Level(pos1,
        "Eroded Rocks",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ScatteredSides/-1,2.txt"));
    //endregion
    //region Level Behaviors
    erodedRocks.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    erodedRocks.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-1,3) "temp10"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-1,3});}};
    LevelBhvr.Level temp10 = new LevelBhvr.Level(pos1,
        "temp10",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ScatteredSides/-1,3.txt"));
    //endregion
    //region Level Behaviors
    temp10.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp10.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-2,2) "Jagged River Rocks"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-2,2});}};
    LevelBhvr.Level jaggedRiverRocks = new LevelBhvr.Level(pos1,
        "Jagged River Rocks",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ScatteredSides/-2,2.txt"));
    //endregion
    //region Level Behaviors
    jaggedRiverRocks.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    jaggedRiverRocks.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion
    //region (-2,3) "temp11"
    //region Make Level Object
    pos1=new ArrayList<>(){{add(new byte[]{-2,3});}};
    LevelBhvr.Level temp11 = new LevelBhvr.Level(pos1,
        "temp11",
        fileTranslator(curFilePath+
            "/src/BackEnd/FileManagement/FreshAssets/LevelLayout/ScatteredSides/-2,3.txt"));
    //endregion
    //region Level Behaviors
    temp11.assignUniqueBhvr(""+
        tilesName.get("grass") + tilesName.get("dirtHorz") + tilesName.get("dirtVert"),true);
    temp11.assignAutoDrawing(new ArrayList<String>(){{
      add("PLAYER GRASS RUFFLE"); add("BUSH GRASS TUF"); add("DIRT GRASS BLEND");add("DIRT TREE BLEND");
    }});
    //endregion
    //region Add GamePlay Objects

    //endregion
    //endregion

    createLevelFile(erodedRocks);
    createLevelFile(temp10);
    createLevelFile(jaggedRiverRocks);
    createLevelFile(temp11);
  }
  public static void loadAndValidateLevels() throws InterruptedException, IOException, ClassNotFoundException{
    //NOTE: (Y,X)

    loadLivelyGreens();
    loadFarmenTump();
    loadWetLands();
    loadRiverSide();
    loadMountainSide();
    loadKnotCross();
    loadChowderLake();
    loadScatteredSides();
  }
  //TODO ANYTIME: istead of create new file when missing warn user of missing file and ask if want to continue
  public static void createLevelFile(LevelBhvr.Level level) throws InterruptedException, IOException, ClassNotFoundException {
    File tempFile = new File(level.selfPath);
    if(Runner.deleteFilesOnStart){
      tempFile.delete();
    }
    if(!tempFile.exists()) {
      FileWriter writer = new FileWriter(level.selfPath);
      writer.close();
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(tempFile));
      output.writeObject(level);
      output.close();
    }

  }


  public static void levelSelect(ArrayList<byte[]> levelPos) throws IOException, ClassNotFoundException {
    ObjectInputStream input = new ObjectInputStream(new FileInputStream(
        new File(LoadLevels.getLevelPath(levelPos, 0, 0))));
    LevelBhvr.curLev = (LevelBhvr.Level) input.readObject();
    input.close();
  }
  public static void levelSelect(String filePath) throws IOException, ClassNotFoundException {
    ObjectInputStream input = new ObjectInputStream(new FileInputStream( filePath ));
    LevelBhvr.curLev = (LevelBhvr.Level)input.readObject();
    input.close();
  }

  public static char[][] fileTranslator(String filePath) throws FileNotFoundException {
    char[][] arr = new char[LevelBhvr.yB][LevelBhvr.xB];
    File level = new File(filePath);
    Scanner sc = new Scanner(level);
    byte row=0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      byte offset=0;
      for(byte i=0; i<line.length(); i++){
        if(line.charAt(i)!='\t'){
          arr[row][i-offset]=line.charAt(i);
        } else {offset++;}
      }

     row++;
    }
    return arr;
  }

  //region Finding File Path Methods
  public static String getLevelPath(ArrayList<byte[]> pos,int yMod, int xMod){
    return fileFinder.get("Levels")+ getPackagePath(pos, (byte) yMod, (byte) xMod)+"/"+CTS(pos,yMod,xMod)+".txt";
  }
  public static String getPackagePath(ArrayList<byte[]> pos,byte yMod, byte xMod){
    switch((pos.get(0)[0]+yMod)+","+(pos.get(0)[1]+xMod)){
      case "-1,0" ,"-1,1" ,"-2,0" ,"-2,1"->{return "/ChowderLake";}
      case "-1,2" ,"-1,3" ,"-2,2" ,"-2,3"->{return "/ScatteredSides";}

      case "0,-1","0,-2","1,-1","1,-2"->{return "/FarmenTump";}
      case "0,0" ,"0,1" ,"1,0" ,"1,1" ->{return "/LivelyLeafs";}
      case "0,2" ,"0,3" ,"1,2" ,"1,3" ->{return "/KnotCross";}

      case "2,-1","2,-2","3,-1" ,"3,-2"->{return "/WetLands";}
      case "2,0" ,"2,1" ,"3,0"  ,"3,1" ->{return "/RiverSide";}
      case "2,2" ,"2,3" ,"3,2"  ,"3,3" ->{return "/MountainSide";}

      default -> {return "N/A";}
    }
  }
  public static String CTS(ArrayList<byte[]> arr, int yMod, int xMod){//cords to string
    String temp=arr.get(0)[0]+","+arr.get(0)[1];
    if(arr.size()>1){
      for(byte i=1; i<arr.size(); i++){
        if(i%2==1){//if in the first part of sublevel notiotion y,x_y,x
          temp+="-"+arr.get(i)[0]+","+arr.get(i)[1];
        }
        else{
          if( (i+1)==arr.size()){
            temp+="_"+(arr.get(i)[0]+yMod)+","+(arr.get(i)[1]+xMod);
          } else{temp+="_"+arr.get(i)[0]+","+arr.get(i)[1];}
        }
      }
    }
    else{return (arr.get(0)[0]+yMod)+","+(arr.get(0)[1]+xMod);}

    return temp;
  }
  //endregion
}
