package hangman;

import java.util.Arrays;

/**
 * This class provides an array representation of a string or word.
 * It is also implements hashCode() and equals() so it can function as
 * a key in a HashMap.
 */
public class CharArray {
	public final char[] c;
	
	public CharArray(char[] c) {
		this.c = c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(c);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CharArray)) {
			return false;
		}
		CharArray other = (CharArray) obj;
		if (!Arrays.equals(c, other.c)) {
			return false;
		}
		return true;
	}	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CharArray [c=" + Arrays.toString(c) + "]";
	}
}