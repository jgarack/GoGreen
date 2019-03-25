package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import resources.AbstractTest;
import utility.DbAdaptor;
import utility.HttpRequestHandler;
import utility.UpdateRequest;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        controller.HTTP_HANDLER_API = Mockito.mock(HttpRequestHandler.class);
        controller.DB_ADAPTOR = Mockito.mock(DbAdaptor.class);
        for(String s : httpmockcalls) {
            when(controller.HTTP_HANDLER_API.reqGet(s))
                    .thenReturn(new BufferedReader(new StringReader(JSON)));
        }
        when(controller.DB_ADAPTOR.updateActivity(
                "user", 1, 0)).thenReturn(true);
        when(controller.DB_ADAPTOR.updateActivity(
                "user", 4, 0)).thenReturn(true);
        when(controller.DB_ADAPTOR.updateActivity(
                "user", 2, 1000)).thenReturn(true);
        when(controller.DB_ADAPTOR.updateActivity(
                "user", 3, 1000)).thenReturn(true);
        when(controller.DB_ADAPTOR.getActivityAmount("user", 1))
                .thenReturn(1);
        when(controller.DB_ADAPTOR.getActivityAmount("user", 2))
                .thenReturn(1);
        when(controller.DB_ADAPTOR.getActivityAmount("user", 3))
                .thenReturn(1);
        when(controller.DB_ADAPTOR.getActivityAmount("user", 4))
                .thenReturn(1);

        when(controller.DB_ADAPTOR.updateActivity("user", 7, 1000))
                .thenReturn(false);
        when(controller.DB_ADAPTOR.getTotalScore("user")).thenReturn(100);
    }

    @Test
    public void jsonConTest() throws IOException {
        assertTrue(jsonCon(JSON) == 1000);
    }
    @Test
    public void update_vegmeal() throws Exception {
        route = "/points";
        json_req = super.mapToJson(fabricate(1));
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_bycicle() throws Exception {
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
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(route)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json_req)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
    @Test
    public void update_publicTransport() throws Exception {
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
}
