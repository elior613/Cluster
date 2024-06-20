package Ex5Clustering;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.lang.Math;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		String[] parts = str.split(",");
		x = Double.parseDouble(parts[0]);
		y = Double.parseDouble(parts[1]);
	}
	public TwoDPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public double distance(TwoDPoint other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
		// TODO: Complete
		return null;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
