package info.jemsit.chessFigure;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ChessFigureImpl extends StackPane implements ChessFigure {

    public double mouseX;
    public double mouseY;
    public FigureTypes type;
    public int XCoordinate;
    public int YCoordinate;
    public boolean isWhite;


    @Override
    public void handleMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
        PreMoveFigure preMoveThread = new  PreMoveFigure(this, isWhite);
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

        if (canNotMoveTo((int) (snappedX / ApplicationStart.TILE_SIZE),(int) (snappedY / ApplicationStart.TILE_SIZE))) {
            snappedX = this.XCoordinate * ApplicationStart.TILE_SIZE;
            snappedY = this.YCoordinate * ApplicationStart.TILE_SIZE;
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
        return ApplicationStart.hasFigureAt(x, y) || !ApplicationStart.hasPreMoveFigureAt(x, y);
    }
}
