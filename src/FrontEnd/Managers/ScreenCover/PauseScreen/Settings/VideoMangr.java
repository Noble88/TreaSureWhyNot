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
        i++; ScreenCoverMangr.displayLine(i,"1) Increase Window Size *"+wSize);
        i++; ScreenCoverMangr.displayLine(i,"2) Increase Window Size /"+wSize);
        i++; ScreenCoverMangr.displayLine(i,"3) Increase Text Size *"+tSize);
        i++; ScreenCoverMangr.displayLine(i,"4) Increase Text Size /"+tSize);
        i++; ScreenCoverMangr.displayLine(i,"RESET TO DEFAULT");
    }

}
