package utility;

import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DbAdaptorTest {
    DbAdaptor db = new DbAdaptor();


    @Test
    void insertUser() {
        db.connect();
        User user = new User("testUser", 1000 );
        db.insertUser(user);
        db.disconnect();
        db.connect();
        assertEquals(db.getUser("testUser"), user);
        db.disconnect();
    }

    @Test
    void insertActivity() {

    }

    @Test
    void addActivity() {
        db.connect();
        Date date = new Date(System.currentTimeMillis());
        ActivityDb activity = new ActivityDb(1, 450, date, "testUser");
        db.addActivity(activity);
        db.disconnect();
        db.connect();
        assertEquals(db.getActivityByDate("testUser", date), activity);
        db.disconnect();
    }

    @Test
    void comparecredentials() {
    }

    @Test
    void addNewUser() {
    }

    @Test
    void getUser() {
    }
}