package server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import resources.AbstractTest;
import utility.DbAdaptor;
import utility.HttpRequestHandler;
import utility.UpdateRequest;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static server.PointsController.jsonCon;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PointsControllerTest extends AbstractTest {
    private static final String JSON =
            "{\"decisions\":{\"carbon\":{\"object\":{\"value\":1}}}}";
    private static final String KEY =
            "&key=5a98005a-09ff-4823-8d5b-96a3bbf3d7fd";
    private static final String[] httpmockcalls = {
            "/diets.json?size=1&timeframe=2019-01-01%2F2019-01-02"+KEY,
            "/diets.json?size=1&diet_class=vegetarian"
                    + "&timeframe=2019-01-01%2F2019-01-02" + KEY,
            "/automobile_trips.json?duration=60" + KEY,
            "/automobile_trips.json?duration=1" + KEY,
            "/bus_trips.json?duration=60" + KEY,
    };
    private String json_req;
    private String route;
    private MvcResult mvcResult;
    private BufferedReader JSON_BUFFER;
    @Autowired
    private PointsController controller;

    public UpdateRequest fabricate(int id) {
        return new UpdateRequest("user", id, 1);
    }

    @BeforeAll
    public void setUp() {
        super.setUp();
    }
    @BeforeEach
    public void setMocks() throws Exception {
        controller.httpHandler = Mockito.mock(HttpRequestHandler.class);
        controller.dbAdaptor = Mockito.mock(DbAdaptor.class);
        for(String s : httpmockcalls) {
            when(controller.httpHandler.reqGet(s))
                    .thenReturn(new BufferedReader(new StringReader(JSON)));
        }
        when(controller.dbAdaptor.updateActivity(
                "user", 1, 0)).thenReturn(true);
        when(controller.dbAdaptor.updateActivity(
                "user", 4, 0)).thenReturn(true);
        when(controller.dbAdaptor.updateActivity(
                "user", 2, 1000)).thenReturn(true);
        when(controller.dbAdaptor.updateActivity(
                "user", 3, 88)).thenReturn(true);
        when(controller.dbAdaptor.updateActivity(
                "user", 6, 110)).thenReturn(true);
        when(controller.dbAdaptor.updateActivity(
                "user", 6, 1100)).thenReturn(true);
        when(controller.dbAdaptor.getActivityAmount("user", 1))
                .thenReturn(1);
        when(controller.dbAdaptor.getActivityAmount("user", 2))
                .thenReturn(1);
        when(controller.dbAdaptor.getActivityAmount("user", 3))
                .thenReturn(1);
        when(controller.dbAdaptor.getActivityAmount("user", 4))
                .thenReturn(1);

        when(controller.dbAdaptor.updateActivity("user", 7, 1000))
                .thenReturn(false);
        when(controller.dbAdaptor.getTotalScore("user")).thenReturn(100);
    }

    @Test
    public void jsonConTest() throws IOException {
        assertTrue(jsonCon(JSON) == 1000);
    }
    @Test
    public void update_vegmeal() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithFiveAndSix = new ArrayList<Integer>(2);
        listWithFiveAndSix.add(5);
        listWithFiveAndSix.add(6);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithFiveAndSix);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithFiveAndSix);

        route = "/points";
        json_req = super.mapToJson(fabricate(1));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_bycicle() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithOneAndTwo = new ArrayList<Integer>(2);
        listWithOneAndTwo.add(1);
        listWithOneAndTwo.add(2);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithOneAndTwo);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithOneAndTwo);

        route = "/points";
        json_req = super.mapToJson(fabricate(2));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_localProduce() throws Exception {
        route = "/points";
        json_req = super.mapToJson(fabricate(3));
        System.out.println(json_req);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_publicTransport() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithThreeAndFour = new ArrayList<Integer>(2);
        listWithThreeAndFour.add(3);
        listWithThreeAndFour.add(4);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithThreeAndFour);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithThreeAndFour);

        route = "/points";
        json_req = super.mapToJson(fabricate(4));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_publicTransport1() throws Exception {
        route = "/points";
        json_req = super.mapToJson(fabricate(4));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_onDbFailure_returnInternalServerError() throws Exception {
        route = "/points";
        json_req = super.mapToJson(fabricate(7));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(500, mvcResult.getResponse().getStatus());
    }
    @Test
    public void getTotalScore() throws Exception{
        route = "/total";
        json_req = super.mapToJson("user");
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_FiveSix_NotAchievedNotEligible() throws Exception{
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 1))
                .thenReturn(2);

        route = "/points";
        json_req = super.mapToJson(fabricate(1));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_FiveSix_NotAchievedEligible() throws Exception {
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 1))
                .thenReturn(100);

        route = "/points";
        json_req = super.mapToJson(fabricate(1));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        verify(controller.dbAdaptor).addAchievement(5, "user");
        verify(controller.dbAdaptor).addAchievement(6, "user");
    }
    @Test
    public void achievements_OneAndTwo_NotAchievedNotEligible() throws Exception{
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 2))
                .thenReturn(2);

        route = "/points";
        json_req = super.mapToJson(fabricate(2));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_OneAndTwo_NotAchievedEligible() throws Exception {
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 2))
                .thenReturn(100);

        route = "/points";
        json_req = super.mapToJson(fabricate(2));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        verify(controller.dbAdaptor).addAchievement(1, "user");
        verify(controller.dbAdaptor).addAchievement(2, "user");
    }
    @Test
    public void achievements_ThreeFour_NotAchievedNotEligible() throws Exception{
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 4))
                .thenReturn(2);

        route = "/points";
        json_req = super.mapToJson(fabricate(4));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_ThreeFour_NotAchievedEligible() throws Exception {
        //stubbing
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 4))
                .thenReturn(100);

        route = "/points";
        json_req = super.mapToJson(fabricate(4));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        verify(controller.dbAdaptor).addAchievement(3, "user");
        verify(controller.dbAdaptor).addAchievement(4, "user");
    }

    @Test
    public void update_solarPanels_NoMonthPassed() throws Exception {
        //stubbing
        Date today = Date.valueOf(LocalDate.now(ZoneId.of("Europe/Amsterdam")).toString());
        when(controller.dbAdaptor.getDate("user"))
                .thenReturn(today);

        route = "/points";
        json_req = super.mapToJson(fabricate(5));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_solarPanels() throws Exception {
        //stubbing
        Date epoch = Date.valueOf("1970-01-01");
        when(controller.dbAdaptor.getDate("user"))
                .thenReturn(epoch);
        List<Integer> listWithEight = new ArrayList<Integer>(1);
        listWithEight.add(8);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithEight);

        route = "/points";
        json_req = super.mapToJson(fabricate(5));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_Eight_NotAchievedNotEligible() throws Exception {
        //stubbing
        Date epoch = Date.valueOf("1970-01-01");
        when(controller.dbAdaptor.getDate("user"))
                .thenReturn(epoch);
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 5))
                .thenReturn(1);

        route = "/points";
        json_req = super.mapToJson(fabricate(5));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void achievements_Eight_NotAchievedEligible() throws Exception {
        //stubbing
        Date epoch = Date.valueOf("1970-01-01");
        when(controller.dbAdaptor.getDate("user"))
                .thenReturn(epoch);
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getPerformedTimes("user", 5))
                .thenReturn(3);

        route = "/points";
        json_req = super.mapToJson(fabricate(5));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_homeTemperature() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithNineAndTen = new ArrayList<Integer>(2);
        listWithNineAndTen.add(9);
        listWithNineAndTen.add(10);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithNineAndTen);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithNineAndTen);

        route = "/points";
        json_req = super.mapToJson(fabricate(6));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void activity_NineTen_NotAchievedNotEligible() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);

        route = "/points";
        json_req = super.mapToJson(fabricate(6));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void activity_NineTen_NotAchievedEligible() throws Exception {
        //extra stubbing for achievements
        List<Integer> listWithZero = new ArrayList<Integer>(1);
        listWithZero.add(0);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);
        when(controller.dbAdaptor.getAchievements("user"))
                .thenReturn(listWithZero);

        route = "/points";
        json_req = super.mapToJson(new UpdateRequest("user", 6, 10));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
