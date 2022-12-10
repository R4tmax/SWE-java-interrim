package cz.vse.java.kadm09.jfx.gatekeepermk2.knight;

import cz.vse.java.kadm09.jfx.gatekeepermk2.gameLogic.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Martin Kadlec
 * @version Last refactor on 10.12.2022
 *
 * Tests player movement and spell casting.
 */
class TheKnightTest {

    Game TestingGrounds = new Game();

    @BeforeEach
    void resetGame () {
        TestingGrounds = new Game();
    }

    @Test
    void checkCorrectBaseSetting () {
        assertAll(("Check if base stats are correctly set on game load up"),
                () -> assertEquals(225, TestingGrounds.getPlayer().getCurrentHealth()),
                () -> assertEquals(75, TestingGrounds.getPlayer().getCurrentMana()),
                () -> assertEquals(4, TestingGrounds.getPlayer().getArmor()),
                () -> assertEquals(30, TestingGrounds.getPlayer().getDamage()),
                () -> assertEquals(500, TestingGrounds.getPlayer().getGoldHeld())
        );
    }


    @Test
    void checkRudimentaryMoves(){
        assertEquals(4,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(2,TestingGrounds.getPlayer().getPosition().getVertical());

        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);

        assertEquals(2,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(2,TestingGrounds.getPlayer().getPosition().getVertical());

        TestingGrounds.getPlayer().moveKnight("west",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("east",TestingGrounds);

        assertEquals(2,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(2,TestingGrounds.getPlayer().getPosition().getVertical());

    }

    @Test
    void checkOutOfBoundsExceptionHandling () {
        assertEquals(4,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(2,TestingGrounds.getPlayer().getPosition().getVertical());

        TestingGrounds.getPlayer().moveKnight("south",TestingGrounds);

        assertEquals(4,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(2,TestingGrounds.getPlayer().getPosition().getVertical());

        TestingGrounds.getPlayer().moveKnight("west",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("west",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("west",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("west",TestingGrounds);


        assertEquals(4,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(0,TestingGrounds.getPlayer().getPosition().getVertical());


        TestingGrounds.getPlayer().moveKnight("east",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("east",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("east",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("east",TestingGrounds);

        assertEquals(4,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(4,TestingGrounds.getPlayer().getPosition().getVertical());

        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);
        TestingGrounds.getPlayer().moveKnight("north",TestingGrounds);

        assertEquals(0,TestingGrounds.getPlayer().getPosition().getHorizontal());
        assertEquals(4,TestingGrounds.getPlayer().getPosition().getVertical());

    }

    @Test
    void offensiveSpellsLock () {
        String readbackLT = TestingGrounds.getPlayer().lightningTouch(TestingGrounds);
        String readbackS = TestingGrounds.getPlayer().holySmite(TestingGrounds);
        String readbackLS = TestingGrounds.getPlayer().lightningStrike(TestingGrounds);
        assertEquals("No target!",readbackLT);
        assertEquals("No target!",readbackS);
        assertEquals("No target!",readbackLS);
    }

    @Test
    void buffSpellBehavior () {
        TestingGrounds.getPlayer().prayerOfStrength(TestingGrounds);
        TestingGrounds.getPlayer().prayerOfResolve(TestingGrounds);
        assertEquals(35,TestingGrounds.getPlayer().getDamage());
        assertEquals(6,TestingGrounds.getPlayer().getArmor());

        String noManaReadback = TestingGrounds.getPlayer().prayerOfResolve(TestingGrounds);
        assertEquals("Not enough mana to cast!",noManaReadback);
    }

}