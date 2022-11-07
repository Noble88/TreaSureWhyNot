package FrontEnd.Audio;

import BackEnd.GlobalInfo.GlobData;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import java.util.Arrays; import java.util.List;

import javax.sound.midi.*;


public class SoundFx {
  static int musicNote=0;

  public static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
  public static MidiChannel[] channels;
  public static int volAdj=0;
  public static int INSTRUMENT = 9; // 0 is a piano, 9 is percussion, other channels are for other instruments
  public static int VOLUME = 101; // between 0 et 127
  public static void openSounds(){
    try{
      Synthesizer synth = MidiSystem.getSynthesizer();
      synth.open();
      channels = synth.getChannels();
    }
    catch (MidiUnavailableException e) {e.printStackTrace();}
  }
/* Sound IDEAS
ICE: "8D",1   | Cave/Undergound Wet: "4D", 9  OR 5D#, 9|
*/

  /* DISCOVERIES
  CHANNEL 9: Wind chimes:6C | Type Writer: 6C#| Bongos: 6D/6D#/5E/4C#/4D#/4E            |Bell Rattle:6E# & 5B |
             Whistle:5C & 5E#| ZZZT: 5C#       | BBRRRT: 5D       | dink:        5D# | light bonk:5F | Hooh:5F# & 5G | high ding: 5G# | ding: 5A |sandpaper: 5A# |
             Str Pull: 4C/4F    |Meat Clap: 4D    |
  */
  //region
  public static void play(String note, int dur,int vol,int instrument ) throws InterruptedException{
    INSTRUMENT=instrument;VOLUME=vol;
    channels[INSTRUMENT].noteOn(id(note), VOLUME );
    Thread.sleep(dur);
    channels[INSTRUMENT].noteOff(id(note));
  }
  public static void play(int dur) throws InterruptedException{
    channels[INSTRUMENT].noteOn(musicNote,60);
    Thread.sleep(dur);
    channels[INSTRUMENT].noteOff(musicNote);
  }
  public static int id(String note)
  {
    int octave = Integer.parseInt(note.substring(0, 1));
    return notes.indexOf(note.substring(1)) + 12 * octave + 12;
  }

  public static void Sfx(){

  }

/*
  public static void surfaceSound(char sym) throws MidiUnavailableException {

    //System.out.println("NOTE:"+musicNote);

      Synthesizer synth = MidiSystem.getSynthesizer();
      synth.open();
      channels = synth.getChannels();
      switch(sym){
        case GlobData.grass->{play("6E#",  30,40 +volAdj,0);}
        //case (GlobData.dirtVert || GlobData.dirtHorz)->{play("6C",  30,40 +volAdj,0);}
        synth.close();
        //case"."->{play("5A#",  1,60+volAdj,9);}
      }

 */


   // catch (InterruptedException e) {e.printStackTrace();}}
/*
      try {
          //Open a synthesizer


          // * Play some notes
          play("6D",  1000);
          rest(500);

          play("6D",  300);
          play("6C#", 300);
          play("6D",  1000);
          rest(500);

          play("6D",  300);
          play("6C#", 300);
          play("6D",  1000);
          play("6E",  300);
          play("6E",  600);
          play("6G",  300);
          play("6G",  600);
          rest(500);

 */


      //}
  //}

  // Plays the given note for the given duration




}
/* types of sound in NOTE NUMBER
27: echoy drop | 28:echoy slap | 29 & 30: echoy record skratch | 31: drum stick clap | 32: static blib |
33: light tap  | 34:light tap + bell | 35: deep light tap| 36: deeper hard tap| 37: tap | 38 & 40: Drumb tap |
39: DJ clap    | 41 faded tap| 42 & 44 & 46: drum cymbol tap| 43: deep high tap | 45: drum boom | 47: deep drum |
48: deep high drum | 49: cymbols | 50: Deep low DOO |
 */