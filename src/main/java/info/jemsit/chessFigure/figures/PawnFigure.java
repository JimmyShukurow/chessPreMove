package info.jemsit.chessFigure.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.FigureTypes;
import info.jemsit.chessFigure.premove.PreMoveFigure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PawnFigure extends ChessFigureImpl {

    private boolean firstMove = true;

    public PawnFigure(int x, int y, boolean isWhite) {
        this.XCoordinate = x;
        this.YCoordinate = y;
        this.isWhite = isWhite;
        type = FigureTypes.PAWN;
        relocate(x* ApplicationStart.TILE_SIZE, y * ApplicationStart.TILE_SIZE);

        Image image;
        if (isWhite) image = new Image("images/pawnw.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        else image = new Image("images/pawnb.png", ApplicationStart.TILE_SIZE, ApplicationStart.TILE_SIZE, false, false);
        ImageView imageView = new ImageView(image);

        getChildren().add(imageView);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseDragExited);
    }

    @Override
    public void handleMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
        PreMoveFigure preMoveThread = new PreMoveFigure(this, isWhite, isFirstMove());
        preMoveThread.addPreMove();
    }

    @Override
    public void handleMouseDragExited(MouseEvent mouseEvent) {
        int originalX = this.XCoordinate;
        int originalY = this.YCoordinate;

        super.handleMouseDragExited(mouseEvent);

        if (this.XCoordinate != originalX || this.YCoordinate != originalY) {
            setFirstMove(false);
        }
    }


    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
