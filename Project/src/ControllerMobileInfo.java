import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControllerMobileInfo implements Initializable {

    @FXML
    private AnchorPane info;

    @FXML 
    private Button bBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {   
        
    }

    void showInfo(String type, int index) {

        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
      
        // Obtenir les dades de l'opció seleccionada
        JSONObject dades = appData.getItemData(type, index);

        URL resource;
        switch (type.toLowerCase()) {
            case "personatges":
                resource = this.getClass().getResource("assets/template_info_item_personatges.fxml");
                info.getChildren().clear();
                try {
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerInfoItemPersonatges itemController = loader.getController();
                    itemController.setImage("assets/images/" + dades.getString("imatge"));
                    itemController.setTitle(dades.getString("nom"));
                    itemController.setText(dades.getString("nom_del_videojoc"));
                    itemController.setColor(dades.getString("color"));
                    
                
                    // Afegeix la informació a la vista
                    info.getChildren().add(itemTemplate);
                
                    // Estableix que la mida de itemTemplaate s'ajusti a la mida de info
                    AnchorPane.setTopAnchor(itemTemplate, 0.0);
                    AnchorPane.setRightAnchor(itemTemplate, 0.0);
                    AnchorPane.setBottomAnchor(itemTemplate, 0.0);
                    AnchorPane.setLeftAnchor(itemTemplate, 0.0);
                
                    } catch (Exception e) {
                      System.out.println("ControllerDesktop: Error showing info.");
                      System.out.println(e);
                    }
                  
                break;
            case "jocs":
                resource = this.getClass().getResource("assets/template_info_item_joc.fxml");
                info.getChildren().clear();
                try {
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerInfoItemJoc itemController = loader.getController();
                    System.out.println(dades.getString("nom") + "\n" + dades.getString("descripcio"));
                    itemController.setImage("assets/images/" + dades.getString("imatge"));
                    itemController.setTitle(dades.getString("nom"));
                    itemController.setText(dades.getString("descripcio"));
                    itemController.setYear(Integer.toString(dades.getInt("any")));
                    itemController.setPlataform(dades.getString("tipus"));
                
            
                    // Afegeix la informació a la vista
                    info.getChildren().add(itemTemplate);
                
                    // Estableix que la mida de itemTemplaate s'ajusti a la mida de info
                    AnchorPane.setTopAnchor(itemTemplate, 0.0);
                    AnchorPane.setRightAnchor(itemTemplate, 0.0);
                    AnchorPane.setBottomAnchor(itemTemplate, 0.0);
                    AnchorPane.setLeftAnchor(itemTemplate, 0.0);
            
                } catch (Exception e) {
                  System.out.println("ControllerDesktop: Error showing info.");
                  System.out.println(e);
                }
              
                break;
            case "consoles":
                resource = this.getClass().getResource("assets/template_info_item_consola.fxml");
                info.getChildren().clear();
                try {
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerInfoItemConsola itemController = loader.getController();
                    itemController.setImage("assets/images/" + dades.getString("imatge"));
                    itemController.setTitle(dades.getString("nom"));
                    itemController.setVenudes(Integer.toString(dades.getInt("venudes")));
                    itemController.setDate(dades.getString("data"));
                    itemController.setText(dades.getString("procesador"));
                    itemController.setColor(dades.getString("color"));
                
            
                    // Afegeix la informació a la vista
                    info.getChildren().add(itemTemplate);
                
                    // Estableix que la mida de itemTemplaate s'ajusti a la mida de info
                    AnchorPane.setTopAnchor(itemTemplate, 0.0);
                    AnchorPane.setRightAnchor(itemTemplate, 0.0);
                    AnchorPane.setBottomAnchor(itemTemplate, 0.0);
                    AnchorPane.setLeftAnchor(itemTemplate, 0.0);
            
                } catch (Exception e) {
                  System.out.println("ControllerDesktop: Error showing info.");
                  System.out.println(e);
                }
              
                break;
        }
    }

    @FXML
    private void backControler() {
      UtilsViews.setViewAnimating("MobileItem");
    }
}
