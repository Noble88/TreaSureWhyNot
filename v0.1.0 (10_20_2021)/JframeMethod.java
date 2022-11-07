import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;


public class JframeMethod {
/*---------------------------------------------WHAT TO KNOW----------------------------------------------------*//*
1) IDK
/*--------------------------------------Declare/Call/Initiate Variables-----------------------------------------------*/

    //region |OPEN ME|
    //region ---MiscStuff.Misc assign Var To Classes
    DeBugger debug = new DeBugger();
    //endregion
    //region ---D&I: JFRAME realted vars
    JFrame frame = new JFrame("JLayeredPane");
    Font font = new Font("Monospaced", Font.CENTER_BASELINE, 25); //mess around with font to see what its good
    //endregion
    //endregion
/*-----------------------------------------------Code-----------------------------------------------------------------*/
    //region |OPEN ME|
    // region A)--------------------!Create and Assign attributes to JFrame & Panel
    //region 1) ---JFrame---
    public void createJframe() {
        frame.setResizable(false);
        frame.setVisible(true); //sets visibility of GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //says what happends when click close button
        frame.setSize(1400, 800); // make it so when change font change window size
        frame.setFocusable(true);
    }
    //endregion
    //region 2) ---JPannel---
    JPanel  panel = new JPanel();
    JPanel  backgroundPanel = new JPanel();
    JLayeredPane layeredPane = new JLayeredPane();
    public JFrame getJframe() { return frame;}
    public JLayeredPane getLayeredPane() { return layeredPane;}

    public void executeWindowProperties(){
        panel.setBounds(0,0,1400,800);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        backgroundPanel.setBounds(0,0,200,200);
        panel.setOpaque(false);backgroundPanel.setOpaque(false);
        layeredPane.setBounds(0,0,1400,800);
        layeredPane.add(panel);layeredPane.add(backgroundPanel);

    }
    /*
    public JPanel getpanel(){return panel;}
    public JPanel getBackgroundPanel(){return backgroundPanel; }
    public JLayeredPane getLayeredPlane(){ return layeredPane; }
    */

    public void layerPanels(){

    }




    JTextPane TEST = new JTextPane();
    public void testing(){
        backgroundPanel.add(TEST);  //<- adds JTextPane to JPanel
        TEST.setFont(font);                                            //<- makes row a certain font
        TEST.setMargin(new Insets(0,0,0,0)); //<- crops the row
        TEST.setOpaque(true); //<- toggles opacity
        TEST.setEditable(true); //<- toggles if can edit text
        TEST.setText("HDJKSDFHSDKFHLSDJKFHJLSFHKJSDFLJK");
    }
//endregion
    //endregion
    // region B)--------------------!BIG:Creating, Modifying, and Methods for Rows     w/Summary
                        /*Summary: This chunk is responsible for creating the rows, assigning them to the JPanel.
    as well as giving the rows attributes and properties. Lastly an arrangement of methods are applied as to use
    the rows in other pieces of code
     1) Will: create and initialize rows
     2) Will: take initialized rows and put it through a method
     3) Have: A method that sets the properties of the rows
     4) Have: A method for changing each row: Used to update row with each |printLevel| Note: has a debugger |if|
        This is so that that code will print out each row when called if |if = true|
     5) Have: Getter method for |tBox|es so when called input number and gives row needed.
     */
    //region 1) ---Initialize Main Rows
    JTextPane hudTP = new JTextPane(),hud01 = new JTextPane();
    JTextPane tBord = new JTextPane(),row01 = new JTextPane(),row02 = new JTextPane();
    JTextPane row03 = new JTextPane(),row04 = new JTextPane(),row05 = new JTextPane();
    JTextPane row06 = new JTextPane(),row07 = new JTextPane(),row08 = new JTextPane();
    JTextPane row09 = new JTextPane(),row10 = new JTextPane(),row11 = new JTextPane();
    JTextPane row12 = new JTextPane(),row13 = new JTextPane(),row14 = new JTextPane();
    JTextPane row15 = new JTextPane(),row16 = new JTextPane(),row17 = new JTextPane();
    JTextPane row18 = new JTextPane(),row19 = new JTextPane(),row20 = new JTextPane();
    JTextPane bBord = new JTextPane();
    JTextPane tBox1 = new JTextPane(),tBox2 = new JTextPane(),tBox3 = new JTextPane(),tBoxB = new JTextPane();
    //endregion


    //region 2) ---Assign Rows Properties
    public void addRows(){
        modifyRow(hudTP);modifyRow(hud01);modifyRow(tBord);
        modifyRow(row01);modifyRow(row02);modifyRow(row03);modifyRow(row04);
        modifyRow(row05);modifyRow(row06);modifyRow(row07);modifyRow(row08);
        modifyRow(row09);modifyRow(row10);modifyRow(row11);modifyRow(row12);
        modifyRow(row13);modifyRow(row14);modifyRow(row15);modifyRow(row16);
        modifyRow(row17);modifyRow(row18);modifyRow(row19);modifyRow(row20);
        modifyRow(bBord);modifyRow(tBox1);modifyRow(tBox2);modifyRow(tBox3);
        modifyRow(tBoxB);
    }
    //endregion

    //region 3) ---Method For Assign Rows Properties
    public void modifyRow(JTextPane row){

        panel.add(row);  //<- adds JTextPane to JPanel
        row.setFont(font);                                            //<- makes row a certain font
        row.setMargin(new Insets(-8,0,-20,0)); //<- crops the row
        row.setOpaque(false); //<- toggles opacity
        row.setEditable(false); //<- toggles if can edit text
    }
    //endregion
    //region 4) ---CHANG: each row
    public void changeHudTP(String arrayString){hudTP.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeHud01(String arrayString){hud01.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeTBord(String arrayString){tBord.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow01(String arrayString){row01.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow02(String arrayString){row02.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow03(String arrayString){row03.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow04(String arrayString){row04.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow05(String arrayString){row05.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow06(String arrayString){row06.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow07(String arrayString){row07.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow08(String arrayString){row08.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow09(String arrayString){row09.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow10(String arrayString){row10.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow11(String arrayString){row11.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow12(String arrayString){row12.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow13(String arrayString){row13.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow14(String arrayString){row14.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow15(String arrayString){row15.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow16(String arrayString){row16.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow17(String arrayString){row17.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow18(String arrayString){row18.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow19(String arrayString){row19.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeRow20(String arrayString){row20.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    public void changeBBord(String arrayString){bBord.setText(arrayString);if(debug.getPLOnMove()){System.out.println(arrayString);}}
    //endregion
    //region 5) ---GET: method for tBox#
    public JTextPane getTbox(int num){
        switch(num){
            case 1->{return tBox1;}
            case 2->{return tBox2;}
            case 3->{return tBox3;}
            case 4->{return tBoxB;}
        }
    return tBoxB;
}
    //endregion
    //region 6) ---GET: row.StyledDocument Getter Method
        // NOTE: the c in cRow means color
    public StyledDocument getCrow01(){return row01.getStyledDocument();}
    public StyledDocument getCrow02(){return row02.getStyledDocument();}
    public StyledDocument getCrow03(){return row03.getStyledDocument();}
    public StyledDocument getCrow04(){return row04.getStyledDocument();}
    public StyledDocument getCrow05(){return row05.getStyledDocument();}
    public StyledDocument getCrow06(){return row06.getStyledDocument();}
    public StyledDocument getCrow07(){return row07.getStyledDocument();}
    public StyledDocument getCrow08(){return row08.getStyledDocument();}
    public StyledDocument getCrow09(){return row09.getStyledDocument();}
    public StyledDocument getCrow10(){return row10.getStyledDocument();}
    public StyledDocument getCrow11(){return row11.getStyledDocument();}
    public StyledDocument getCrow12(){return row12.getStyledDocument();}
    public StyledDocument getCrow13(){return row13.getStyledDocument();}
    public StyledDocument getCrow14(){return row14.getStyledDocument();}
    public StyledDocument getCrow15(){return row15.getStyledDocument();}
    public StyledDocument getCrow16(){return row16.getStyledDocument();}
    public StyledDocument getCrow17(){return row17.getStyledDocument();}
    public StyledDocument getCrow18(){return row18.getStyledDocument();}
    public StyledDocument getCrow19(){return row19.getStyledDocument();}
    public StyledDocument getCrow20(){return row20.getStyledDocument();}

    //endregion
    //endregion
    //endregion
/*-----------------------------------------------NOTES---------------------------------------------------------*//*
    //region |OPEN ME|
    highlighter.removeAllHighlights();
    //Set up a function to draw the shapes
    */
}

