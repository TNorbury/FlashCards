# FlashCards
Program that creates a collection of flash cards in order to aid with studying

# How to run
Compile the file using `javac` and then run using `java`

Pass the COMPLETE* file path of you txt file with questions and answers as the parameter. 

Windows:  `java FlashCards "M:\School\Class\FlashCardNotes.txt"` 

Bash:     `$java FlashCards "/home/tmoney"`

#FILE FORMAT
The file format should be as follows:

question1:answer1

question2:answer2

...

questionN:answerN

The program uses UTF-8 character set so some characters (such as quotation marks) will appear as "?". This is something that I'll get around to working on (see To-Do)

If your file doesn't follow that format your cards will probably come out weird.




#To-Do
1. Improve parser  
  * Some characters (such as numbers or quotation marks) cause the parser to split up cards in a weird way
2. Make a GUI  
  * This would also include a file browser, at some point, so the user wouldn't have to 
3. Add a write/wrong discrimination  
  * User would indicate if they got a card write or wrong, and depending on the answer the cards will be put into one of two collections, and on the next pass through the cards, only the "wrong" cards will be displayed
4. Create an executable so user doesn't have to compile code
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
*Might work with relative file paths, haven't tested that though
