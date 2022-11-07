public class HudAndUI {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1) This will create a HUD of 2 rows (top and middle) and as methods create the middle with appropriate information to update counter
*/
/*--------------------------------------Declare/Call/Initialize/Variables---------------------------------------------*/
    //region ---MiscStuff.Misc: assign Var To Classes---
    static JframeMethod FrameStuff = new JframeMethod();
    static PrintGame print = new PrintGame();
    static Levels getLevels = new Levels();
    static Text text = new Text();
    //endregion
    //region ---D&I vars---
    static String hud;
    static String space="                                              ";
    //endregion
/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------!BIG Create Hud
    //region 1)---Create Top Row---
    public String gethudTP(){ return "|----------------------------------------|";} //Top Row
    //endregion
    //region 2)---Create the Main Hud Row---
    public String getHud(){
        updateTreasureTracker();
        String treasureCounter= Integer.toString(treasureGotInLevel)+"/"+Integer.toString(treasureTotalInLevel);
        int lengthOfCounter=treasureCounter.length();
        int lengthOfLevelName=getLevels.getLEVELNAME().length();
        int totalLength = lengthOfLevelName+lengthOfCounter;
            hud=("| " +getLevels.getLEVELNAME()+space.substring(totalLength, 37)+treasureCounter+"  |");
            return hud;
    }
//endregion
    //endregion
    //region B)--------------------Update Counters
    static int treasureGotInLevel,treasureTotalInLevel;
    static int MA,HM;    //Note: MA=mainArea & HM=home
    public void updateTreasureTracker(){
        switch(getLevels.getLEVELNAME()){
            case"MAIN AREA"->{treasureGotInLevel =MA;treasureTotalInLevel = 8;}
            case"HOME"->     {treasureGotInLevel =HM;treasureTotalInLevel = 8;}
        }
    }
    public void changeMainAreaTreasureCounter(){MA++;}
    public void changeHomeTreasureCounter(int number){HM++;}
    //endregion
    //Most definatly will need to make getter methods
    //MAKE A METHOD TO UPDATE TREASURE COUNTER
}
/*
How to do this:
call LEVELNAME and get length +1
Call amount of treasure have 00/10
make a public that updates the treasure

LATER MAKE IT SO CHANGE COLOR DEPEND IF COLLECTED ALL TREASURE OR NOT
 */