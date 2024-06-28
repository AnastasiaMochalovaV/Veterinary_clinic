import Controller.HomePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;
import Controller.HomePage;
import Controller.LoginPage;

public class Main extends Application{
    Model model = new Model();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Parent root = loader.load();

        HomePage controller = loader.getController();

        controller.setModel(model, primaryStage);

        primaryStage.setTitle("Ветеринарная клиника");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}