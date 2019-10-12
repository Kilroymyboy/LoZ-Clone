package launcher;

public class Vector
{
    public int x, y;
    public Vector()
    {
        x = 0;
        y = 0;
    }

    public Vector(int x2, int y2)
    {
        x = x2;
        y = y2;
    }

    public Vector(int v)
    {
        x = v;
        y = v;
    }

    public void set(int x2, int y2)
    {
        x = x2;
        y = y2;
    }
}
