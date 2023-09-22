import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerSelectorItem {

  @FXML
  private Label title = new Label();

  public void setText(String text) {
    // Estableix el contingut del Label
    this.title.setText(text);
  }

}

