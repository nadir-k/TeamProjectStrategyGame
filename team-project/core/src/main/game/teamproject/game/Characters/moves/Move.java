package game.teamproject.game.Characters.moves;

/**
 * List of all the move the player can used
 */

public enum Move {

    NORMAL("Pure Strike"),
    STUN("Light Wave"),
    ARMORBREAK("Steel Break"),
    FIRE("Lava Slash");

    String move;

    Move(String name){
        move = name;
    }

    public String moveToString(){
        return move;
    }
}
