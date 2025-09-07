package info.jemsit.chessFigure;

import info.jemsit.ApplicationStart;
import info.jemsit.Sides;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import info.jemsit.chessFigure.premove.PreMoveSquare;
import info.jemsit.components.SideBarComponent;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import static info.jemsit.ApplicationStart.TILE_SIZE;

public class ChessFigureImpl extends StackPane implements ChessFigure {

    public double mouseX;
    public double mouseY;
    public FigureTypes type;
    public int XCoordinate;
    public int YCoordinate;
    public boolean isWhite;

    private MediaPlayer currentMediaPlayer;
    private Media moveMedia;

    public ChessFigureImpl() {
        setDefaultMoveSound();
    }

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
        double snappedX = Math.round(getLayoutX() / TILE_SIZE) * TILE_SIZE;
        double snappedY = Math.round(getLayoutY() / TILE_SIZE) * TILE_SIZE;

        if (ApplicationStart.turn == (isWhite ? Sides.WHITE : Sides.BLACK) && canCaptureAt((int) (snappedX / TILE_SIZE), (int) (snappedY / TILE_SIZE))) {
            capture((int) (snappedX / TILE_SIZE), (int) (snappedY / TILE_SIZE));
            makeCaptureSound();
        } else if (ApplicationStart.turn != (isWhite ? Sides.WHITE : Sides.BLACK) || canNotMoveTo((int) (snappedX / TILE_SIZE), (int) (snappedY / TILE_SIZE))) {
            snappedX = this.XCoordinate * TILE_SIZE;
            snappedY = this.YCoordinate * TILE_SIZE;
        } else {
            makeMoveSound();
            ApplicationStart.turn = isWhite ? Sides.BLACK : Sides.WHITE;
            SideBarComponent.updateTurnDisplay();
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
        this.XCoordinate = (int) (snappedX / TILE_SIZE);
        this.YCoordinate = (int) (snappedY / TILE_SIZE);
        PreMoveFigure.clearPreMove();
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
    public void capture(int x, int y) {
        StackPane figureToCapture = null;
        for (Node node : ApplicationStart.figureGroup.getChildren()) {
            if (node instanceof StackPane) {
                if (node.getLayoutX() / TILE_SIZE == x && node.getLayoutY() / TILE_SIZE == y) {
                    figureToCapture = (ChessFigureImpl) node;
                    break;
                }
            }
        }
        if (figureToCapture != null) {
            ApplicationStart.figureGroup.getChildren().remove(figureToCapture);
        }
        ApplicationStart.turn = isWhite ? Sides.BLACK : Sides.WHITE;
        SideBarComponent.updateTurnDisplay();
    }

    @Override
    public boolean canNotMoveTo(int x, int y) {
        return ApplicationStart.hasFigureAt(x, y) != null || !ApplicationStart.hasPreMoveFigureAt(x, y);
    }

    public void setDefaultMoveSound() {
        URL soundURL = ChessFigureImpl.class.getResource("/sounds/kiss-move.wav");
        this.moveMedia = new Media(soundURL.toString());
    }
    public void setCaptureSound() {
        URL soundURL = ChessFigureImpl.class.getResource("/sounds/shot-move.wav");
        this.moveMedia = new Media(soundURL.toString());
    }

    private void makeMoveSound() {
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

    private void makeCaptureSound() {
        setCaptureSound();
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
                        setDefaultMoveSound();
                    }
                } catch (Exception e) {
                    System.err.println("Error playing sound: " + e.getMessage());
                }
            });
        });
    }

    public boolean canCaptureAt(int x, int y) {
        for (Node node : ApplicationStart.premovefigureGroup.getChildren()) {
            if (node instanceof StackPane) {
                if (node.getLayoutX() / TILE_SIZE == x && node.getLayoutY() / TILE_SIZE == y) {
                    return node instanceof PreMoveSquare;
                }
            }
        }
        return false;
    }
}
