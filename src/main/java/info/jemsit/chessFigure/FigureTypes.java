package info.jemsit.chessFigure;

public enum FigureTypes {
    KING("King"),
    QUEEN("Queen"),
    ROOK("Rook"),
    BISHOP("Bishop"),
    KNIGHT("Knight"),
    PAWN("Pawn");

    private final String name;

    FigureTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
