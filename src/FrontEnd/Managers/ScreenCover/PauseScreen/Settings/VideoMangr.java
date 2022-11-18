package FrontEnd.Managers.ScreenCover.PauseScreen.Settings;

import BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.KeyBinds;
import FrontEnd.Managers.ScreenCover.ScreenCoverMangr;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.Video.tSize;
import static BackEnd.GameBehaviors.ScreenCover.PauseScreen.Settings.Video.wSize;
import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class VideoMangr {
    public static void showPage() throws IOException {
        ScreenCoverMangr.clearGrid();
        byte i=0;
        ScreenCoverMangr.displayLine(i,"----------Game Paused (Help)----");
        i++; ScreenCoverMangr.displayLine(i,"---Display Settings---");
        i++; ScreenCoverMangr.displayLine(i,"1) <Window Size ("+wSize+")>");
        i++; ScreenCoverMangr.displayLine(i,"2) <Text Size ("+tSize+")>");
        i++; ScreenCoverMangr.displayLine(i,"RESET TO DEFAULT");
        i++;
        i++; ScreenCoverMangr.displayLine(i,"use left and right keys to");
        i++; ScreenCoverMangr.displayLine(i,"increase or decrease a ");
        i++; ScreenCoverMangr.displayLine(i,"highlighted setting");


    }

}
