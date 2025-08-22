package info.jemsit.chessFigure.premove.figures;

import info.jemsit.ApplicationStart;
import info.jemsit.chessFigure.premove.PreMoveDot;
import info.jemsit.chessFigure.premove.PreMoveImpl;

public class KnightPreMove extends PreMoveImpl {


    // Knight moves: (x, y) offsets
    int[] knightXMoves = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] knightYMoves = {2, -2, 1, -1, 2, -2, 1, -1};


    public KnightPreMove(int currentXCoordinate, int currentYCoordinate) {
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
    }

    @Override
    public void addPreMoves(){
        for (int i = 0; i < knightXMoves.length; i++) {
            int newX = currentXCoordinate + knightXMoves[i];
            int newY = currentYCoordinate + knightYMoves[i];

            if (isValidMove(newX, newY)) {
                ApplicationStart.premovefigureGroup.getChildren().add(new PreMoveDot(newX, newY)); // Add pre-move knight figure
            }
        }
    }

    @Override
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && !ApplicationStart.hasFigureAt(x,y);
    }
}

