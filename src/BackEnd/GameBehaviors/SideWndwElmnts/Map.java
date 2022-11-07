package BackEnd.GameBehaviors.SideWndwElmnts;
//TODO Later implement map
public class Map {
  public static char[][] curMap = new char[][]{
      {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},//0
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//1 Start of Mpa
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//2
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//3
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//4
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//5
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//6
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//7
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//8
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//9
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//10 End of Map
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//11
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//12
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//13
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//14
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//15
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//16
      {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','|'},//17
      {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','|'},//18
  };

  //Notes on how to create map
  /*
  The plan
  -Levels will hold information of what details it contains; ex -> stores/place of interests/
  -Levels and lev info will not display if lev has not been discovered
  -does map change when in sublevel?
    -if so can make a file that stores all the map icons and etc and when level switches to sublevel looks for that map
  -Ideas of multipages
    -can go down to see more
    -can go left to check the details of the level (multiple pages)
      -seperate pages for different object's ex: places/people/Items/Needs/area description...
      -press enter on selected level of and shows details of that map
        -does it load in the level from saved file?
  -Ideas on how to get details of thing of interest
    -go through all objects and ask for "isNPC" & if so ask for sym & name
  -

  NOTE: when adding name see if name is longer than box it contains and on last letter put a '-'

  Can generate the whole overworld map by having level hold sym
    -do i make a file the has the overworld map icon locations?
  */
  public static void generateMap(){ //this needs to be after the levels are loaded in

  }

  /* SCRAPPED

  public static void moveMap(String direction){
    switch(direction){
      case "UP"->{
        for(byte r=1;r< curMap.length-2;r++){
          for(byte c=0;c< curMap[r].length-1;c++){

          }
        }
        for(byte i=0;i< curMap.length-1;i++){
          curMap[curMap.length-1][i]=' ';
        }
      }
      case "DOWN"->{
        for(byte r=1;r< curMap.length-2;r++){
          for(byte c=0;c< curMap[r].length-1;c++){

          }
        }
        for(byte i=0;i< curMap.length-1;i++){
          curMap[curMap.length-1][i]=' ';
        }
      }
    }

  }

   */

}
