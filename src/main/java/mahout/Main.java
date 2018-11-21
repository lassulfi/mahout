package mahout;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import recommender.IRecommender;

public class Main {
	
	public static void main(String[] args) {
		
		File file = new File(ClassLoader.getSystemClassLoader().getResource("files/data.txt").getFile());
		
		IRecommender recommender = new recommender.Recommender(file);
		recommender.setThreshold(0.1);
		
		try {
			List<RecommendedItem> recommendations = recommender.recomendations(2, 3);
			for(RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);
			}
		} catch (IOException e) {
			System.out.print("An error ocurred ");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.print("An error ocurred ");
			e.printStackTrace();
		}
		
				
	}

}
