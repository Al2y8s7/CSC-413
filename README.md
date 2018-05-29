#csc413-03-TankGame- Team 12

Authors: Alvin Nguyen, Moses Martinez

This repository contains all documentation and resources written/provided by the authors and is therefore only used for 

reference purposes as proof of work completed during Undergraduate Studies (Computer Science) at San Francisco State 

University.

The source code reflect the following topics covered in CSC 413 - Software Development: Data Structures, Encapsulation, 

Inheritance, Polymorphism and Object Oriented Programming (OOP) using the Observation Design Pattern.

****** Instructions ******

Note: This game was written in the Java Programming Language (Java 8) using NetBeans IDE.  

The working directory for the game: /csc413-03-tankgame-Team12/src/TankWars


****** How to Play *****

The object of the game is to eliminate the other player by shooting them and reducing his/her health to 0.  Once a player's 

health reaches 0, a life is lost and the player respawns back to their original coordinates.  Each player has 3 lives, the 

player who loses all 3 lives loses the game.


****** Controls ******

Player 1 (Left Screen):

Up Arrow - Move forward
Down Arrow - Move backward
Left Arrow - Rotate left
Right Arrow - Rotate right
Enter - Shoot

Player 2 (Right Screen):

W - Move forward
A - Move backward
S - Rotate left
D - Rotate right
Space - Shoot


****** How to run the game ******

Option 1:

After dowloading the repository from GitHub, create a new project in NetBeans with existing Resources.  Select the project 

and right click, choose clean and build. Run the project by pressing the green play button located at the toolbar section or 

right clicking on the project and selecting "Run."


Option 2:

Navigate to the dist folder under the project directory. Double click on the TankWars executable jar (TankWars.jar) file or 

right click and select "Open."


!!! Important Notes !!!

1) The split screen functionality was not correctly implemented.  Refer to GameWorld.java under SplitScreen() method located 

at line 170.  

The follow problems occured during implementation:

a) Unable to follow the tanks (camera) without either receiving a "...Y + height out of raster exception" when moving to the 

far top or lower half of the screen

b) Painting the camera views of the tank are only draw to half of the screen

Please uncomment lines 178 - 180 under GameWorld.java:

//tank1View = gameMap.getSubimage(tankX1bounds, tankY1bounds, WINDOW_WIDTH / 2 - 21, WINDOW_HEIGHT /2);
//tank2View = gameMap.getSubimage(tankX2bounds, tankY2bounds, WINDOW_WIDTH / 2 - 22, WINDOW_HEIGHT /2);
//tankViewBounds();

2) The tanks can get stuck during  while colliding against the Normal walls.  If this occurs during gameplay, rotate in the 

direction opposite of the wall

3) Both tanks can collided with one another and will be pushed back.  However, if both players simultaneously continue to 

collide with each, one of the tanks will be able move on top of the other
