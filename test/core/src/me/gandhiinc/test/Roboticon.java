package me.gandhiinc.test;

/**
 * Roboticon class
 * Implements the roboticon class for the game
 * 
 * @author Robert
 *
 */
class Roboticon {
	// Setting the standard production rate as a constant for reference during specialisation
	private static int BASEPROD = 2;
	/* Store the roboticon's base production for each resource
	 * Handling production as integers internally to avoid potential decimal inaccuracies
	 * Note that the food resource will not be implemented until Phase Three
	 */
	private int OreProd = 0;
	private int EnergyProd = 0;
	private Resource Specialisation = Resource.NONE;
	
	/**
	 * Constructor for roboticons
	 */
	Roboticon () {
		
	}
	
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
	
	/**
	 * Takes an enumerated resource type and sets the specialisation
	 * and base production rates accordingly
	 * 
	 * @param resource	the resource being set as an enumerated type
	 * @return			a boolean for success of the operation
	 */
	boolean setSpecialisation (Resource resource) {
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
	Resource getSpecialisation () {
		return this.Specialisation;
	}
}
