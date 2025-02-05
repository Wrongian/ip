package demacia;

import java.io.IOException;

import demacia.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class representing the entire application
 */
public class Main extends Application {
    private final Demacia demacia = new Demacia();

    /**
     * Starts the application.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDemacia(demacia);
            stage.show();
            fxmlLoader.<MainWindow>getController().showGreeting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

