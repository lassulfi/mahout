package recommender;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public interface IRecommender {
	
	public void setThreshold(double threshold);
	public List<RecommendedItem> recomendations(int userID, int numberOfRecommendations) throws IOException, TasteException;

}
