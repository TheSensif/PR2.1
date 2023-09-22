import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControllerMobileItem implements Initializable {

    @FXML
    private VBox yPane = new VBox();

    @FXML 
    private Button bBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {   
        
    }

    void loadItems(String key) {

        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
    
        // Obtenir les dades de l'opció seleccionada
        JSONArray dades = appData.getData(key);

        URL resource = this.getClass().getResource("assets/template_list_item.fxml");
        
        try {
        yPane.getChildren().clear();
        
        for (int i = 0; i < dades.length(); i++) {
            JSONObject consoleObject = dades.getJSONObject(i);
          
            if (consoleObject.has("nom")) {
              String nom = consoleObject.getString("nom");
              String imatge = "assets/images/" + consoleObject.getString("imatge");
              FXMLLoader loader = new FXMLLoader(resource);
              Parent itemTemplate;
                itemTemplate = loader.load();
                ControllerListItem itemController = loader.getController();
                itemController.setText(nom);
                itemController.setImage(imatge);
                final String type = key;
                final int index = i;
                itemTemplate.setOnMouseClicked(event -> {
                    ControllerMobileInfo cmi = (ControllerMobileInfo) UtilsViews.getController("MobileInfo");
                    cmi.showInfo(type, index);
                    UtilsViews.setViewAnimating("MobileInfo");
                });
                yPane.getChildren().add(itemTemplate);
              
            }
        }    

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }

    @FXML
    private void backControler() {
      UtilsViews.setViewAnimating("Mobile0");
    }
}
