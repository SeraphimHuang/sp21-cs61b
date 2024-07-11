package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);
        int size = board.size();
        for (int j = 0; j < size; j += 1){
            if (column_tilt(j)){
                changed = true;
            }
        }
        board.setViewingPerspective(Side.NORTH);
        return changed;
    }

    public boolean column_tilt(int column_number){
        boolean changed = false;
        int size = board.size();
        // default value: [false] * size
        boolean[] merged = new boolean[size];

        for (int i = size - 2; i >= 0; i -= 1){
            Tile t = board.tile(column_number, i);

            if (t != null){
                int non_empty = rowIndexOfNearestTopNonEmptyTile(i, column_number);
                int empty = rowIndexOfFarestAccessibleEmptyTile(i, column_number);

                // if there's is a non_empty tile looking up from tile t
                if  (non_empty != i){
                    if (board.tile(column_number, non_empty).value() == t.value()){
                        if (merged[non_empty] == false){
                            board.move(column_number, non_empty, t);
                            score += 2*t.value();
                            changed = true;
                            merged[non_empty] = true;
                        }
                        else if (empty != i){
                            board.move(column_number, empty, t);
                            changed = true;
                        }

                    }
                    else if (board.tile(column_number, non_empty).value() != t.value() && empty != i){
                        board.move(column_number, empty, t);
                        changed = true;
                    }
                }
                else {
                    board.move(column_number, empty, t);
                    changed = true;
                }
            }
        }
        return changed;
    }


    /** Given the row_number and column_number of the tile t,
     * return the row index of the first non-empty tile looking up from tile t.
     * if there's no such tile, return row_number
     */
    public int rowIndexOfNearestTopNonEmptyTile(int row_number, int column_number){
        int size = board.size();
        for (int i = row_number + 1; i < size; i += 1){
            if (board.tile(column_number, i) != null){
                return i;
            }
        }
        return row_number;
    }

    /** Given the row_number and column_number of the tile t,
     * return the row index of the farest accessible empty tile looking up from tile t.
     * if there's no such tile, return row_number
     */
    public int rowIndexOfFarestAccessibleEmptyTile(int row_number, int column_number){
        int size = board.size();
        for (int i = row_number + 1; i < size; i += 1){
            if (board.tile(column_number, i) != null){
                return i - 1;
            }
        }
        return size - 1;
    }









    public boolean col_tilt(int col){
        int size = board.size();
        boolean col_changed = false;
        int mergeable_value = -1;
        int mergeable_tile_row_index = -1;
        for (int i = size  - 1; i >= 0; i -= 1){
            int top_tile_row_index = find_top_tile(col, i);
            if (top_tile_row_index == -1){
                return col_changed;
            }
            else {
                Tile t = board.tile(col, top_tile_row_index);
                int value = t.value();
                if (value == mergeable_value){
                    board.move(col, mergeable_tile_row_index, t);
                    col_changed = true;
                    score += 2*mergeable_value;
                    mergeable_value = -1;
                    mergeable_tile_row_index = -1;
                } else {
                    int top_empty_row_index = find_top_empty(col, top_tile_row_index);
                    if (top_empty_row_index != -1){
                        board.move(col, top_empty_row_index, t);
                        col_changed = true;
                        mergeable_value = value;
                        mergeable_tile_row_index = top_empty_row_index;
                    } else {
                        mergeable_value = value;
                        mergeable_tile_row_index = top_tile_row_index;
                    }
                }
            }
        }
        return col_changed;
    }

    /**
     *
     * @param col, top_row
     * @return the index of the top non-empty tile in within the range of col and top_row
     * if the col is empty, return -1
     */
    public int find_top_tile(int col, int top_row){
        for (int row = top_row; row >= 0; row -= 1){
            Tile t = board.tile(col, row);
            if (t != null){
                return row;
            }
        }
        return -1;
    }

    /**
     *
     * @param col current col index
     * @param row
     * @return the row index of top empty tile in col above row, if there is None, return -1
     */
    public int find_top_empty(int col, int row){
        int size = board.size();
        for (int j = size - 1; j >= row + 1; j -= 1){
            Tile t = board.tile(col, j);
            if (t == null){
                return j;
            }
        }
        return -1;
    }




    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int size = b.size();
        for (int i = 0; i < size; i += 1){
            for (int j = 0; j < size; j += 1){
                if (b.tile(i, j) == null){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        int size = b.size();
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                Tile t = b.tile(i, j);
                if (t == null){
                    continue;
                }
                if (t.value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }




    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.

        if (emptySpaceExists(b)){
            return true;
        }
        int size = b.size();
        for (int i = 0; i < size; i += 1){
            for (int j = 0; j < size; j += 1){
                /** Because there's no empty space exists in the board, Tile t is not null */
                Tile t = b.tile(i, j);
                if (validTileIndex(b, i+1, j)){
                    if (b.tile(i+1, j).value() == t.value()){
                        return true;
                    }
                }
                if (validTileIndex(b, i, j+1)){
                    if (b.tile(i, j+1).value() == t.value()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean validTileIndex(Board b, int row, int col){
        int size = b.size();
        if (row >= 0 & row < size & col >= 0 & col < size){
            return true;
        }
        return false;
    }

    public static boolean validTile(Board b, int col, int row){
        int size = b.size();
        if (((col >= 0) & (col < size)) & ((row >= 0) & (row < size))){
            return true;
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
