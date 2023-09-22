import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ControllerMobile0 implements Initializable {

    @FXML
    private VBox yPane = new VBox();

    String opcions[] = { "Personatges", "Jocs", "Consoles" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        showList();
    }


    void showList() {
        AppData appData = AppData.getInstance();
        URL resource = this.getClass().getResource("assets/template_selector_item.fxml");

        for (int i = 0; i < opcions.length; i++) {
            String nom = opcions[i];
            appData.load(nom, (result) -> {
                if (result == null) {
                    System.out.println("ControllerDesktop: Error loading data.");
                  } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(resource);
                        try {
                            Parent itemTemplate = loader.load();
                            ControllerSelectorItem selectorItem = loader.getController();
                            selectorItem.setText(nom);
                            final String type = nom;
                            itemTemplate.setOnMouseClicked(event -> {
                                showOption(type);
                            });
                            yPane.getChildren().add(itemTemplate);
            
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        System.out.println("ControllerDesktop: Error showing list.");
                    }
                }
            });
            
        }
    }
    void showOption(String type) {
        ControllerMobileItem cmi = (ControllerMobileItem) UtilsViews.getController("MobileItem");
        cmi.loadItems(type);
        UtilsViews.setViewAnimating("MobileItem");
    }
    
}
