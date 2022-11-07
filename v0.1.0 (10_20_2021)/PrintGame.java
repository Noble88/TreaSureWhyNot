import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.lang.Math;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
        //Basic Navigation Below
/*NOTE: basic naviagation for code is:
| misc = miscellaneous                | I&D = initialise and Declare       | Method: = public void                     |
| ! = Import at overall code          |------------------=big chunk of code| --INFO-- : = basic lines of code          |
| GET: getting something              | |     | = This is a specific name  | CHANG: = a method that changes a variable |

Summary: summary of the code it contains (Note: Summaries first say what it do then the Will/Has/Upon/etc doing)
*/
public class PrintGame {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1) How this class switches gameState and level Display:
    This class a Dominate Array that contains and final version of what needs to be be displayed so
    When switching game state (handled object.method gameStateSwitcher) it will assign the arrays from different
    class to single one. keeping the state of other classes arrays when switch states.
2) KeyListener and Main Move independently of each other So I've used a |synchronize| for the sole reason of having
    scrolling text (might do more w/it)
3) hasPrinted make sure that nothing is change until not print anything until either Level or Bag Movement is detected
NOTE) Read the Summary for clarification of code
*/
/*-------------------------------------Declare/Call/Initialize/Variables----------------------------------------------*/
    //region ---MiscStuff.Misc: assign Var To Classes---
    static JframeMethod windowInfo = new JframeMethod();
    static final PrintGame printGame = new PrintGame();
    public PrintGame getPrintGameClassObject(){return printGame;} //used for threads
    static Levels getLevels = new Levels();
    static Movement movement = new Movement();
    static Text text = new Text();
    static DeBugger debug = new DeBugger();
    static HudAndUI hud = new HudAndUI();
    static Bag bag = new Bag();
    static Treasure treasure = new Treasure();
    static final GameRun runner = new GameRun(); //used for threads
    //endregion
    //region ---D&I: Vars---
    static boolean hasPrinted;
    static String gameState = "";
    static ArrayList<String> gameArray = new ArrayList<>();
    static int numOfColomsMA = 42, numOfColomsSM = 11;
    static String keyPressed="";
    static String validKeys="LEFT"+"RIGHT"+"UP"+"DOWN"+"ENTER"+"B"+"N/A";
    static String validMoveKeys="LEFT"+"RIGHT"+"UP"+"DOWN";
    static boolean doneWStartup=false;
    StringBuilder printGameDebugger = new StringBuilder(); //part of the debugger
    //endregion

/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------!BIG: Starts the game
    public void startOfGame(){
        windowInfo.createJframe();  //add JFrame and main Panel for rows
        windowInfo.getJframe().add(windowInfo.getLayeredPane());
        windowInfo.testing();
        windowInfo.addRows();
        windowInfo.executeWindowProperties();
                                    //add rows for building level (1-20)

        System.out.println("START GAME");                         //debugger for start of game
        getLevels.changeLevel("MAIN AREA");          //Sets the level to main area
        gameState="LEVEL";                                        //makes class know that it should be displaying Levels
        gameStateSwitcher();                                       //Makes it so the what is displayed is = to the proper arrays
        printLevel();                                              //the entirety of the game
        text.getTextInteractions();                                //Have this so it can display box in the bottom for text
        text.createText();
        //region GIVE ITEMS/TREASURE
        debug.giveTreasure(true);
        debug.giveItem(true);
        //endregion

    }
    public boolean getDoneWStartup() {return doneWStartup;}           //Method used tell Main that it is done with loading game
    public void changeDoneWStartup(boolean temp) {doneWStartup=temp; }
    //endregion

    //region B)--------------------!BIG: Key Listener    w/Summary
                /*Summary: The Key Listener gets assigned to JFrame at start of game in main method and will/has/upon->
     1) Has: printGameDebugger string builder reset value & starting text
     2) Will: when press key calls changeKeyPressed if it is a assigned key & if not becomes "ALL
     3) Upon: completion will call gameProcessor.
     */
    public void getKeyListener() {

        KeyListener listener = new KeyListener() {
            @Override public void keyTyped(KeyEvent e) { }
            @Override public void keyPressed(KeyEvent e){int keycode = e.getKeyCode();
                /* DEACTIVATED synchornized point IDK if need now
                                System.out.println("key before keylistner update:"+getKeyPressed());
                if(keyPressed.equals("ENTER")){
                    System.out.println("WHEN INTO ENTER IF &   WAIT");
                    synchronized(printGame) {try {printGame.wait();}
                    catch (InterruptedException error) {error.printStackTrace();}
                    }
                    System.out.println("DONE WAITING");
                }
                 */
                //region 1)---printGameDeBugger---
                printGameDebugger.delete(0,printGameDebugger.length());
                printGameDebugger.append("PrintGame -> GameStateBefore(").append(gameState).append(") / ");
                //printGameDebugger.append("KeyPressed BEFORE Listner:("+getKeyPressed()+") / "); //detects to see if key changed at any point in code
                //endregion
                //region 2)---Assigning Keys---
                switch(keycode){
                    case KeyEvent.VK_LEFT-> {changeKeyPressed("LEFT");}
                    case KeyEvent.VK_RIGHT->{changeKeyPressed("RIGHT");}
                    case KeyEvent.VK_UP->   {changeKeyPressed("UP");}
                    case KeyEvent.VK_DOWN-> {changeKeyPressed("DOWN");}
                    case KeyEvent.VK_ENTER->{changeKeyPressed("ENTER");}
                    case KeyEvent.VK_B->    {changeKeyPressed("B");}
                    default -> {changeKeyPressed("N/A");}
                }
                //endregion
                //region 3)---Ending Process---
                printGameDebugger.append("KeyPressed AFTER Listner:("+getKeyPressed()+") / ");
                gameProcessor();
                //endregion

            }@Override public void keyReleased(KeyEvent e) {}
        };windowInfo.getJframe().addKeyListener(listener); //adds keylistner to JFrame
    }
    //endregion
    //region C)--------------------!BIG gameProcessor    w/Summary
                    /*Summary: The gameProcessor Check the game state and runs code accordingly (The brains of the code) ->
     1) Will: PrintLevel for every game state
     2) Will: Move Player when a valid movement key is pressed & change level when needed
     3) Has: Debugger for each class and other debugging misc
     4) Upon completion will have successfully printed out
     */
    public void gameProcessor(){
        switch(gameState)
        {
            case "LEVEL"-> {
                if(getKeyPressed().equals("B"))
                {
                    getLevels.saveLevel(getLevels.getLEVELNAME()); gameState="BAG";
                    bag.changeBagPageState("IB1");bag.navigation("N/A");
                    gameStateSwitcher();
                }
                if(validMoveKeys.contains(getKeyPressed())) {
                    movement.receiveMovement(getKeyPressed(), getLevels.getCurrentLevelTemp(), getLevels.getCurrentLevel());
                    movement.movePlayer();
                    getLevels.LEVELCHANGE();
                }
                else if(getKeyPressed().equals("ENTER")){
                    synchronized(runner.getGameRunClass()) {runner.getGameRunClass().notifyAll();}
                }
                printLevel();
            }
            case "BAG"->{
                if(validMoveKeys.contains(getKeyPressed())){
                    bag.navigation(getKeyPressed());
                    printLevel();
                }
                else if(getKeyPressed().equals("B")){
                    getLevels.changeLevel(getLevels.getLEVELNAME());
                    gameState="LEVEL";
                    gameStateSwitcher();
                    printLevel();
                }
            }
            case "MAIN MENU"->{}
        }
        gameStateSwitcher(); //Makes it so the what is displayed is = to the proper arrays

        //DEBUGGER: USE THIS WHEN WANTING TO CHECK CERTAIN CLASS AND OTHER IMPORTANT INFO
        debug.misc(true, false, false, false,
                false, false);
        debug.classes(true, true, true, true, true, true);


        //NOTE: have gameStateSwitcher & printLevel inside ifs because so when press any key wont print level saving resources
        System.out.println();
    }
//endregion
    // region D)--------------------!BIG:printLevel       w/Summary
                        /*Summary: This Method is how game show up how colors are added
     1)Will: Declared Strings for each part the needs to be in 1 row
     2)Will: Call what is assigned to |gameArray|(modified in |gameStateSwitcher|) and will string together (For Loops)
     the declared Strings from before, reassigning them.
     3)Will: get JFrame rows and update them to the Strings in step 2
     4)Will: set each character inside each row to a color (color determind by |colorLEVELRows| method)
     5)Upon: Completion will print out debugger and change hasPrinted(used to not print if not nessessary) to true
     6)Has: a method that will taken in any character and depending on the level ill change colors
     7)Has: a method that will randomly change colors attached to |colorLEVELRows|
     8)Has: a Change method that causes the text box to change at will of other classes
     */
    public void printLevel(){
        //region 1) ---Declared Variables---
        String tBord = "", row01 = "", row02 = "", row03 = "", row04 = "", row05 = "";
        String row06 = "", row07 = "", row08 = "", row09 = "", row10 = "", BBor = "";
        String row11 = "", row12 = "", row13 = "", row14 = "", row15 = "";
        String row16 = "", row17 = "", row18 = "", row19 = "", row20 = "";
        String TSM = "", SMrow1 = "", SMRow2 = "", SMrow03 = "", SMrow04 = "", SMrow05 = "";
        String SMrow06 = "", SMrow07 = "", SMrow08 = "", SMrow09 = "", SMrow10 = "", BSM = "";
        //endregion
        //region 2) ---Array to String Loop---
        for (int i = 0; i < numOfColomsMA; i++) {
            tBord += gameArray.get(i);
            row01 += gameArray.get(i + numOfColomsMA);
            row02 += gameArray.get(i + numOfColomsMA * 2);
            row03 += gameArray.get(i + numOfColomsMA * 3);
            row04 += gameArray.get(i + numOfColomsMA * 4);
            row05 += gameArray.get(i + numOfColomsMA * 5);
            row06 += gameArray.get(i + numOfColomsMA * 6);
            row07 += gameArray.get(i + numOfColomsMA * 7);
            row08 += gameArray.get(i + numOfColomsMA * 8);
            row09 += gameArray.get(i + numOfColomsMA * 9);
            row10 += gameArray.get(i + numOfColomsMA * 10);
            row11 += gameArray.get(i + numOfColomsMA * 11);
            row12 += gameArray.get(i + numOfColomsMA * 12);
            row13 += gameArray.get(i + numOfColomsMA * 13);
            row14 += gameArray.get(i + numOfColomsMA * 14);
            row15 += gameArray.get(i + numOfColomsMA * 15);
            row16 += gameArray.get(i + numOfColomsMA * 16);
            row17 += gameArray.get(i + numOfColomsMA * 17);
            row18 += gameArray.get(i + numOfColomsMA * 18);
            row19 += gameArray.get(i + numOfColomsMA * 19);
            row20 += gameArray.get(i + numOfColomsMA * 20);
            BBor += gameArray.get(i + numOfColomsMA * 21);
        }
        for (int i=0; i <numOfColomsSM; i++)      {
            TSM    += getLevels.getSM().get(i);
            SMrow1 += getLevels.getSM().get(i + numOfColomsSM);
            SMRow2 += getLevels.getSM().get(i + numOfColomsSM * 2);
            SMrow03 += getLevels.getSM().get(i + numOfColomsSM * 3);
            SMrow04 += getLevels.getSM().get(i + numOfColomsSM * 4);
            SMrow05 += getLevels.getSM().get(i + numOfColomsSM * 5);
            SMrow06 += getLevels.getSM().get(i + numOfColomsSM * 6);
            SMrow07 += getLevels.getSM().get(i + numOfColomsSM * 7);
            SMrow08 += getLevels.getSM().get(i + numOfColomsSM * 8);
            SMrow09 += getLevels.getSM().get(i + numOfColomsSM * 9);
            SMrow10+= getLevels.getSM().get(i + numOfColomsSM * 10);
            BSM    += getLevels.getSM().get(i + numOfColomsSM * 11);
        }
        //endregion
        //region 3) ---GET: rows and reassign JFrame Rows---
        windowInfo.changeHudTP(hud.gethudTP());
        windowInfo.changeHud01(hud.getHud());
        windowInfo.changeTBord(tBord+TSM);
        windowInfo.changeRow01(row01+SMrow1);windowInfo.changeRow02(row02+SMRow2);
        windowInfo.changeRow03(row03+SMrow03);windowInfo.changeRow04(row04+SMrow04);
        windowInfo.changeRow05(row05+SMrow05);windowInfo.changeRow06(row06+SMrow06);
        windowInfo.changeRow07(row07+SMrow07);windowInfo.changeRow08(row08+SMrow08);
        windowInfo.changeRow09(row09+SMrow09);windowInfo.changeRow10(row10+SMrow10);
        windowInfo.changeRow11(row11);windowInfo.changeRow12(row12);
        windowInfo.changeRow13(row13);windowInfo.changeRow14(row14);
        windowInfo.changeRow15(row15);windowInfo.changeRow16(row16);
        windowInfo.changeRow17(row17);windowInfo.changeRow18(row18);
        windowInfo.changeRow19(row19);windowInfo.changeRow20(row20);
        windowInfo.changeBBord(BBor+BSM);
        //endregion
        //region 4) ---Color chars---
        switch(gameState) {
            case "LEVEL" -> {
                colorLevelRows(windowInfo.getCrow01(), row01);
                colorLevelRows(windowInfo.getCrow02(), row02);
                colorLevelRows(windowInfo.getCrow03(), row03);
                colorLevelRows(windowInfo.getCrow04(), row04);
                colorLevelRows(windowInfo.getCrow05(), row05);
                colorLevelRows(windowInfo.getCrow06(), row06);
                colorLevelRows(windowInfo.getCrow07(), row07);
                colorLevelRows(windowInfo.getCrow08(), row08);
                colorLevelRows(windowInfo.getCrow09(), row09);
                colorLevelRows(windowInfo.getCrow10(), row10);
                colorLevelRows(windowInfo.getCrow11(), row11);
                colorLevelRows(windowInfo.getCrow12(), row12);
                colorLevelRows(windowInfo.getCrow13(), row13);
                colorLevelRows(windowInfo.getCrow14(), row14);
                colorLevelRows(windowInfo.getCrow15(), row15);
                colorLevelRows(windowInfo.getCrow16(), row16);
                colorLevelRows(windowInfo.getCrow17(), row17);
                colorLevelRows(windowInfo.getCrow18(), row18);
                colorLevelRows(windowInfo.getCrow19(), row19);
                colorLevelRows(windowInfo.getCrow20(), row20);
            }
            case "BAG" -> {
                bag.bagColorer(
                        windowInfo.getCrow01(),windowInfo.getCrow02(),windowInfo.getCrow03(),windowInfo.getCrow04(),
                        windowInfo.getCrow05(),windowInfo.getCrow06(),windowInfo.getCrow07(),windowInfo.getCrow08(),
                        windowInfo.getCrow09(),windowInfo.getCrow10(),windowInfo.getCrow11(),windowInfo.getCrow12(),
                        windowInfo.getCrow13(),windowInfo.getCrow14(),windowInfo.getCrow15(),windowInfo.getCrow16(),
                        windowInfo.getCrow17(),windowInfo.getCrow18(),windowInfo.getCrow19(),windowInfo.getCrow20());
            }
        }


        //endregion
        //region 5) ---PrintGame Debugger---
        hasPrintedChange(true);
        printGameDebugger.append("Has Print:("+hasPrinted()+") / "+"GameStateAfter("+gameState+") / "); //add to PrintClass Debugger
        if(debug.printClass()){System.out.println(printGameDebugger);}//Prints debugger for class
        //endregion
    }
        //region 6) ---GET: Color acording to Char
        public void colorLevelRows(StyledDocument colorRow,String row){
            for (int i = 0; i < 42; i++) {
                colorRow.setCharacterAttributes(i, 1, colorLEVELRows(row.charAt(i)),true);}
        }
    public SimpleAttributeSet colorLEVELRows(char character){
        SimpleAttributeSet black     = new SimpleAttributeSet();StyleConstants.setForeground(black,    new Color(0, 0, 0));
        SimpleAttributeSet white     = new SimpleAttributeSet();StyleConstants.setForeground(white,    new Color(225, 225, 225));
        SimpleAttributeSet shinyGold = new SimpleAttributeSet();StyleConstants.setForeground(shinyGold,new Color(255, 215, 0));
        SimpleAttributeSet riverBlue = new SimpleAttributeSet();StyleConstants.setForeground(riverBlue,new Color(62, 68, 246));
        SimpleAttributeSet brown     = new SimpleAttributeSet();StyleConstants.setForeground(brown    ,new Color(150, 75, 1));
        SimpleAttributeSet green     = new SimpleAttributeSet();StyleConstants.setForeground(green,getRandomColors("GRASS"));
        switch(getLevels.getLEVELNAME())//depending on which level in, change char color
        {case"MAIN AREA"->{
            switch(character) {
                case ',' -> {return green;}
                case '.' -> {return brown;}
                case '*' -> {return shinyGold;}
                case '~' -> {return riverBlue;}
            }
        }case"HOUSE"    ->{}
        }
        return black;
    }
    //endregion
        //region 7) ---GET: RandomColor
    public Color getRandomColors(String toColor) { //this makes random colors that will be assigned to a StyleConstant.setForeground
        switch (toColor) {
            case "GRASS" -> { //Will get various colors of green
                switch(((int)(Math.random()*9)+1)){
                    case 1->{ return new Color(70, 230, 70);}// has 1/10 grass=this color
                    case 2->{ return new Color(32, 240, 32);}// has 1/10 grass=this color
                    default ->{return new Color(7, 165, 7); }// has 8/10 grass=this color
                }
            }
            case "Filler"->{}
        }
        return new Color(1,1,1);
    }
    //endregion
        //region 8)---CHANG: textBoxes
    public void changeTBox1(String arrayString){windowInfo.getTbox(1).setText(arrayString);}
    public void changeTBox2(String arrayString){windowInfo.getTbox(2).setText(arrayString);}
    public void changeTBox3(String arrayString){windowInfo.getTbox(3).setText(arrayString);}
    public void changeTBoxB(String arrayString){windowInfo.getTbox(4).setText(arrayString);}
    //endregion
    //endregion

/*------------------------------------------------Other---------------------------------------------------------------*/
    //region ---GET: & CHANG: keyPressed
    public String getKeyPressed() {return keyPressed; }
    public void changeKeyPressed(String temp){ keyPressed=temp; }
    //endregion
    //region ---!CHANG: gameArray depending on gameState
    public void gameStateSwitcher() {
        switch(gameState){
            case"LEVEL"->{gameArray=getLevels.getCurrentLevelTemp();}
            case"BAG"  ->{gameArray=bag.getCurrentBagPage();}
            case"TEXT" ->{}
        }
    }
    //endregion
    //region ---GET: & CHANG: hasPrinted
    public boolean hasPrinted() {return hasPrinted; }
    public void hasPrintedChange(boolean temp){ hasPrinted=temp; }
    //endregion

}


