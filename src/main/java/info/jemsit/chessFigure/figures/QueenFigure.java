package info.jemsit.chessFigure.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QueenFigure extends ChessFigureImpl {


    public QueenFigure(int x, int y, boolean isWhite) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        this.isWhite = isWhite;
        type = FigureTypes.QUEEN;
        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);
        Image image;
        if (isWhite) image = new Image("images/queenw.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        else image = new Image("images/queenb.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        ImageView imageView = new ImageView(image);

        getChildren().add(imageView);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }



}
