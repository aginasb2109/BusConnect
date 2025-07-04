package BusReservationSystem;

import java.util.*;
public class BusReservation {
    public static void main(String[] args) {

        try {
            BusDAO busdao = new BusDAO();
            int OptionUser = 1;
            Scanner scan = new Scanner(System.in);
            busdao.displayInfo();
            while (OptionUser == 1 || OptionUser==2 ||OptionUser==3 ||OptionUser==4) {
                System.out.println("""
                        ---------------------------------------------------------
                        Welcome to Online Bus Reservation System 
                        Please choose an option from the menu below:
                        1. Book a Bus Ticket \s
                        2. Cancel an Existing Reservation \s
                        3. View Bus Information \s
                        4. Add new Bus Information(Only Admin) \s
                        5. Exit the Application 
                        --------------------------------------------------""");
                try{
                    OptionUser = scan.nextInt();
                    if (OptionUser == 1) {
                        BookingDAO bookingdao = new BookingDAO();


                        Booking booking = new Booking();
                        if (booking.isAvailable()) {
                            int res = bookingdao.bookOrder(booking);
                            if (res > 0) {
                                System.out.println("Your Booking is confirmed");
                            } else {

                                System.out.println("Your Booking is not confirmed");

                            }
                        }
                        else {
                            System.out.println("Sorry Bus is Full !");
                        }


                    }
                    else if(OptionUser==2){
                        Cancellation cancel=new Cancellation();
                        BookingDAO bookingdao=new BookingDAO();
                        int res=bookingdao.cancelOrder(cancel);
                        if(res>0){
                            System.out.println("Your cancellation is successfull");
                        }
                        else{
                            System.out.println("Your cancellation is not successfull");
                        }

                    }

                    else if(OptionUser==3){
                        BusDAO busdao1=new BusDAO();
                        busdao1.fullDisplayInfo();


                    }
                    else if(OptionUser==4){
                        Bus busAdd= new Bus();
                        BusDAO busdao2 = new BusDAO();
                        busdao2.addBusInfo(busAdd);

                    }
                }
                catch(InputMismatchException e){
                    System.out.println("The input is invalid");
                    scan.next();
                    continue;

                }



            }
            scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
