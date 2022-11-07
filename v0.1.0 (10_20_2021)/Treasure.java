import java.util.ArrayList;

public class Treasure {
/*--------------------------------------Declare/Call/Iniltize Varaibles-----------------------------------------------*/
    //region ---MiscStuff.Misc: Assign Vars To Classes---
    static Text text = new Text();
    static Levels getLevels = new Levels();
    static Bag bag = new Bag();
    static HudAndUI hud=new HudAndUI();
    static PrintGame print = new PrintGame();
    //endregion
    //region ---D&I Vars & Arrays
    static ArrayList<String> tempRay = new ArrayList<>();
    static String customText="";
    static int symbolInteraction;
    //endregion
/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------!BIG:Treasure Text Interactions   w/Summary
/*Summary: When called, ussally when intracts with a treasure and text is needed, checks to see what level the player is on
  Then calls designated interaction and outputs text given to Text class
1) Checks to see what level player on and calls a interaction method
2) Contains all the interactions organized by level (might put in differnt class as would get long)

*/
    //region 1)---Treasure Checker---
    public void treasureChecker(){ //gets interactions w/treasure
        switch(getLevels.getLEVELNAME()) {
            case "MAIN AREA" -> { mainAreaInteractions(); }
            case "HOME"->       { homeInteractions();}
            default -> {customText="                         ";}
        }
    }
    public String getCustomText(){return customText;}
    //endregion
    //region 2)---Level Interactions---
    //region Main Area
    public void mainAreaInteractions(){
        symbolInteraction=text.symbolPosition();

        if(symbolInteraction==816 && text.getIntractableVar().equals("*")) {
            if (bag.playerHas("SHOVEL")) {
                customText="You lift the mound of dirt out of the way to reveal a shiny coin";
                changePlayerHas("GOLD COIN",true);
                tempRay=text.getTempArray(); tempRay.set(816,"X"); getLevels.changeCurrentLevelTemp(tempRay); // REPLACES TREASURE SYMBOL W/"X"
                hud.changeMainAreaTreasureCounter();//adds 1 to main area treasure count

            } else if(text.getIntractableVar().equals("*")){customText="You see a shine underneath the dirt, but the dirt pile is to heavy for you. Perhaps a shovel would help";

            } else{customText="           ";}

        }else{customText="           ";}
    }
    //endregion
    //endregion
    //endregion
    //region B)--------------------Check Player Treasure             w/Summary
/*Summary: Methods that either check to see if player has a certain treasure or changes if player has it.
1) D&I all variables the player could have
2) Check to see if Player has a certain treasure and returns a boolean
3) Change if a player has a certain item
*/
    //region 1)---D&I Treasure---
    static boolean goldCoin=false;
    //endregion
    // region 2)---GET: see if player has a treasure---
    public boolean playerHas(String treasure){
        switch(treasure){
            case"GOLD COIN"->{return goldCoin;}
        }
        return false;
    }
    //endregion
    //region 3)---CHANG: if player has a treasure---
    public boolean changePlayerHas(String treasure, boolean temp){
        switch(treasure){
            case"GOLD COIN"->{goldCoin=temp;}
        }
        return false;
    }
    //endregion
    //endregion
    public void homeInteractions(){ symbolInteraction=text.symbolPosition(); } //receives what symbol was interacted with
}