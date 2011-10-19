# here goes
from itertools import *

#read the words in the dictionary

f = open('/Users/ray/hangman/words.txt', 'r')

lines = f.readlines()

for i in xrange(len(lines)):
	lines[i] = lines[i].strip()
	
dictionary = frozenset(lines)


cost = {
'a': 1, 
'b': 4, 
'c': 4, 
'd': 2, 
'e': 1,
'f': 4, 
'g': 3, 
'h': 3, 
'i': 1, 
'k': 5,
'l': 2, 
'm': 4, 
'n': 2, 
'o': 1, 
'p': 4,
'q': 10, 
'r': 1, 
's': 1, 
't': 1, 
'u': 2,
'v': 5,
'w': 4,
'x': 8,
'y': 3,
'z': 10,
}
	

def word_cost(s):
	global cost
	sum = 0
	i = 0
	for l in s:
		i += 1
		if i == 8:
			sum += 3*cost[l]
		else:
			sum += cost[l]
	if len(s) < -1:
		sum *= 3
	return sum
	
s = "afilnorstuxy"

for length in xrange(4, 9):
	p = permutations(list(l for l in s), length)

	for letter_list in p:

		word = ""
		for letter in letter_list:
			word += letter
	
		if word not in dictionary:
			continue
			
		c = word_cost(word)
		print "%03d  %s" % (c, word)	

