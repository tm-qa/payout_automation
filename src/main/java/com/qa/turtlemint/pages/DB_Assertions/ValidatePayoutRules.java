package com.qa.turtlemint.pages.DB_Assertions;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.qa.turtlemint.commands.WebCommands;
import junit.framework.Assert;
import org.bson.Document;
import org.testng.annotations.BeforeClass;

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


}
