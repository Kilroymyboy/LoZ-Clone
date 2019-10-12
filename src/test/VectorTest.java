package test;

import launcher.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class VectorTest
{
    @org.junit.jupiter.api.Test
    void inits()
    {
        Vector vector1 = new Vector();
        assert(vector1.x == 0 && vector1.y == 0);

        Vector vector2 = new Vector(30);
        assert(vector2.x == 30 && vector2.y == 30);

        Vector vector3 = new Vector(5,7);
        assert(vector3.x == 5 && vector3.y == 7);

    }

    @org.junit.jupiter.api.Test
    void set()
    {
        Vector vector = new Vector();
        vector.set(10,20);
        assert(vector.x == 10 && vector.y == 20);
    }



}