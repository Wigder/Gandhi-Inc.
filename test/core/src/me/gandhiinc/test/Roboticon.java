package me.gandhiinc.test;

import java.util.Random;

/**
 * Roboticon class
 * Implements the roboticon class for the game
 * 
 * @author Robert Sayles
 * @version 1.0
 */
class Roboticon {
	/* Setting the standard production rate as a constant for reference during specialisation
	 * Handling production as integers internally (to avoid potential decimal inaccuracies, etc)
	 */
	private static int BASEPROD = 20;
	/* Store the roboticon's base production for each resource
	 * Note that the food resource will not be implemented until Phase Three
	 */
	private int OreProd = 0;
	private int EnergyProd = 0;
	/* Store the specialisation of the roboticon as the enumerated resource type
	 * Store the assigned plot object for reference for production modifiers
	 */
	private Resource Specialisation = Resource.NONE;
	private Plot AssignedPlot = null;
	// Initialise random numbers for production variance
	Random rnd = new Random();
	
	/**
	 * Constructor for roboticons
	 */
	Roboticon () {
		
	}
	
	/* -----------------------------
	 * --- Base Production Rates ---
	 * -----------------------------
	 */
	/**
	 * Returns the base production value for a given resource
	 * 
	 * @param resource	the resource for which the base production is being requested as an enumerated type
	 * @return			the production rate for requested resource as an integer
	 */
	int getBaseProd (Resource resource) {
		switch (resource) {
		case ORE:
			return OreProd;
		case ENERGY:
			return EnergyProd;
		default:
			return 0;
		}
	}
	
	/**
	 * Sets the base production for the given resource to the given value
	 * 
	 * @param resource	the resource for which the base production rate is being set as an enumerated type
	 * @param newprod	the new production rate as an integer
	 * @return			a boolean for success of the operation
	 */
	boolean setBaseProd (Resource resource, int newprod) {
		if (newprod >= 0) {
			switch (resource) {
		case ORE:
			this.OreProd = newprod;
			return true;
		case ENERGY:
			this.EnergyProd = newprod;
			return true;
		default:
			return false;
		} 
		} else {
			return false;
		}
		
	}
	
	/* ----------------------
	 * --- Specialisation ---
	 * ----------------------
	 */
	/**
	 * Takes an enumerated resource type and sets the specialisation
	 * and base production rates accordingly
	 * 
	 * @param resource	the resource being set as an enumerated type
	 * @return			a boolean for success of the operation
	 */
	boolean setSpec (Resource resource) {
		switch (resource) {
		case NONE:
			this.Specialisation = Resource.NONE;
			this.OreProd = 0;
			this.EnergyProd = 0;
			return true;
		case ORE:
			this.Specialisation = Resource.ORE;
			this.OreProd = BASEPROD;
			this.EnergyProd = 0;
			return true;
		case ENERGY:
			this.Specialisation = Resource.ENERGY;
			this.OreProd = 0;
			this.EnergyProd = BASEPROD;
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * Returns the current specialisation as an enumerated resource type
	 * 
	 * @return 			the current specialisation as an enumerated resource type
	 */
	Resource getSpec () {
		return this.Specialisation;
	}
	
	/* ---------------------
	 * --- Assigned Plot ---
	 * ---------------------
	 */
	/**
	 * Set the plot the roboticon is assigned to
	 * 
	 * @param plot		the plot object being assigned
	 * @return			a boolean for success of the operation
	 */
	boolean setPlot (Plot plot) {
		AssignedPlot = plot;
		return true;
	}
	
	/**
	 * Get the currently assigned plot object
	 * 
	 * @return			returns the currently assigned plot object
	 */
	Plot getPlot () {
		return this.AssignedPlot;
	}
	
	/* --------------------------------------------
	 * --- Calculating Modified Production Rate ---
	 * --------------------------------------------
	 */
	/**
	 * Calculate the modified production rate for a given resource
	 * 
	 * @param resource	the resource for which production is to be calculated as an enumerated type
	 * @param playermod	the production modifiers on the player for that resource (eg. from events)
	 * @return			returns the modified production rate for the given resource
	 */
	int calcProd (Resource resource, int playermod) {
		int plotmod = 0;
		if (this.AssignedPlot != null) {
				plotmod = AssignedPlot.getModifiers(resource);
		}
		int variance = rnd.nextInt(11)-5;
		int finalprod = (this.getBaseProd(resource)+variance) * plotmod * playermod;
		return finalprod;
	}
}
