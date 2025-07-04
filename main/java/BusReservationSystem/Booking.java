package BusReservationSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Booking {

    int bookingId;
    String PassengerName;
    int BusNo;
    Date dateOfJourney;
    String From;
    String To;





    Booking() throws SQLException {
        BookingDAO book=new BookingDAO();
        BusDAO busdao= new BusDAO();
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the Name of the Passenger:");
        PassengerName=scan.nextLine();
        System.out.println("Enter the Bus No of the Passenger:");
        int Bus=scan.nextInt();
        scan.nextLine();
        if(book.checkBusNo(Bus)){
            BusNo=Bus;
        }
        else{
            System.out.println("The selected Bus is not available");
            throw new IllegalArgumentException("Invalid Bus Number");
        }
        System.out.println("Enter the date(dd-mm-yyyy)");
        String dataInput=scan.next();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateOfJourney=dateFormat.parse(dataInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }java.sql.Date sqlDate = new java.sql.Date(dateOfJourney.getTime());
        int busnum = busdao.currentCapacity(BusNo, sqlDate);

        System.out.println("Please notice that the Current available seats are "+busnum);
        System.out.println("Enter the arrival place");
        String FromPlace=scan.next();
        if(book.checkPlace(FromPlace,BusNo)){
            From=FromPlace;
        }
        else{
            System.out.println("Selected Bus does not follow this Route");
            throw new IllegalArgumentException("Invalid Place");
        }

        System.out.println("Enter the Destination place");
        String ToPlace=scan.next();
        if(book.checkPlace(ToPlace,BusNo)){
            To=ToPlace;
        }
        else{
            System.out.println("Selected Bus does not follow this Route");
            throw new IllegalArgumentException("Invalid Place");
        }


        bookingId=book.getBookingId(BusNo);
        System.out.println("Your Booking Id is "+bookingId);






    }

    public boolean isAvailable() throws SQLException {


        BusDAO busdao=new BusDAO();
        BookingDAO bookingdao =new BookingDAO();
        int capacity=busdao.findCapacity(BusNo);
        java.sql.Date sqlDate = new java.sql.Date(dateOfJourney.getTime());

        int booked=bookingdao.getNumberofBooking(BusNo, sqlDate);



        if(booked<capacity){
            return true;
        }
        return false;

    }

}

