package com.qa.turtlemint.pages.DB_Assertions;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.qa.turtlemint.commands.WebCommands;
import junit.framework.Assert;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ValidatePayoutRules {

    public void ValidatePayoutRules() {
            WebCommands.staticSleep(5000);
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("****************** Database DemoDB connected successfully ******************");
            MongoDatabase db = mongoClient.getDatabase("payouts");

// Validate Payouts Rules
            MongoCollection<Document> payoutRules = db.getCollection("PayoutRules");
            long PRCount = payoutRules.countDocuments();
            System.out.println("Payouts Rule Count : " + PRCount);
            Assert.assertEquals(PRCount, 20);

            List<Integer> expected = Arrays.asList(50705, 50706, 51234, 51235, 51236, 51237, 51238, 51239, 51240, 51243, 51258, 51259, 51263, 51264, 51268, 51269, 51302, 51303, 51315, 51321);

            MongoCursor<Document> cursorPayouts = payoutRules.find().iterator();
            List<Integer> actualPayouts = new ArrayList<>();
            while (cursorPayouts.hasNext()) {
                Document d = cursorPayouts.next();
                Document meta = d.get("metaInfo", Document.class);
                actualPayouts.add(meta.getInteger("ruleId"));
            }
            System.out.println("Payouts Rule Presents : " + actualPayouts);
            Assert.assertEquals("ruleId list does not match!", expected, actualPayouts);

// Validate Earning Rules
            MongoCollection<Document> earningRules = db.getCollection("EarningRules");
            long ERCount = earningRules.countDocuments();
            System.out.println("Earning Rule Count : " + ERCount);
            Assert.assertEquals(ERCount, 20);
            MongoCursor<Document> cursorEarning = earningRules.find().iterator();
            List<Integer> actualEarning = new ArrayList<>();
            while (cursorEarning.hasNext()) {
                Document d = cursorEarning.next();
                Document meta = d.get("metaInfo", Document.class);
                actualEarning.add(meta.getInteger("ruleId"));
            }
            System.out.println("Earning Rules Presents : " + actualEarning);
            Assert.assertEquals("ruleId list does not match!", expected, actualEarning);
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ValidatePayoutSplitRules() {
        WebCommands.staticSleep(5000);
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27118);
            System.out.println("****************** Database DemoDB connected successfully ******************");
            MongoDatabase db = mongoClient.getDatabase("payouts");

// Validate Payouts Split Rules
            MongoCollection<Document> payoutSplitRules = db.getCollection("DealerPayoutSplitRules");
            long PRCount = payoutSplitRules.countDocuments();
            System.out.println("Payouts Split Rule Count : " + PRCount);
            Assert.assertEquals(PRCount, 7);

            List<Integer> expectedSplitPayoutsRules = Arrays.asList(2, 3, 4, 5, 6, 7, 8);

            MongoCursor<Document> cursorPayouts = payoutSplitRules.find().iterator();
            List<Integer> actualSplitPayoutsRules = new ArrayList<>();
            while (cursorPayouts.hasNext()) {
                Document d = cursorPayouts.next();
                Document meta = d.get("metaInfo", Document.class);
                actualSplitPayoutsRules.add(meta.getInteger("ruleId"));
            }
            System.out.println("Payouts Split Rule Presents : " + actualSplitPayoutsRules);
            Assert.assertEquals("ruleId list does not match!", expectedSplitPayoutsRules, actualSplitPayoutsRules);

//  Assert on Rule ID No 5
            Document doc = payoutSplitRules.find(eq("metaInfo.ruleId", 5)).first();
            Assert.assertNotNull("No record found for metaInfo.ruleId = 5",doc);
            Document condition = doc.get("condition", Document.class);
            List<Document> andList = condition.getList("AND", Document.class);
            Document dealerID = andList.get(0).get("EQ", Document.class);
            String dealerId = dealerID.getString("value");
            Assert.assertEquals("dealerId value mismatch!", "63b54bb9ee10470001250bb6", dealerId);
            Document subAgentID = andList.get(1).get("EQ", Document.class);
            String subAgentId = subAgentID.getString("value");
            Assert.assertEquals("subAgentId value mismatch!", "6398575471c5dc16af014253", subAgentId);
            Document productCat = andList.get(4).get("EQ", Document.class);
            String productCategory = productCat.getString("value");
            Assert.assertEquals("subAgentId value mismatch!", "HEALTH", productCategory);
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
