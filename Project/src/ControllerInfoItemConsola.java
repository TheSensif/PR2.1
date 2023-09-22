import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ControllerInfoItemConsola {
    @FXML
    private ImageView img;
  
    @FXML
    private Label title = new Label();

    @FXML
    private Label date = new Label();

    @FXML
    private Label consolBuy = new Label();
    
    @FXML
    private Circle coloredShape = new Circle();

    @FXML
    private Label text = new Label();
  
    public void setImage(String resourceName) {
  
      // Obté una referència al recurs dins del .jar
      ClassLoader classLoader = getClass().getClassLoader();
      Image image = new Image(classLoader.getResourceAsStream(resourceName));
  
      // Estableix la imatge a l'ImageView
      img.setImage(image);
    }
  
    public void setTitle(String text) {
  
      // Estableix el contingut del Label
      this.title.setText(text);
    }

    public void setDate(String text) {
  
      // Estableix el contingut del Label
      this.date.setText(text);
    }

    public void setVenudes(String venudes) {
  
      // Estableix el contingut del Label
      this.consolBuy.setText(venudes);
    }
  
    public void setText(String text) {
  
      // Estableix el contingut del Label
      this.text.setText(text);
    }

    public void setColor(String c) {
        this.coloredShape.setStyle("-fx-fill: " + c);
    }
}
