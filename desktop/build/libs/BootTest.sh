#This code will run the program 10 times, however it will not close the aplication after it has run them.
# this means that you should run this code, close the 10 that open it and then run it again for a total of 5 times. 
#this means that you will have booted the program 50 times, thus testing our requirement. 
#This is a shell script to be run on a linux machine.


for X in 0 1 2 3 4 5 6 7 8 9
do 
java -jar bu.jar&
done 
