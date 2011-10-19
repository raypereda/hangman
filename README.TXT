This java program performs tests on strategies
for playing the pencil and paper game hangman.

Ray Pereda
raypereda@gmail.com

Running the program
===================

$ java -jar hangman.jar

Usage: java -jar hangman.jar [--OPTION] ...
Score a set of words for a hangman strategy.
Example 1: java -jar hangman.jar --valid-words=words.txt --random-seed=7 --random-test-size=100
Example 2: java -jar hangman.jar --valid-words=words.txt --max-wrong-guesses=5 --words-to-score=score.txt

Required Option:
  --valid-words=FILE        obtain list of valid words from FILE

Not Required Options:
  --max-wrong-guesses=NUM   set maximum number of wrong guesses to NUM. Defaults to 5.
  --look-ahead=NUM          set number of guesses to look ahead to NUM. Defaults to 1.
  --help                    display this help and exit

Option 1: For scoring a set of randomly selected words:
  --random-seed=NUM         set the random seed generator to NUM. Defaults to 0.
  --random-test-size=NUM    select NUM random words from valid words file for test.

Option 2: For scoring a set of preselected words:
  --words-to-score=FILE     obtain list of words to score from FILE


Note that the look-ahead option is experimental and doesn't improve the guessing
scores. The default value of 1 will typically yield just as good results as other
lookahead values. The runtime of the program typically increases exponentially with
the number of guesses looked ahead.


List of Files
=============
README.TXT - this file
hangman.jar - executable jar file
src - folder with source files
score.txt - a sample list of words for testing
words.txt - a list of valid words, aka a dictionary
output-example1.txt - sample ouput made using the command-line below
  $ java -jar hangman.jar --valid-words=words.txt --random-seed=0 --random-test-size=100 > output-example1.txt
output-example2.txt - sample output made using the command-line below
  $ java -jar hangman.jar --valid-words=words.txt --max-wrong-guesses=5 --words-to-score=score.txt > output-example2.txt
design-notes.txt - some design notes, showing the progression of ideas for better strategies.


Below is a list of the source files.
$ ls -1R src
combinations
hangman

src/combinations:
Combinations.java
Combos.java

src/hangman:
AbstractRunConfiguration.java
AbstractScoring.java
CharArray.java
ComboAndEntropy.java
CommandLineException.java
CommandLineParser.java
EntropyGuessingStrategy.java
FileReader.java
FixedWordsRunConfiguration.java
FixedWordsScoring.java
Guess.java
GuessLetter.java
GuessWord.java
GuessingStrategy.java
HangmanGame.java
Math2.java
Partition.java
RandomWordsRunConfiguration.java
RandomWordsScoring.java
RunConfiguration.java
Scoreable.java
Timer.java