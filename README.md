# 🏨 Hotel Reservation System

A Java-based hotel reservation application with a front-end website demo for room booking and reservation management.

## 📌 Project Overview

This project is designed to manage hotel room bookings in a simple and beginner-friendly way. The core logic is implemented in Java using classes such as `HotelReservationSystem`, `Room`, `Reservation`, and `Customer`, while the project also contains a separate website front-end for a more visual booking experience.

The Java version allows the user to:

- View available rooms
- Create and cancel reservations
- Check room availability
- Calculate the total cost for a reservation
- Manage reservations by ID and room number

The website version provides a polished hotel booking interface with room cards, booking forms, filters, and a reservation list.

---

## ✨ Features

### Java console application

- Display all available rooms
- Reserve a room by room number
- Cancel an existing reservation
- View all current reservations
- Update room availability manually
- Check reservation cost using reservation ID
- Validate check-in and check-out dates
- Generate unique reservation IDs automatically

### Front-end website demo

- Responsive hotel landing page
- Room cards with room type, price, and status
- Room filtering by type and availability
- Booking form with guest and date details
- Automatic calculation of nights and total price
- Reservation preview and booking summary
- Reservation history rendered on the page
- Local browser storage for saved reservations

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core hotel reservation logic and business rules |
| Java SE | Date handling, object modeling, and console application flow |
| HTML5 | Web page structure |
| CSS3 | Styling and responsive layout |
| JavaScript | Booking UI logic and interactive behavior |
| LocalStorage | Saves reservation data in the browser |
| VS Code | Project development and editing |

---

## 📂 Project Structure

```text
HotelReservationSystem/
├── Customer.java
├── GradeTrackerMain.java
├── HotelReservationSystem.java
├── Main.java
├── Reservation.java
├── Room.java
├── index.html
├── script.js
├── style.css
├── README.md
├── website/
│   ├── index.html
│   ├── script.js
│   ├── style.css
│   └── README.md
└── HotelReservationSystem.java
```

### File descriptions

| File | Description |
|---|---|
| `Main.java` | Runs the console menu and handles user interaction |
| `HotelReservationSystem.java` | Main service class for room and reservation management |
| `Room.java` | Represents a hotel room with type, price, number, and availability |
| `Reservation.java` | Stores reservation data and calculates total cost |
| `Customer.java` | Stores guest details such as name and phone |
| `index.html` | Front-end hotel booking landing page |
| `style.css` | Styling for the website |
| `script.js` | Booking form interactions and room management logic |
| `website/` | Additional front-end project files for the hotel website demo |

---

## ⚙️ How the System Works

The Java application follows a simple object-oriented flow:

1. `HotelReservationSystem` creates a list of rooms in `seedRooms()`.
2. Each `Room` has a room number, type, price, and availability flag.
3. A guest is represented by the `Customer` class.
4. When a reservation is made, `makeReservation()` validates the room and checks whether it is available.
5. A new `Reservation` object is created with the selected room, customer, and check-in/check-out dates.
6. `Reservation.getNights()` calculates the number of nights using `ChronoUnit.DAYS.between(...)`.
7. `Reservation.getTotalCost()` calculates:

```java
getNights() * room.getPrice()
```

8. When a reservation is canceled, the room becomes available again and the reservation is removed from the list.

The website version works similarly visually by managing room availability, room filters, and reservation summaries in the browser.

---

## ▶️ How to Run the Project

### 1) Java console application

Open a terminal in the project folder and run:

```bash
javac *.java
java Main
```

This starts the interactive hotel reservation menu.

### 2) Front-end website demo

You can open `index.html` directly in a browser, or run a local server:

```bash
cd HotelReservationSystem
python -m http.server 8000
```

Then open:

```text
http://localhost:8000
```

For the website inside the `website/` folder, run:

```bash
cd website
python -m http.server 8000
```

---

## 🧭 How to Use the Application

### Console app usage

When `Main.java` runs, the menu displays:

- Display available rooms
- Make reservation
- Cancel reservation
- View reservations
- Update room availability
- Calculate reservation cost
- Exit

### Typical flow

1. Choose option `1` to view available rooms.
2. Choose option `2` to make a reservation.
3. Enter the room number and customer details.
4. Enter valid check-in and check-out dates in `YYYY-MM-DD` format.
5. Confirm the reservation is created successfully.
6. Use option `4` to view all reservations.
7. Use option `6` to calculate the total cost for a reservation ID.
8. Use option `3` to cancel a reservation.

---

## 🧾 Example Reservation Workflow

Example workflow:

```text
1. Display available rooms
2. Choose room 101
3. Enter customer name: John Smith
4. Enter phone: 9876543210
5. Check-in: 2026-08-20
6. Check-out: 2026-08-23
7. Reservation created successfully
8. View reservations
9. Total cost is calculated based on the number of nights
```

Example calculation:

- Room: `Room 101`
- Type: `Single`
- Price: `$75.00/night`
- Nights: `3`
- Total cost: `$225.00`

---

## 🖼️ Screenshots

> Add screenshots here before publishing to GitHub.

### Placeholder screenshots

![Home page placeholder](https://via.placeholder.com/1200x700?text=Hotel+Home+Page)

![Room listing placeholder](https://via.placeholder.com/1200x700?text=Room+Listing)

![Booking form placeholder](https://via.placeholder.com/1200x700?text=Booking+Form)

![Reservations page placeholder](https://via.placeholder.com/1200x700?text=Reservation+List)

---

## 💻 Sample Output

```text
=== CodeAlpha Hotel Reservation System ===
1. Display available rooms
2. Make reservation
3. Cancel reservation
4. View reservations
5. Update room availability
6. Calculate reservation cost
0. Exit
Choose an option: 1
Available rooms:
Room 101 - Single - $75.00 - Available
Room 102 - Single - $80.00 - Available
Room 201 - Double - $120.00 - Available
Room 202 - Double - $125.00 - Available
Room 301 - Suite - $200.00 - Available

Choose an option: 2
Enter room number to reserve: 101
Customer name: John Smith
Customer phone: 9876543210
Check-in date (YYYY-MM-DD): 2026-08-20
Check-out date (YYYY-MM-DD): 2026-08-23
Reservation created successfully.

Choose an option: 4
Reservation 1: Room 101 - Single - $75.00 - Occupied | John Smith (9876543210) | 2026-08-20 -> 2026-08-23 (3 nights) - $225.00
```

---

## 🚀 Future Enhancements

The current project is a solid foundation and can be expanded with:

- Room booking validation for overlapping dates
- Customer login and account management
- Admin dashboard for room and reservation management
- Database integration using MySQL or SQLite
- Payment processing module
- Email or SMS confirmation feature
- Better UI/UX for the website version
- Search and filtering by price, date, and room type

---

## 👤 Author

- Name: ARPITA DESAI
- Project: Hotel Reservation System
- Role: Java and web development project
- LinkedIn/GitHub: Add your profile link here

---

## 🏆 Internship / Task Information

This project was developed as part of the CodeAlpha internship/task program for hotel reservation system development.

- Organization: CodeAlpha
- Project Type: Java Application + Hotel Booking Website Demo
- Objective: Build a functional hotel reservation system using Java logic and a simple front-end interface
- Status: Completed / In progress / Ready for review

---

## ✅ Summary

This project demonstrates a practical approach to hotel booking management using Java classes and a clean web interface. It is ideal for learning core concepts such as:

- Object-oriented programming
- Data modeling
- Reservation logic
- Date handling
- User input validation
- UI interaction with JavaScript

If you want, this README can also be adapted for a more formal GitHub portfolio style or a more internship-report style format.