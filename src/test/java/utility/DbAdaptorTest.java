package utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
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

    @Test
    void DateTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);

        Date date = new Date(System.currentTimeMillis());

        db.updateDate(random, date);

        assertEquals(date.toString(), db.getDate(random).toString());

        db.deleteByUsername(random);
    }

    @Test
    void AchievementTest(){
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);

        db.addAchievement(1, random);

        List<Integer> tempList = new ArrayList<>();
        tempList.add(1);

        assertEquals(db.getAchievements(random),tempList);

        db.deleteByUsername(random);
    }

    @Test
    void getAchievementTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        List<Achievement> listAch = new ArrayList<>();
        listAch.add(new Achievement("Regular  biker", false, "You have used 5 times a bike"));
        listAch.add(new Achievement("Pro  biker", false, "You have used 50 times a bike"));
        listAch.add(new Achievement("Public  transport", false, "You have used 5 times public transport"));
        listAch.add(new Achievement("Commuter", false, "You have used 50 times public transport"));
        listAch.add(new Achievement("Vegetarian", false, "You have eaten 5 veggie meals"));
        listAch.add(new Achievement("Vegan", false, "You have eaten 50 veggie meals"));
        listAch.add(new Achievement("Go  Green", false, "You have used solar panels for 1 month"));
        listAch.add(new Achievement("Elon  Musk", false, "You have used solar panels for 3 month"));
        listAch.add(new Achievement("Breezy", false, "You have lowered home temperature by 5 degrees"));
        listAch.add(new Achievement("Antarctica", false, "You have lowered home temperature by 10 degrees"));
        listAch.add(new Achievement("Prom  king/queen", false, "You have 10 friends"));
        listAch.add(new Achievement("Difference  maker", false, "You have earned 1 000 000 points"));


        List<Achievement> l = db.getAllAchievements();

        for(int i = 0; i <12; i++) {
            assertTrue(l.get(i).equals(listAch.get(i)));
        }
        db.deleteByUsername(random);
    }

    @Test
    void changePassTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        db.changepass(random, "@");
        assertTrue(db.comparecredentials(new LoginCredentials(random, "@")));
        db.deleteByUsername(random);
    }

    @Test
    void updateAvatarTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        db.updateAvatarUrl(random,"1");
        assertEquals(db.getUser(random).getAvatarUrl(), "1");
        db.deleteByUsername(random);
    }

    @Test
    void getTotalScoreTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);

        assertEquals(db.getTotalScore(random), 0);
        db.deleteByUsername(random);
    }


    @Test
    void FriendsTest() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        String random2 = UUID.randomUUID().toString();
        RegisterCredentials randomUser2 = new RegisterCredentials(random2, "1", "!","!");
        db.addNewUser(randomUser2);
        db.sendFriendReq(random, random2);
        db.considerRequest(random, random2, true);
        assertEquals(db.getFriends(random).get(0), random2);
        db.deleteByUsername(random);
        db.deleteByUsername(random2);
    }

    @Test
    void addNewUserFalse() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        assertFalse(db.addNewUser(randomUser));
        db.deleteByUsername(random);
    }


    @Test
    void getActivityAmountException() {
        assertEquals(-1, db.getActivityAmount(UUID.randomUUID().toString(),1));
    }

    @Test
    void getTotalScoreException() {
        assertEquals(-1, db.getTotalScore(UUID.randomUUID().toString()));
    }

    @Test
    void checkIfInDB() {
        String random = UUID.randomUUID().toString();
        RegisterCredentials randomUser = new RegisterCredentials(random, "1", "!","!");
        db.addNewUser(randomUser);
        String random2 = UUID.randomUUID().toString();
        RegisterCredentials randomUser2 = new RegisterCredentials(random2, "1", "!","!");
        db.addNewUser(randomUser2);

        db.sendFriendReq(random, random2);
        assertTrue(db.checkIfInDb(random, random2));
    }


    @Test
    void getRequestException() {
        assertEquals(new ArrayList<>(), db.getRequest(UUID.randomUUID().toString()));
    }

    @Test
    void getFriendsException() {
        assertEquals(new ArrayList<>(), db.getFriends(UUID.randomUUID().toString()));
    }

    @Test
    void getDateException() {
        assertEquals(null, db.getDate(UUID.randomUUID().toString()));
    }


}