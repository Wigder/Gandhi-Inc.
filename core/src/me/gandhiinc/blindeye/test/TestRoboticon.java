package me.gandhiinc.blindeye.test;

import static org.junit.Assert.*;

import org.junit.Test;

import me.gandhiinc.blindeye.Roboticon;
import me.gandhiinc.blindeye.Resource;
import me.gandhiinc.blindeye.Plot;

public class TestRoboticon {

	@Test
	public void testRoboticon() {
		Roboticon LabRat = new Roboticon();
		assertEquals(LabRat.getBaseProd(Resource.ENERGY), 0);
		assertEquals(LabRat.getBaseProd(Resource.ORE), 0);
		assertEquals(LabRat.getSpec(), Resource.NONE);
		assertEquals(LabRat.getPlot(), null);
	}

	@Test
	public void testGetBaseProd() {
		Roboticon LabRat = new Roboticon();
		int newenergy = 37;
		int newore = 28;
		try {
			LabRat.setBaseProd(Resource.ENERGY, newenergy);
			assertEquals(LabRat.getBaseProd(Resource.ENERGY), newenergy);
			LabRat.setBaseProd(Resource.ORE, newore);
			assertEquals(LabRat.getBaseProd(Resource.ORE), newore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetBaseProd_FailResource() {
		Roboticon LabRat = new Roboticon();
		LabRat.getBaseProd(Resource.NONE);
	}
	
	@Test
	public void testSetBaseProd() {
		Roboticon LabRat = new Roboticon();
		int newenergy = 37;
		int newore = 28;
		try {
			LabRat.setBaseProd(Resource.ENERGY, newenergy);
			assertEquals(LabRat.getBaseProd(Resource.ENERGY), newenergy);
			LabRat.setBaseProd(Resource.ORE, newore);
			assertEquals(LabRat.getBaseProd(Resource.ORE), newore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetBaseProd_FailResource() {
		Roboticon LabRat = new Roboticon();
		int newnone = 16;
		LabRat.setBaseProd(Resource.NONE, newnone);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetBaseProd_FailNegative() {
		Roboticon LabRat = new Roboticon();
		int newenergy = -7;
		LabRat.setBaseProd(Resource.ENERGY, newenergy);
	}
	
	@Test
	public void testSetSpec() {
		Roboticon LabRat = new Roboticon();
		
		try {
		LabRat.setSpec(Resource.NONE);
		assertEquals(LabRat.getSpec(), Resource.NONE);
		assertEquals(LabRat.getBaseProd(Resource.ENERGY), 0);
		assertEquals(LabRat.getBaseProd(Resource.ORE), 0);
		
		LabRat.setSpec(Resource.ENERGY);
		assertEquals(LabRat.getSpec(), Resource.ENERGY);
		assertEquals(LabRat.getBaseProd(Resource.ENERGY), 20);
		assertEquals(LabRat.getBaseProd(Resource.ORE), 0);
		
		LabRat.setSpec(Resource.ORE);
		assertEquals(LabRat.getSpec(), Resource.ORE);
		assertEquals(LabRat.getBaseProd(Resource.ENERGY), 2);
		assertEquals(LabRat.getBaseProd(Resource.ORE), 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSpec() {
		Roboticon LabRat = new Roboticon();

		try {
			LabRat.setSpec(Resource.ENERGY);
			assertEquals(LabRat.getSpec(), Resource.ENERGY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetPlot() {
		Roboticon LabRat = new Roboticon();
		Plot TestPlot = new Plot(0,0,0,0);
		
		try {
			LabRat.setPlot(TestPlot);
			assertEquals(LabRat.getPlot(), TestPlot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPlot() {
		Roboticon LabRat = new Roboticon();
		Plot TestPlot = new Plot(0,0,0,0);
		
		try {
			LabRat.setPlot(TestPlot);
			assertEquals(LabRat.getPlot(), TestPlot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCalcProd() {
		Roboticon LabRat = new Roboticon();
		Plot TestPlot = new Plot(0,20,40,0);
		int PlayerModEnergy = 25;
		int PlayerModOre = 15;
		LabRat.setPlot(TestPlot);
		
		try {
			int expResult = 20*TestPlot.getEnergyMod()*PlayerModEnergy;
			LabRat.setSpec(Resource.ENERGY);
			for (int i=0;i<20;i++){
			int actualResult = LabRat.calcProd(Resource.ENERGY, PlayerModEnergy);
			assertTrue((expResult-5) <= actualResult);
			assertTrue((expResult+5) >= actualResult);
			}
			expResult = 20*TestPlot.getOreMod()*PlayerModOre;
			LabRat.setSpec(Resource.ORE);
			for (int i=0;i<20;i++){
				int actualResult = LabRat.calcProd(Resource.ORE, PlayerModOre);
				assertTrue((expResult-5) <= actualResult);
				assertTrue((expResult+5) >= actualResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
