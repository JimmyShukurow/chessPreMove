package info.jemsit;

import info.jemsit.chessFigure.figures.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationStart extends Application {
    public static final int TILE_SIZE = 80;
    private final static int SCENE_WIDTH = TILE_SIZE * 8;
    private final static int SCENE_HEIGHT = TILE_SIZE * 8;

    private final Group tileGroup = new Group();
    public final static Group figureGroup = new Group();
    public final static Group premovefigureGroup = new Group();


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        placeAllBlackFigures();
        placeAllWhiteFigures();
        pane.getChildren().addAll(tileGroup, premovefigureGroup, figureGroup);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile((i + j) % 2 == 0, i, j);
                tileGroup.getChildren().add(tile);
            }
        }

        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Jimmy Chess Application");
        stage.setScene(scene);
        stage.show();
    }


    public static boolean hasFigureAt(int x, int y) {
        double targetX = x * ApplicationStart.TILE_SIZE;
        double targetY = y * ApplicationStart.TILE_SIZE;

        for (Node node : ApplicationStart.figureGroup.getChildren()) {
            if (node instanceof StackPane) {
                if (node.getLayoutX() == targetX && node.getLayoutY() == targetY) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasPreMoveFigureAt(int x, int y) {

        for (Node node : ApplicationStart.premovefigureGroup.getChildren()) {
            if (node instanceof StackPane) {
                if (node.getLayoutX() / TILE_SIZE == x && node.getLayoutY() / TILE_SIZE == y) {
                    return true;
                }
            }
        }
        return false;
    }

    private void placeAllBlackFigures() {
        figureGroup.getChildren().add(new RookFigure(0, 0, false));
        figureGroup.getChildren().add(new KnightFigure(1, 0, false));
        figureGroup.getChildren().add(new BishopFigure(2, 0, false));
        figureGroup.getChildren().add(new QueenFigure(3, 0, false));
        figureGroup.getChildren().add(new KingFigure(4, 0, false));
        figureGroup.getChildren().add(new BishopFigure(5, 0, false));
        figureGroup.getChildren().add(new KnightFigure(6, 0, false));
        figureGroup.getChildren().add(new RookFigure(7, 0, false));
        for (int i = 0; i < 8; i++) {
            figureGroup.getChildren().add(new PawnFigure(i, 1, false));
        }
    }

    private void placeAllWhiteFigures(){
        figureGroup.getChildren().add(new RookFigure(0, 7, true));
        figureGroup.getChildren().add(new KnightFigure(1, 7, true));
        figureGroup.getChildren().add(new BishopFigure(2, 7, true));
        figureGroup.getChildren().add(new QueenFigure(3, 7, true));
        figureGroup.getChildren().add(new KingFigure(4, 7, true));
        figureGroup.getChildren().add(new BishopFigure(5, 7, true));
        figureGroup.getChildren().add(new KnightFigure(6, 7, true));
        figureGroup.getChildren().add(new RookFigure(7, 7, true));
        for (int i = 0; i < 8; i++) {
            figureGroup.getChildren().add(new PawnFigure(i, 6, true));
        }
    }

}
