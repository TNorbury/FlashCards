# FlashCards
Program that creates a collection of flash cards in order to aid with studying

# How to run
Compile src/FlashCard.java using `javac` and then run it using `java`

Pass the file path of your text file with questions and answers as the parameter. 

Windows:  `java FlashCards M:\School\Class\FlashCardNotes.txt` 

Bash:     `$java FlashCards /home/tmoney/FlashCardNotes.txt`

# FILE FORMAT
The file format should be as follows:

question1:answer1

question2:answer2

...

questionN:answerN


If your file doesn't follow that format your cards will probably come out weird or the program will crash


#To-Do
1. Make a GUI  
  * This would also include a file browser, at some point, so the user wouldn't have to 
2. Add a write/wrong discrimination  
  * User would indicate if they got a card right or wrong, and depending on the answer the cards will be put into one of two collections, and on the next pass through the cards, only the "wrong" cards will be displayed
3. Create an executable so user doesn't have to compile code
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>  
<br/>
<br/>
<br/>
<br/>
