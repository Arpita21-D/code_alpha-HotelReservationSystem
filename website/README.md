# CodeAlpha Grand Hotel Website

## Project Title
CodeAlpha Grand Hotel Booking Website

## Project Description
This project is a modern, responsive hotel booking website built for the CodeAlpha Hotel Reservation System internship project. It demonstrates room availability, reservation management, cost calculation, and booking workflows in a clean web interface.

## Features
- Display available hotel rooms
- Room categories: Standard, Deluxe, and Suite
- Show room number, type, nightly price, and status
- Filter rooms by room type and availability
- Select check-in and check-out dates
- Calculate the number of nights and total cost automatically
- Make a reservation with validation
- Generate a unique reservation ID
- Display all bookings in a reservation list
- Cancel reservations with confirmation
- Update room availability after booking or cancellation
- Store reservations in browser localStorage
- Show success and error messages for user feedback

## Technologies Used
- HTML5
- CSS3
- JavaScript (Vanilla JS)
- LocalStorage for persistence
- Responsive design with mobile-first layout

## How to Run
1. Open the project folder in your browser.
2. Navigate to the `website` folder.
3. Open `index.html` directly in a browser.
4. If you prefer a local server, run:

```bash
cd website
python -m http.server 8000
```

Then open http://localhost:8000 in the browser.

## How to Test the Booking System
1. Open the website.
2. View the room cards and use the filters to check room types and available rooms.
3. Select a room in the booking form.
4. Choose a valid check-in and check-out date.
5. Enter your name, email, phone number, and guest count.
6. Click the Book Room button.
7. Check that a reservation is added to the Reservations section.
8. Verify that the room becomes unavailable after booking.
9. Click Cancel Reservation to remove the booking and restore the room availability.
10. Refresh the page to confirm that reservations remain saved in localStorage.

## Example Reservation
- Customer: Jane Doe
- Room: Room 201 - Deluxe
- Check-in: 2026-08-21
- Check-out: 2026-08-24
- Nights: 3
- Total: $450.00
- Booking ID: CA-201-2026-1234

## Screenshots
Add screenshots here for:
- Home section
- Room listing
- Booking form
- Reservation list
- Cancelled reservation example

## GitHub Project Information
- Project Name: CodeAlpha Grand Hotel
- Category: Internship web development project
- Repository: Add your GitHub repository link here
- Author: Your Name
- Date: 2026

## Notes
This website is designed to complement the existing Java console application without modifying the Java project files. It is a separate front-end demo created for presentation and internship submission.
