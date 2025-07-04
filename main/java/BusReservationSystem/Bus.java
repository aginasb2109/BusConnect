package BusReservationSystem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Bus{
    private int BusNo;
    private boolean AcOrNonAc;
    private int Capacity;
    private String Departure ;
    private String Destination;
    private String routes[];
    private LocalTime  timingOfRoutes[];
    private LocalTime departureTime;



    public Bus(){
        Scanner scan =new Scanner(System.in);
        System.out.println("Enter the Bus number to be added");
        BusNo=scan.nextInt();
        System.out.println("Entered Bus is Ac or Non-Ac Enter True/False");
        AcOrNonAc=scan.nextBoolean();
        System.out.println("Enter the capacity of the Bus");
        Capacity=scan.nextInt();
        System.out.println("Enter the Departure of the Bus");
        Departure =scan.next();
        System.out.println("Enter the Destination of the Bus");
        Destination=scan.next();
        System.out.println("Enter the Departure Timimg of the Bus in HH.MM.SS");
        String localtimeOfbus= scan.next();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");

        try {

            LocalTime timeBus = LocalTime.parse(localtimeOfbus, formatter);
            departureTime=timeBus;
        } catch (Exception e) {
            System.out.println("Invalid time format. Please use HH-MM-SS.");
        }
        scan.nextLine();

        System.out.println("Enter the route stops (comma-separated, e.g., Stop1,Stop2,Stop3):");
        String routeInput = scan.nextLine();
        routes= routeInput.split(",");

        System.out.println("Enter the arrival times for each stop in HH.mm.ss format (comma-separated, e.g., 08.00.00,08.30.00,09.00.00):");
        String timeInput = scan.nextLine();
        String timeStrings[] = timeInput.split(",");
        this.timingOfRoutes = new LocalTime[timeStrings.length];


        try {
            for (int i = 0; i < timeStrings.length; i++) {
                this.timingOfRoutes[i] = LocalTime.parse(timeStrings[i].trim(), formatter);
            }
        } catch (Exception e) {
            System.out.println("Invalid time format in route timings.");
        }

        if (routes.length != timingOfRoutes.length) {
            System.out.println("Mismatch between number of stops and arrival times.");
            return;
        }


    }



    public Bus(int no, boolean ac, int cap,String destination,LocalTime departure){
        this.BusNo=no;
        this.AcOrNonAc=ac;
        this.Capacity=cap;
        this.Departure="Chennai";
        this.Destination=destination;
        this.departureTime=departure;

    }
    public String[] getRoutes() {
        return routes;
    }

    public void setRoutes(String[] routes) {
        this.routes = routes;
    }

    public LocalTime[] getTimingOfRoutes() {
        return timingOfRoutes;
    }

    public void setTimingOfRoutes(LocalTime[] timingOfRoutes) {
        this.timingOfRoutes = timingOfRoutes;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }







    public  int getBusNo(){
        return BusNo;
    }


    public  int getCapacity(){
        return Capacity;
    }

    public void SetCapacity(int n){
        this.Capacity=n;

    }
    public  boolean getAcOrNonAc(){
        return AcOrNonAc;
    }

    public void SetCapacity(boolean n){
        this.AcOrNonAc=n;

    }




}



