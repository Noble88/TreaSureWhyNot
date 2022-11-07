import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bag {
    /*---------------------------------------------WHAT TO KNOW------------------------------------------------*//*
1) How this class switches gameState and level Display:
    This class a Dominate Array that contains and final version of what needs to be be displayed so
    When switching game state (handled object.method gameStateSwitcher) it will assign the arrays from different
    class to single one. keeping the state of other classes arrays when switch states.
2) KeyListener and Main Move independently of each other So I've used a |synchronize| for the sole reason of having
    scrolling text (might do more w/it
3) Read the Summary for clarification of code
*/
    /*--------------------------------------Declare/Call/Initialize/Variables-----------------------------------------*/
    //region ---MiscStuff.Misc: assign Vars To Class---
    static JframeMethod windowInfo = new JframeMethod();
    static Levels getLevels = new Levels();
    static Movement movement = new Movement();
    static Text text = new Text();
    static DeBugger debug = new DeBugger();
    static HudAndUI hud = new HudAndUI();
    static PrintGame print = new PrintGame();
    static Treasure treasure = new Treasure();
    //endregion
    //region ---D&I : Vars && Arrays---
    String W="|" , b="-" , B="_", E=" ",P="+"; /*Boarders & Empty Space*/
    static String u="^"; static String R=">"; static String V="v"; static String L="<"; /*Directions*/
    static String bagPageState="",arrowKey="";
    static boolean TorF=false;
    StringBuilder bagDebugger = new StringBuilder();

    ArrayList<String> bag1 = new ArrayList<>();
    ArrayList<String> bag2 = new ArrayList<>();
    ArrayList<String> tres1Bag = new ArrayList<>();
    ArrayList<String> currentBagPage = new ArrayList<>();

    //endregion
    /*------------------------------------------Main Parts of Code----------------------------------------------------*/
    /*NOTE: B.2 DOING BAG UPDATE SEEMS INEFFICIENT: as it will always every time check and
      reprint something it doesn't need to reprint*/
    //region A)--------------------!BIG: Array Creation & Assign to Relevant Array
    public Bag(){
        //region ITEM BAG PAGE 1
        String [] BAG1tBord = new String[]{W,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,W};
        String [] BAG1row01 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row02 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row03 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row04 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row05 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,R};
        String [] BAG1row06 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row07 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row08 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row09 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row10 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,R};
        String [] BAG1row11 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row12 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row13 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row14 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row15 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,R};
        String [] BAG1row16 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row17 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row18 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row19 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row20 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,R};
        String [] BAG1bBord = new String[]{W,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,W};
        bag1.addAll(Arrays.asList(BAG1tBord));bag1.addAll(Arrays.asList(BAG1row01));bag1.addAll(Arrays.asList(BAG1row02));
        bag1.addAll(Arrays.asList(BAG1row03));bag1.addAll(Arrays.asList(BAG1row04));bag1.addAll(Arrays.asList(BAG1row05));
        bag1.addAll(Arrays.asList(BAG1row06));bag1.addAll(Arrays.asList(BAG1row07));bag1.addAll(Arrays.asList(BAG1row08));
        bag1.addAll(Arrays.asList(BAG1row09));bag1.addAll(Arrays.asList(BAG1row10));bag1.addAll(Arrays.asList(BAG1row11));
        bag1.addAll(Arrays.asList(BAG1row12));bag1.addAll(Arrays.asList(BAG1row13));bag1.addAll(Arrays.asList(BAG1row14));
        bag1.addAll(Arrays.asList(BAG1row15));bag1.addAll(Arrays.asList(BAG1row16));bag1.addAll(Arrays.asList(BAG1row17));
        bag1.addAll(Arrays.asList(BAG1row18));bag1.addAll(Arrays.asList(BAG1row19));bag1.addAll(Arrays.asList(BAG1row20));
        bag1.addAll(Arrays.asList(BAG1bBord));
        //endregion
        //region ITEM BAG PAGE 2
        String [] BAG2tBord = new String[]{W,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,W};
        String [] BAG2row1 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row2 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row03 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row04 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row05 = new String[]{L,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] BAG2row06 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row07 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row08 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row09 = new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row10= new String[]{L,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] BAG2row11= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row12= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row13= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row14= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row15= new String[]{L,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] BAG2row16= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row17= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row18= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row19= new String[]{L,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] BAG2row20= new String[]{L,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] BAG2bBord = new String[]{L,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,W};
        bag2.addAll(Arrays.asList(BAG2tBord));bag2.addAll(Arrays.asList(BAG2row1));bag2.addAll(Arrays.asList(BAG2row2));
        bag2.addAll(Arrays.asList(BAG2row03));bag2.addAll(Arrays.asList(BAG2row04));bag2.addAll(Arrays.asList(BAG2row05));
        bag2.addAll(Arrays.asList(BAG2row06));bag2.addAll(Arrays.asList(BAG2row07));bag2.addAll(Arrays.asList(BAG2row08));
        bag2.addAll(Arrays.asList(BAG2row09));bag2.addAll(Arrays.asList(BAG2row10));bag2.addAll(Arrays.asList(BAG2row11));
        bag2.addAll(Arrays.asList(BAG2row12));bag2.addAll(Arrays.asList(BAG2row13));bag2.addAll(Arrays.asList(BAG2row14));
        bag2.addAll(Arrays.asList(BAG2row15));bag2.addAll(Arrays.asList(BAG2row16));bag2.addAll(Arrays.asList(BAG2row17));
        bag2.addAll(Arrays.asList(BAG2row18));bag2.addAll(Arrays.asList(BAG2row19));bag2.addAll(Arrays.asList(BAG2row20));
        bag2.addAll(Arrays.asList(BAG2bBord));
        //endregion
        //region TREASURE BAG PAGE 1
        String [] TREStBord = new String[]{W,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,W};
        String [] TRESrow01 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow02 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow03 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow04 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow05 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] TRESrow06 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow07 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow08 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow09 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow10= new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] TRESrow11= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow12= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow13= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow14= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow15= new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] TRESrow16= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow17= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow18= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow19= new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,W};
        String [] TRESrow20= new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,W};
        String [] TRESbBord = new String[]{W,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,V,W};
        tres1Bag.addAll(Arrays.asList(TREStBord));tres1Bag.addAll(Arrays.asList(TRESrow01));tres1Bag.addAll(Arrays.asList(TRESrow02));
        tres1Bag.addAll(Arrays.asList(TRESrow03));tres1Bag.addAll(Arrays.asList(TRESrow04));tres1Bag.addAll(Arrays.asList(TRESrow05));
        tres1Bag.addAll(Arrays.asList(TRESrow06));tres1Bag.addAll(Arrays.asList(TRESrow07));tres1Bag.addAll(Arrays.asList(TRESrow08));
        tres1Bag.addAll(Arrays.asList(TRESrow09));tres1Bag.addAll(Arrays.asList(TRESrow10));tres1Bag.addAll(Arrays.asList(TRESrow11));
        tres1Bag.addAll(Arrays.asList(TRESrow12));tres1Bag.addAll(Arrays.asList(TRESrow13));tres1Bag.addAll(Arrays.asList(TRESrow14));
        tres1Bag.addAll(Arrays.asList(TRESrow15));tres1Bag.addAll(Arrays.asList(TRESrow16));tres1Bag.addAll(Arrays.asList(TRESrow17));
        tres1Bag.addAll(Arrays.asList(TRESrow18));tres1Bag.addAll(Arrays.asList(TRESrow19));tres1Bag.addAll(Arrays.asList(TRESrow20));
        tres1Bag.addAll(Arrays.asList(TRESbBord));

        //endregion
        }
        //endregion
    //region B)--------------------!BIG: Bag Navigator & Relevant Component        w/Summary
                    /*Summary: Takes |keyPressed| from |PrintGame|'s Key Listener and Displays Bag depending on what
                    state the bag is in.
     1) Has: Debugger Reset-er and Start Point
     2) Will: change Var |arrowKey| to input when method called  & |.clear| current bag  &  calls |bagUpdater|
     3) Will: check what page its on & use arrow keys to find where it needs to go (& print debugger)
     4) Has: GET and CHANG methods for bagPageState & currentBagPage
     GENERAL NOTE: IB#=item bag #   &   TB#=treasure bag #
     */

    public void navigation(String keyPressed){
        //region 1)---Debugger---
        bagDebugger.delete(0,bagDebugger.length());
        bagDebugger.append("Bag -> navigation -> ");
        //endregion
        //region 2)---Prep-er---
        arrowKey=keyPressed;
        print.hasPrintedChange(false);
        currentBagPage.clear();
        bagUpdater();

        //endregion
        //region 3)---Bag Navigator---
        switch(bagPageState){
            case"IB1"-> {
                switch (arrowKey) {
                    case "UP" ->    {currentBagPage.addAll(tres1Bag);bagPageState = "TB1";}
                    case "RIGHT" -> {currentBagPage.addAll(bag2);    bagPageState = "IB2";}
                    default ->      {currentBagPage.addAll(bag1);    bagPageState = "IB1";}
                }
            }
            case"IB2"-> {
                switch (arrowKey) {
                    case "UP" ->   {currentBagPage.addAll(tres1Bag);bagPageState = "TB1";}
                    case "LEFT" -> {currentBagPage.addAll(bag1);    bagPageState = "IB1";}
                    case "RIGHT" ->{currentBagPage.addAll(bag2);}//replace bag2 w/the next right page
                    default ->     {currentBagPage.addAll(bag2);    bagPageState = "IB2";
                    }
                }
            }
            case"TB1"-> {
                switch (arrowKey) {
                    case "DOWN" -> {currentBagPage.addAll(bag1);bagPageState = "IB1";}
                    case "RIGHT" ->{currentBagPage.addAll(tres1Bag);bagPageState = "TB1";} //REPLACE WHEN SLOT AVAILABLE
                    default ->     {currentBagPage.addAll(tres1Bag);bagPageState = "TB1";
                    }
                }
            }
            default -> {System.out.print("ERROR IN FINDING CURRENT PAGE STATE");}
        }
        bagDebugger.append("KeyPressed("+arrowKey+") / PageState("+bagPageState+") / ");
        if(debug.bagCLass()){System.out.println(bagDebugger);}
        //endregion
    }
        //region 4)---GET AND CHANG: BagPageState  &  GET: currentBagPage---
    public void changeBagPageState(String bagState){bagPageState=bagState;}
    public String getBagPageState(){return bagPageState;}
    public ArrayList<String> getCurrentBagPage(){return currentBagPage;}
        //endregion
    //endregion
    //region C)--------------------!BIG: Bag Updater  &  Color Bag                 w/Summary
                    /*Summary: This will update if obtained item and color it when viewing bag
     1) Will: D&I row margins so easy to read
     2) Will: Checks to see if player has an Item if so add it to the array
     3) Will: Colors the rows depending on if have item
     */
    //region 1)---D&I Row Margins---
    int row01=42,row02=42*2,row03=42*3,row04=42*4;
    //endregion
    //region 2)---Bag Updater---
    public void bagUpdater(){
        //make and statment that checks to see if it has empty space in the area of the item
        /*
        String [] BAG1tBord = new String[]{W,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,u,W};
        String [] BAG1row01 = new String[]{W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row02 = new String[]{W,|,E,E,E,E,_,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row03 = new String[]{W,|,=,=,=,|,_,],W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row04 = new String[]{W,|,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,W,E,E,E,E,E,E,E,E,R};
        String [] BAG1row05 = new String[]{W,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,P,b,b,b,b,b,b,b,b,R};
         */
        if(playerHas("SHOVEL")) {
            /*bag1.set(row01+1, "|");*/bag1.set(row01+6, "_");
            bag1.set(row02+1, "|");bag1.set(row02+2, "=");bag1.set(row02+3, "=");bag1.set(row02+4, "=");bag1.set(row02+5, "|");bag1.set(row02+6, "_");bag1.set(row02+7, "}");
            //bag1.set(row03+1, "|");
        }
        if(treasure.playerHas("GOLD COIN")){
            for (int i = 44; i < 49; i++) { tres1Bag.set(i, "-"); }
            tres1Bag.set((43+42),"|"); tres1Bag.set((49+42),"|");
            for (int i = (44+42*2); i < (49+42*2); i++) {tres1Bag.set(i, "-"); }
        }
    }
    //endregion
    //region 3)---Color Bag---
    public void bagColorer(StyledDocument r01,StyledDocument r02,StyledDocument r03,StyledDocument r04,StyledDocument r05,
                           StyledDocument r06,StyledDocument r07,StyledDocument r08,StyledDocument r09,StyledDocument r10,
                           StyledDocument r11,StyledDocument r12,StyledDocument r13,StyledDocument r14,StyledDocument r15,
                           StyledDocument r16,StyledDocument r17,StyledDocument r18,StyledDocument r19,StyledDocument r20){
        SimpleAttributeSet black     = new SimpleAttributeSet();StyleConstants.setForeground(black,    new Color(0, 0, 0));
        SimpleAttributeSet white     = new SimpleAttributeSet();StyleConstants.setForeground(white,    new Color(225, 225, 225));
        SimpleAttributeSet shinyGold = new SimpleAttributeSet();StyleConstants.setForeground(shinyGold,new Color(255, 215, 30));
        SimpleAttributeSet riverBlue = new SimpleAttributeSet();StyleConstants.setForeground(riverBlue,new Color(62, 68, 246));
        SimpleAttributeSet brown     = new SimpleAttributeSet();StyleConstants.setForeground(brown    ,new Color(150, 75, 1));
        // Type Of Bag /box # / object
        //Treasure Bag /box 1 / GOLD COIN
        if(treasure.playerHas("GOLD COIN") && getBagPageState().equals("TB1")){ //So if player has X and is on the proper page color
            System.out.println("went through if for GOLD COIN");
            r01.setCharacterAttributes(1, 7, shinyGold, true);
            r02.setCharacterAttributes(1, 7, shinyGold, true);
            r03.setCharacterAttributes(1, 7, shinyGold, true);
            r04.setCharacterAttributes(1, 7, shinyGold, true);
        }//Item Bag / box 1 / SHOVEL
        if(playerHas("SHOVEL") && getBagPageState().equals("IB1")){
            System.out.println("went through if for SHOVEL");
            r01.setCharacterAttributes(1, 6, black, true);
            r02.setCharacterAttributes(1, 6, black, true);
            r03.setCharacterAttributes(1, 6, black, true);
            r04.setCharacterAttributes(1, 6, black, true);
        }
    }


    //endregion
    //endregion
    //region D)--------------------!BIG: Check & Change Player Items               w/Summary
                    /*Summary: This can GET or CHANG if has item
     1) D&I all obtainable items
     2) Checks Players Items and return a true or false
     3) Change whether or not Player Has Item
     */
    //region 1)---D&I items---
    static boolean shovel=false;
    //endregion
    //region 2)---GET: if player has an item---
    public boolean playerHas(String item){
        switch(item){
            case"SHOVEL"->{TorF=shovel;}
        }
        return TorF;
    }
    //endregion
    //region 3)---CHANG: if player has an item---
    public void changePlayerHas(String item,boolean temp){
        switch(item){
            case"SHOVEL"->{shovel=temp;}
        }
    }
    //endregion
    //endregion
}
