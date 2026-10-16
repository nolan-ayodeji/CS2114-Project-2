package DefinitelyStolenGame;

import student.TestCase;

public class GameSystemTest extends TestCase {
    private GameSystem gameSystem;

    public void setUp() {
        gameSystem = new GameSystem(5);
    }


    public void testInitialChainIsEmpty() {
        assertEquals(0, gameSystem.getWaitingCount());
        assertFalse(gameSystem.hasVisitors());
    }


    public void testStartDayCreatesVisitors() {
        gameSystem.startDay();

        assertEquals(5, gameSystem.getWaitingCount());
        assertTrue(gameSystem.hasVisitors());
    }


    public void testStartDayRejectsInvalidDay() {
        Exception thrown = null;

        try {
            gameSystem.startDay(0);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }

        assertNotNull(thrown);
        assertEquals("Day must be positive.", thrown.getMessage());
    }


    public void testStartDayAddsLandownerOnSeventhDay() {
        gameSystem.startDay(7);

        assertEquals(6, gameSystem.getWaitingCount());
    }


    public void testStartDayDoesntAddLandownerOnNormalDay() {
        gameSystem.startDay(3);

        assertEquals(5, gameSystem.getWaitingCount());
    }


    public void testNextVisitorRemovesVisitors() {
        gameSystem.startDay();

        Visitor first = gameSystem.nextVisitor();
        assertNotNull(first);
        assertEquals(4, gameSystem.getWaitingCount());

        while (gameSystem.hasVisitors()) {
            assertNotNull(gameSystem.nextVisitor());
        }
    }


    public void testGeneratedVisitorsAreValid() {
        gameSystem.startDay();

        while (gameSystem.hasVisitors()) {
            Visitor visitor = gameSystem.nextVisitor();
            assertNotNull(visitor);
            assertTrue(visitor instanceof Buyer || visitor instanceof Seller);
        }
    }
}
