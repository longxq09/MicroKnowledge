package com.wzs.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.sql.SQLException;
import java.util.List;

public class ColFilter {


    private static MysqlDataSource dataSource = new MysqlDataSource();

    public ColFilter() throws SQLException {
        dataSource.setUseSSL(true);
        dataSource.setServerName("localhost");
//        dataSource.setPort(33006);
        dataSource.setPort(3306);
        dataSource.setUser("root");
//        dataSource.setPassword("haojiwei");
        dataSource.setPassword("123456");
        dataSource.setDatabaseName("wzs");
    }

    public static void collaborative_Filtering(int userId, int itemId) throws TasteException {
        JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "user_rating", "userId", "noticeId", "score",null);

        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);

        Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        List<RecommendedItem> recommendItems = recommender.recommend(userId, itemId);

        for (RecommendedItem recommendedItem : recommendItems) {
            System.out.println(recommendedItem);
        }



    }
}
