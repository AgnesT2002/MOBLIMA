/**
 * @author Alan
 * @version 1.0
 * 2022-11-08
 */
public class Cinema_Seat {
    private int Seat_ID;
    private boolean Assigned;

    public Cinema_Seat(int seatId, boolean assignment) {

        Seat_ID = seatId;
        Assigned = assignment;
    }

    /**
     * @return int
     */
    public int getSeatID() {
        return Seat_ID;
    }

    /**
     * @return boolean
     */
    public boolean isOccupied() {
        return Assigned;
    }

    /**
     * @param seatId
     */
    public void assign(int seatId) {
        Seat_ID = seatId;
        Assigned = true;
    }

    public void unAssign() {
        Assigned = false;
    }
}
