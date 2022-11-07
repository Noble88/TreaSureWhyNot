import java.util.ArrayList;

public class Movement {
/*--------------------------------------Declare/Call/Initialize Variables-----------------------------------------------*/
    //region Class object declaration
    static JframeMethod FrameStuff = new JframeMethod();
    static PrintGame print = new PrintGame();
    static Levels getLevels = new Levels();
    static Text text = new Text();
    static DeBugger debug = new DeBugger();
    static Treasure treasure=new Treasure();
    //endregion
    //region Variables
    static int desPos,playerPos; static String desVar = ""; static String direction = "";
    static String walkableSurface = ","+"."+" "; //says what positions are walkable
    //endregion
    //region Arrays
    ArrayList<String> areaTemp = new ArrayList<>();
    ArrayList<String> areaPerm = new ArrayList<>();
    //endregion
    StringBuilder movementDebugger = new StringBuilder(); //debugger
/*------------------------------------------Main Parts of Code--------------------------------------------------------*/
    //region A)--------------------!BIG: get and execute movement + send info to other classes
                    /*Summary: takes player input from |PrintGame->keyListener| and re-asignes currentLevel to
                      a new array calculated on player input
     1) Will: Takes user input (got from keyListener & updated by |PrintGame.gameProcessor| ) & resigns varibles for class
     2) Will: Takes resigned Vars and calculates movement
     3) Will: Use the calculated movement and finally execute the movement
     4) Will: send variables in the after math of executing movement
     */
    //region 1)--- Receive Movement Method---
    public void receiveMovement(String arrowKeyPressed, ArrayList<String> tempArea, ArrayList<String> permArea) {
        direction = arrowKeyPressed;
        areaTemp = tempArea;
        areaPerm = permArea;
        print.hasPrintedChange(false);
    }
    //endregion
    public void movePlayer() {
        //region 2)---Calculate Movementr---
        movementDebugger.delete(0,movementDebugger.length()); //reset class debugger
        movementDebugger.append("Movement -> MovePlayer ->");

        playerPos = areaTemp.indexOf("@");   //declars play position at "@" which is player
        movementDebugger.append("Key Press:");
        //takes keypressed from receiveMovement method and moves character accordingly
        switch (print.getKeyPressed())
        {
            case "LEFT"  -> {
                //tells me what direction player went
                desPos = playerPos - 1;        // find position of where it needs to go
                desVar = areaTemp.get(desPos); // finds the character at the destination the player needs to be at
            }
            case "RIGHT" -> {
                desPos = playerPos + 1;
                desVar = areaTemp.get(desPos);
            }
            case "UP"    -> {
                desPos = playerPos - 42; //42 is the num of columbs in level
                desVar = areaTemp.get(desPos);
            }
            case "DOWN"  -> {
                    desPos = playerPos + 42; //42 is the num of columbs in level
                    desVar = areaTemp.get(desPos);
            }
            default ->{ movementDebugger.append("(NON ASSIGNED KEY)");}
        }
        movementDebugger.append("Key Press:("+direction+") / ");
        movementDebugger.append("Destination Variable:("+(desVar)+") / Surface:");
        //endregion
        //region 3)---Execute Movement---
            if(walkableSurface.contains(desVar) && !print.hasPrinted()){               //check to see if can walk on destinaiton

                areaTemp.set(playerPos, areaPerm.get(playerPos));                      //Sets play position with
                areaTemp.set(desPos, "@");                                             //Sets the destination and replaces the char there with the player
                getLevels.changeCurrentLevelTemp(areaTemp);                            //Reasignes currentLevelTemp
                movementDebugger.append("(Walkable) / Has Printed:").append(print.hasPrinted());
            } else {movementDebugger.append("(Not Walkable) / Has Printed:"+print.hasPrinted()+" / ");} //if not walkable don't do anything
        if(debug.movementClass()){System.out.println(movementDebugger);}   //prints out class debugger
        //endregion-
        //region 4)---Send Var Information To other Classes
        text.interactableCheck(direction,areaTemp);                        //sends the direction and currentLevelTemp to text->interactableCheck
        getLevels.collisionLCVar(desVar);                                  //sends desVar and checks to see if teleport character
        debug.changeLocationOfSymbols(areaTemp);                           //sends areaTemp to be debugged/displayed by Dbug
        //endregion
    }
    //endregion
}
