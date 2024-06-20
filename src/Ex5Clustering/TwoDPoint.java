package Ex5Clustering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.lang.Math;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;
	public TwoDPoint(String str){
		String[] parts = str.split(",");
		this.x = Double.parseDouble(parts[0]);
		this.y = Double.parseDouble(parts[1]);
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
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
			Set<TwoDPoint> points = lines
					.map(line -> new TwoDPoint(line))
					.collect(Collectors.toSet());
			return points;
		} catch (IOException e) {
			e.printStackTrace();
		}
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
