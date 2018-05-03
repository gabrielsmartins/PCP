package br.ifsp.edu.pcp.main;

import br.ifsp.edu.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author HOME-PC
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       LoginView loginView = new LoginView();
       loginView.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
