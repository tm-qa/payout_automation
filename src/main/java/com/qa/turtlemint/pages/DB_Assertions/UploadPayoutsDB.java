package com.qa.turtlemint.pages.DB_Assertions;

/*
Reusable MongoDB Utility Class for Java projects (Selenium test suites, test setup/teardown, etc.)

Maven dependency (add to pom.xml):

<dependency>
  <groupId>org.mongodb</groupId>
  <artifactId>mongodb-driver-sync</artifactId>
  <version>4.11.1</version>
</dependency>

Usage:
1) Initialize once (e.g. in @BeforeSuite or application startup):
   MongoDBUtility.init("mongodb://localhost:27017", "myDatabase");

2) Use helper methods in tests or utilities:
   Document doc = new Document("name", "rambo").append("age", 30);
   MongoDBUtility.insertOne("users", doc);

3) Close on shutdown (e.g. @AfterSuite):
   MongoDBUtility.close();
*/

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

public class UploadPayoutsDB {

    public void deleteSingleEntry(String collectionName, String misID) {
        try {
            com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);

            String PC = "PolicyCommissions";
            if(collectionName.equalsIgnoreCase(PC)) {

                Document entryDlt1 = new Document("mISId", misID);//.append("policyPaymentScheduleId", "id1");
                DeleteResult result1 = collection.deleteOne(entryDlt1);

                System.out.println("Deleted From PC " + result1.getDeletedCount() + " document.");
            } else {
                Document entryDlt1 = new Document("policyDetailsId", misID);//.append("policyPaymentScheduleId", "id1");
                DeleteResult result1 = collection.deleteOne(entryDlt1);

                System.out.println("Deleted From LE " + result1.getDeletedCount() + " document.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEntitriesFromLedgerEntity(String collectionName, String paymentCycle) {
        try {
            com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);
            //Delete multiple entries
            List<String> misIdList = Arrays.asList("MIS_MHA13RIGX7A4V1","MIS_MHA13RIGX7A4V2","MIS_MHA13RIGX7A4V3","MIS_MHA13RIGX7A4V4","MIS_MHA13RIGX7A4V5");

            Bson filter = Filters.and(
                    Filters.eq("paymentCycle", paymentCycle),
                    Filters.in("policyDetailsId", misIdList)
            );

// Preview
            long preview = collection.countDocuments(filter);
            System.out.println("Entries to delete from LedgerEntity: " + filter);
            System.out.println("Entries Count to delete from LedgerEntity: " + preview);

// Delete
            DeleteResult result = collection.deleteMany(filter);
            System.out.println("Deleted records from LedgerEntity: " + result.getDeletedCount());
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteEntitriesFromPolicyCommissions(String collectionName, String paymentCycle) {
        try {
            com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);
            //Delete multiple entries
            List<String> misIdList = Arrays.asList("MIS_MHA13RIGX7A4V1","MIS_MHA13RIGX7A4V2","MIS_MHA13RIGX7A4V3","MIS_MHA13RIGX7A4V4","MIS_MHA13RIGX7A4V5");

            Bson filter = Filters.and(
                    Filters.eq("paymentCycle", paymentCycle),
                    Filters.in("mISId", misIdList)
            );

// Preview
            long preview = collection.countDocuments(filter);
            System.out.println("Entries to delete from PolicyCommissions: " + filter);
            System.out.println("Entries Count to delete from PolicyCommissions: " + preview);

// Delete
            DeleteResult result = collection.deleteMany(filter);
            System.out.println("Deleted records from PolicyCommissions: " + result.getDeletedCount());
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


