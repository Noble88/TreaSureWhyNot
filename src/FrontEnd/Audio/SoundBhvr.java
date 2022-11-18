package FrontEnd.Audio;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;


import java.util.Arrays; import java.util.List;

import javax.sound.midi.*;

import static FrontEnd.Runner.disableSound;


public class SoundBhvr {
  static int musicNote=0;

  public static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");

  public static MidiChannel[] channels;
  public static Synthesizer synth;
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
//region channel 9 stuff

  //region catagorized
  /*

  --World Stuff
  Basic Floor: 32,85,82
  cave floor: 33
  Door knock: 61
  Horse: 62
  Ice/Snow: 67,68,75,80
  Sand: 69,70,82
  mountain: 73
  Glass, 80
  using rope: 74

  NPC Stuff
  Idea: 34,81
  Oops: 50
  Bad Joke: 58
  Ackward silence: 60 (3 times)
  Toung Click: 70
  Panic: 78
  Oh crap: 79
  Good interaction: 84


  */

  //endregion
  //region Drums
  /*
  31: drum stick | 33: echo drum rim | 35 & 36: low deep drum | 37: deep high drum |
  38 & 40: drum hit | 41: low drum | 42 cymbal | 43: low drum thud | 45: mid drum thud |
  46: cymbal tis | 47,48,50: high drum thud | 49: cymbal crash | 51 light cymbal hit |
  52: lite echo cymbal crash | 53: lite echo cymbal tink | 55: low cymbal hit |
  57: cymbal crash | 59: super light cymbal hit |
   */
  //endregion
  //region Bongos
  /*
  61: mid bongo hit |  62: echo bongo | 63,86,87: heavy far | 64: very heavy far |
  65: light fast hit| 66: low light fast hit|
  */
  //endregion
  //region tinks
  /*
34: metal tink | 54: tink | 67: high echo tink | 68: low echo tink | 75: little tink echo |
80: glass tink | 81: glass tink long | 85: wood tink |
  */
  //endregion
  //region Others
  /*
  27: echo doot | 28: elctro clap | 29: record scratch |  32: tiny bip |
  39: clap | 56,60,76,77: cow bell | 58: twang |
  69,82: maraca shake | 70: maraca hard shake | 71: command whistle short |
  72: command whistle long | 73: bumpy stick short |  74: bumpy stick long |
  78: funny yell high | 79: funny yell low | 83: christmas bells | 84: xylophone rift|

   */
  //endregion

//endregion


  public static void warmUpSoundBhvr() throws MidiUnavailableException{
    synth = MidiSystem.getSynthesizer();
    synth.open();
    channels = synth.getChannels();
  }

  //region
  public static void play(String note, int dur,int vol,int instrument ) throws InterruptedException, MidiUnavailableException {
    if(disableSound){return;}
    channels[instrument].noteOn(id(note), vol);
    //Thread.sleep(dur);
    channels[instrument].noteOff(id(note));
    channels[instrument].allSoundOff();
  }
  public static void play(int note, int dur,int vol,int instrument ) throws InterruptedException, MidiUnavailableException {
    if(disableSound){return;}
    channels[instrument].noteOn(note, vol);
    //Thread.sleep(dur);
    channels[instrument].allNotesOff();
    channels[instrument].allSoundOff();
  }
  /*
  public static void play(String note, int dur,int vol,int instrument ) throws InterruptedException{
    INSTRUMENT=instrument;VOLUME=vol;
    channels[INSTRUMENT].noteOn(id(note), VOLUME );
    Thread.sleep(dur);
    channels[INSTRUMENT].noteOff(id(note));
  }

   */


  public static void play(int dur) throws InterruptedException{
    channels[INSTRUMENT].noteOn(musicNote,60);
    Thread.sleep(dur);
    channels[INSTRUMENT].noteOff(musicNote);
  }
  public static int id(String note){
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