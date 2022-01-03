import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void checkCreation(){
        Board bd = new Board();
        assertNotNull(bd);
        for(int i = 0; i < 23; i++){
            assertNotNull(bd.getBoard().get(i));
            for(int j = 0; j < bd.getBoard().get(i).length;j++){
                assertNotNull(bd.getBoard().get(i)[j]);
                assertEquals(i+1,bd.getBoard().get(i)[j].getY());
                assertEquals(j+1,bd.getBoard().get(i)[j].getX());
            }
        }
    }

    @Test
    public void moveTest() {
//        Board bd = new Board();
//        bd.getBoard().get(0)[0].setOccupied(true);
//        bd.getBoard().get(0)[0].setOccupant(new Piece("p1"));
//        bd.getBoard().get(1)[1].setOccupied(true);
//        bd.getBoard().get(1)[1].setOccupant(new Piece("p1"));
//        bd.getBoard().get(2)[2].setOccupied(true);
//        bd.getBoard().get(2)[2].setOccupant(new Piece("p2"));
//
//        try {
//            bd.movePiece(3,3,5,5);
//        } catch (Exception e){
//            assertEquals(e.getMessage(),"Provided cell doesn't contain any piece!");
//        }
//
//        try {
//            bd.movePiece(0,0,1,1);
//        } catch (Exception e){
//            assertEquals(e.getMessage(),"Player can't kill own piece!");
//        }
//
//        try {
//            bd.movePiece(0,0,5,5);
//        } catch (Exception e){
//        }
//        finally {
//            assertFalse(bd.getBoard().get(0)[0].isOccupied());
//            assertNull(bd.getBoard().get(0)[0].getOccupant());
//            assertNotNull(bd.getBoard().get(5)[5].getOccupant());
//            assertTrue(bd.getBoard().get(5)[5].isOccupied());
//        }
//
//        try {
//            bd.movePiece(1,1,2,2);
//        } catch (Exception e){
//        }
//        finally {
//            assertFalse(bd.getBoard().get(1)[1].isOccupied());
//            assertNull(bd.getBoard().get(1)[1].getOccupant());
//            assertNotNull(bd.getBoard().get(2)[2].getOccupant());
//            assertTrue(bd.getBoard().get(2)[2].isOccupied());
//            assertEquals(bd.getBoard().get(2)[2].getOccupant().getOwner(),"p1");
//        }
//
//        try {
//            bd.movePiece(1,1,5,5);
//        } catch (Exception e){
//            assertEquals(e.getMessage(),"Provided cell doesn't contain any piece!");
//        }
    }

    @Test
    public void getMoves(){
        Board bd = new Board();
        bd.getBoard().get(2)[5].setOccupant(new Infantry(Player.PLAYER_1, 6,3));
        List<Cell> res = bd.getBoard().get(2)[5].getOccupant().getMoveCells();
        for(Cell c : res){
            System.out.println(c.getX() + " : " + c.getY());
        }
    }

    // Testing SemiCircleNorth - BEGIN

    @Test
    public void testNorthernSemiCircleBottomLeft(){
        Infantry i = new Infantry(Player.PLAYER_1,1,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(1,2)));
        assertTrue(iCells.contains(new Cell(2,2)));
        assertTrue(iCells.contains(new Cell(2,1)));
    }

    @Test
    public void testNorthernSemiCircleBottomRight(){
        Infantry i = new Infantry(Player.PLAYER_1,12,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(11,1)));
        assertTrue(iCells.contains(new Cell(12,2)));
        assertTrue(iCells.contains(new Cell(13,2)));
    }

    @Test
    public void testNorthernSemiCircleBottomMiddle(){
        Infantry i = new Infantry(Player.PLAYER_1,4,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,1)));
        assertTrue(iCells.contains(new Cell(4,2)));
        assertTrue(iCells.contains(new Cell(5,2)));
        assertTrue(iCells.contains(new Cell(5,1)));
    }

    @Test
    public void testNorthernSemiCircleLowerLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_1,1,3);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(1,4)));
        assertTrue(iCells.contains(new Cell(2,4)));
        assertTrue(iCells.contains(new Cell(2,3)));
    }

    @Test
    public void testNorthernSemiCircleLowerRightSide(){
        Infantry i = new Infantry(Player.PLAYER_1,14,3);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(13,3)));
        assertTrue(iCells.contains(new Cell(14,4)));
        assertTrue(iCells.contains(new Cell(15,4)));
    }

    @Test
    public void testNorthernSemiCircleLowerMiddle(){
        Infantry i = new Infantry(Player.PLAYER_1,4,6);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,6)));
        assertTrue(iCells.contains(new Cell(4,7)));
        assertTrue(iCells.contains(new Cell(5,7)));
        assertTrue(iCells.contains(new Cell(5,6)));
    }

    @Test
    public void testNorthernSemiCircleCenterLeft(){
        Infantry i = new Infantry(Player.PLAYER_1,1,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(1,13)));
        assertTrue(iCells.contains(new Cell(2,12)));
    }

    @Test
    public void testNorthernSemiCircleCenterMiddle(){
        Infantry i = new Infantry(Player.PLAYER_1,4,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,12)));
        assertTrue(iCells.contains(new Cell(3,13)));
        assertTrue(iCells.contains(new Cell(4,13)));
        assertTrue(iCells.contains(new Cell(5,12)));
    }

    @Test
    public void testNorthernSemiCircleCenterRight(){
        Infantry i = new Infantry(Player.PLAYER_1,23,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(22,12)));
        assertTrue(iCells.contains(new Cell(22,13)));
    }

    @Test
    public void testNorthernSemiCircleUpperMiddle(){
        Infantry i = new Infantry(Player.PLAYER_1,4,16);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,16)));
        assertTrue(iCells.contains(new Cell(3,17)));
        assertTrue(iCells.contains(new Cell(4,17)));
        assertTrue(iCells.contains(new Cell(5,16)));
    }

    @Test
    public void testNorthernSemiCircleUpperLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_1,1,14);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(1,15)));
        assertTrue(iCells.contains(new Cell(2,14)));
    }

    @Test
    public void testNorthernSemiCircleUpperRightSide(){
        Infantry i = new Infantry(Player.PLAYER_1,20,15);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(19,15)));
        assertTrue(iCells.contains(new Cell(19,16)));
    }

    @Test
    public void testNorthernSemiCircleTopLeft(){
        Infantry i = new Infantry(Player.PLAYER_1,1,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),1);
        assertEquals(iCells.get(0),new Cell(2,23));
    }

    @Test
    public void testNorthernSemiCircleTopMiddle(){
        Infantry i = new Infantry(Player.PLAYER_1,6,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(5,23)));
        assertTrue(iCells.contains(new Cell(7,23)));
    }

    @Test
    public void testNorthernSemiCircleTopRight(){
        Infantry i = new Infantry(Player.PLAYER_1,12,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),1);
        assertEquals(iCells.get(0),new Cell(11,23));
    }

    // Testing SemiCircleNorth - END

    // Testing SemiCircleSouth - BEGIN

    @Test
    public void testSouthernSemiCircleBottomLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),1);
        assertEquals(iCells.get(0),new Cell(2,1));
    }

    @Test
    public void testSouthernSemiCircleBottomRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),1);
        assertEquals(iCells.get(0),new Cell(11,1));
    }

    @Test
    public void testSouthernSemiCircleBottomMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,1);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(3,1)));
        assertTrue(iCells.contains(new Cell(5,1)));
    }

    @Test
    public void testSouthernSemiCircleLowerLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,3);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(1,2)));
        assertTrue(iCells.contains(new Cell(2,3)));
    }

    @Test
    public void testSouthernSemiCircleLowerRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,14,3);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(13,2)));
        assertTrue(iCells.contains(new Cell(13,3)));
    }

    @Test
    public void testSouthernSemiCircleLowerMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,6);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,6)));
        assertTrue(iCells.contains(new Cell(3,5)));
        assertTrue(iCells.contains(new Cell(4,5)));
        assertTrue(iCells.contains(new Cell(5,6)));
    }

    @Test
    public void testSouthernSemiCircleCenterLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(1,11)));
        assertTrue(iCells.contains(new Cell(2,12)));
    }

    @Test
    public void testSouthernSemiCircleCenterMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,12)));
        assertTrue(iCells.contains(new Cell(3,11)));
        assertTrue(iCells.contains(new Cell(4,11)));
        assertTrue(iCells.contains(new Cell(5,12)));
    }

    @Test
    public void testSouthernSemiCircleCenterRight(){
        Infantry i = new Infantry(Player.PLAYER_2,23,12);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),2);
        assertTrue(iCells.contains(new Cell(22,12)));
        assertTrue(iCells.contains(new Cell(22,11)));
    }

    @Test
    public void testSouthernSemiCircleUpperMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,16);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(3,16)));
        assertTrue(iCells.contains(new Cell(4,15)));
        assertTrue(iCells.contains(new Cell(5,15)));
        assertTrue(iCells.contains(new Cell(5,16)));
    }

    @Test
    public void testSouthernSemiCircleUpperLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,14);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(1,13)));
        assertTrue(iCells.contains(new Cell(2,14)));
        assertTrue(iCells.contains(new Cell(2,13)));
    }

    @Test
    public void testSouthernSemiCircleUpperRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,20,15);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(19,15)));
        assertTrue(iCells.contains(new Cell(20,14)));
        assertTrue(iCells.contains(new Cell(21,14)));
    }

    @Test
    public void testSouthernSemiCircleTopLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(1,22)));
        assertTrue(iCells.contains(new Cell(2,22)));
        assertTrue(iCells.contains(new Cell(2,23)));
    }

    @Test
    public void testSouthernSemiCircleTopMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,6,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),4);
        assertTrue(iCells.contains(new Cell(5,23)));
        assertTrue(iCells.contains(new Cell(6,22)));
        assertTrue(iCells.contains(new Cell(7,22)));
        assertTrue(iCells.contains(new Cell(7,23)));
    }

    @Test
    public void testSouthernSemiCircleTopRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,23);
        ArrayList<Cell> iCells = i.getMoveCells();
        assertEquals(iCells.size(),3);
        assertTrue(iCells.contains(new Cell(11,23)));
        assertTrue(iCells.contains(new Cell(12,22)));
        assertTrue(iCells.contains(new Cell(13,22)));
    }

    // Testing SemiCircleSouth - END

    // Testing NorthWestArm - BEGIN

    @Test
    public void testNorthWestArmBottomLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,1);
        ArrayList<Cell> iCells = i.northWestArm(1,1);
        assertEquals(iCells.size(),11);
        for(int k = 2; k < 13; k++){
            assertTrue(iCells.contains(new Cell(1,k)));
        }
    }

    @Test
    public void testNorthWestArmBottomRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,1);
        ArrayList<Cell> iCells = i.northWestArm(12,1);
        assertEquals(iCells.size(),22);
        int cx = 12;
        for(int k = 2; k < 24; k++){
            if(k > 12) cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmBottomMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,1);
        ArrayList<Cell> iCells = i.northWestArm(4,1);
        assertEquals(iCells.size(),14);
        int cx = 4;
        for(int k = 2; k < 16; k++){
            if(k > 12) cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmLowerLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,3);
        ArrayList<Cell> iCells = i.northWestArm(1,3);
        assertEquals(iCells.size(),9);
        for(int k = 4; k < 13; k++){
            assertTrue(iCells.contains(new Cell(1,k)));
        }
    }

    @Test
    public void testNorthWestArmLowerRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,14,3);
        ArrayList<Cell> iCells = i.northWestArm(14,3);
        assertEquals(iCells.size(),20);
        int cx = 14;
        for(int k = 4; k < 24; k++){
            if(k > 12) cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmLowerMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,6);
        ArrayList<Cell> iCells = i.northWestArm(4,6);
        assertEquals(iCells.size(),9);
        int cx = 4;
        for(int k = 7; k < 16; k++){
            if(k > 12) cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmCenterLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,12);
        ArrayList<Cell> iCells = i.northWestArm(1,12);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthWestArmCenterMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,12);
        ArrayList<Cell> iCells = i.northWestArm(4,12);
        assertEquals(iCells.size(),3);
        int cx = 4;
        for(int k = 13; k < 16; k++){
            cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmCenterRight(){
        Infantry i = new Infantry(Player.PLAYER_2,23,12);
        ArrayList<Cell> iCells = i.northWestArm(23 ,12);
        assertEquals(iCells.size(),11);
        int cx = 23;
        for(int k = 13; k < 24; k++){
            cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmUpperMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,16);
        ArrayList<Cell> iCells = i.northWestArm(4,16);
        assertEquals(iCells.size(),3);
        int cx = 4;
        for(int k = 17; k < 20; k++){
            cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmUpperLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,14);
        ArrayList<Cell> iCells = i.northWestArm(1,14);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthWestArmUpperRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,20,15);
        ArrayList<Cell> iCells = i.northWestArm(20,15);
        assertEquals(iCells.size(),8);
        int cx = 20;
        for(int k = 16; k < 24; k++){
            cx--;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthWestArmTopLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,23);
        ArrayList<Cell> iCells = i.northWestArm(1,23);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthWestArmTopMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,6,23);
        ArrayList<Cell> iCells = i.northWestArm(6,23);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthWestArmTopRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,23);
        ArrayList<Cell> iCells = i.northWestArm(12,23);
        assertEquals(iCells.size(),0);
    }

    // Testing NorthWestArm - END

    // Testing NorthEastArm - BEGIN

    @Test
    public void testNorthEastArmBottomLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,1);
        ArrayList<Cell> iCells = i.northEastArm(1,1);
        assertEquals(iCells.size(),22);
        int cx = 1;
        for(int k = 2; k < 24; k++){
            if(cx < 12) cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmBottomRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,1);
        ArrayList<Cell> iCells = i.northEastArm(12,1);
        assertEquals(iCells.size(),11);
        int cx = 12;
        for(int k = 2; k < 13; k++){
            cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmBottomMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,1);
        ArrayList<Cell> iCells = i.northEastArm(4,1);
        assertEquals(iCells.size(),19);
        int cx = 4;
        for(int k = 2; k < 21; k++){
            if(k <= 12) cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmLowerLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,3);
        ArrayList<Cell> iCells = i.northEastArm(1,3);
        assertEquals(iCells.size(),20);
        int cx = 1;
        for(int k = 4; k < 24; k++){
            if(k <= 12) cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmLowerRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,14,3);
        ArrayList<Cell> iCells = i.northEastArm(14,3);
        assertEquals(iCells.size(),9);
        int cx = 14;
        for(int k = 4; k < 13; k++){
            cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmLowerMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,6);
        ArrayList<Cell> iCells = i.northEastArm(4,6);
        assertEquals(iCells.size(),17);
        int cx = 4;
        for(int k = 7; k < 23; k++){
            if(k <= 12) cx++;
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmCenterLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,12);
        ArrayList<Cell> iCells = i.northEastArm(1,12);
        assertEquals(iCells.size(),11);
        for(int k = 13; k < 23; k++){
            assertTrue(iCells.contains(new Cell(1,k)));
        }
    }

    @Test
    public void testNorthEastArmCenterMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,15,12);
        ArrayList<Cell> iCells = i.northEastArm(15,12);
        assertEquals(iCells.size(),8);
        for(int k = 13; k < 21; k++){
            assertTrue(iCells.contains(new Cell(15,k)));
        }
    }

    @Test
    public void testNorthEastArmCenterRight(){
        Infantry i = new Infantry(Player.PLAYER_2,23,12);
        ArrayList<Cell> iCells = i.northEastArm(23 ,12);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthEastArmUpperMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,4,16);
        ArrayList<Cell> iCells = i.northEastArm(4,16);
        assertEquals(iCells.size(),7);
        int cx = 4;
        for(int k = 17; k < 24; k++){
            assertTrue(iCells.contains(new Cell(cx,k)));
        }
    }

    @Test
    public void testNorthEastArmUpperLeftSide(){
        Infantry i = new Infantry(Player.PLAYER_2,1,14);
        ArrayList<Cell> iCells = i.northEastArm(1,14);
        assertEquals(iCells.size(),9);
        for(int k = 15; k < 24; k++){
            assertTrue(iCells.contains(new Cell(1,k)));
        }
    }

    @Test
    public void testNorthEastArmUpperRightSide(){
        Infantry i = new Infantry(Player.PLAYER_2,20,15);
        ArrayList<Cell> iCells = i.northEastArm(20,15);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthEastArmTopLeft(){
        Infantry i = new Infantry(Player.PLAYER_2,1,23);
        ArrayList<Cell> iCells = i.northEastArm(1,23);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthEastArmTopMiddle(){
        Infantry i = new Infantry(Player.PLAYER_2,6,23);
        ArrayList<Cell> iCells = i.northEastArm(6,23);
        assertEquals(iCells.size(),0);
    }

    @Test
    public void testNorthEastArmTopRight(){
        Infantry i = new Infantry(Player.PLAYER_2,12,23);
        ArrayList<Cell> iCells = i.northEastArm(12,23);
        assertEquals(iCells.size(),0);
    }

    // Testing NorthEastArm - END

    // Testing Artillery
    @Test
    public void testArt(){
        Artillery a = new Artillery(Player.PLAYER_1,1,23);
        ArrayList<Cell> moves = a.getMoveCells();
        for(Cell c : moves){
            System.out.println(String.valueOf(c.getX()) + " : " + String.valueOf(c.getY()));
        }
    }

    //Testing Cavalry
    @Test
    public void testCav(){
        Cavalry cav = new Cavalry(Player.PLAYER_1,8,16);
        ArrayList<Cell> moves = cav.getMoveCells();
        for(Cell c : moves){
            System.out.println(String.valueOf(c.getX()) + " : " + String.valueOf(c.getY()));
        }
    }

    @Test
    public void testCircle(){
        Cavalry cav = new Cavalry(Player.PLAYER_1,8,16);
        ArrayList<Cell> moves = cav.circle(8,16);
        for(Cell c : moves){
            System.out.println(String.valueOf(c.getX()) + " : " + String.valueOf(c.getY()));
        }
    }

    @Test
    public void testArm(){
        Artillery a = new Artillery(Player.PLAYER_1,1,23);
        ArrayList<Cell> moves = a.southWestArm(6,23);
        for(Cell c : moves){
            System.out.println(String.valueOf(c.getX()) + " : " + String.valueOf(c.getY()));
        }
    }

    @Test
    public void testData(){
        Board bd = new Board();
        System.out.println(bd.getData());
    }

    @Test
    public void testMoveNew(){
        Board bd = new Board();
        assertFalse(bd.movePiece(3,2,5,5));
        assertFalse(bd.movePiece(2,3,3,3));
        assertFalse(bd.movePiece(2,3,5,5));
        assertTrue(bd.movePiece(2,3,2,4));
        assertEquals(bd.getBoard().get(2)[1].getOccupant().getType(), Piece.pieceType.EMPT);
        assertEquals(bd.getBoard().get(3)[1].getOccupant().getType(), Piece.pieceType.INF);
    }
}
