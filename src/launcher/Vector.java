package launcher;

public class Vector
{
    public int x, y;
    Vector()
    {
        x = 0;
        y = 0;
    }

    Vector(int x2, int y2)
    {
        x = x2;
        y = y2;
    }

    Vector(int v)
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
