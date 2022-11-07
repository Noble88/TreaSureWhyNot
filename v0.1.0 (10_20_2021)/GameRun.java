public class GameRun {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1) THIS makes everything run
*/
/*--------------------------------------Declare/Call/Iniltize Varaibles-----------------------------------------------*/
    //region ---MiscStuff.Misc: Assign Var To CLasses---
    static PrintGame print = new PrintGame();
    static JframeMethod windowInfo = new JframeMethod();
    static Levels getLevels = new Levels();
    static Movement movement = new Movement();
    static Text text = new Text();
    static DeBugger debug = new DeBugger();
    static HudAndUI hud = new HudAndUI();
    static Bag bag = new Bag();
    static Treasure treasure = new Treasure();
    //endregion
    //region Synchronized necessities
    static final GameRun runner = new GameRun();
    static final PrintGame printGame = new PrintGame();
    public GameRun getGameRunClass(){return runner;}
    //endregion

    public static void main(String[] args) throws InterruptedException {
        if (print.getDoneWStartup()){
            synchronized (runner) {runner.wait();} //create the scrolling text NOTE: find way to produce live printing text w/out being inside main
            text.interactableCheck(text.getPlayerDirection(),text.getVoidTempArray());
            text.getTextInteractions();
            text.createText();
            synchronized(printGame.getPrintGameClassObject()) {printGame.getPrintGameClassObject().notifyAll();}
        }
        else {
            System.out.println("WENT THROUGH ELSE!!!!!!");
            print.startOfGame();
            print.getKeyListener();
            print.printLevel();
            print.changeKeyPressed("N/A"); //adding this because if don't bugs accrue
            print.changeDoneWStartup(true);

            //add keylisten and only display what key was pressed
        }
        GameRun.main(null);
    }
}

/*


TODO
-------------STUFF TO LEARN-----------
find out more about setCharacterAttributes and what u can do with it

-----------Stuff to fully add--------------
ADD SO WHEN IN BAG STATE HUD IS REPLACED W/CURRENT INFO ON BAG PAGE AND STUFF
ADD TREASURE DEBUGGER & MAKE DEBUGGER SO WHEN NOT GO THROUGH CLASS THEN PRINT (NOT ACTIVE)
ADD SO EITHER WHEN TEXT IS PLAYING CHECK IF ENGANGED IN ANOTHER TEXT IF SO CUT CURRENT LOOP AND GO AGAIN
    OR FREEZE PLAYER WHEN TEXT IS BEING DISPLAYED
ADD A DEBUGGER FOR GAME RUN AND ORGANIZE DEBUGGER
ADD shapes
ADD MOVEMENT BOX BY BOX MOVEMENT IN BAG AND WHEN PRESS ENETER GET DESCRIPTION
    box to box movement is nessessary for map level design
        ADD MAP AND WHEN HOVER OVER IT DISPLAYS SECTION OF THE AREA
ADD MENU
ADD when all treasure is gotten in the area it text of hud indiactor turns green
ADD Water to main area

ADD kitch and bedrooms and stuff in HOME
----------Things to change/minor add ----------
CHANGE: make it so when move clear text
make it so when text exceeds box cap print and put rest of text
    COULD MAE A SEPERATE GAME STATE CALLED TEXT WHERE DISABLES MOVEMENT AND CUTS OFF THE TEXT SETING ENTER --> AT THE END
    AND IF PRESS ENETER THEN GOES TO NEXT AREA AND CHANGE GAME STATE
        if length is > than text box cut off & add add ENTER --> grab substring of not displayed text insert it over again and keep sutracting substring until <textbox limit
        once that is true make game state = Level
make non editable text in JFRAME
-------------------BUGS--------------------
Pressing enter when game starts breaks game
text will presist after walking CLEAR TEXT
WHEN CHANGING TO BAG NOT WORK
picking up treasure and (I think having active dialogue)
------------------INEFFICIENT CODE----------
Bag.B.2 (Check notes) w/Bag.C.2
Le


 --------- what to tidy up-------
 CHANGE OBJECT NAMES SO INSTEAD OF bag.bagColor DO bag.color

 Class Print 159
 JFRAME 67-132
    make it so it just addRows as it is only being declared in once
    Look up String builder to simplify PrintGame 117 for loops



-------------Game Design-----------
 HAVE PARETNS LEAVE AFTER GET FIRST TREASURE AND UNLOCK OTHER AREAS/GET OUT OF TUTORIAL

 */

