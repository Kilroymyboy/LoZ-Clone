package launcher;

enum Constants
{
    UP(17),
    DOWN(31),
    RIGHT(32),
    LEFT(30),

    GAMESCALE(2),
    DISPLAYWIDTH(800),
    DISPLAYHEIGHT(600),

    GAMENAME("LoZ Clone"),

    SPRITEPATH("res/sprite/"),

    MAPLOCATION("res/map/LoZmapTop.tmx"),
    MAPCOLLISION("collision"),
    MAPTOPLAYER("toplayer"),
    MAPOBJECTS("objects"),
    MAPBACKGROUND("background");

    private int value;
    private String text;

    private Constants(int value) {
        this.value = value;
    }
    private Constants(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }
    public String getText() {
        return text;
    }
}