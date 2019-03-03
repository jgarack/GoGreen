package gui;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import server.GreetingController;


/**
 * Deprecated methods in this class are still supported.
 */
@SuppressWarnings("deprecation")

/**
 * Application class, run to boot server and start fxml.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = GreetingController.class)
public class DemoApplication extends Application {
    /**
     * The number 800.
     */
    private static final int EIGHTHUNDRED = 800;
    /**
     * The number 600.
     */
    private static final int SIXHUNDRED = 600;
    //A list of files used in this class.
    /**
     * Login page fxml file.
     * {@value}
     */

    //private final String loginFXML = "src/main/java/gui/loginView.fxml";

    /**
     * Index page css file.
     * {@value}
     */
    private final String indexCSS = "src/main/java/gui/indexPage.css";

    /**
     * The width dimension of the application window: {@value}.
     */
    private int width = EIGHTHUNDRED;

    /**
     * The height dimension of the application window: {@value}.
     */
    private int height = SIXHUNDRED;
    /**
     * The root of the application.
     */
    private Parent root;
    /**
     * Spring server.
     */
    private ConfigurableApplicationContext springContext;

    /**
     * Main method.
     * @param args program arguments
     */
    public static void main(final String[] args) {

        launch(DemoApplication.class, args);
    }

    /**
     * Boots the spring server and loads in the fxml login page
     * Overrides the init() method in Application.
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DemoApplication.class);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);

        root = fxmlLoader.load(getClass().getResource("/loginView.fxml"));


    }

    /**
     * Applies css file to stage and starts it.
     * @param primaryStage the stage to start.
     * @throws Exception
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("FXML Tryout");
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add((getClass().getResource("/indexPage.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }



    /**
     * Stops the server.
     * @throws Exception
     */
    @Override
    public void stop() {
        springContext.stop();
    }
}
