package FrontEnd.Managers.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import BackEnd.GameLoop;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.event.KeyEvent.*;

public class HelpManagr {
    //24 by 24 (i think)
    public static void showPage1() throws IOException {
        ScreenCoverMangr.clearGrid();
        byte i=0;
             ScreenCoverMangr.displayLine(i,"----------Game Paused (Help)----");
        i++; ScreenCoverMangr.displayLine(i,"---Basic Key Bindings---");
        i++; ScreenCoverMangr.displayLine(i,"Right:"+GameLoop.getKeyFromAssociatedKey("RIGHT")+" | " +
                                                "Left:"+GameLoop.getKeyFromAssociatedKey("LEFT"));
        i++; ScreenCoverMangr.displayLine(i,"Up:"+GameLoop.getKeyFromAssociatedKey("UP")+" | " +
                                                "Down:"+GameLoop.getKeyFromAssociatedKey("DOWN"));
        i++; ScreenCoverMangr.displayLine(i,"-Shift switch between the");
        i++; ScreenCoverMangr.displayLine(i," side window");
        i++; ScreenCoverMangr.displayLine(i,"-ESC to back out of menus");

    }
    public static void showPage2() throws IOException {
        ScreenCoverMangr.clearGrid();
        byte i=0;
        ScreenCoverMangr.displayLine(i,"----------Game Paused (Help)----");
        i++; ScreenCoverMangr.displayLine(i,"---Game Play Mechanics-----");
        i++; ScreenCoverMangr.displayLine(i,"-Hit the edge of the level to");
        i++; ScreenCoverMangr.displayLine(i,"go to a new area");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"-Try speaking to people and");
        i++; ScreenCoverMangr.displayLine(i,"see what they have to say!");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"-The game saves every time you");
        i++; ScreenCoverMangr.displayLine(i,"switch levels, but can manual");
        i++; ScreenCoverMangr.displayLine(i,"save if needed");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"You can enter buildings by");
        i++; ScreenCoverMangr.displayLine(i,"walking into them");
    }
    /*
    public static void showPage3() throws IOException {
        ScreenCoverMangr.clearGrid();
        byte i=0;
        ScreenCoverMangr.displayLine(i,"----------Game Paused (Help)----");
        i++; ScreenCoverMangr.displayLine(i,"---Game Play Mechanics-----");
        i++; ScreenCoverMangr.displayLine(i,"-Hit the edge of the level to");
        i++; ScreenCoverMangr.displayLine(i,"go to a new area");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"-Try speaking to people and");
        i++; ScreenCoverMangr.displayLine(i,"see what they have to say!");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"-The game saves every time you");
        i++; ScreenCoverMangr.displayLine(i,"switch levels, but can manual");
        i++; ScreenCoverMangr.displayLine(i,"save if needed");
    }
     */
}
