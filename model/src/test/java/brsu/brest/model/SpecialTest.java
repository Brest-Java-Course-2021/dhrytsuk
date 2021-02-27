package brsu.brest.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialTest {

    @Test
    public void getSpecialNameConstructor() {
        Special special = new Special("Математика и компьютерные науки");
        Assert.assertEquals("Математика и компьютерные науки", special.getSpecialName());
    }

    @Test
    public void getSpecialNameSetter() {
        Special special = new Special();
        special.setSpecialName("Математика и компьютерные науки");
        Assert.assertEquals("Математика и компьютерные науки", special.getSpecialName());
    }
}