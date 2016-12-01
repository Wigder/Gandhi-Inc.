package me.gandhiinc.blindeye.test;

import static org.junit.Assert.*;

import org.junit.Test;

import me.gandhiinc.blindeye.Roboticon;

import me.gandhiinc.blindeye.Resource;

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
		int newnone = 16;
		LabRat.setBaseProd(Resource.NONE, newnone);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetBaseProd_FailNegative() {
		Roboticon LabRat = new Roboticon();
		int newenergy = -7;
		LabRat.setBaseProd(Resource.NONE, newenergy);
	}

	@Test
	public void testSetBaseProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSpec() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSpec() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcProd() {
		fail("Not yet implemented");
	}

}
