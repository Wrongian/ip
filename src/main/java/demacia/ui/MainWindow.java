package demacia.ui;

import demacia.Demacia;
import demacia.DemaciaResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Demacia demacia;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image demaciaImage = new Image(this.getClass().getResourceAsStream("/images/demacia.png"));

    /**
     * Initialise the main GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /**
     * Injects the Demacia instance.
     * */
    public void setDemacia(Demacia demacia) {
        this.demacia = demacia;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DemaciaResponse response = demacia.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDemaciaDialog(response.getResponse(), this.demaciaImage)
        );
        userInput.clear();
        if (response.getIsExit()) {
            // close program
            Platform.exit();
        }
    }

    /**
     * Shows the greeting for the bot as a dialog box.
     */
    public void showGreeting() {
        DialogBox greetingDialogBox = DialogBox.getDemaciaDialog(this.demacia.getGreeting(), this.demaciaImage);
        this.dialogContainer.getChildren().addAll(greetingDialogBox);
    }
}
