import java.util.ArrayList;
import java.util.Arrays;
//region DEFINITIONS AND TERMS
/*
LCVAR: Level Change Variable
   LS: Level Switch
*/
//endregion
public class Levels {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1) LCVAR is updated Every time Movement is ran. so if not ran can alwyas KEEP LCVAR MIGHT CREATE BUGS
*/
/*--------------------------------------Declare/Call/Iniltize Varaibles-----------------------------------------------*/
    //region Declaring Vars
    static String W="|" , b="-" , B="_";                        /*Borders or intangable*/
    static String g="," , G="." , E=" " , h="H";                /*walkable surface*/    static String walkableSurface = g+G+E+h;
    static String w="~";                                        /*walkable surface W/item*/
    static String p="@" , e="=";                                /*special types*/
    static String D="&" , M="%";                                /*NPC*/
    static String X="*";                                        /*treasure icons*/
    static String LEVELNAME="";
    static String LCVAR="";
    static String P="+";
    static String mainAreaLCVars="="+"$";
    static String homeLCVars="=";
    //endregion
    //region Arrays
    ArrayList<String> mainAreaTemp = new ArrayList<>();
    ArrayList<String> mainAreaPerm = new ArrayList<>();
    ArrayList<String> homePerm = new ArrayList<>();
    ArrayList<String> homeTemp = new ArrayList<>();
    ArrayList<String> sMArray = new ArrayList<>();
    ArrayList<String> currentLevelTemp = new ArrayList<>();
    ArrayList<String> currentLevel = new ArrayList<>();
    //endregion
    //region Assigning Classes to Vars
    Movement movement = new Movement();
    DeBugger debug = new DeBugger();
    //endregion
    StringBuilder LevelsDebugger = new StringBuilder(); //DEBUGGER
/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------Create Levels + GET & CHANG: methods
    public Levels(){
        //region MAIN AREA
        String [] tBord = new String[]{W,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,W};
        String [] row01 = new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row02 = new String[]{W,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row03 = new String[]{W,G,G,G,G,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row04 = new String[]{W,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row05 = new String[]{W,g,g,g,g,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,W};
        String [] row06 = new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,W};
        String [] row07 = new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row08 = new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row09 = new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row10= new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row11= new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row12= new String[]{W,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row13= new String[]{W,G,G,G,G,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row14= new String[]{W,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,W};
        String [] row15= new String[]{W,g,g,g,g,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,W};
        String [] row16= new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,G,w,W};
        String [] row17= new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,w,w,W};
        String [] row18= new String[]{W,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,w,w,w,W};
        String [] row19= new String[]{W,g,g,g,p,g,g,g,g,g,g,g,g,g,g,g,g,g,X,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,w,w,w,w,W};
        String [] row20= new String[]{W,g,"|",e,"|",D,M,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,g,w,w,w,w,w,W};
        String [] bBord = new String[]{W,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,W};
        mainAreaTemp.addAll(Arrays.asList(tBord));mainAreaTemp.addAll(Arrays.asList(row01));mainAreaTemp.addAll(Arrays.asList(row02));
        mainAreaTemp.addAll(Arrays.asList(row03));mainAreaTemp.addAll(Arrays.asList(row04));mainAreaTemp.addAll(Arrays.asList(row05));
        mainAreaTemp.addAll(Arrays.asList(row06));mainAreaTemp.addAll(Arrays.asList(row07));mainAreaTemp.addAll(Arrays.asList(row08));
        mainAreaTemp.addAll(Arrays.asList(row09));mainAreaTemp.addAll(Arrays.asList(row10));mainAreaTemp.addAll(Arrays.asList(row11));
        mainAreaTemp.addAll(Arrays.asList(row12));mainAreaTemp.addAll(Arrays.asList(row13));mainAreaTemp.addAll(Arrays.asList(row14));
        mainAreaTemp.addAll(Arrays.asList(row15));mainAreaTemp.addAll(Arrays.asList(row16));mainAreaTemp.addAll(Arrays.asList(row17));
        mainAreaTemp.addAll(Arrays.asList(row18));mainAreaTemp.addAll(Arrays.asList(row19));mainAreaTemp.addAll(Arrays.asList(row20));
        mainAreaTemp.addAll(Arrays.asList(bBord));

        mainAreaPerm.addAll(mainAreaTemp);
        mainAreaPerm.set(mainAreaTemp.indexOf("@"),","); //takes player out of the level and replaces with grass
        //endregion
        //region HOME
        //ADD: 2 bedroom, kitchen, living room, a table that is a hexagon for 3 people
        String [] HOMEtBord = new String[]{W,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,W};
        String [] HOMErow01 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow02 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow03 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow04 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow05 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow06 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow07 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow08 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow09 = new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,b,b,b,b,b,b,b,b,b,W,b,b,b,b,b,b,W,E,E,E,W};
        String [] HOMErow10= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,W,"(","|", E , E ,":","]",W,E,E,E,W};
        String [] HOMErow11= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,W, E , E , E , E , E , E, W,E,E,E,W};
        String [] HOMErow12= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,W,b,b,b,b,b,E,b,W,b,b,b,b,b,b,b,W};
        String [] HOMErow13= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,E, E , E , E , E , E , E ,"}",W};
        String [] HOMErow14= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,W, E , E , E , E , E , E , E ,W};
        String [] HOMErow15= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,W,"8","#","#", E , B , B , E ,W};
        String [] HOMErow16= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,W,"8","#","#", W ,"+","+", W ,W};
        String [] HOMErow17= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,W,b,b,b,b,b,b,b,W};
        String [] HOMErow18= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W};
        String [] HOMErow19= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W};
        String [] HOMErow20= new String[]{W,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,E,W,E,B,B,B,B,E,E,E,E,E,p,E,W,E,E,E,E,E,E,E,W};
        String [] HOMEbBord = new String[]{W,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,e,b,b,b,b,b,b,b,b,b,W};
        homeTemp.addAll(Arrays.asList(HOMEtBord));homeTemp.addAll(Arrays.asList(HOMErow01));homeTemp.addAll(Arrays.asList(HOMErow02));
        homeTemp.addAll(Arrays.asList(HOMErow03));homeTemp.addAll(Arrays.asList(HOMErow04));homeTemp.addAll(Arrays.asList(HOMErow05));
        homeTemp.addAll(Arrays.asList(HOMErow06));homeTemp.addAll(Arrays.asList(HOMErow07));homeTemp.addAll(Arrays.asList(HOMErow08));
        homeTemp.addAll(Arrays.asList(HOMErow09));homeTemp.addAll(Arrays.asList(HOMErow10));homeTemp.addAll(Arrays.asList(HOMErow11));
        homeTemp.addAll(Arrays.asList(HOMErow12));homeTemp.addAll(Arrays.asList(HOMErow13));homeTemp.addAll(Arrays.asList(HOMErow14));
        homeTemp.addAll(Arrays.asList(HOMErow15));homeTemp.addAll(Arrays.asList(HOMErow16));homeTemp.addAll(Arrays.asList(HOMErow17));
        homeTemp.addAll(Arrays.asList(HOMErow18));homeTemp.addAll(Arrays.asList(HOMErow19));homeTemp.addAll(Arrays.asList(HOMErow20));
        homeTemp.addAll(Arrays.asList(HOMEbBord));

        homePerm.addAll(homeTemp);
        homePerm.set(homeTemp.indexOf("@")," ");
        //endregion

        // region SIDE MENU
            String [] tSMenu = new String[]   {b,b,b,b,b,b,b,b,b,b,W};
            String [] sMenuRow1 = new String[]{E,"1",")","S","a","v","e",E,E,E,W};
            String [] sMenuRow2 = new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] sMenurow03 = new String[]{E,"2",")","M","e","n","u",E,E,E,W};
            String [] sMenurow04 = new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] sMenurow05 = new String[]{E,"3",")","O","p","t","i","o","n",E,W};
            String [] sMenurow06 = new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] sMenurow07 = new String[]{E,"4",")","Q","u","i","t",E,E,E,W};
            String [] sMenurow08 = new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] sMenurow09 = new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] sMenuRow10= new String[]{E,E,E,E,E,E,E,E,E,E,W};
            String [] bSMenu = new String[]   {b,b,b,b,b,b,b,b,b,b,W};
            sMArray.addAll(Arrays.asList(tSMenu));   sMArray.addAll(Arrays.asList(sMenuRow1));sMArray.addAll(Arrays.asList(sMenuRow2));
            sMArray.addAll(Arrays.asList(sMenurow03));sMArray.addAll(Arrays.asList(sMenurow04));sMArray.addAll(Arrays.asList(sMenurow05));
            sMArray.addAll(Arrays.asList(sMenurow06));sMArray.addAll(Arrays.asList(sMenurow07));sMArray.addAll(Arrays.asList(sMenurow08));
            sMArray.addAll(Arrays.asList(sMenurow09));sMArray.addAll(Arrays.asList(sMenuRow10));sMArray.addAll(Arrays.asList(bSMenu));

        //endregion
    }
    public String getLEVELNAME(){return LEVELNAME;}
    //region Current Level: GETTER & CHANGE METHOD
    public void changeCurrentLevelTemp(ArrayList<String> areaTemp){currentLevelTemp=areaTemp;}
    public void changeCharInCurrentLevelTemp(int pos,String temp){currentLevelTemp.set(pos,temp);}
    public ArrayList<String> getCurrentLevelTemp(){ return currentLevelTemp; }

    public void changeCurrentLevel(ArrayList<String> areaPerm){ currentLevel=areaPerm; }
    public ArrayList<String> getCurrentLevel(){
        return currentLevel;
    }
    //endregion
    //region SideMenu     : GETTER METHOD
    public ArrayList<String> getSM(){return sMArray;}
    //endregion
    //regionMAIN AREA    : GETTER & CHANGE METHOD & LEVEL SWITCH
    public ArrayList<String> getMainAreaPerm(){return mainAreaPerm;}
    public void mainAreaPermChange(ArrayList<String> temp){mainAreaPerm=temp;}

    public ArrayList<String> getMainAreaTemp(){return mainAreaTemp;}
    public void mainAreaTempChange(ArrayList<String> temp){mainAreaTemp=temp;}


    //endregion
    //region HOME         : GETTER & CHANGE METHOD & LEVEL SWITCH
    public ArrayList<String> getHomeTemp()            {return homeTemp;}
    public ArrayList<String> getHomePerm()            {return homePerm;}
    public void homePermChange(ArrayList<String> temp){homePerm=temp;}
    public void homeTempChange(ArrayList<String> temp){homeTemp=temp;}
    //endregion
    //endregion
    //region B)--------------------LEVEL SWITCHING      w/Summary
                /*Summary: When |LEVELCHANGE| called upon will save current level check to see what level it needs to
                go to then takes they level it needs and replaces the current level variable accordingly.
     1) Will: When called will use current level to determin what method to run and does that
     2) Will: takes level name Temp and Perm, clear it, then saves |currentLevel| Temp & Perm to it
     3) Will: see what level the player entered and change the level Name then clear |currentLevel| and assign
              |currentLevel| to the level it is entering obtained from |collisionLCVar|
     4) Will: Depending on the symbol it interacts with, switch level accordingly &
        Has : a collision method where it detect to see the symbol interacted with need to trigger a level change

     */
    //region 1)---Level Change---
    public void LEVELCHANGE(){
        LevelsDebugger.delete(0,LevelsDebugger.length());
        switch(LEVELNAME){
            case"MAIN AREA"->{mainAreaLS();}
            case"HOME"     ->{homeLS();}
        }LCVAR="";
        if(debug.levelClass()){System.out.println(LevelsDebugger);}
    }
    //endregion
    //region 2)---Save Level---
    public void saveLevel(String level){ LEVELNAME=level;
        LevelsDebugger.append("Level Saved:("+LEVELNAME+") / ");
        switch(LEVELNAME){
            case "MAIN AREA"->{
                mainAreaPerm.clear();                mainAreaTemp.clear();
                mainAreaPerm.addAll(currentLevel);   mainAreaTemp.addAll(currentLevelTemp);
            }
            case "HOME"->     {
                homePerm.clear();                homeTemp.clear();
                homePerm.addAll(currentLevel);   homeTemp.addAll(currentLevelTemp);
            }
        }
    }
    //endregion
    //region 3)---change Level---
    public void changeLevel(String levelPlayerEnter){
        LEVELNAME=levelPlayerEnter;
        LevelsDebugger.append("Level Enter:("+LEVELNAME+") / ");
        switch(LEVELNAME){

            case "MAIN AREA"->{
                currentLevel.clear();                currentLevelTemp.clear();             //clear currentLevel arrays
                currentLevel.addAll(mainAreaPerm);   currentLevelTemp.addAll(mainAreaTemp);//addAll array of that level
            }
            case "HOME"->{
                currentLevel.clear();                currentLevelTemp.clear();
                currentLevel.addAll(homePerm);       currentLevelTemp.addAll(homeTemp);

            }
        }
    }
    //endregion
    //region 4)---Level Switches---
    public void  mainAreaLS(){
        LevelsDebugger.append("Levels -> mainAreaLS -> saveLevel, changeLevel -> Level Before Switch:("+LEVELNAME+") / LCVAR:("+LCVAR+") / ");
        switch(LCVAR){
            case"="-> {saveLevel(LEVELNAME); changeLevel("HOME");}
            case"$"-> {}
        }
    }
    public void  homeLS(){
        LevelsDebugger.append("Levels -> homeLS -> saveLevel, changeLevel -> Level Before Switch:("+LEVELNAME+") / LCVAR:("+LCVAR+") / ");
        switch(LCVAR){
            case"="-> {saveLevel(LEVELNAME); changeLevel("MAIN AREA");}
        }
    }

    public void collisionLCVar(String levelChangeVar){
        switch(LEVELNAME){
            case "MAIN AREA"->{if(mainAreaLCVars.contains(levelChangeVar)){LCVAR=levelChangeVar;}}
            case "HOME"     ->{if(homeLCVars.contains(levelChangeVar))    {LCVAR=levelChangeVar;}}
        }
    }
    //endregion
    //endregion

}
