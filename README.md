# cse237-project
This project aims to create a fully-playable virtual poker game, where a user can play against the computer in one-on-one no-limit Texas Hold'em.

Iteration 3:

- What user stories were completed this iteration?

In our final iteration, we focused on increasing the functionality of the GUI. Now, the user can play the game using buttons on the left side of the screen. We updated the visuals, which now include the title of the game and an updating pot counter in the middle of the screen. On the bottom of the GUI, there is an automatically updating history, which displays the last few game actions. Finally, we added a little bit of user customization. When the user runs the game, he or she can choose the amount of money that both players will begin with. 

- Is there anything that you implemented but doesn't currently work?

At this time, we are not aware of any major bugs or issues. If a user enters in unexpected input, such as a String when the game asks for a number, the program may crash.

- What commands are needed to compile and run the code?

java -cp ./PokerTable.jar gui.PokerTable
