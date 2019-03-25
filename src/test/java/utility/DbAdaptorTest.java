package utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DbAdaptorTest {
    DbAdaptor db = new DbAdaptor();


    @Test
    void insertUser() {
        db.connect();
        String random =  UUID.randomUUID().toString();

        User user = new User(random, 1000 );
        db.insertUser(user);
        db.disconnect();

        User user2 = db.getUser(random);
        assertEquals(user2.getUsername(), user.getUsername());
        assertEquals(user2.getTotalScore(), user.getTotalScore());

        db.deleteByUsername(random);
    }

    @Test
    void updateTotalScoreTest() {
        String random = UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "password", "q", "a"));
        db.updateTotalScore(random);
        User test = db.getUser(random);
        assertEquals(test.getTotalScore(), 0);
        db.deleteByUsername(random);
    }


    @Test
    void comparecredentials() {
        String random =  UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "pass","que", "ans"));
        LoginCredentials LC = new LoginCredentials(random,"pass");
        assertEquals(db.comparecredentials(LC),true);
        db.deleteByUsername(random);
    }

    @Test
    void addNewUser() {
        String random =  UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "pass","que", "ans"));
        LoginCredentials LC = new LoginCredentials(random,"pass");
        assertEquals(db.comparecredentials(LC),true);
        db.deleteByUsername(random);
    }

    @Test
    void getUser() {
        String random =  UUID.randomUUID().toString();
        db.insertUser(new User(random, 0));
        assertEquals(db.getUser(random).getUsername(), random);
        db.deleteByUsername(random);
    }



    @Test
    void updateActivityTest (){
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        db.addActivity(new ActivityDb(1,12, 12, random));
        db.updateActivity(random, 1, 134);
        assertEquals(134, db.getActivityAmount(random, 1));
        db.deleteByUsername(random);

    }

    @Test
    void getActivityAmountTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        db.updateActivity(random,1,1 );
        assertEquals(1, db.getActivityAmount(random, 1));
        db.deleteByUsername(random);
    }

    @Test
    void addActivityTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        assertEquals(0, db.getActivityAmount(random, 1));
        db.deleteByUsername(random);
    }

    @Test
    void checkIfInDbTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        String random2 = UUID.randomUUID().toString();
        RegisterCredentials randomUser2 = new RegisterCredentials(random2, "1", "!","!");
        db.addNewUser(randomUser2);
        System.out.println(random+"   :::  "+random2);
        assertEquals(true ,db.checkIfInDb(random, random2));

        db.deleteByUsername(random);
        db.deleteByUsername(random2);
    }

    @Test
    void sendRequestTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        String random2 = UUID.randomUUID().toString();
        RegisterCredentials randomUser2 = new RegisterCredentials(random2, "1", "!","!");
        db.addNewUser(randomUser2);

        db.sendFriendReq(random, random2);


        List<String> list1 = db.getRequest(random2);

        List<String> list2 = new ArrayList<>();
        System.out.println(list1.toString());
        System.out.println(list2.toString());

        list2.add(random);
        assertEquals(list1, list2);

        db.deleteByUsername(random);
        db.deleteByUsername(random2);


    }



}