package DefinitelyStolenGame;

import student.TestCase;

public class LinkedChainTest extends TestCase{
    private LinkedChain<String> chain;
    
    public void setUp() {
        chain = new LinkedChain<>();
    }
    
    public void testNewChainIsEmpty() {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertNull(chain.remove());
    }
    
    public void testAddAddsToFront() {
        chain.add("A");
        chain.add("B");
        
        assertEquals(2, chain.size());
        assertEquals("B", chain.get(0));
        assertEquals("A", chain.get(1));
    }
    
    public void testAddRejectNull() {
        Exception thrown = null;
        try {
            chain.add(null);
        }
        catch(IllegalArgumentException e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertEquals("Cannot add null to chain", thrown.getMessage());
    }
    
    public void testAddToEndPreservesOrder() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");
        
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }
    
    public void testAddtoEndRejectNull() {
        Exception thrown = null;
        try {
            chain.addToEnd(null);
        }
        catch(IllegalArgumentException e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    public void testRemoveRemovesFirstEntry() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertEquals("A", chain.remove());
        assertEquals(1, chain.size());
        assertEquals("B", chain.get(0));
    }
    
    public void testRemoveSpecificEntry() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");
        
        assertTrue(chain.remove("B"));
        assertEquals(2, chain.size());
        assertFalse(chain.contains("B"));
    }
    
    public void testRemoveMissingEntry() {
        chain.addToEnd("A");
        assertFalse(chain.remove("Z"));
        assertFalse(chain.remove(null));
    }
    
    public void testRemoveFirstMatchingEntry() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");
        
        assertTrue(chain.remove("A"));
        assertEquals(2, chain.size());
        assertTrue(chain.contains("A"));
    }
    
    public void testClear() {
        chain.add("A");
        chain.add("B");
        chain.clear();
        
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }
    
    public void testGetValidIndexes() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
    }
    
    public void testgetInvalidIndexes() {
        chain.add("A");
        
        Exception low = null;
        Exception high = null;
        
        try {
            chain.get(-1);
        }
        catch (IndexOutOfBoundsException e) {
            low = e;
        }
        
        try {
            chain.get(1);
        }
        catch (IndexOutOfBoundsException e) {
            high = e;
        }
        
        assertNotNull(low);
        assertNotNull(high);
    }
    
    public void testContainsAndCount() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");
        
        assertTrue(chain.contains("A"));
        assertTrue(chain.contains("B"));
        assertFalse(chain.contains("C"));
        assertFalse(chain.contains(null));
        
        assertEquals(2, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("C"));
        assertEquals(0, chain.count(null));
    }
    
    public void testToArray() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        String[] array = new String[2];
        chain.toArray(array);
        
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
    }
    
    public void testToArrayRejectsNullAndSmallArray() {
        chain.add("A");
        
        Exception nullArray = null;
        Exception smallArray = null;
        
        try {
            chain.toArray(null);
        }
        catch (IllegalArgumentException e) {
            nullArray = e;
        }
        
        try {
            chain.toArray(new String[0]);
        }
        catch(IllegalArgumentException e) {
            smallArray = e;
        }
        
        assertNotNull(nullArray);
        assertNotNull(smallArray);
    }
    
    public void testToString() {
        assertEquals("[]", chain.toString());
        
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertEquals("[A, B]", chain.toString());
    }
}
