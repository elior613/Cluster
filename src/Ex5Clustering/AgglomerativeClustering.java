package Ex5Clustering;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}

	private double distance(Set<T> c1, Set<T> c2) {

		return c1.stream().flatMap(var1->c2.stream().map(var2-> var1.distance(var2))).min(Double::compare).get();
	}




	public Set<Set<T>> clusterSet(Set<T> elements) {
		Set<Set<T>> clusters = elements.stream()
				.map(Collections::singleton)
				.collect(Collectors.toSet());
		while (elements.size()>1){
			Optional<AbstractMap.SimpleEntry<Set<T>, Set<T>>> closestClusters = clusters.stream()
					.flatMap(c1 -> clusters.stream().filter(c2 -> !c1.equals(c2))
							.map(c2 -> new AbstractMap.SimpleEntry<>(c1, c2)))
					.min(Comparator.comparing(entry -> this.distance(entry.getKey(),entry.getValue())));

			if (closestClusters.isEmpty() || distance(closestClusters.get().getKey(),
					closestClusters.get().getValue()) > threshold) {
				return clusters;
			}

			Set<T> c1 = closestClusters.get().getKey();
			Set<T> c2 = closestClusters.get().getValue();
			Set<T> union = new HashSet<>(c1);
			union.addAll(c2);

			clusters.remove(c1);
			clusters.remove(c2);
			clusters.add(union);
		}
		return clusters;
	}

}
