package combinations;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class provides an Iterable interface to generating the combinations
 * of a set of items of type T.
<pre>
// This code shows how to use the combinations generator for 
// a list of letters. The combinations are of size 3.
List<Character> l = new LinkedList<Character>();
l.add('A');
l.add('B');
l.add('C');
l.add('D');
l.add('E');

int r = 3;
Character[] items = l.toArray(new Character[l.size()]);
Combos<Character> combos = new Combos<Character>(items, r);		

for (Set<Character> c : combos) {
	System.out.println(c);
}
</pre>

 * @param <T> The type of the items from which combinations will be made. <br>
 */
public class Combos<T> implements Iterable<Set<T>> {
	private T[] items;
	private final int r;
	private final int n;

	/**
	 * @param items the items from which to make a combination
	 * @param r choosing r items at a time
	 */
	public Combos(T[] items, int r) {
		this.items = items;
		this.n = items.length;
		this.r = r;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Set<T>> iterator() {
		return new Iterator<Set<T>>() {
			private int[] indices = Combinations.initialCombo(r);

			@Override
			public boolean hasNext() {
				return indices != null;
			}
			@Override
			public Set<T> next() {
				int[] indicesOld = indices;
				this.indices = Combinations.nextCombo(this.indices, n);
				return select(indicesOld, items);
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	
	/**
	 * @param indices specifies a combination
	 * @param items the items from which to make a combination
	 * @return a set representing a combination of items
	 */
	private Set<T> select(int[] indices, T[] items) {
		Set<T> selectedSet = new TreeSet<T>();
		for (int i : indices) {
			selectedSet.add(items[i]);
		}
		return selectedSet;
	}		
	
	public static void main(String[] args) {
		// This method show how to use the combinations generator for 
		// a list of letters. The combinations are of size 3.
		List<Character> l = new LinkedList<Character>();
		l.add('A');
		l.add('B');
		l.add('C');
		l.add('D');
		l.add('E');

		int r = 3;
		Character[] items = l.toArray(new Character[l.size()]);
		Combos<Character> combos = new Combos<Character>(items, r);		
	
		for (Set<Character> c : combos) {
			System.out.println(c);
		}
	}
}