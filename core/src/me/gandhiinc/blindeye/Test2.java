package me.gandhiinc.blindeye;

public class Test2{
	public static void main(String args[]){
		Pub instance = new Pub();
        int counter = 0;
        int stupidVariableNameToPissOffBen = 100000000;
        double runningTotal = 0;
        while(counter < stupidVariableNameToPissOffBen){
        	try{
        	runningTotal += instance.playScratchcard();
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("hello");
        	}
        }
        double average = runningTotal / stupidVariableNameToPissOffBen;
        System.out.println(average);
        
        
        counter = 0;
        stupidVariableNameToPissOffBen = 100000000;
        runningTotal = 0;
        while(counter < stupidVariableNameToPissOffBen){
        	try{
        	runningTotal += instance.playLottery(1,2,3);
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("hello");
        	}
        }
        System.out.println(runningTotal / stupidVariableNameToPissOffBen);
        
        
        counter = 0;
        stupidVariableNameToPissOffBen = 100000000;
        runningTotal = 0;
        while(counter < stupidVariableNameToPissOffBen){
        	try{
        	runningTotal += instance.playOneArmBandit()[3];
        	counter += 1;
        	}
        	catch(Exception IlligalArgumentException){
        		System.out.println("hello");
        	}
        }
        System.out.println(runningTotal / stupidVariableNameToPissOffBen);
        
        
        
        
	}
}
