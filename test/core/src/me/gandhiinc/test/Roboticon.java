package me.gandhiinc.test;

/**
 * Roboticon class
 * Implements the roboticon class for the game
 * 
 * @author Robert
 *
 */

class Roboticon {
	enum resource {
		none, ore, energy
	}
	private static int BASEPROD = 2;
	/* Store the roboticon's base production for each resource
	 * Handling production as integers internally to avoid potential decimal inaccuracies
	 * Note that the food resource will not be implemented until Phase Three
	 */
	private int OreProd = 0;
	private int EnergyProd = 0;
	private resource Specialisation = resource.none;
	
	// Constructor for roboticons
	Roboticon () {
		
	}
	
	// Returns the base production value for the given resource
	int getBaseProd (resource resource) {
		switch (resource) {
		case ore:
			return OreProd;
		case energy:
			return EnergyProd;
		default:
			return 0;
		}
	}
	
	// Sets the base production for the given resource to the given value
	boolean setBaseProd (resource resource, int newprod) {
		if (newprod >= 0) {
			switch (resource) {
		case ore:
			this.OreProd = newprod;
			return true;
		case energy:
			this.EnergyProd = newprod;
			return true;
		default:
			return false;
		} 
		} else {
			return false;
		}
		
	}
	
	// Sets the specialisation to the given resource and sets the base production accordingly
	boolean setSpecialisation (resource resource) {
		switch (resource) {
		case none:
			this.Specialisation = resource.none;
			this.OreProd = 0;
			this.EnergyProd = 0;
			return true;
		case ore:
			this.Specialisation = resource.ore;
			this.OreProd = BASEPROD;
			this.EnergyProd = 0;
			return true;
		case energy:
			this.Specialisation = resource.energy;
			this.OreProd = 0;
			this.EnergyProd = BASEPROD;
			return true;
		default:
			return false;
		}
	}
	
	// Returns the current specialisation
	resource getSpecialisation () {
		return this.Specialisation;
	}
}
