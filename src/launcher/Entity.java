package launcher;

import org.newdawn.slick.Animation;

public class Entity
{
    protected Animation curAnim;
    protected int xPos, yPos;
    protected int HitBoxUpDownX;
    protected int HitBoxUpDownY;
    protected int HitBoxLeftX;
    protected int HitBoxLeftY;
    protected int HitBoxRightX;
    protected int HitBoxRightY;

    Entity() {

    }

    public void render()
    {
        curAnim.draw(xPos, yPos);
    }

    public void update(int delta, float mapOffsetX, float mapOffsetY)
    {
        curAnim.update(delta);

        HitBoxUpDownX= (int) ((xPos+3 - mapOffsetX)/8);
        HitBoxUpDownY = (int) ((yPos+25 - mapOffsetY)/8);
        HitBoxRightX = (int) ((xPos+1 - mapOffsetX)/8);
        HitBoxRightY = (int) ((yPos+20 - mapOffsetY)/8);
        HitBoxLeftX = (int) ((xPos-1 - mapOffsetX)/8);
        HitBoxLeftY = (int) ((yPos+20 - mapOffsetY)/8);
    }

    public int getXHitbox()
    {
        return HitBoxUpDownX;
    }
    public int getYHitbox()
    {
        return HitBoxUpDownY;
    }
    public int getLXHitbox()
    {
        return HitBoxLeftX;
    }
    public int getLYHitbox()
    {
        return HitBoxLeftY;
    }
    public int getRXHitbox()
    {
        return HitBoxRightX;
    }
    public int getRYHitbox()
    {
        return HitBoxRightY;
    }


}
