import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelReservationSystem app = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== CodeAlpha Hotel Reservation System ===");
            System.out.println("1. Display available rooms");
            System.out.println("2. Make reservation");
            System.out.println("3. Cancel reservation");
            System.out.println("4. View reservations");
            System.out.println("5. Update room availability");
            System.out.println("6. Calculate reservation cost");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    app.displayAvailableRooms();
                    break;
                case "2":
                    app.displayAvailableRooms();
                    System.out.print("Enter room number to reserve: ");
                    int roomNum = readInt(scanner);
                    if (roomNum == Integer.MIN_VALUE) break;
                    if (!app.findRoomByNumber(roomNum).isPresent()) {
                        System.out.println("Room not found.");
                        break;
                    }
                    if (!app.findRoomByNumber(roomNum).get().isAvailable()) {
                        System.out.println("Room is not available.");
                        break;
                    }
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }
                    System.out.print("Customer phone: ");
                    String phone = scanner.nextLine().trim();
                    if (phone.isEmpty()) {
                        System.out.println("Phone cannot be empty.");
                        break;
                    }
                    System.out.print("Check-in date (YYYY-MM-DD): ");
                    String in = scanner.nextLine().trim();
                    LocalDate checkIn = app.parseDate(in);
                    if (checkIn == null) {
                        System.out.println("Invalid check-in date format.");
                        break;
                    }
                    System.out.print("Check-out date (YYYY-MM-DD): ");
                    String out = scanner.nextLine().trim();
                    LocalDate checkOut = app.parseDate(out);
                    if (checkOut == null) {
                        System.out.println("Invalid check-out date format.");
                        break;
                    }
                    if (!checkOut.isAfter(checkIn)) {
                        System.out.println("Check-out must be after check-in.");
                        break;
                    }
                    Customer customer = new Customer(name, phone);
                    boolean ok = app.makeReservation(roomNum, customer, checkIn, checkOut);
                    if (ok) System.out.println("Reservation created successfully.");
                    else System.out.println("Failed to create reservation.");
                    break;
                case "3":
                    System.out.print("Enter reservation ID to cancel: ");
                    int rid = readInt(scanner);
                    if (rid == Integer.MIN_VALUE) break;
                    boolean canceled = app.cancelReservation(rid);
                    System.out.println(canceled ? "Reservation canceled." : "Reservation not found.");
                    break;
                case "4":
                    app.viewReservations();
                    break;
                case "5":
                    System.out.print("Enter room number to update: ");
                    int rnum = readInt(scanner);
                    if (rnum == Integer.MIN_VALUE) break;
                    System.out.print("Set available? (y/n): ");
                    String a = scanner.nextLine().trim().toLowerCase();
                    boolean available = a.equals("y") || a.equals("yes");
                    boolean updated = app.updateRoomAvailability(rnum, available);
                    System.out.println(updated ? "Room updated." : "Room not found.");
                    break;
                case "6":
                    System.out.print("Enter reservation ID to calculate cost: ");
                    int cid = readInt(scanner);
                    if (cid == Integer.MIN_VALUE) break;
                    double cost = app.calculateReservationCost(cid);
                    if (cost <= 0) System.out.println("Reservation not found or cost is $0.00");
                    else System.out.println("Total cost: $" + String.format("%.2f", cost));
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    private static int readInt(Scanner scanner) {
        String s = scanner.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number.");
            return Integer.MIN_VALUE;
        }
    }
}
