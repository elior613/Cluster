package Ex5Clustering;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class co <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		while (elements.size()>1){
			Set<T> finalElements = elements;
			elements = IntStream.range(0, elements.size())
					.boxed()
					.flatMap(i -> IntStream.range(i + 1, finalElements.size())
							.filter(j -> Math.abs(i - j) < threshold)
							.mapToObj(j -> Set.of((T) finalElements.toArray()[i], (T) finalElements.toArray()[j])))
					.flatMap(Set::stream)
					.collect(Collectors.toSet());
			if(elements.size()==0){return Set<elements>;}
			}
		}
		// TODO: Complete
		return null;
	}
}
