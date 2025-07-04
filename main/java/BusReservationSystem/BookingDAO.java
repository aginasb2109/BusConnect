package BusReservationSystem;
import java.sql.*;

public class BookingDAO {

    public int getNumberofBooking(int BusNO,Date dateOfJourney) throws SQLException {
        int booked=0;
        String query="SELECT COUNT(passenger_Name) FROM Booking WHERE Bus_no=? AND Date_of_journey=?";
        Connection con =DBConnection.getConnection();
        PreparedStatement pst =con.prepareStatement(query);
        java.sql.Date sqldate= new java.sql.Date(dateOfJourney.getTime());
        pst.setInt(1,BusNO);
        pst.setDate(2,sqldate);
        ResultSet rs= pst.executeQuery();
        rs.next();
        return rs.getInt(1);


    }

    public int bookOrder(Booking book) throws SQLException {
        String query = "INSERT INTO Booking (booking_id,Passenger_Name, Bus_no, Date_of_journey, Departure, Destination) VALUES (?,?, ?, ?, ?, ?)";

        Connection con =DBConnection.getConnection();
        PreparedStatement pst =con.prepareStatement(query);
        String name=book.PassengerName;
        int busno=book.BusNo;
        int id=book.bookingId;
        java.sql.Date sqldate= new java.sql.Date(book.dateOfJourney.getTime());
        pst.setInt(1,id);
        pst.setString(2, name);
        pst.setInt(3, busno);
        pst.setDate(4, sqldate);
        pst.setString(5, book.From);
        pst.setString(6, book.To);

        int rows=pst.executeUpdate();
        return rows;



    }

    public int cancelOrder(Cancellation cancel) throws SQLException {
        String query ="DELETE FROM Booking WHERE Booking_id=?";
        Connection con =DBConnection.getConnection();
        PreparedStatement canpst =con.prepareStatement(query);
        canpst.setInt(1,cancel.bookingid);
//        pst.setString(2, cancel.passengerName);
//        java.sql.Date sqldate= new java.sql.Date(cancel.dateofJourneytobeCancelled.getTime());
//        pst.setDate(3,sqldate);
        int rows=canpst.executeUpdate();
        return rows;


    }


    public int getBookingId(int BusNo) throws SQLException {

            Connection con = DBConnection.getConnection();
            String query = "SELECT MAX(Booking_id) FROM Booking WHERE Bus_no = ?";
            PreparedStatement pstId = con.prepareStatement(query);
            pstId.setInt(1, BusNo);
            ResultSet rs = pstId.executeQuery();
            int bookingId = 0;

            if (rs.next()) {
                int lastId = rs.getInt(1);
                if (lastId == 0) {
                    // First booking for this bus
                    bookingId = BusNo * 10000 + 1;
                } else {
                    bookingId = lastId + 1;
                }

            }
            return bookingId;




    }


    public boolean checkPlace(String place,int BusNo) throws SQLException {
        String query="SELECT  * FROM RouteStops WHERE StopName=? AND BusNo=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, place);
        pst.setInt(2,BusNo);
        ResultSet rs = pst.executeQuery();


        return rs.next();

    }

    public boolean checkBusNo(int BusNo) throws SQLException {
        String query="SELECT * from Bus WHERE BusNo=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, BusNo);

        ResultSet rs = pst.executeQuery();
        return rs.next();


    }


}

