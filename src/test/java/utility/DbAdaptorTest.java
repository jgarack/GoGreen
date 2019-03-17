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
        User user2 = db.getUser("testUser");
        assertEquals(user2.getUsername(), user.getUsername());
        assertEquals(user2.getTotalScore(), user.getTotalScore());
        db.disconnect();
    }

    @Test
    void insertActivity() {

    }

    @Test
    void addActivity() {
        db.connect();
        Date date1 = new Date(System.currentTimeMillis());
        String date = date1.toString();
        ActivityDb activity = new ActivityDb(1, 450, date, "testUser");
        db.addActivity(activity);
        db.disconnect();
        db.connect();
        ActivityDb adb = db.getActivityByDate("testUser", date);
        assertEquals(adb.getActivityId(), activity.getActivityId());
        assertEquals(adb.getDateOfActivity(), activity.getDateOfActivity());
        assertEquals(adb.getScore(), activity.getScore());
        assertEquals(adb.getUsernameAct(), activity.getUsernameAct());

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