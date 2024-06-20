package Ex5Clustering;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		bits = new ArrayList<Boolean>();
	}

	public BitArray(boolean[] bits) {
		// Use IntStream to convert boolean array to List<Boolean>
		this.bits = (ArrayList<Boolean>) IntStream.range(0, bits.length)
				.mapToObj(i -> bits[i])
				.collect(Collectors.toList());
	}

	@Override
	public double distance(BitArray other) {
		long differingBitsCount = IntStream.range(0, this.bits.size())
				.filter(i -> !this.bits.get(i).equals(other.bits.get(i)))
				.count();
		return differingBitsCount ;
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		// TODO: Complete. If the file contains bitarrays of different lengths,
		//  retain only those of maximal length
		return null;
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
