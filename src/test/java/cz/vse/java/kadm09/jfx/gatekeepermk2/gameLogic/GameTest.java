package cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic;

import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheBrute;
import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheHag;
import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheMatriarch;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Martin Kadlec
 * @version Last refactor on 10.12.2022
 * <p>
 * Tests interactions between player and the Monster entities.
 * Also runs a simple unit tests for interactions.
 */
class GameTest {

    Game TestingGrounds = new Game();

    @BeforeEach
    void resetGame () {
        TestingGrounds = new Game();
    }

    @Test
    void testDamageCalculationsToEnemies () {
        TestingGrounds.getGameMap().getCurrentPosition(4,2).setRoomEnemy(new TheBrute("Dummy",500,10,10));
        TestingGrounds.strikeEnemy();
        assertEquals(470,TestingGrounds.getGameMap().getCurrentPosition(4,2).getRoomEnemy().getHealth());
    }

    @Test
    void testDamageCalculationToKnightBrute ()
    {
        TestingGrounds.getGameMap().getCurrentPosition(4,2).setRoomEnemy(new TheBrute("Dummy",500,10,10));
        TestingGrounds.getGameMap().getCurrentPosition(4,2).getRoomEnemy().attackPattern(TestingGrounds);
        assertEquals(215,TestingGrounds.getPlayer().getCurrentHealth());

    }

    @Test
    void testDamageCalculationToKnightHag ()
    {
        TestingGrounds.getGameMap().getCurrentPosition(4,2).setRoomEnemy(new TheHag("Dummy",500,10,10));
        TestingGrounds.getGameMap().getCurrentPosition(4,2).getRoomEnemy().attackPattern(TestingGrounds);
        assertEquals(219,TestingGrounds.getPlayer().getCurrentHealth());
        assertEquals(69,TestingGrounds.getPlayer().getCurrentMana());

    }

    @Test
    void testDamageCalculationToKnightMatriarch ()
    {
        TestingGrounds.getGameMap().getCurrentPosition(4,2).setRoomEnemy(new TheMatriarch("Dummy",500,10));
        TestingGrounds.getGameMap().getCurrentPosition(4,2).getRoomEnemy().attackPattern(TestingGrounds);
        assertEquals(213,TestingGrounds.getPlayer().getCurrentHealth());
    }

    @Test
    void testDamageUpgrade () {
        TestingGrounds.improveWeapons();
        TestingGrounds.improveWeapons();
        TestingGrounds.improveWeapons();
        TestingGrounds.improveWeapons();
        TestingGrounds.improveWeapons();
        TestingGrounds.improveWeapons();
        assertEquals(40,TestingGrounds.getPlayer().getDamage());
        assertEquals(125,TestingGrounds.getPlayer().getGoldHeld());
    }

    @Test
    void testArmorUpgrade () {
        TestingGrounds.improveArmor();
        TestingGrounds.improveArmor();
        TestingGrounds.improveArmor();
        TestingGrounds.improveArmor();
        TestingGrounds.improveArmor();
        TestingGrounds.improveArmor();
        assertEquals(8,TestingGrounds.getPlayer().getArmor());
        assertEquals(125,TestingGrounds.getPlayer().getGoldHeld());

    }

    @Test
    void testRest () {
        TestingGrounds.player.setCurrentHealth(2);
        TestingGrounds.player.setCurrentMana(2);
        TestingGrounds.player.setDamage(0);

        TestingGrounds.rest();
        TestingGrounds.rest();

        assertEquals(TheKnight.MAX_HEALTH,TestingGrounds.getPlayer().getCurrentHealth());
        assertEquals(TheKnight.MAX_MANA,TestingGrounds.getPlayer().getCurrentMana());
        assertEquals(2,TestingGrounds.getPlayer().getDamage());
        assertEquals(200, TestingGrounds.getPlayer().getGoldHeld());

    }



}