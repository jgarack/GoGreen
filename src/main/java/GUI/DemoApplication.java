package GUI;

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

import java.io.File;
import java.net.URL;


@SpringBootApplication
@ComponentScan(basePackageClasses = GreetingController.class)
public class DemoApplication extends Application {

    private Parent root;
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception{
        springContext = SpringApplication.run(DemoApplication.class);
        URL url = new File("src/main/java/GUI/loginView.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);

        root = FXMLLoader.load(url);


    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("FXML Tryout");
        Scene scene = new Scene(root, 800, 600);
        URL url = new File("src/main/java/GUI/indexPage.css").toURL();
        scene.getStylesheets().add(url.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception{
        springContext.stop();
    }


    public static void main(String[] args) {

        launch(DemoApplication.class,args);
    }
}
