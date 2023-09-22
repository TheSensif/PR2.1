import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.Initializable;

public class ControllerDesktop implements Initializable{

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private VBox yPane = new VBox();

    @FXML
    private AnchorPane info;

    String opcions[] = { "Personatges", "Jocs", "Consoles" };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Auto-generated method stub
        choiceBox.getItems().addAll(opcions); 

        choiceBox.setValue(opcions[0]);

        choiceBox.setOnAction((event) -> {loadList();});
        loadList();
    }

    public void loadList() {
        String opcio = choiceBox.getValue();
        AppData appData = AppData.getInstance();
        showLoading();
        appData.load(opcio, (result) -> {
            if (result == null) {
                System.out.println("ControllerDesktop: Error loading data.");
              } else {
                try {
                    showList(opcio);
                } catch (Exception e) {
                    System.out.println("ControllerDesktop: Error showing list.");
                }
              }
            });
    }
    
    public void showLoading() {

        // Esborrar la llista actual
        yPane.getChildren().clear();
        
        // Afegeix un indicador de progrés com a primer element de la llista
        ProgressIndicator progressIndicator = new ProgressIndicator();
        yPane.getChildren().add(progressIndicator);
        }
        

    public void showList(String opcioCarregada) throws IOException {

        // Si s'ha carregat una altra opció, no cal fer res
        // (perquè el callback pot arribar després de que l'usuari hagi canviat d'opció)
        String opcioSeleccionada = choiceBox.getValue();
       
        if (opcioCarregada.compareTo(opcioSeleccionada) != 0) {
          return;
        }
    
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
    
        // Obtenir les dades de l'opció seleccionada
        JSONArray dades = appData.getData(opcioCarregada);

        URL resource = this.getClass().getResource("assets/template_list_item.fxml");

        yPane.getChildren().clear();

    // Carregar la llista amb les dades
    for (int i = 0; i < dades.length(); i++) {
        JSONObject consoleObject = dades.getJSONObject(i);
      
        if (consoleObject.has("nom")) {
          String nom = consoleObject.getString("nom");
          String imatge = "assets/images/" + consoleObject.getString("imatge");
          FXMLLoader loader = new FXMLLoader(resource);
          Parent itemTemplate = loader.load();
          ControllerListItem itemController = loader.getController();
          itemController.setText(nom);
          itemController.setImage(imatge);
          final String type = opcioSeleccionada;
          final int index = i;
          itemTemplate.setOnMouseClicked(event -> {
            showInfo(type, index);
          });
          
          yPane.getChildren().add(itemTemplate);
        }
      }
    
    }
  
    void showInfo(String type, int index) {

        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
      
        // Obtenir les dades de l'opció seleccionada
        JSONObject dades = appData.getItemData(type, index);

        // type == jocs / personatges / consola

        System.out.println("Tipo: " + type);
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
      
        // Carregar la plantilla
       
      
        // Esborrar la informació actual
        
    
    // Carregar la llista amb les dades
    }

}