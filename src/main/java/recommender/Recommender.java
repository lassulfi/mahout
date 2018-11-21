package recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
public class Recommender implements IRecommender {
	
	private File file;
	private double threshold;
	
	public Recommender(File file) {
		this.file = file;
	}
	
	
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	

	public List<RecommendedItem> recomendations(int userID, int numberOfRecommendations) throws IOException, TasteException {
		DataModel dataModel = new FileDataModel(file);		
		
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		
		if(threshold == 0.0) {
			threshold = 0.1;
		}
		
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(threshold, similarity, dataModel);
		
		UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
		
		return recommender.recommend(userID, numberOfRecommendations);
	}

}
