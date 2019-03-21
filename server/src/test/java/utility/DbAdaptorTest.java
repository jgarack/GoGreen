package utility;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DbAdaptorTest {
    DbAdaptor db = new DbAdaptor();

    @Test
    void ConstructorTest(){

    }


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
/*
    @Test
    void updateActivityTest() {
        String random =  UUID.randomUUID().toString();

        db.connect();
        db.insertUser(new User(random, 0));
        db.disconnect();

        db.connect();
        db.addActivity(new ActivityDb(1, 20, 12, random));
        db.disconnect();

        db.updateActivity(random, 1,2);

        db.connect();
        assertEquals(2, db.getActivityAmount(random, 1));
        db.disconnect();
    }

    @Test
    void getTotalScoreTest(){
        String random =  UUID.randomUUID().toString();

        db.connect();
        db.insertUser(new User(random, 0));
        db.disconnect();

        db.connect();
        assertEquals(db.getTotalScore(random),0);
        db.disconnect();

    }
    */

}