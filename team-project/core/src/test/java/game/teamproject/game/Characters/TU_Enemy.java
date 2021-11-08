package game.teamproject.game.Characters;

import org.junit.Test;

import java.util.HashMap;

import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.DamageMove;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.Characters.moves.MoveSet;

import static org.junit.Assert.*;

public class TU_Enemy {

    game.teamproject.game.Characters.Enemy enemy;

    public TU_Enemy() {
        enemy = new Enemy();
    }

    @Test
    public void test_createMoveSet() {
        MoveSet moveSet = enemy.getMoveSet();
        HashMap<Move, DamageMove> moves = moveSet.getMoves();
        assertEquals(1, moves.size());

        enemy.createMoveSet();
        moveSet = enemy.getMoveSet();
        moves = moveSet.getMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void test_setHeath() {
        assertEquals(100, enemy.getHealth());

        enemy.setHealth(10);
        assertEquals(10, enemy.getHealth());

        enemy.setHealth(0);
        assertEquals(0, enemy.getHealth());

        enemy.setHealth(1000);
        assertEquals(1000, enemy.getHealth());

        enemy.setHealth(-100);
        assertEquals(-100, enemy.getHealth()); //health cannot be -ve
    }

    @Test
    public void test_addDebuff() {
        HashMap<Debuff, DebuffDetail> currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(0, currDebuffs.size());

        DebuffDatabase db = new DebuffDatabase();
        enemy.addDebuff(Debuff.BURNED, db);
        currDebuffs = enemy.getCurrentDebuffs();
        enemy.checkDebuff();
        assertEquals(true, enemy.isDebuffed()); //should be true after adding debuff
        assertEquals(1, currDebuffs.size());

        enemy.addDebuff(Debuff.STUNNED, db);
        currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(2, currDebuffs.size());

        currDebuffs.get(Debuff.STUNNED).reduceDuration();
        assertEquals( 0, currDebuffs.get(Debuff.STUNNED).getDuration());

        enemy.addDebuff(Debuff.STUNNED, db);
        currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(2, currDebuffs.size());
        assertEquals( 1, currDebuffs.get(Debuff.STUNNED).getDuration());
    }

    @Test
    public void test_reduceAllDebuffsDuration() {
        enemy.reduceAllDebuffsDuration();
        HashMap<Debuff, DebuffDetail> currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(0, currDebuffs.size());

        DebuffDatabase db = new DebuffDatabase();
        enemy.addDebuff(Debuff.STUNNED, db);
        enemy.reduceAllDebuffsDuration();
        currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(1, currDebuffs.size());
        assertEquals( 0, currDebuffs.get(Debuff.STUNNED).getDuration());

        enemy.reduceAllDebuffsDuration();
        System.out.println(enemy.getCurrentDebuffs().get(Debuff.STUNNED).getDuration());

        assertEquals(0, enemy.getCurrentDebuffs().size()); //debuff is not removed!!!

        enemy.addDebuff(Debuff.STUNNED, db);
        enemy.addDebuff(Debuff.BURNED, db);
        enemy.reduceAllDebuffsDuration();
        currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(2, currDebuffs.size());

        enemy.reduceAllDebuffsDuration();
        currDebuffs = enemy.getCurrentDebuffs();
        assertEquals(1, currDebuffs.size());
        assertEquals( 1, currDebuffs.get(Debuff.STUNNED).getDuration());

    }

}