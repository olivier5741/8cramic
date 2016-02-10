import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Predicate<String> i = (s) -> s.length() > 5;
        System.out.println(i.test("java2s.com "));

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27018);
        MongoDatabase db = mongoClient.getDatabase("test");
        Employee employee = new Employee(); // Create java object
        employee.setNo(1L);
        employee.setName("yogesh");
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        Document doc = Document.parse(json);

        db.getCollection("NameColl").insertOne(doc);


        // Retrieve to ensure object was inserted
        FindIterable<Document> iterable = db.getCollection("NameColl").find();
        iterable.forEach((Block<Document>) System.out::println);
    }
}
