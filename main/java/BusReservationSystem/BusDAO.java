package BusReservationSystem;



import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BusDAO {
   public void displayInfo() throws SQLException {
       String query1="SELECT * FROM Bus";
       String query2="SELECT StopOrder, StopName, ArrivalTime FROM RouteStops WHERE BusNo = ? ORDER BY StopOrder";
       Connection con=DBConnection.getConnection();
       Statement st= con.createStatement();
       ResultSet rs= st.executeQuery(query1);
       System.out.println("--------Bus Information for Chennai City------------------------------------------");
       while(rs.next()){

           System.out.println("Bus No: "+rs.getInt(1)+"| Departure: "+rs.getString(4)+" | Destination: "+rs.getString(5)+"  | AC: "+rs.getBoolean(2)+" | Total Capacity: "+ rs.getInt(3)+" | Departure Time: "+rs.getTime(6));

       }
       System.out.println("---------------------------------------------------------------------");



   }

    public void fullDisplayInfo() throws SQLException {
        String busQuery = "SELECT * FROM Bus";
        String routeQuery = "SELECT StopOrder, StopName, ArrivalTime FROM RouteStops WHERE BusNo = ? ORDER BY StopOrder";

        Connection con = DBConnection.getConnection();
        Statement busStmt = con.createStatement();
        ResultSet busRs = busStmt.executeQuery(busQuery);

        PreparedStatement routeStmt = con.prepareStatement(routeQuery);

        System.out.println("-------- Bus Information for Chennai City ------------------------------------------");

        while (busRs.next()) {
            int busNo = busRs.getInt("BusNo");
            boolean ac = busRs.getBoolean("AcOrNonAc");
            int capacity = busRs.getInt("Capacity");
            String departure = busRs.getString("Departure");
            String destination = busRs.getString("Destination");
            Time departureTime = busRs.getTime("DepartureTime");

            System.out.println("\nBus No: " + busNo +
                    " | AC: " + (ac ? "Yes" : "No") +
                    " | Capacity: " + capacity +
                    " | From: " + departure +
                    " | To: " + destination +
                    " | Departs at: " + departureTime);

            System.out.println("   Route Stops:");

            routeStmt.setInt(1, busNo);
            ResultSet routeRs = routeStmt.executeQuery();

            while (routeRs.next()) {
                int order = routeRs.getInt("StopOrder");
                String stop = routeRs.getString("StopName");
                Time arrival = routeRs.getTime("ArrivalTime");

                System.out.println("      " + order + ". " + stop + " (Arrives at: " + arrival + ")");
            }
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }


    public int findCapacity(int BusNo) throws SQLException {
       int capacity=0;
       String query="SELECT * FROM Bus WHERE BusNO=?";
       Connection con=DBConnection.getConnection();
       PreparedStatement pst= con.prepareStatement(query);
       pst.setInt(1,BusNo);
       ResultSet rs= pst.executeQuery();
       while(rs.next()){

          capacity=rs.getInt(3);

       }
       return capacity;
   }


   public void addBusInfo(Bus bus) throws SQLException {
       String query="INSERT INTO Bus VALUES(?,?,?,?,?,?)";
       Connection con= DBConnection.getConnection();
       PreparedStatement pst= con.prepareStatement(query);
       pst.setInt(1,bus.getBusNo());
       pst.setBoolean(2,bus.getAcOrNonAc());
       pst.setInt(3,bus.getCapacity());
       pst.setString(4,bus.getDeparture());
       pst.setString(5,bus.getDestination());

       java.sql.Time sqlTime = java.sql.Time.valueOf(bus.getDepartureTime());
       pst.setTime(6, sqlTime);
       int rows=pst.executeUpdate();
       if(rows>0){
           System.out.println("The Bus information is Successfully Added");
       }
       else{
           System.out.println("The Bus information is not Successfully Added. Please provide the details according to the format");
       }

       String routeQuery = "INSERT INTO RouteStops (BusNo, StopOrder, StopName, ArrivalTime) VALUES (?, ?, ?, ?)";
       PreparedStatement routePst = con.prepareStatement(routeQuery);
       String stops[]=bus.getRoutes();
       LocalTime times[]=bus.getTimingOfRoutes();
       for (int i = 0; i < stops.length; i++) {
           String stopName = stops[i].trim();
           try {
               java.sql.Time sqlArrivalTime = java.sql.Time.valueOf(times[i]); // Already a LocalTime

               routePst.setInt(1, bus.getBusNo());
               routePst.setInt(2, i + 1); // Stop order starts from 1
               routePst.setString(3, stopName);
               routePst.setTime(4, sqlArrivalTime);

               routePst.executeUpdate();
           } catch (Exception e) {
               System.out.println("Error inserting stop '" + stopName + "'. Skipping this stop.");
           }
       }





   }

    public int currentCapacity(int BusNo,Date dateofJourney)  {
       String query="SELECT COUNT(Booking_id) FROM Booking WHERE Bus_no=? AND Date_of_journey=?";
        int BookedCapacity=0;
        int capacityOfBus=0;


     try{
         capacityOfBus=findCapacity(BusNo);
         Connection con=DBConnection.getConnection();
         PreparedStatement pst= con.prepareStatement(query);
         java.sql.Date sqlDate = new java.sql.Date(dateofJourney.getTime());
         pst.setInt(1,BusNo);
         pst.setDate(2,sqlDate);

         ResultSet rs= pst.executeQuery();
         while(rs.next()){
             BookedCapacity=rs.getInt(1);
         }

         return capacityOfBus-BookedCapacity;


     }
     catch(SQLException e){
         System.out.println(e);
     }
        return capacityOfBus-BookedCapacity;


    }
}
