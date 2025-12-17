package com.qa.turtlemint.pages.DB_Assertions;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.qa.turtlemint.base.TestBase;
import junit.framework.Assert;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DBAssertion <T> extends TestBase {


    public void dbAssertionN(String misID, String collectionName) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);
            Document entryDlt1 = new Document("mISId", misID);
            System.out.println(entryDlt1.toJson());
            FindIterable<Document> docData = collection.find();
            String partnerId = Objects.requireNonNull(docData.first()).get("partnerId").toString();
            System.out.println(partnerId);
            System.out.println("test mew jxshjchsd");
            Objects.requireNonNull(docData.first()).forEach((k, v) -> {
                if (k.equals("payoutPolicyMapper") ) {
                    Object policyMapper = ((Document) v).get("policyMapper");
                    System.out.println(policyMapper);
                    Map<String,String> object=(Map<String,String>)policyMapper;
                    Map<String, Object> objectMap = payoutsExpectedData();
                    objectMap.forEach((key, val) -> Assert.assertEquals(object.get(key),val.toString()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payoutRules_dbAssertion() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> payoutRules = db.getCollection("PayoutRules");

            long PRCount = payoutRules.countDocuments();
            Assert.assertEquals(PRCount, "20420");
            MongoCollection<Document> earningRules = db.getCollection("PayoutRules");
            long ERCount = payoutRules.countDocuments();
            Assert.assertEquals(ERCount, "20420");

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbAssertion(String misID, String collectionName) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);
            Document entryDlt1 = new Document("mISId", misID);
            System.out.println(entryDlt1.toJson());
            FindIterable<Document> docData = collection.find();
            String partnerId = Objects.requireNonNull(docData.first()).get("partnerId").toString();
            System.out.println(partnerId);

            Document query = new Document("mISId", misID);

            // Execute the query
            FindIterable<Document> result = collection.find(query);

            // Iterate through the results
            for (Document doc : result) {
                System.out.println("Mis search in DB For Loop :"+doc.toJson());

                String actualMisID = doc.getString("policyDetailsId");
                String expectedMisID = "MIS_MHA13RIGX7A4T1";

                String actualMisQCStatus = doc.getString("misQC");
                String expectedMisQCStatus = "DONE";

                String actualMisOpsQCStatus = doc.getString("opsQC");
                String expectedOpsQCStatus = "DONE";

                String actualMisOpsQCDoneBy = doc.getString("opsQCDoneBy");
                String expectedOpsQCDoneBy = "automation testing";

//                Double actualMasterTotal = doc.getDouble("payouts.masterTotal");
//                Double expectedMasterTotal = 1800.0;

                // Assertion
                Assert.assertEquals(expectedMisID, actualMisID);
                Assert.assertEquals(expectedMisQCStatus, actualMisQCStatus);
                Assert.assertEquals(expectedOpsQCStatus, actualMisOpsQCStatus);
                Assert.assertEquals(expectedOpsQCDoneBy, actualMisOpsQCDoneBy);

//                Assert.assertEquals(expectedMasterTotal, actualMasterTotal);
            }



            System.out.println("Validated Fields In DB For Manual Upload Entry");
//            Objects.requireNonNull(docData.first()).forEach((k, v) -> {
//                if ( (k.equals("payouts") )){
//                    System.out.println("test mew jxshjchsd");
//                    Map<String,Object> subObject =getFinalPercents(v);
//                    System.out.println(subObject);
//                    if (finalP.equals("base")){
//                        System.out.println(subObject);
//                        Map<String, Object> objectMap = finalPercentsBase();
//                        System.out.println("base data: "+objectMap);
//                        objectMap.forEach((key, val) -> Assert.assertEquals(String.valueOf(subObject.get(key)),String.valueOf(val)));
//                    }
//                    else {
//                        System.out.println(subObject);
//                        Map<String, Object> objectMap = finalPercentsTotal();
//                        objectMap.forEach((key, val) -> Assert.assertEquals(String.valueOf(subObject.get(key)),val.toString()));
//                    }
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Validate LedgerEntity Collection
    public void deviation_LE_DB_Validation(String misID) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection("LedgerEntity");
            Document query = new Document("policyDetailsId", misID);
            System.out.println(query.toJson());
            FindIterable<Document> result = collection.find(query);
            for (Document doc : result) {
                System.out.println("Mis search in DB For Loop :"+doc.toJson());
                policyComisionId = doc.getString("policyCommissionId");
            }
            System.out.println("*** Testing Flow ***");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deviation_LE_DB_After_DeviationUpload(String misID, String FileName) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection("LedgerEntity");
//            Document entryDlt1 = new Document("policyDetailsId", misID);
//            System.out.println(entryDlt1.toJson());
//            FindIterable<Document> docData = collection.find();
//            String partnerId = Objects.requireNonNull(docData.first()).get("partnerId").toString();
//            System.out.println(partnerId);

//            String mis = "MIS_MAH23RJ25GIRZ2";
            Document query = new Document("policyDetailsId", misID);
            System.out.println(query.toJson());

            // Execute the query
            FindIterable<Document> result = collection.find(query);
//            String policyCommisionId = "";
            // Iterate through the results
            for (Document doc : result) {
                System.out.println("Mis search in DB For Loop :"+doc.toJson());

//                String actualMisID = doc.getString("policyDetailsId");

//                ninja ninj = new ninja();
//                String expectedMisID = ninj.validate_MIS_EntryAtPayouts();

//                policyCommisionId = doc.getString("policyCommissionId");

                String actualPartnerID = doc.getString("payablePartnerId");
                String expectedPartnerId = "6290f07ed35ae3058a14b495";


                if(FileName.equals("SPECIAL_REQUEST")){
                    Double actualpoints = doc.getDouble("points");
                    Double expectedpoints = 1860.0;
                    Assert.assertEquals(expectedpoints, actualpoints);

                    Double actualnetPoints = doc.getDouble("netPoints");
                    Double expectednetPoints = 1860.0;
                    Assert.assertEquals(expectednetPoints, actualnetPoints);
                } else {
                    Double actualpoints = doc.getDouble("points");
                    Double expectedpoints = 1800.0;
                    Assert.assertEquals(expectedpoints, actualpoints);

                    Double actualnetPoints = doc.getDouble("netPoints");
                    Double expectednetPoints = 1800.0;
                    Assert.assertEquals(expectednetPoints, actualnetPoints);
                }

//                Double actualpoints = doc.getDouble("points");
//                Double expectedpoints = 1800.0;

//                Double actualnetPoints = doc.getDouble("netPoints");
//                Double expectednetPoints = 1800.0;

                String actualmisQCStatus = doc.getString("misQC");
                String expectedmisQCStatus = "DONE";

                String actualopsQCStatus = doc.getString("opsQC");
                String expectedopsQCStatus = "DONE";

//                Double actualMasterTotal = doc.getDouble("payouts.masterTotal");
//                Double expectedMasterTotal = 1800.0;

                // Assertion
//                Assert.assertEquals(expectedMisID, actualMisID);
                Assert.assertEquals(expectedPartnerId, actualPartnerID);
//                Assert.assertEquals(expectedpoints, actualpoints);
//                Assert.assertEquals(expectednetPoints, actualnetPoints);
                Assert.assertEquals(expectedmisQCStatus, actualmisQCStatus);
                Assert.assertEquals(expectedopsQCStatus, actualopsQCStatus);
//                Assert.assertEquals(expectedMasterTotal, actualMasterTotal);
                policyComisionId = doc.getString("policyCommissionId");
            }
            System.out.println("*** Testing Flow ***");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Validate

    public void deviation_PC_DB_Validation(String misID) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection("LedgerEntity");
            Document entryDlt1 = new Document("policyDetailsId", misID);
            System.out.println(entryDlt1.toJson());
//            FindIterable<Document> docData = collection.find();
//            String partnerId = Objects.requireNonNull(docData.first()).get("partnerId").toString();
//            System.out.println(partnerId);

//            String mis = "MIS_MAH23RJ25GIRZ2";
            Document query = new Document("policyDetailsId", misID);

            // Execute the query
            FindIterable<Document> result = collection.find(query);
//            String policyCommisionId = "";
            // Iterate through the results
            for (Document doc : result) {
                System.out.println("Mis search in DB For Loop :"+doc.toJson());

//                String actualMisID = doc.getString("policyDetailsId");

//                ninja ninj = new ninja();
//                String expectedMisID = ninj.validate_MIS_EntryAtPayouts();

//                policyCommisionId = doc.getString("policyCommissionId");

                String actualPartnerID = doc.getString("payablePartnerId");
                String expectedPartnerId = "6290f07ed35ae3058a14b495";

                Double actualpoints = doc.getDouble("points");
                Double expectedpoints = 1440.0;

                Double actualnetPoints = doc.getDouble("netPoints");
                Double expectednetPoints = 1440.0;

                String actualmisQCStatus = doc.getString("misQC");
                String expectedmisQCStatus = "DONE";

                String actualopsQCStatus = doc.getString("opsQC");
                String expectedopsQCStatus = "PENDING";

//                Double actualMasterTotal = doc.getDouble("payouts.masterTotal");
//                Double expectedMasterTotal = 1800.0;

                // Assertion
//                Assert.assertEquals(expectedMisID, actualMisID);
                Assert.assertEquals(expectedPartnerId, actualPartnerID);
                Assert.assertEquals(expectedpoints, actualpoints);
                Assert.assertEquals(expectednetPoints, actualnetPoints);
                Assert.assertEquals(expectedmisQCStatus, actualmisQCStatus);
                Assert.assertEquals(expectedopsQCStatus, actualopsQCStatus);
//                Assert.assertEquals(expectedMasterTotal, actualMasterTotal);

            }

            System.out.println("*** Testing Flow ***");
//            Objects.requireNonNull(docData.first()).forEach((k, v) -> {
//                if ( (k.equals("payouts") )){
//                    System.out.println("test mew jxshjchsd");
//                    Map<String,Object> subObject =getFinalPercents(v);
//                    System.out.println(subObject);
//                    if (finalP.equals("base")){
//                        System.out.println(subObject);
//                        Map<String, Object> objectMap = finalPercentsBase();
//                        System.out.println("base data: "+objectMap);
//                        objectMap.forEach((key, val) -> Assert.assertEquals(String.valueOf(subObject.get(key)),String.valueOf(val)));
//                    }
//                    else {
//                        System.out.println(subObject);
//                        Map<String, Object> objectMap = finalPercentsTotal();
//                        objectMap.forEach((key, val) -> Assert.assertEquals(String.valueOf(subObject.get(key)),val.toString()));
//                    }
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String policyComisionId;
    public void getPolicyCommisonID(String misID, String collectionName){
        MongoClient mongoClient = new MongoClient("localhost", 27118);
        System.out.println("Database DemoDB connected successfully");
        MongoDatabase db = mongoClient.getDatabase("payouts");
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document entryDlt1 = new Document("policyDetailsId", misID);
        System.out.println(entryDlt1.toJson());
        Document query = new Document("policyDetailsId", misID);
        // Execute the query
        FindIterable<Document> result = collection.find(query);

        String a ;
        // Iterate through the results
        for (Document doc : result) {
            System.out.println("Mis search in DB For Loop :" + doc.toJson());
            policyComisionId = doc.getString("policyCommissionId");
            System.out.println(policyComisionId+" comission od db");
            //   a = policyComisionId;
        }
        // a =  policyComisionId;

    }

    public void partnerLevel(String partnerID, String collectionName) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("Database DemoDB connected successfully");
            MongoDatabase db = mongoClient.getDatabase("payouts");
            MongoCollection<Document> collection = db.getCollection(collectionName);
            Document entryDlt1 = new Document("_id", "ObjectId("+partnerID+")");
            System.out.println(entryDlt1.toJson());
            FindIterable<Document> docData = collection.find();
            String partnerId = Objects.requireNonNull(docData.first()).get("partnerId").toString();
            System.out.println(partnerId);
            System.out.println("test mew jxshjchsd");
            Map<String,Object> object=(Map<String,Object>)docData;
            Map<String,Object> parnerLevel = partnerLevelActivity();
            parnerLevel.forEach((key,val) -> Assert.assertEquals(object.get(key),val.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String,Object> getFinalPercents(Object docObj, String key){
        Object finalPercents = ((Document) docObj).get("finalPercents");
        Map<String,Object> percentObject = (Map<String,Object>)finalPercents;
        return ( Map<String,Object>)percentObject.get(key);
    }
    public Map<String, Object>  payoutsExpectedData(){
        Map<String, Object> policyMapper = new HashMap<>();
        policyMapper.put("policyProposerFName","TEST");
        policyMapper.put("policyProposerLName","kdjsgh");
        policyMapper.put("odPremium",50);
        policyMapper.put("netPremium",50);
        policyMapper.put("netOdPremium",50);
        policyMapper.put("basicTpPremium",50);
        policyMapper.put("tpPremium",50);
        return policyMapper;
    }
    public Map<String, Object>  finalPercentsBase(){
        Map<String, Object> finalPercentsBase = new HashMap<>();
        finalPercentsBase.put("np",50.0);
        finalPercentsBase.put("odb",50.0);
        finalPercentsBase.put("odr",50.0);
        finalPercentsBase.put("tp",50.0);
        return finalPercentsBase;
    }
    public Map<String, Object>  finalPercentsTotal(){
        Map<String, Object> finalPercentsBase = new HashMap<>();
        finalPercentsBase.put("np",50.0);
        finalPercentsBase.put("od",50.0);
        finalPercentsBase.put("tp",50.0);
        return finalPercentsBase;
    }
    public Map<String, Object>  partnerLevelActivity(){
        Map<String, Object> finalPercentsBase = new HashMap<>();
        finalPercentsBase.put("partnerId","63b54bb9ee10470001250bb6");
        finalPercentsBase.put("points",20);
        finalPercentsBase.put("paymentCycle",20231214);
        finalPercentsBase.put("partnerLevelActivityType","MUTUAL_FUND");
        return finalPercentsBase;
    }
}


