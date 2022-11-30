package BackEnd.FileManagement.Loaders;

import BackEnd.GameBehaviors.SideWndwElmnts.ToolBag;
import BackEnd.GameBehaviors.SideWndwElmnts.TresBag;
import FrontEnd.Runner;

import java.io.*;

import static BackEnd.GlobalInfo.GlobData.fileFinder;

public class LoadBag {
  public static void loadBag() throws IOException, InterruptedException, ClassNotFoundException {
    for(byte i=0;i<TresBag.menu.getMaxPg();i++){
      createPage(i,true);
    }
    //region Create the first page
    ObjectInputStream input1 = new ObjectInputStream(new FileInputStream(new File(TresBag.pagePaths[0])));
    TresBag.curPage = (TresBag.TrePage)input1.readObject();
    input1.close();
    //endregion

    for(byte i=0;i<ToolBag.menu.getMaxPg();i++){
      createPage( i,false);
    }
    //region Create the first page
    ObjectInputStream input2 = new ObjectInputStream(new FileInputStream(new File(ToolBag.pagePaths[0])));
    ToolBag.curPage = (ToolBag.ToolPage) input2.readObject();
    input2.close();
    //endregion

    //region items

    //endregion
  }

  public static void createPage(byte pos, boolean tre) throws InterruptedException, IOException, ClassNotFoundException {
    File tempFile;
    if(tre){
      TresBag.pagePaths[pos]=getLevelPath(pos,tre);
      tempFile = new File(TresBag.pagePaths[pos]);
    }
    else{
      ToolBag.pagePaths[pos]=getLevelPath(pos,tre);
      tempFile = new File(ToolBag.pagePaths[pos]);
    }

    if(Runner.deleteFilesOnStart){
      tempFile.delete();
    }
    if(!tempFile.exists()) {
      FileWriter writer;
      if(tre){ writer = new FileWriter(TresBag.pagePaths[pos]);}
      else{writer = new FileWriter(ToolBag.pagePaths[pos]);}
      writer.close();
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(tempFile));
      if(tre){output.writeObject(new TresBag.TrePage(pos));}
      else{output.writeObject(new ToolBag.ToolPage(pos));}
      output.close();
    }
  }

  public static String getLevelPath(byte pos, boolean tre){
    String folder="";
    if(tre){folder="Treasure";} else{folder="Tools";}
    return fileFinder.get("BagPages")+"/"+folder+"/page"+pos+".txt";
  }

}
