package info.jemsit.chessFigure;

import info.jemsit.ApplicationStart;
import info.jemsit.Sides;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import info.jemsit.components.SideBarComponent;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class ChessFigureImpl extends StackPane implements ChessFigure {

    public double mouseX;
    public double mouseY;
    public FigureTypes type;
    public int XCoordinate;
    public int YCoordinate;
    public boolean isWhite;

    private static MediaPlayer currentMediaPlayer;
    private static Media moveMedia;


    @Override
    public void handleMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
        toFront();
        PreMoveFigure preMoveThread = new PreMoveFigure(false, this, isWhite);
        preMoveThread.addPreMove();
    }

    @Override
    public void handleMouseDragged(MouseEvent mouseEvent) {
        setLayoutX(mouseEvent.getSceneX() - mouseX);
        setLayoutY(mouseEvent.getSceneY() - mouseY);
    }

    @Override
    public void handleMouseDragExited(MouseEvent mouseEvent) {
        double snappedX = Math.round(getLayoutX() / ApplicationStart.TILE_SIZE) * ApplicationStart.TILE_SIZE;
        double snappedY = Math.round(getLayoutY() / ApplicationStart.TILE_SIZE) * ApplicationStart.TILE_SIZE;

        if (canNotMoveTo((int) (snappedX / ApplicationStart.TILE_SIZE), (int) (snappedY / ApplicationStart.TILE_SIZE)) || ApplicationStart.turn != (isWhite ? Sides.WHITE : Sides.BLACK)) {
            snappedX = this.XCoordinate * ApplicationStart.TILE_SIZE;
            snappedY = this.YCoordinate * ApplicationStart.TILE_SIZE;
        } else {
            makeMoveSound();
        }

        TranslateTransition transition = new TranslateTransition(Duration.millis(50), this);
        transition.setToX(snappedX - getLayoutX());
        transition.setToY(snappedY - getLayoutY());

        double finalSnappedX = snappedX;
        double finalSnappedY = snappedY;

        transition.setOnFinished(e -> {
            setLayoutX(finalSnappedX);
            setLayoutY(finalSnappedY);
            setTranslateX(0);
            setTranslateY(0);
        });

        transition.play();
        this.XCoordinate = (int) (snappedX / ApplicationStart.TILE_SIZE);
        this.YCoordinate = (int) (snappedY / ApplicationStart.TILE_SIZE);

        PreMoveFigure.clearPreMove();
        ApplicationStart.turn = isWhite ? Sides.BLACK : Sides.WHITE;
        SideBarComponent.updateTurnDisplay();

    }

    @Override
    public FigureTypes getFigureType() {
        return type;
    }

    @Override
    public int getXCoordinate() {
        return XCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public boolean canNotMoveTo(int x, int y) {
        return ApplicationStart.hasFigureAt(x, y) != null || !ApplicationStart.hasPreMoveFigureAt(x, y);
    }

    static {
        try {
            File moveSound = new File("/home/jemsit/Desktop/JavaFXProjects/ChessTest/ChessPremove/src/main/resources/sounds/kiss-move.wav");
            if (moveSound.exists()) {
                moveMedia = new Media(moveSound.toURI().toString());
            }
        } catch (Exception e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }

    private static void makeMoveSound() {
        CompletableFuture.runAsync(() -> {
            Platform.runLater(() -> {
                try {
                    // Stop current sound
                    if (currentMediaPlayer != null) {
                        currentMediaPlayer.stop();
                        currentMediaPlayer.dispose();
                    }

                    // Play new sound
                    if (moveMedia != null) {
                        currentMediaPlayer = new MediaPlayer(moveMedia);
                        currentMediaPlayer.setOnEndOfMedia(() -> {
                            currentMediaPlayer.dispose();
                            currentMediaPlayer = null;
                        });
                        currentMediaPlayer.play();
                    }
                } catch (Exception e) {
                    System.err.println("Error playing sound: " + e.getMessage());
                }
            });
        });
    }
}
