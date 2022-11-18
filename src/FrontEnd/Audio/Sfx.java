package FrontEnd.Audio;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.Arrays;

public class Sfx {
  //Avlible keys to play("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

  public static class NoteAdv extends Sfx{
    String key;
    int dur, vol, instrm;

    //region constructor methods
    public NoteAdv(String note, int dur,int vol,int instrument ){
      key=note; this.dur=dur; this.vol=vol; instrm=instrument;
    }
    public NoteAdv(){key="5A#"; instrm=9; vol=100; dur=1000;}
    //endregion

    public void play() throws InterruptedException, MidiUnavailableException {
      SoundBhvr.play(key,dur,vol,instrm);
    System.out.println("ATTEMPTED TO PLAY SOUND");
    }
  }

  public static int testNum=20;
  public static class Test extends Sfx{
    int dur, vol, instrm;

    public Test(int dur,int vol,int instrument ){
      this.dur=dur; this.vol=vol; instrm=instrument;
    }

    public void play() throws InterruptedException, MidiUnavailableException {
      SoundBhvr.play(testNum,dur,vol,instrm);
      System.out.println("Key note played "+testNum);
      testNum++;
    }
  }

  public static class NoteNumInstrm extends Sfx{
    int key, dur, vol, instrm;

    public NoteNumInstrm(int key, int dur,int vol,int instrument){
      this.key=key; this.dur=dur; this.vol=vol; instrm=instrument;
    }

    public void play() throws InterruptedException, MidiUnavailableException {
      SoundBhvr.play(key,dur,vol,instrm);
    }
  }

  //region RNG notes
  public static class NoteRngBsc extends Sfx{
    ArrayList<Sfx> notes = new ArrayList<>();
    public NoteRngBsc(ArrayList<Sfx> notesArr){notes.addAll(notesArr);}

    public void play() throws InterruptedException, MidiUnavailableException {
      notes.get((int)(Math.random() * notes.size())).play();
      System.out.println("Key note played "+testNum);
      testNum++;
    }
  }

  public static class NoteRngAdv extends Sfx{
    ArrayList<Sfx> notes = new ArrayList<>();
    byte[] probability;//problility are how many in 10 -> 3,5,10 <- should be low to high and add to 10 & translates to ->30%,20%,50%
    public NoteRngAdv(byte[] probability, ArrayList<Sfx> notesArr){
      this.probability=probability;
      notes.addAll(notesArr);
      System.out.println("prob ("+ Arrays.toString(this.probability) +") & arr ("+this.notes+")");
    }

    public void play() throws InterruptedException, MidiUnavailableException {
      int rng=(int)(Math.random() * 10)+1;
      for(byte i=0; i<notes.size();i++){
        if(rng<probability[i]){
          System.out.println("attempt to play "+i+" with rng "+rng);

          notes.get(i).play(); return;}
      }
    }
  }

  //endregion

  public static class NoteOrder extends Sfx{
    ArrayList<Sfx> notes = new ArrayList<>();
    byte curNote=0;
    public NoteOrder(ArrayList<Sfx> notesArr){
      notes.addAll(notesArr);
    }

    public void play() throws InterruptedException, MidiUnavailableException {
      notes.get(curNote).play();
      if(curNote+1==notes.size()){curNote=0;}   else{curNote++;}
    }
  }

  public static class BgMusic extends Sfx{
    //public BgMusic
  }

  public void play() throws InterruptedException, MidiUnavailableException {System.out.println("(EMPTY SUPER METHOD PLAYED) -> SFX");}

}
