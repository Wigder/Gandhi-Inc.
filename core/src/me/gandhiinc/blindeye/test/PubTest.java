/*url: http://gandhi-inc.me/downloads/blindeye.jar*/
package me.gandhiinc.blindeye.test;

import me.gandhiinc.blindeye.Pub;


/*
//these tests should all be aproxamatly equal to 0
*/

public class PubTest{
	public static void main(String args[]){
		Pub instance = new Pub();
        int counter = 0;
        int totalTests = 1000000000;
        double runningTotal = 0;
        while(counter < totalTests){
        	try{
        	runningTotal += instance.playScratchcard();
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("Error");
        	}
        }
        double average = runningTotal / totalTests;
        System.out.println(average);
        
        
        counter = 0;
        runningTotal = 0;
        while(counter < totalTests){
        	try{
        	runningTotal += instance.playLottery(1,2,3);
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("Error");
        	}
        }
        System.out.println(runningTotal / totalTests);
        
        
        counter = 0;
        runningTotal = 0;
        while(counter < totalTests){
        	try{
        	runningTotal += instance.playOneArmBandit()[3];
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("Error");
        	}
        }
        System.out.println(runningTotal / totalTests);
        
        
        
        
	}
}
