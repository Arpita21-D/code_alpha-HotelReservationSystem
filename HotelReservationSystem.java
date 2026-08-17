import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        seedRooms();
    }

    private void seedRooms() {
        rooms.add(new Room(101, "Single", 75.00));
        rooms.add(new Room(102, "Single", 80.00));
        rooms.add(new Room(201, "Double", 120.00));
        rooms.add(new Room(202, "Double", 125.00));
        rooms.add(new Room(301, "Suite", 200.00));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void displayAvailableRooms() {
        System.out.println("Available rooms:");
        boolean found = false;
        for (Room r : rooms) {
            if (r.isAvailable()) {
                System.out.println(r.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available at the moment.");
    }

    public Optional<Room> findRoomByNumber(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst();
    }

    public Optional<Reservation> findReservationById(int id) {
        return reservations.stream().filter(res -> res.getId() == id).findFirst();
    }

    public boolean makeReservation(int roomNumber, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        Optional<Room> opt = findRoomByNumber(roomNumber);
        if (!opt.isPresent()) return false;
        Room room = opt.get();
        if (!room.isAvailable()) return false;
        Reservation res = new Reservation(room, customer, checkIn, checkOut);
        reservations.add(res);
        room.setAvailable(false);
        return true;
    }

    public boolean cancelReservation(int reservationId) {
        Optional<Reservation> opt = findReservationById(reservationId);
        if (!opt.isPresent()) return false;
        Reservation res = opt.get();
        res.getRoom().setAvailable(true);
        reservations.remove(res);
        return true;
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println(r.toString());
        }
    }

    public boolean updateRoomAvailability(int roomNumber, boolean available) {
        Optional<Room> opt = findRoomByNumber(roomNumber);
        if (!opt.isPresent()) return false;
        opt.get().setAvailable(available);
        return true;
    }

    public double calculateReservationCost(int reservationId) {
        Optional<Reservation> opt = findReservationById(reservationId);
        if (!opt.isPresent()) return 0.0;
        return opt.get().getTotalCost();
    }

    public LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
