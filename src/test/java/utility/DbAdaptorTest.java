package utility;

import org.junit.jupiter.api.Test;
import java.sql.Date;
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
        db.connect();
        User user2 = db.getUser(random);
        assertEquals(user2.getUsername(), user.getUsername());
        assertEquals(user2.getTotalScore(), user.getTotalScore());
        db.disconnect();
    }

    @Test
    void updateTotalScoreTest() {
        db.connect();
        String random = UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "password", "q", "a"));
        db.disconnect();
        db.connect();
        db.updateTotalScore(random);
        db.disconnect();
        db.connect();
        User test = db.getUser(random);
        assertEquals(test.getTotalScore(), 20);
        db.disconnect();
    }


    @Test
    void comparecredentials() {
        db.connect();
        String random =  UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "pass","que", "ans"));
        db.disconnect();
        db.connect();
        LoginCredentials LC = new LoginCredentials(random,"pass");
        assertEquals(db.comparecredentials(LC),true);
        db.disconnect();
    }

    @Test
    void addNewUser() {
        db.connect();
        String random =  UUID.randomUUID().toString();
        db.addNewUser(new RegisterCredentials(random, "pass","que", "ans"));
        db.disconnect();
        LoginCredentials LC = new LoginCredentials(random,"pass");
        db.connect();
        assertEquals(db.comparecredentials(LC),true);
        db.disconnect();
    }

    @Test
    void getUser() {
        String random =  UUID.randomUUID().toString();
        db.connect();
        db.insertUser(new User(random, 0));
        db.disconnect();
        db.connect();
        assertEquals(db.getUser(random).getUsername(), random);
        db.disconnect();


    }

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

}