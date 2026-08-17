import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static int nextId = 1;

    private int id;
    private Room room;
    private Customer customer;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(Room room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        this.id = nextId++;
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getTotalCost() {
        return getNights() * room.getPrice();
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE;
        return "Reservation " + id + ": " + room.toString() + " | " + customer.toString() + " | " + checkIn.format(f) + " -> " + checkOut.format(f) + " (" + getNights() + " nights) - $" + String.format("%.2f", getTotalCost());
    }
}
