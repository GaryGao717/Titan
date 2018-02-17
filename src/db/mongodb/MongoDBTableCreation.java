package db.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class MongoDBTableCreation {
	 // Run as Java application to create MongoDB collections with index.
	  public static void main(String[] args) {
	    MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);
	    
	    // drop old collection
	    db.getCollection("users").drop();
	    db.getCollection("items").drop();
	    
	    // create new collection, fake new user
	    db.getCollection("users").insertOne(new Document().append("user_id", "1111")
	    													 .append("password", "3229c1097c00d497a0fd282d586be050")
	    													 .append("first_name", "John")
	    													 .append("last_name", "Smith"));

	    IndexOptions indexOptions = new IndexOptions().unique(true);
	    
	    // use 1 for ascending index , -1 for descending index
	    // Different to MySQL, users table in MongoDB also has history info.
	    db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);

	    // make sure item_id is unique.
	    // Different to MySQL, items table in MongoDB also has categories info.
	    db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);

	    
	    mongoClient.close();
	    System.out.println("Import is done successfully.");
	  }

}
