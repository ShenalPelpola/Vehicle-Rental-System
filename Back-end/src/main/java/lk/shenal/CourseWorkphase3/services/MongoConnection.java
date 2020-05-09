package lk.shenal.CourseWorkphase3.services;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;

public class MongoConnection {
    public static DB connect(){

        MongoClient mongo = new MongoClient( "localhost" , 27017 );


        // Accessing the database
        DB db = mongo.getDB("VehicleRental");
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
        return db;
    }
}
