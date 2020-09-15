/*
 * MapStorage.java
 * Sasha Barazandeh
 * 2020-01-17
 */
public class MapStorage implements GameConstants {
    private int currentLevel;
    /* MapStorage constructor
     * @param int levelNum
     * This constructor takes in the given level and makes it the current level
     */
    public MapStorage(int levelNum) { 
        currentLevel = levelNum;
    }
    /* updateLevel
     * @param int levelNum
     * This method updates the current level of the map being chosen
     */
    public void updateLevel(int levelNum) {
        currentLevel = levelNum;
    }
    /* chooseMap
     * @returns String value
     * This method returns the string name for a textfile to be read depending on the current level
     */
    public String chooseMap() {
        if (currentLevel == 1) {
            return LEVEL_1;
        }else if (currentLevel == 2) {
            return LEVEL_2;
        }else if (currentLevel == 3) {
            return LEVEL_3;
        }else if (currentLevel == 4) {
            return LEVEL_4;
        }else {
            return LEVEL_5;
        }
    }
    
}
