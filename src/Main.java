import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/HomePage.fxml"));
//        Parent root = loader.load();

        Parent root = FXMLLoader.load(getClass().getResource("View/HomePage.fxml"));

//        Model model = new Model();
//        controller.initModel(model, primaryStage);
//        model.getAllBikeModels();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setTitle("Veterinary clinic");
        primaryStage.show();
    }
}