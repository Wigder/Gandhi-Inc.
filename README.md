# Gandhi-Inc.

## Setup
To set up for the eclipse IDE run the command `./gradlew eclipse` in the root of the project.

## Build & Run
To run the project using gradle, in the root of the project directory run the command :
WINDOWS: `gradlew desktop:run`. 
MAC & LINUX: `./gradlew desktop:run`
This will compile and run the game.

## Compiling to JAR
To compile the project to an executable jar file you run the command 
WINDOWS: `gradlew desktop:dist`
MAC & LINUX: `./gradlew desktop:dist`
which will output a jar file called `desktop-1.0.jar` in `./desktop/build/libs/`

## Source for the Game
The source code for the game is split into 2 parts, one part being for the graphics and the user interface (i.e how the user will interact with the game), and the other part being the game logic (i.e the actual game).
### Interface Source
The interface source code exists in the file `game.java` which exists in the folder `./core/src/me/gandhiinc/blindeye/`
### Game Source
the game source consists of files `AIPlayer.java`, `GameEngine.java`, `MarketPlace.java`, `Player.java`,`Plot.java`, `Pub.java`, `Resource.java`, `Roboticon.java`. All of the source exists in the foler `./core/src/me/gandhiinc/blindeye/`
