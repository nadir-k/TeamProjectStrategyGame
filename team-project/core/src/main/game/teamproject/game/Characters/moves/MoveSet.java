package game.teamproject.game.Characters.moves;

import java.util.HashMap;

/**
 * A Class that contain the list of  moves and the value of each moves
 */

public class MoveSet {
    private HashMap<Move, DamageMove> moves;

    /**
     * Create the hash map to store all the moves
     */
    public MoveSet() {
        moves = new HashMap<>();
    }

    /**
     * Put the Move and its value in the hash map
     * @param move Name of the move
     * @param damageMove Value of each move
     */
    public void addMove(Move move, DamageMove damageMove) {
        moves.put(move, damageMove);
    }

    /**
     *
     * @param move Name of the move
     * @return Value of that move
     */

    public DamageMove getMove(Move move) {
        return moves.get(move);
    }

    /**
     *
     * @return moves The hashmap containing all the moves
     */
    public HashMap<Move, DamageMove> getMoves() {
        return moves;
    }

}
