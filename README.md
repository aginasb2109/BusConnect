🚍 BusConnect – Console-Based Bus Reservation System
Overview:
BusConnect is a Java console-based application integrated with JDBC for backend database operations. It simulates a basic bus reservation system with real-world functionalities such as booking, cancellation, capacity management, and bus information retrieval.

💡 Key Features:
Book a Seat
Allows passengers to book seats on a selected bus for a specific date, updating availability in real-time.

Cancel Booking
Enables passengers to cancel their booking and automatically updates the seat count for the respective bus and date.

Check Bus Capacity
Displays the number of available seats for a given bus on a specific date, ensuring users can plan accordingly.

View Bus Information
Shows details about all registered buses, including bus number, name, source, destination, and timing.

Add New Bus
Admin can add a new bus with all relevant details like route, stops, timings, and seat capacity directly into the system.

Exit Application
Provides a clean exit from the program, closing all connections and resources properly.

🛠️ Technologies Used:
Java – Core programming for the logic and user interaction

JDBC (Java Database Connectivity) – For connecting and performing SQL operations on the backend database (e.g., MySQL)

MySQL/Other RDBMS – Used to store all booking records, bus information, and route details

SQL – To create, retrieve, update, and delete data from the database
