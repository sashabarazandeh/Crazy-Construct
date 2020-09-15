/* GameConstants
 * @author George and Sasha
 *  interface that includes constants used for CrazyConstruct game
 */
interface GameConstants {
  //Level names used for loading map
  static final String LEVEL_1 = "level1";
  static final String LEVEL_2 = "level2";
  static final String LEVEL_3 = "level3";
  static final String LEVEL_4 = "level4";
  static final String LEVEL_5 = "level5";
  //Spawn locations of enemies and player spawn
  static final int PLAYER_WALL_DECONSTRUCT_TIME = 1;//how long the player wall will stay up for
  static final int PLAYER_SPAWN_X = 40;
  static final int PLAYER_SPAWN_Y = 40;
  static final int PLAYER_SPEED = 20;
  static final int ENEMY_SPAWN_X = 260;//enemy spawn x coordinate
  static final int ENEMY_SPAWN_Y = 300;//enemy spawn y coordinate
  //level 1
  static final int ENEMY_SPEED_1 = 2;
  //level 2
  static final int ENEMY_SPEED_2 = 4;
  //level 3
  static final int ENEMY_SPEED_3 = 5;
  //level 4
  static final int ENEMY_SPEED_4 = 5;
  //level 5
  static final int ENEMY_SPEED_5 = 10;
    //width and height of the start window
    static final int START_W = 600;
    static final int START_H = 600;
    //width and height of the game window
    static final int GAME_W = 800;
    static final int GAME_H = 600;
    //width and height of each tile on map
    static final int BOX_W = 20;
    static final int BOX_H = 20;
    //game-specific constants
    static final int FRAME_TIME = 55;
    static final int SPRITE_UPDATE_MS = 55;
    static final int LAST_LEVEL = 5;
    static final int GAME_TIME = 300;//how many seconds the player has to beat the game
    //directions (used for loading sprite)
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    static final int SPACE_BETWEEN_BLOCKS = 20;
    //tile values for reading the map in
    static final char WALKABLE = '0';
    static final char COIN = '2';
    static final char WALL = '1';
}