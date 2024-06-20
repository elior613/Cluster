package Ex5Clustering;

import java.util.Set;


public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{
	double threshold;
	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {
		while (elements.size()>1){
			Set<Set<T>> clusters =
		}
		// TODO: Complete
		return null;
	}
}
