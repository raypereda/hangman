def combinations(iterable, r):
    # combinations('ABCD', 2) --> AB AC AD BC BD CD
    # combinations(range(4), 3) --> 012 013 023 123
    pool = tuple(iterable)
    n = len(pool)
    if r > n:
        return
    indices = range(r)
    yield tuple(pool[i] for i in indices)
    while True:
        for i in reversed(range(r)):
            if indices[i] != i + n - r:
                break
        else:
            return
        indices[i] += 1
        for j in range(i+1, r):
            indices[j] = indices[j-1] + 1
        yield tuple(pool[i] for i in indices)

print "here"

def next_combo(indices_in, n):
	# copy indices
	indices = indices_in[:]
	r = len(indices)
	
	# find the first index from the right
	# that we can slide over one space 
	i = r - 1
	while i >= 0:
	    if indices[i] != i + n - r:
	        break
	    i -= 1
	if i == -1:
	    return None
	indices[i] += 1

	# for j in range(i+1, r):
	j = i+1
	while j < r:
		indices[j] = indices[j-1] + 1
		j += 1
		
	return indices


iterable = 'ABCD'
r = 2

pool = tuple(iterable)

n = len(pool)
if r > n:
    print "awe shucks"
indices = range(r)

print "indices "
print indices

indices = next_combo(indices, len(iterable))
while indices != None:
	print indices
	indices = next_combo(indices, len(iterable))
	
	


