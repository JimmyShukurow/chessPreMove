package info.jemsit.chessFigure;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class KnightFigure extends StackPane implements ChessFigure {
    private int XCoordinate;
    private int YCoordinate;
    private boolean isWhite;
    private double mouseX;
    private double mouseY;

    private FigureTypes type;


    public KnightFigure(int x, int y, boolean isWhite, boolean preMove) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        this.isWhite = isWhite;
        type = FigureTypes.KNIGHT;

        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);

        Image image = new Image("images/knightb.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        ImageView imageView = new ImageView(image);

        if (preMove){
            imageView.setOpacity(0.3);
        }
        getChildren().add(imageView);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }


    @Override
    public void handleMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
        Thread preMoveThread = new Thread(new PreMoveFigure(this));
        preMoveThread.start();
    }

    @Override
    public void handleMouseDragged(MouseEvent mouseEvent) {
        setLayoutX(mouseEvent.getSceneX() - mouseX);
        setLayoutY(mouseEvent.getSceneY() - mouseY);
        updateChesBoard(ApplicationStart.chessBoard, true);
    }

    @Override
    public void handleMouseDragExited(MouseEvent mouseEvent) {
        double snappedX = Math.round(getLayoutX() / ApplicationStart.TILE_SIZE) * ApplicationStart.TILE_SIZE;
        double snappedY = Math.round(getLayoutY() / ApplicationStart.TILE_SIZE) * ApplicationStart.TILE_SIZE;

        if (ApplicationStart.chessBoard[(int) (snappedY / ApplicationStart.TILE_SIZE)][(int) (snappedX / ApplicationStart.TILE_SIZE)] != 0) {
            // If the tile is occupied, revert to the previous position
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
        updateChesBoard(ApplicationStart.chessBoard, false);

        PreMoveFigure.clearPreMove();

        ApplicationStart.displayChessBoard();
    }

    @Override
    public void updateChesBoard(int[][] chessboard, boolean clearPreviousPosition) {
        if (clearPreviousPosition){
            if (this.XCoordinate >= 0 && this.XCoordinate < 8 &&
                    this.YCoordinate >= 0 && this.YCoordinate < 8) {
                chessboard[this.YCoordinate][this.XCoordinate] = 0;
            }
        } else {
            if (this.XCoordinate >= 0 && this.XCoordinate < 8 &&
                    this.YCoordinate >= 0 && this.YCoordinate < 8) {
                chessboard[this.YCoordinate][this.XCoordinate] = 1;
            }
        }
    }

    @Override
    public FigureTypes getFigureType() {
        return type;
    }

    public int getXCoordinate() {
        return XCoordinate;
    }

    public int getYCoordinate() {
        return YCoordinate;
    }


}
