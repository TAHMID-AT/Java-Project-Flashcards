# Java-Project-Flashcards
Project where I learned to use Map and implemented it for the first time. Followed the learning path on Java from JetBrains Academy. Would like to return once I know something about adding graphics

On running, asks for input in String what action to take. It's possible to pass command line arguments to import a file and also export to a file after exiting the program.

The functions available are -
  1. add - adds a card to the exisitng list of cards. If the card exists, it cannot be added.
  2. remove - removes a card. Displays a message if the requested card doesn't exist
  3. import - import a file where the data is in the format- card<Tab>definition<Tab>mistakeCount. If the file is unavailable, relevant message is printed.
  4. export - exports to a file in the same format as mentioned above.
  5. ask - prints the card and asks its' definition. If the definition is wrong for printed card, but correct for another, that card is printed out. 
  6. log - writes all the lines, both input and output from the console to a file.
  7. hardest card - prints the card which has the highest mistakeCount. If multiple cards have same number of mistakes, all of them are printed along with mistakeCount
  8. reset stats - resets the mistake count for all cards to 0
  9. exit - exits the program

New functions that can be implemented - 
  1. Randomize the order in which the cards are asked
  2. Add some interactivity/graphics
