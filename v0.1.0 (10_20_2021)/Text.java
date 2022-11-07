import java.util.ArrayList;

public class Text {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1)contains all the text needed for each interaction or every text that will appear in the text box
2)create a sliding text when text is being displayed
*/
/*--------------------------------------Declare/Call/Initialize/Variables---------------------------------------------*/
    //region Class vars
    Movement movement = new Movement();
    PrintGame print = new PrintGame();
    Levels getLevels = new Levels();
    Treasure treasurerText = new Treasure();
    Bag bag = new Bag();
    DeBugger debug = new DeBugger();
    JframeMethod windowStuff = new JframeMethod();
    //endregion
    //region D&I Vars
    static int intractableVarPos=0;
    String text = "";
    static int symbolInteraction=0;
    static String intractableVar ="";
    static ArrayList<String> tempArray = new ArrayList<>();
    static ArrayList<String> voidTempArray = new ArrayList<>();
    static int dadIC=1;
    static String PD="";
    static String space="                                                                                                                  ";//38 *3 spaces
    //endregion
    StringBuilder textDeBugger = new StringBuilder();

/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------!Calculate Player Direction            w/Summary
                /*Summary: This code will take in the player direction & an array (both received from movement class)
                and see what variable it is looking at so if player where to ever press enter ti knows what it is
                interacting with
     1) Will: Calculate what varaible player is looking at (after movement is executed)
     2) Has : Debugger
     3) Has & Will: take info class/methods and create getter methods
     */
    public void interactableCheck(String playerDirection, ArrayList<String> arrayTemp){ //gets what direction player facing
        //region 1)---Calculate var looking at---
        tempArray=arrayTemp;
        int playerPos=arrayTemp.indexOf("@");
        switch (playerDirection) {
            case "LEFT" ->{intractableVarPos=(playerPos-1); }
            case "RIGHT"->{intractableVarPos=(playerPos+1); }
            case "UP"   ->{intractableVarPos=(playerPos-42);}
            case "DOWN" ->{intractableVarPos=(playerPos+42);}
        }
        //endregion
        //region 2)---Debugger---
        intractableVar =arrayTemp.get(intractableVarPos);
        textDeBugger.delete(0,textDeBugger.length());
        textDeBugger.append("Text -> interactableCheck -> ");
        textDeBugger.append("Player Pos:("+playerPos+") / playerDirection:("+playerDirection+")");
        textDeBugger.append(" / intractableVarPos:("+intractableVarPos+") / intractableVar:("+ intractableVar +")");
        if(DeBugger.DBTextClass){System.out.println(textDeBugger);}
        //endregion
        //region 3.1)---Define vars for GET methods
        PD=playerDirection;
        voidTempArray=arrayTemp;
        //endregion
    }
        //region 3.2)---GET: terms in interaction check
    public String getPlayerDirection(){return PD;}
    public ArrayList<String> getTempArray(){return tempArray;}
    public ArrayList<String> getVoidTempArray(){return voidTempArray;}
    public int symbolPosition(){return intractableVarPos;}
    public String getIntractableVar(){return intractableVar;}
    //endregion

    //endregion
    //region B)--------------------Library of Text Interactions W/GET methods
    public void getTextInteractions() {

        switch(getLevels.getLEVELNAME()){
            case "MAIN AREA"->{getMainAreaText();}
        }
    }
    //Note: IC standands for Interaction Chain  && when IC=0 is its default phrase
    public void getMainAreaText(){
        symbolInteraction=symbolPosition();
        switch(symbolInteraction){
            //DAD TEXT
            case 845->{
                switch(dadIC) {
                    case 1 -> {text="Dad: Hey you've always wanted to go treasure hunting. I bought you a shovel take, good care of it.    ---->";
                        bag.changePlayerHas("SHOVEL",true); dadIC=2; }
                    case 2 ->{
                        if(treasurerText.playerHas("GOLD COIN")){text="Nice coin, your on your way to becoming rich. Keep at it.";dadIC=3;}
                        else{text="Dad: Hey I think I saw thing shiny in the dirt over there *He points*";}
                    }
                    case 3->{text="Keep at it son.";}
                }
            }
            case 846->{ text="Mom: Hello son";}
            case 816->{
                treasurerText.treasureChecker();
                text=treasurerText.getCustomText();
            }
            default -> {
                text="                                         ";
            }
        }
    } //All Main Area text interactions
    //endregion
    //region C)--------------------!BIG:Creation of text                  w/Summary
                /*Summary: This will take |text| and will print with a delay the rows it needs to display
     1) Will: see what rows need wait until be displayed (NOTE THIS IS UNNECESSARY FIND FIX)
     2) Will: reset text box and display and generate scrolling text
     3) Has : Debugger
     */
    public void createText(){ //PRODUCE LIVE TEXT WHEN IN MAIN
        //region 1)---Checker for need to wait---
        boolean T1,T2,T3;
        if(text.trim().length()>37*2)   {T1=true ;T2=true ;T3=true ;}
        else if(text.trim().length()>37){T1=true ;T2=true ;T3=false;}
        else if(text.trim().length()>0) {T1=true ;T2=false;T3=false;}
        else                            {T1=false;T2=false;T3=false;}
        //endregion
        //region 2)---Text Creation & Reset er---
        text = text+"                                                                                                                          ";
        print.changeTBox1("|                                        |" + "          |");
        print.changeTBox2("|                                        |" + "          |");
        print.changeTBox3("|                                        |" + "          |");


        for (int i = 0; i < 38; i++)  {print.changeTBox1("| " + text.substring(0, i)+space.substring(0,37-i)    + "  |" + "          |");
            //make .05 wait time
            if(T1){try { Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}}
        }
        for (int i = 0; i < 38; i++)  {print.changeTBox2("| " + text.substring(37, i+38)+space.substring(0,37-i)    + " |" + "          |");
            if(T2){try { Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}}
        }
        for (int i = 0; i < 38; i++)  { print.changeTBox3("| " + text.substring(38*2, i+38*2)+space.substring(0,37-i)+ "  |" + "          |");
            if(T3){try { Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}}
        }
        print.changeTBoxB("|________________________________________|" + "__________|");
        //endregion
        //region 3)---Debugger---
        textDeBugger.delete(0,textDeBugger.length());
        textDeBugger.append("Text -> createText");
        textDeBugger.append(" T1: ("+T1+") / T2:("+T2+") / T3:("+T3+") / ");
        textDeBugger.append("text:("+text.trim()+") / ");
        if(DeBugger.DBTextClass){System.out.println(textDeBugger);}
        //endregion
    }
    //endregion
}

