package info.jemsit.components;

import info.jemsit.ApplicationStart;
import info.jemsit.Sides;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.URL;
import java.util.concurrent.CompletableFuture;


public class SideBarComponent extends VBox {


    private Label volumeText;
    private static Label whosTurnStatic; // Static reference

    private Label whosTurn; // Make it an instance variable


    public  MediaPlayer currentMediaPlayer;
    public  Media moveMedia;

    private double currentVolume = 0.5; // Track volume as instance variable


    public SideBarComponent(int width) {
        startBackgroundSound();
        setPrefWidth(width);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #2c3e50; -fx-border-color: #34495e; -fx-border-width: 2px;");
        this.getChildren().addAll(createLabel("Chess Game"));
        setAlignment(Pos.TOP_CENTER);
    }

    private VBox createLabel(String text) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(200);
        Label titleLabel = new Label(text);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);

        volumeText = new Label("Volume" + String.format(": %.1f", currentVolume));
        volumeText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        volumeText.setTextFill(Color.WHITE);
        volumeText.setAlignment(Pos.CENTER);
        whosTurn = new Label("Turn: " + (ApplicationStart.turn == Sides.WHITE ? "White" : "Black"));
        whosTurn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        whosTurn.setTextFill(Color.WHITE);
        whosTurn.setAlignment(Pos.CENTER);
        whosTurnStatic = whosTurn;
        vBox.getChildren().addAll(titleLabel, volumeText, whosTurn);
        vBox.setFillWidth(true);
        vBox.setPrefHeight(100);
        return vBox;
    }

    private Button increaseVolume() {
        Button increaseVolumeButton = new Button("Increase Volume");
        increaseVolumeButton.setOnAction(e -> {
            if (currentVolume < 1.0) {
                currentVolume += 0.1;
                currentVolume = Math.round(currentVolume * 10.0) / 10.0; // Round to avoid floating point errors
                if (currentMediaPlayer != null) {
                    currentMediaPlayer.setVolume(currentVolume);
                }
            }
            this.volumeText.setText(String.format("Volume: %.1f", currentVolume));
        });
        return increaseVolumeButton;
    }

    private Button decreaseVolume() {
        Button decreaseVolumeButton = new Button("Decrease Volume");
        decreaseVolumeButton.setOnAction(e -> {
            if (currentVolume > 0.0) {
                currentVolume -= 0.1;
                currentVolume = Math.round(currentVolume * 10.0) / 10.0; // Round to avoid floating point errors
                if (currentMediaPlayer != null) {
                    currentMediaPlayer.setVolume(currentVolume);
                }
            }
            this.volumeText.setText(String.format("Volume: %.1f", currentVolume));
        });
        return decreaseVolumeButton;
    }

    private void startBackgroundSound() {
        URL soundURL = getClass().getResource("/sounds/background-sound.wav");
        if (soundURL != null) {
            moveMedia = new Media(soundURL.toString());
        } else {
            System.err.println("Sound file not found in resources");
        }

        CompletableFuture.runAsync(() -> {
            Platform.runLater(() -> {
                try {
                    if (moveMedia != null) {
                        currentMediaPlayer = new MediaPlayer(moveMedia);
                        currentMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                        currentMediaPlayer.play();
                        this.getChildren().addAll(increaseVolume(), decreaseVolume());
                    }
                } catch (Exception e) {
                    System.err.println("Error playing background sound: " + e.getMessage());
                }
            });
        });
    }

    public static void updateTurnDisplay() {
        if (whosTurnStatic != null) {
            Platform.runLater(() -> {
                whosTurnStatic.setText("Turn: " + (ApplicationStart.turn == Sides.WHITE ? "White" : "Black"));
                whosTurnStatic.setTextFill(ApplicationStart.turn == Sides.WHITE ? Color.LIGHTBLUE : Color.LIGHTCORAL);
            });
        }
    }


}
