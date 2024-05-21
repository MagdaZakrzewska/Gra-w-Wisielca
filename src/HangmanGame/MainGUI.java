package HangmanGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

    Stage window;
    Scene ekranGlowny, ekranGry, ekranBazySlow;
    Button nowaGra, baza, wyjscie, powrot, powrot1;
    ListView<String> listView;
    BazaSlow bazaSlow;

    public static void main(String[] args) {
        launch(args); //uruchamia aplikacje javafx
    }

    @Override //definiuje główny interfejs użytkownika
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Wisielec");
        
        Label label1 = new Label("Witaj w grze Wisielec!");   
       
        Button nowaGra = new Button("Nowa Gra");
        nowaGra.setOnAction(e -> {
            NowaGra nowaGraInstance = new NowaGra(bazaSlow, primaryStage, () -> window.setScene(ekranGlowny));
            window.setScene(nowaGraInstance.getScene());
        });


        Button baza = new Button("Baza słów");
        baza.setOnAction(e -> window.setScene(bazaSlow.getScene()));
        bazaSlow = new BazaSlow(primaryStage, () -> window.setScene(ekranGlowny));

        wyjscie = new Button();
        wyjscie.setText("Wyjście z gry");
        wyjscie.setOnAction(e -> window.close());//wywołanie akcji na przycisku

        HBox downMenu = new HBox(20);
        downMenu.getChildren().addAll(baza,wyjscie);
        downMenu.setAlignment(Pos.CENTER);
        
        VBox nowagra = new VBox(10);
        nowagra.getChildren().addAll(label1,nowaGra);
        nowagra.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(downMenu);
        borderPane.setCenter(nowagra);
        ekranGlowny = new Scene(borderPane, 600, 300);

        window.setScene(ekranGlowny); //"pobranie" sceny
        window.show(); //wyswietlenie stage uzytkownikowi
   }
    
}
