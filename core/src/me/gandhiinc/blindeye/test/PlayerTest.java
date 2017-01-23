package me.gandhiinc.blindeye.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import me.gandhiinc.blindeye.Player;
import me.gandhiinc.blindeye.Plot;
import me.gandhiinc.blindeye.Roboticon;

public class PlayerTest {

    @Test
    public void testPlayer() {
        String desiredName = "Hello World";
        int desiredMoney = 10;
        int desiredOre = 17;
        int desiredEnergy = 20;
        Player testPlayer = new Player(desiredName, desiredMoney, desiredOre, desiredEnergy);
        assertEquals(testPlayer.getName(), desiredName);
        assertEquals(testPlayer.getMoney(), desiredMoney);
        assertEquals(testPlayer.getOre(), desiredOre);
        assertEquals(testPlayer.getEnergy(), desiredEnergy);
        assertEquals(testPlayer.getRoboticons(), new ArrayList<Roboticon>());
        assertEquals(testPlayer.getPlots(), new ArrayList<Plot>());
    }

    @Test
    public void testAquirePlot() {
        Plot desiredPlot = new Plot(0, 0, 0, 0);
        Player testPlayer = new Player("", 0, 0, 0);
        try {
            testPlayer.AcquirePlot(desiredPlot);
            ArrayList<Plot> plots = testPlayer.getPlots();
            assertEquals(plots.contains(desiredPlot), true);
        } catch (Exception e) {
            fail("Unexpected Excpetion thrown: " + e.getMessage() + "\n Stack Trace: " + e.getStackTrace());
        }

    }

    @Test
    public void testSpecialiseRoboticon() {
        fail("Not yet implemented");
    }

    @Test
    public void testProduceResources() {
        fail("Not yet implemented");
    }
}