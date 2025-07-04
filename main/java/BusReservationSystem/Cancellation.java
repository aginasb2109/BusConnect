package BusReservationSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cancellation {
    int bookingid;
    String passengerName;
    Date dateofJourneytobeCancelled;

    public Cancellation(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Booking Id");
        bookingid=scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your Name");
        String passengerName=scan.nextLine();
        System.out.println("Enter your Date of Journey");
        String dataInput=scan.next();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateofJourneytobeCancelled=dateFormat.parse(dataInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
