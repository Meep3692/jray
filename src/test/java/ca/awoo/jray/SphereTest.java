package ca.awoo.jray;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SphereTest {
    @Test
    public void intersectTest(){
        Sphere sphere = new Sphere(new Vector(), 1);
        Ray ray = new Ray(new Vector(0, 0, -5), new Vector(0, 0, 1), 0);
        RayHit[] hits = sphere.hit(ray);
        assertEquals("2 hits", 2, hits.length);
        RayHit closeHit = hits[0].distance() > hits[1].distance() ? hits[1] : hits[0];
        RayHit farHit = hits[0].distance() > hits[1].distance() ? hits[0] : hits[1];
        assertEquals("close hit at 4 units", 4.0, closeHit.distance(), 0.01);
        assertEquals("far hit at 6 units", 6.0, farHit.distance(), 0.01);
    }
}
