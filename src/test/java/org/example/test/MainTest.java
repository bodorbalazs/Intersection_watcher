package org.example.test;

import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void exampleTest() {
        String[] args = {"_","CL","CS","CR"};
        Main.main(args);
        assertEquals("LRB", outContent.toString());
    }

    @Test
    public void onlyStraightTest() {
        String[] args = {"CS","CS","CS","CS"};
        Main.main(args);
        assertEquals("TRBL", outContent.toString());
    }
    @Test
    public void ambulanceTest() {
        String[] args = {"CS","CS","CS","AS"};
        Main.main(args);
        assertEquals("LTRB", outContent.toString());
    }
    @Test
    public void hardSituationTest1() {   // from random online tests
        String[] args = {"CS","CS","CS","CR"};
        Main.main(args);
        assertEquals("LTRB", outContent.toString());
    }

    @Test
    public void hardSituationTest2() {   // from random online tests
        String[] args = {"_","CS","CL","CS"};
        Main.main(args);
        assertEquals("RBL", outContent.toString());
    }
    @Test
    public void oneCar(){
        String[] args = {"_","_","_","CS"};
        Main.main(args);
        assertEquals("L", outContent.toString());
    }



}
