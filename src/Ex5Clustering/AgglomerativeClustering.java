package Ex5Clustering;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	// ctor
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}

	private double distance(Set<T> c1, Set<T> c2) {

		// go over c1. for each element of c1 compare its distance from all c2 elements and return the closest
		return c1.stream().flatMap(var1->c2.stream().map(var2-> var1.distance(var2))).min(Double::compare).get();
	}




	public Set<Set<T>> clusterSet(Set<T> elements) {
		// create a stream of the input: {a,b,c}
		// go over it and make a singleton set from each element (a set containing one element): {a},{b},{c}
		// then, collect all the singleton sets ant create a set of them: { {a},{b},{c} }
		Set<Set<T>> clusters = elements.stream()
				.map(Collections::singleton)
				.collect(Collectors.toSet());
		while (elements.size()>1){
			// save to an Optional (a 'key-value' data set) pairs of every two sets which have the smallest distance
			// and are different from each other. one set is 'key' and the other one is 'value'
			// optional - nullable, abstactMap - key value, simplyEntry - ctor
			Optional<AbstractMap.SimpleEntry<Set<T>, Set<T>>> closestClusters = clusters.stream()
					// flat - transforms elements to a stream and then makes one stream from all
					.flatMap(c1 -> clusters.stream().filter(c2 -> !c1.equals(c2))
							.map(c2 -> new AbstractMap.SimpleEntry<>(c1, c2)))
					.min(Comparator.comparing(entry -> this.distance(entry.getKey(),entry.getValue())));
			// if extracted all elements or threshold is bigger than distance - we finished
			if (closestClusters.isEmpty() || distance(closestClusters.get().getKey(),
					closestClusters.get().getValue()) > threshold) {
				return clusters;
			}

			// they are considered close, so we want to union them and push to 'clusters' set
			Set<T> c1 = closestClusters.get().getKey();
			Set<T> c2 = closestClusters.get().getValue();
			// using HashSet to reach elements via 'key-value'
			Set<T> union = new HashSet<>(c1);
			union.addAll(c2);

			// remove them as individuals before adding it back to avoid issues
			clusters.remove(c1);
			clusters.remove(c2);
			clusters.add(union);
		}
		return clusters;
	}

}
