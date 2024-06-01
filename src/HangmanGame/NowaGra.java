package HangmanGame;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NowaGra {
    private Scene ekranGry;
    private String word;
    private char[] userWord;
    private int lives = 8;
    private BazaSlow bazaSlow;
    private ImageView imageView;
    private int wins;
    private int losses;
    private int attempts;
    private int totalAttempts;
    private Label winsLabel;
    private Label lossesLabel;
    private Label triesLabel;
    private Label tryLabel;
    private Label secretWord;
    private Label infoLabel;
    private Button guessButton;
    private TextField guessInput;
    private boolean gameEnded = false;
    

    public NowaGra(BazaSlow bazaSlow, Stage primaryStage, Runnable backtoMainScene){
        Stage window = primaryStage;
        this.bazaSlow = bazaSlow;
        initializeGame(); 
        imageView = new ImageView();
        updateHangmanImage(0);

        window.setTitle("Wisielec");
    
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(3);
        grid.setHgap(3);

        
        Label guessLabel = new Label("Podaj kolejną literę: ");
        GridPane.setConstraints(guessLabel, 0, 0);

        //pole do wpisywania litery
        guessInput = new TextField();
        GridPane.setConstraints(guessInput, 0, 1);

        infoLabel = new Label();
        GridPane.setConstraints(infoLabel, 0, 2);

        secretWord = new Label();
        secretWord.setText(String.valueOf(userWord));
        GridPane.setConstraints(secretWord, 0, 3);

        tryLabel = new Label("Pozostała liczba prób: " + lives);
        GridPane.setConstraints(tryLabel, 0, 4);

        guessButton = new Button("Zgadnij!");
        GridPane.setConstraints(guessButton, 1,1);
        guessButton.setOnAction(e -> play());

        Button powrot = new Button();
        powrot.setText("Powrót do ekranu głównego");
        GridPane.setConstraints(powrot, 1, 2);
        powrot.setOnAction(e -> backtoMainScene.run());

        winsLabel = new Label("Ilość zwycięstw: ");
        lossesLabel = new Label("Ilość porażek: ");
        triesLabel = new Label("Średnia liczba prób: ");
        GridPane.setConstraints(winsLabel, 5, 10);
        GridPane.setConstraints(lossesLabel, 5, 11);
        GridPane.setConstraints(triesLabel, 5, 12);
        
        imageView.setFitWidth(200); // Szerokość obrazu
        imageView.setFitHeight(150); // Wysokość obrazu
        imageView.setPreserveRatio(true);


        grid.getChildren().addAll(guessLabel,guessInput,guessButton,tryLabel,secretWord,infoLabel,powrot,imageView,winsLabel,lossesLabel,triesLabel);
        ekranGry = new Scene(grid,600,400); //utworzenie gridpane dla ekranu rozgrywki
        window.setScene(ekranGry);
    }

        private void initializeGame(){
            word = bazaSlow.getRandomWord();
            userWord = new char[word.length()];
            Arrays.fill(userWord,'_');
            gameEnded = false;
            attempts = 0;
        }

        private void checkLetter(char letter, Label info, Label secretWord, Label tryLabel){
            letter = Character.toLowerCase(letter);
            boolean foundLetter = false;
            for (int i = 0; i < word.length(); i++){
                if(word.charAt(i) == letter){
                    userWord[i] = letter;
                    foundLetter = true;
                    
                }
            }
            if (foundLetter) {
                secretWord.setText(String.valueOf(userWord));
                info.setText("Dobrze! Litera " + letter + " występuje w słowie.");
            }else {
                    attempts++;
                    lives--;
                    tryLabel.setText("Pozostała liczba prób: " + lives);
                    info.setText("Pudło! W tym słowie nie ma litery " + letter + ".");
                    updateHangmanImage(8 - lives);
                } 
                if (lives == 0 || Arrays.equals(userWord, word.toCharArray())) {
                    gameEnded = true;
                    guessButton.setText("Nowa Gra");
                    guessButton.setOnAction(e -> resetGame());
                }
            }

            private void updateGameState(Label info) {
                if (lives == 0) {
                    info.setText("Niestety nie udało się! Spróbuj jeszcze raz.");
                    losses++;   
                    totalAttempts += attempts;                
                } else if (word.equals(String.valueOf(userWord))) {
                    info.setText("Gratulacje! Odgadłeś poprawnie słowo: " + String.valueOf(userWord));
                    wins++;  
                    totalAttempts += attempts;                  
                }
                updateStatistics();
             }

            public double getAverageAttempts() {
                if (wins + losses == 0) return 0;
                return (double) totalAttempts / wins + losses;
            }
            
            private void resetGame() {
                initializeGame();
                lives = 8;
                updateHangmanImage(0);
                // Zaktualizuj etykiety stanu gry (np. pozostała liczba prób) i ukryj informacje o słowie
                tryLabel.setText("Pozostała liczba prób: " + lives);
                secretWord.setText(String.valueOf(userWord));
                infoLabel.setText("");
                guessInput.clear();
                guessButton.setText("Zgadnij!");
                guessButton.setOnAction(e -> play());
                gameEnded = false;
                }

            private void updateHangmanImage(int stage) {
                    String imagePath = "images/hangman" + stage + ".png";
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    imageView.setImage(image);
                }
    
        
            public Scene getScene() {
                return ekranGry;
            }


            public void play(){
                if (!guessInput.getText().isEmpty() && !gameEnded) {
                    char letter = guessInput.getText().charAt(0);
                    checkLetter(letter, infoLabel, secretWord, tryLabel);
                    updateGameState(infoLabel);
                    guessInput.clear();
                    }
                }
                private void updateStatistics() {
                    winsLabel.setText("Ilość zwycięstw: " + wins);
                    lossesLabel.setText("Ilość porażek: " + losses);
                    triesLabel.setText("Średnia liczba prób: " + getAverageAttempts());
                }
            


           
        }
        
        
        
        


