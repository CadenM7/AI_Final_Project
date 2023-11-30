package Blackjack.GUI;

import Blackjack.Game.Blackjack;
import Blackjack.Game.BlackjackAI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.File;

public class BlackjackController {

    @FXML
    private Label label;

    @FXML
    private Label playerScore;

    @FXML
    private Label dealerScore;

    @FXML
    private HBox playerCards;

    @FXML
    private HBox dealersHand;

    @FXML
    private Button hit;

    @FXML
    private Button stand;

    @FXML
    private Button play;

    @FXML
    private TextField numOfGames;

    private Blackjack blackjack;

    private BlackjackAI blackjackAi;

    @FXML
    public void initialize() {
        blackjack = new Blackjack(52);
        blackjackAi = new BlackjackAI(170, 2, 2, 5, 3, .5);
        updateView();
        System.out.println("Player " + blackjack.getPlayerHandValue());
        System.out.println("Dealer " + blackjack.getPlayer2HandValue());
    }

    private void playGame() {
        boolean gameOver = false;
        while(!gameOver) {
            blackjackAi.senseActLearn(blackjack.getPlayer2HandValue() - 2 * 17 + blackjack.getPlayerHandValue(), 0);
            
        }
    }
    @FXML
    public void aiPlay() {
        int points = 0;
        int games = Integer.parseInt(numOfGames.getText());
        for(int i = 0; i < games; i++){
            if(blackjack.getPlayerHandValue() == 21 || blackjack.getPlayer2HandValue() > 21) {
                points += 1;
                reset();
            }
            if(blackjack.getPlayer2HandValue() == 21 || blackjack.getPlayerHandValue() > 21) {
                points -= 3;
                reset();
            }
        }
    }

    @FXML
    public void reset() {
        blackjack = new Blackjack(52);
        playerCards.getChildren().clear();
        dealersHand.getChildren().clear();
        disableButton(false);
        updateView();
        label.setText("BlackJack");
        System.out.println("Player " + blackjack.getPlayerHandValue());
        System.out.println("Dealer " + blackjack.getPlayer2HandValue());
        if(blackjack.getPlayer2HandValue() == 21) {
            disableButton(true);
            label.setText("LOSE, Play Again?");
        }
        if(blackjack.getPlayerHandValue() == 21) {
            disableButton(true);
            label.setText("WON, Play Again?");
        }
        if(blackjack.getPlayerHandValue() > 21) {
            disableButton(true);
            label.setText("LOSE, Play Again?");
        }
        if(blackjack.getPlayer2HandValue() > 21) {
            disableButton(true);
            label.setText("WON, Play Again?");
        }
    }

    public void updateView() {
        playerCards.getChildren().clear();
        dealersHand.getChildren().clear();
        for(int x = 0; x < blackjack.handSize(); x++) {
            ImageView iv = new ImageView();
            playerCards.getChildren().add(iv);
            setCardImage(blackjack.getCard(x).ab(), iv);
        }
        for(int x = 0; x < blackjack.dealersHand(); x++) {
            ImageView iv2 = new ImageView();
            dealersHand.getChildren().add(iv2);
            setCardImage(blackjack.getDealerCard(x).ab(), iv2);

        }
        getDealerHandValue();
        getPlayerHandValue();

    }
    public void setCardImage(String top, ImageView cardImage) {
        File c = new File("src/Blackjack/Cards/" + top + ".png");
        //System.out.println(c.toURI());
        cardImage.setImage(new Image(c.toURI().toString()));
    }

    public void disableButton(boolean disable) {
        hit.setDisable(disable);
        stand.setDisable(disable);
    }

    @FXML
    public void hit() {
        blackjack.playerHit();
        updateView();
        getPlayerHandValue();
        System.out.println("Player " + blackjack.getPlayerHandValue());
        if(blackjack.getPlayerHandValue() > 21) {
            disableButton(true);
            label.setText("LOSE, Play Again?");
        }
        if(blackjack.getPlayerHandValue() == 21) {
            disableButton(true);
            label.setText("WON, Play Again?");
        }
    }

    @FXML
    public void stand() {
        blackjack.playerStand();
        disableButton(true);
        updateView();

        while (blackjack.getPlayer2HandValue() < 17) {
            if (blackjack.getPlayer2HandValue() == 17) {
                blackjack.dealerStand();
            } else {
                blackjack.dealerHit();
            }
            updateView();
        }
            System.out.println("Dealer " + blackjack.getPlayer2HandValue());
            if (blackjack.getPlayer2HandValue() > 21) {
                label.setText("WON, Play Again?");
            }
            if (blackjack.getPlayer2HandValue() < blackjack.getPlayerHandValue() && blackjack.getPlayerHandValue() < 21) {
                label.setText("WON, Play Again?");
            }
            if (blackjack.getPlayer2HandValue() == 21) {
                label.setText("LOSE, Play Again?");
            }
            if (blackjack.getPlayer2HandValue() > blackjack.getPlayerHandValue() && blackjack.getPlayer2HandValue() < 21) {
                label.setText("LOSE, Play Again?");
            }
            if (blackjack.getPlayer2HandValue() == blackjack.getPlayerHandValue()) {
                label.setText("DRAW, Play Again?");
            }
            updateView();
        }
    @FXML
    public void getPlayerHandValue() {
        playerScore.setText(String.valueOf(blackjack.getPlayerHandValue()));
    }
    @FXML
    public void getDealerHandValue() {
         dealerScore.setText(String.valueOf(blackjack.getPlayer2HandValue()));
    }

}

