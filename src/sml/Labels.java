package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents an instance that contains a map of Strings called "labels"
 * and its address(index) in program,
 * <p>
 * It contains methods to access and add them.
 *
 * @author ...
 */

public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		if (labels.entrySet().stream().noneMatch(i -> i.getKey().equals(label)))
			labels.put(label, address);
		else {
			System.err.println("Invalid SML program: Duplicate label '" + label + "' found at statement number " + (address + 1));
			System.exit(-1);
		}
	}

	/**
	 * Returns the address associated with the label.
	 * <p>
	 * (If label is not present, raise NullPointerException) <br>
	 * Program will not be able to jump non-existent label
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		try {
			if(labels.get(label) == null) {
				throw new NullPointerException("Invalid SML program: non-existent label: " + label);
			}
		}catch(NullPointerException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		return labels.get(label);
	}

	/**
	 * representation of this instance, 
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
	}

	
	@Override
    public boolean equals(Object o) {
        if (o instanceof Labels l) 
            return l.equals(o);
        return false;
    }

	
	@Override
    public int hashCode() {
        return labels.hashCode();
    }

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
