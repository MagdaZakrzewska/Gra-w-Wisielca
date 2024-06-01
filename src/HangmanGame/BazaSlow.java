package HangmanGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BazaSlow {
    List<String> words = new ArrayList<>
    (List.of("kot","robot","papuga","pies","komputer","butelka","jajko","robak","miska","plecak"));
    private Scene ekranBazySlow;

    public BazaSlow(Stage primaryStage, Runnable backtoMainScene){
        Stage window = primaryStage;
        window.setTitle("Wisielec");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(4);
        grid.setHgap(5);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(getWords());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        GridPane.setConstraints(listView, 0, 4);

        TextField wordInput = new TextField();
        GridPane.setConstraints(wordInput, 0, 9);

        Button addword = new Button("Dodaj");
        GridPane.setConstraints(addword, 2,9);
        addword.setOnAction(e -> {
            String newWord = wordInput.getText();
            if (newWord != null && !newWord.isEmpty()) { //sprawdzenie czy wpisano słowo w textfield
                addWord(newWord);
                listView.getItems().add(newWord);
                wordInput.clear();
            }
        });

        Button removeword = new Button("Usuń");
        GridPane.setConstraints(removeword, 6, 9);
        removeword.setOnAction(e -> {
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            if (selectedWord != null) {
                removeWord(selectedWord);
                listView.getItems().remove(selectedWord);
            }
        });

        Button powrot = new Button();
        powrot.setText("Powrót do ekranu głównego");
        powrot.setOnAction(e -> backtoMainScene.run());
        GridPane.setConstraints(powrot, 9, 9);

        ekranBazySlow = new Scene(grid,600,300);

        grid.getChildren().addAll(listView,wordInput,addword,removeword,powrot);

    }

    public Scene getScene(){
        return ekranBazySlow;
    }
    
    

    public void addWord(String word) {
        words.add(word);
    }

    public void removeWord(String word) {
        words.remove(word);
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}

    
