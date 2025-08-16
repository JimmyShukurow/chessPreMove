package info.jemsit;

import info.jemsit.chessFigure.BishopFigure;
import info.jemsit.chessFigure.KnightFigure;
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

    public static int[][] chessBoard = new int[8][8];
    public static int[][] preMoveChessBoard = new int[8][8];

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        figureGroup.getChildren().add(new KnightFigure(5,5, false, false));
        chessBoard[5][5] = 1; // Example of setting a knight's position
        figureGroup.getChildren().add(new BishopFigure(2, 2, false, false));
        chessBoard[2][2] = 1; // Example of setting a bishop's position
        pane.getChildren().addAll(tileGroup, figureGroup, premovefigureGroup);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile((i + j) % 2 == 0, i, j);
                tileGroup.getChildren().add(tile);
            }
        }

        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Jemsit Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void displayChessBoard() {
        System.out.println("Current Chess Board:");
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===================================");
    }
    public static void displayPreMoveChessBoard() {
        System.out.println("Pre-Move Chess Board:");
        for (int i = 0; i < preMoveChessBoard.length; i++) {
            for (int j = 0; j < preMoveChessBoard[i].length; j++) {
                System.out.print(preMoveChessBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===================================");
    }

    public static boolean hasFigureAt(int x, int y) {
        double targetX = x * ApplicationStart.TILE_SIZE;
        double targetY = y * ApplicationStart.TILE_SIZE;

        for (Node node : ApplicationStart.figureGroup.getChildren()) {
            if (node instanceof StackPane) {
                if (node.getLayoutX() == targetX && node.getLayoutY() == targetY) {
                    return true; // Found a figure at this coordinate
                }
            }
        }
        return false;
    }
}
