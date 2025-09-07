package info.jemsit;

import info.jemsit.chessFigure.ChessFigureImpl;
import info.jemsit.chessFigure.figures.*;
import info.jemsit.components.SideBarComponent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;

public class ApplicationStart extends Application {
    public static final int TILE_SIZE = 80;
    private final static int SCENE_WIDTH = TILE_SIZE * 8;
    private final static int SCENE_HEIGHT = TILE_SIZE * 8;
    private final  static int SIDE_BAR_WIDTH = 200;

    private final Group tileGroup = new Group();
    public final static Group figureGroup = new Group();
    public final static Group premovefigureGroup = new Group();
    public final static Group kingSafetyCheck = new Group();

    public static Sides turn = Sides.WHITE;

    public static boolean[] blackKingCanCastle = {true, true}; // {canCastleKingSide, canCastleQueenSide}
    public static boolean[] whiteKingCanCastle = {true, true}; // {canCastleKingSide, canCastleQueenSide}

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
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
        root.setCenter(pane);
        root.setRight(new SideBarComponent(SIDE_BAR_WIDTH));
        Scene scene = new Scene(root, SCENE_WIDTH + SIDE_BAR_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Jimmy Chess Application");
        stage.setScene(scene);
        stage.show();
    }


    public static ChessFigureImpl hasFigureAt(int x, int y) {
        double targetX = x * ApplicationStart.TILE_SIZE;
        double targetY = y * ApplicationStart.TILE_SIZE;

        for (Node figure : ApplicationStart.figureGroup.getChildren()) {
            if (figure instanceof ChessFigureImpl) {
                if (figure.getLayoutX() == targetX && figure.getLayoutY() == targetY) {
                    return (ChessFigureImpl) figure;
                }
            }
        }
        return null;
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

    public static boolean hasPreMoveFigureAtForKingSafety(int x, int y) {

        for (Node node : ApplicationStart.kingSafetyCheck.getChildren()) {
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

    private void placeAllWhiteFigures() {
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
