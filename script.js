const roomCatalog = [
  {
    id: 101,
    number: 101,
    type: 'Standard',
    price: 100,
    image:
      'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  },
  {
    id: 102,
    number: 102,
    type: 'Standard',
    price: 100,
    image:
      'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  },
  {
    id: 201,
    number: 201,
    type: 'Deluxe',
    price: 150,
    image:
      'https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  },
  {
    id: 202,
    number: 202,
    type: 'Deluxe',
    price: 150,
    image:
      'https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  },
  {
    id: 301,
    number: 301,
    type: 'Suite',
    price: 200,
    image:
      'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  },
  {
    id: 302,
    number: 302,
    type: 'Suite',
    price: 200,
    image:
      'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80',
    isAvailable: true
  }
];

const STORAGE_KEY = 'codealpha_hotel_reservations';
let selectedRoomId = null;

const roomsGrid = document.getElementById('roomsGrid');
const roomTypeFilter = document.getElementById('roomTypeFilter');
const availabilityFilter = document.getElementById('availabilityFilter');
const bookingForm = document.getElementById('bookingForm');
const roomSelect = document.getElementById('roomSelect');
const checkInInput = document.getElementById('checkIn');
const checkOutInput = document.getElementById('checkOut');
const nightsDisplay = document.getElementById('nightsDisplay');
const totalPriceDisplay = document.getElementById('totalPriceDisplay');
const formMessage = document.getElementById('formMessage');
const reservationsList = document.getElementById('reservationsList');
const reservationPreview = document.getElementById('reservationPreview');

function getTodayDate() {
  const today = new Date();
  const offset = today.getTimezoneOffset();
  const localToday = new Date(today.getTime() - offset * 60000);
  return localToday.toISOString().split('T')[0];
}

function getReservations() {
  const rawData = localStorage.getItem(STORAGE_KEY);
  return rawData ? JSON.parse(rawData) : [];
}

function setReservations(reservations) {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(reservations));
}

function formatCurrency(value) {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  }).format(value);
}

function numberOfNights(checkIn, checkOut) {
  if (!checkIn || !checkOut) {
    return 0;
  }

  const startDate = new Date(checkIn);
  const endDate = new Date(checkOut);

  const difference = endDate.getTime() - startDate.getTime();
  const nights = difference / (1000 * 60 * 60 * 24);

  return nights > 0 ? nights : 0;
}

function getRoomByNumber(roomNumber) {
  return roomCatalog.find((room) => room.number === Number(roomNumber));
}

function isRoomBookedForDates(roomNumber, checkIn, checkOut, excludeReservationId = null) {
  const reservations = getReservations();

  return reservations.some((reservation) => {
    if (excludeReservationId && reservation.id === excludeReservationId) {
      return false;
    }

    if (Number(reservation.roomNumber) !== Number(roomNumber)) {
      return false;
    }

    if (reservation.status === 'Cancelled') {
      return false;
    }

    const existingCheckIn = new Date(reservation.checkIn);
    const existingCheckOut = new Date(reservation.checkOut);
    const newCheckIn = new Date(checkIn);
    const newCheckOut = new Date(checkOut);

    return newCheckIn < existingCheckOut && newCheckOut > existingCheckIn;
  });
}

function updateRoomSelectOptions() {
  const options = [
    '<option value="">Select a room</option>'
  ];

  roomCatalog.forEach((room) => {
    const option = document.createElement('option');
    option.value = room.number;
    option.textContent = `Room ${room.number} - ${room.type} - ${formatCurrency(room.price)}/night`;
    options.push(option.outerHTML);
  });

  roomSelect.innerHTML = options.join('');
}

function renderRooms() {
  const roomTypeValue = roomTypeFilter.value;
  const availabilityValue = availabilityFilter.value;

  const filteredRooms = roomCatalog.filter((room) => {
    const matchesType = roomTypeValue === 'all' || room.type === roomTypeValue;
    const hasAvailability = room.isAvailable;
    const matchesAvailability =
      availabilityValue === 'all' ||
      (availabilityValue === 'available' && hasAvailability) ||
      (availabilityValue === 'booked' && !hasAvailability);

    return matchesType && matchesAvailability;
  });

  if (!filteredRooms.length) {
    roomsGrid.innerHTML = '<div class="empty-state">No rooms match the current filter.</div>';
    return;
  }

  roomsGrid.innerHTML = filteredRooms
    .map((room) => {
      const roomIsAvailable = room.isAvailable;
      const statusText = roomIsAvailable ? 'Available' : 'Booked';
      const statusClass = roomIsAvailable ? 'available' : 'booked';

      return `
        <article class="room-card">
          <img src="${room.image}" alt="${room.type} room ${room.number}" />
          <div class="room-card-content">
            <div class="room-card-top">
              <h3>Room ${room.number}</h3>
              <span class="status-pill ${statusClass}">${statusText}</span>
            </div>

            <div class="room-meta">
              <span>${room.type}</span>
              <span>Max 2 guests</span>
            </div>

            <div class="room-price">
              <div>
                <small>From</small>
                <strong>${formatCurrency(room.price)}</strong>
              </div>
              <button type="button" data-room-number="${room.number}" ${roomIsAvailable ? '' : 'disabled'}>
                ${roomIsAvailable ? 'Select Room' : 'Unavailable'}
              </button>
            </div>
          </div>
        </article>
      `;
    })
    .join('');

  const roomButtons = roomsGrid.querySelectorAll('button[data-room-number]');
  roomButtons.forEach((button) => {
    button.addEventListener('click', () => {
      const roomNumber = button.dataset.roomNumber;
      roomSelect.value = roomNumber;
      selectedRoomId = Number(roomNumber);
      const selectedRoom = getRoomByNumber(roomNumber);
      if (selectedRoom) {
        const targetSection = document.getElementById('booking');
        targetSection.scrollIntoView({ behavior: 'smooth' });
      }
      updateBookingSummary();
    });
  });
}

function showMessage(message, type = 'success') {
  formMessage.textContent = message;
  formMessage.className = `form-message ${type}`;
}

function updateBookingSummary() {
  const roomValue = roomSelect.value;
  const checkInValue = checkInInput.value;
  const checkOutValue = checkOutInput.value;

  const selectedRoom = getRoomByNumber(roomValue);
  const nights = numberOfNights(checkInValue, checkOutValue);

  if (!selectedRoom || !checkInValue || !checkOutValue || nights <= 0) {
    nightsDisplay.textContent = '0';
    totalPriceDisplay.textContent = '$0.00';
    reservationPreview.value = 'Will be generated after booking';
    return;
  }

  const total = selectedRoom.price * nights;
  nightsDisplay.textContent = String(nights);
  totalPriceDisplay.textContent = formatCurrency(total);
  reservationPreview.value = `CA-${selectedRoom.number}-${nights}n-${Math.floor(Math.random() * 900 + 100)}`;
}

function validateForm(formData) {
  const requiredFields = [
    { name: 'customerName', value: formData.customerName },
    { name: 'customerEmail', value: formData.customerEmail },
    { name: 'customerPhone', value: formData.customerPhone },
    { name: 'roomSelect', value: formData.roomSelect },
    { name: 'checkIn', value: formData.checkIn },
    { name: 'checkOut', value: formData.checkOut }
  ];

  for (const field of requiredFields) {
    if (!field.value || !String(field.value).trim()) {
      return `${field.name.replace(/([A-Z])/g, ' $1').trim()} is required.`;
    }
  }

  const emailValue = formData.customerEmail.trim();
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(emailValue)) {
    return 'Please enter a valid email address.';
  }

  const guestCount = Number(formData.guestCount);
  if (!Number.isInteger(guestCount) || guestCount < 1 || guestCount > 6) {
    return 'Guest count must be between 1 and 6.';
  }

  const checkInDate = new Date(formData.checkIn);
  const checkOutDate = new Date(formData.checkOut);
  const nights = numberOfNights(formData.checkIn, formData.checkOut);

  if (checkOutDate <= checkInDate) {
    return 'Check-out date must be after the check-in date.';
  }

  if (nights <= 0) {
    return 'Reservation must include at least one night.';
  }

  const selectedRoom = getRoomByNumber(formData.roomSelect);
  if (!selectedRoom) {
    return 'Please choose a valid room.';
  }

  if (isRoomBookedForDates(selectedRoom.number, formData.checkIn, formData.checkOut)) {
    return 'This room is already reserved for the selected dates. Please choose another room or change the dates.';
  }

  return null;
}

function saveReservation(formData, totalPrice, reservationId) {
  const reservations = getReservations();
  const room = getRoomByNumber(formData.roomSelect);
  const nights = numberOfNights(formData.checkIn, formData.checkOut);

  const reservation = {
    id: reservationId,
    customerName: formData.customerName.trim(),
    email: formData.customerEmail.trim(),
    phone: formData.customerPhone.trim(),
    roomNumber: Number(room.number),
    roomType: room.type,
    roomPrice: room.price,
    checkIn: formData.checkIn,
    checkOut: formData.checkOut,
    guests: Number(formData.guestCount),
    nights,
    totalCost: totalPrice,
    status: 'Confirmed',
    createdAt: new Date().toISOString()
  };

  reservations.push(reservation);
  setReservations(reservations);
}

function renderReservations() {
  const reservations = getReservations();

  if (!reservations.length) {
    reservationsList.innerHTML = '<div class="empty-state">No reservations yet. Your bookings will appear here.</div>';
    return;
  }

  const activeReservations = reservations.filter((reservation) => reservation.status !== 'Cancelled');

  if (!activeReservations.length) {
    reservationsList.innerHTML = '<div class="empty-state">No active reservations. All bookings have been cancelled.</div>';
    return;
  }

  reservationsList.innerHTML = activeReservations
    .map(
      (reservation) => `
        <article class="reservation-card">
          <div class="reservation-head">
            <h3>${reservation.id}</h3>
            <span class="reservation-tag">${reservation.status}</span>
          </div>

          <p><span>Customer</span><strong>${reservation.customerName}</strong></p>
          <p><span>Room</span><strong>Room ${reservation.roomNumber} (${reservation.roomType})</strong></p>
          <p><span>Check-in</span><strong>${reservation.checkIn}</strong></p>
          <p><span>Check-out</span><strong>${reservation.checkOut}</strong></p>
          <p><span>Nights</span><strong>${reservation.nights}</strong></p>
          <p><span>Guests</span><strong>${reservation.guests}</strong></p>
          <p><span>Total</span><strong>${formatCurrency(reservation.totalCost)}</strong></p>

          <button type="button" class="cancel-btn" data-reservation-id="${reservation.id}">Cancel Reservation</button>
        </article>
      `
    )
    .join('');

  const cancelButtons = reservationsList.querySelectorAll('.cancel-btn');
  cancelButtons.forEach((button) => {
    button.addEventListener('click', () => {
      const reservationId = button.dataset.reservationId;
      const shouldDelete = window.confirm('Are you sure you want to cancel this reservation?');

      if (!shouldDelete) {
        return;
      }

      const reservationsData = getReservations();
      const updatedReservations = reservationsData.map((reservation) => {
        if (reservation.id === reservationId) {
          reservation.status = 'Cancelled';
        }
        return reservation;
      });

      setReservations(updatedReservations);
      const roomToRelease = updatedReservations.find((reservation) => reservation.id === reservationId);
      if (roomToRelease) {
        const room = getRoomByNumber(roomToRelease.roomNumber);
        if (room) {
          room.isAvailable = true;
        }
      }

      renderRooms();
      renderReservations();
      showMessage('Reservation cancelled successfully.', 'success');
    });
  });
}

function setMinDates() {
  const today = getTodayDate();
  checkInInput.min = today;
  checkOutInput.min = today;
}

function handleDateChanges() {
  const checkInValue = checkInInput.value;
  const checkOutValue = checkOutInput.value;

  if (checkInValue) {
    checkOutInput.min = checkInValue;
  }

  if (checkOutValue && checkInValue) {
    if (new Date(checkOutValue) <= new Date(checkInValue)) {
      showMessage('Check-out date must be later than check-in date.', 'error');
    } else {
      formMessage.textContent = '';
      formMessage.className = 'form-message';
    }
  }

  updateBookingSummary();
}

bookingForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const formData = {
    customerName: document.getElementById('customerName').value,
    customerEmail: document.getElementById('customerEmail').value,
    customerPhone: document.getElementById('customerPhone').value,
    roomSelect: document.getElementById('roomSelect').value,
    checkIn: document.getElementById('checkIn').value,
    checkOut: document.getElementById('checkOut').value,
    guestCount: document.getElementById('guestCount').value
  };

  const validationError = validateForm(formData);
  if (validationError) {
    showMessage(validationError, 'error');
    return;
  }

  const room = getRoomByNumber(formData.roomSelect);
  if (!room) {
    showMessage('Please select a valid room.', 'error');
    return;
  }

  const nights = numberOfNights(formData.checkIn, formData.checkOut);
  const total = room.price * nights;
  const reservationId = `CA-${room.number}-${new Date().getFullYear()}-${Math.floor(Math.random() * 9000 + 1000)}`;

  saveReservation(formData, total, reservationId);

  const roomIndex = roomCatalog.findIndex((catalogRoom) => catalogRoom.number === room.number);
  if (roomIndex >= 0) {
    roomCatalog[roomIndex].isAvailable = false;
  }

  showMessage(`Reservation confirmed! Your booking ID is ${reservationId}.`, 'success');
  bookingForm.reset();
  document.getElementById('guestCount').value = 2;
  roomSelect.value = '';
  updateBookingSummary();
  renderRooms();
  renderReservations();
  setMinDates();
});

roomTypeFilter.addEventListener('change', renderRooms);
availabilityFilter.addEventListener('change', renderRooms);
checkInInput.addEventListener('change', handleDateChanges);
checkOutInput.addEventListener('change', handleDateChanges);
roomSelect.addEventListener('change', updateBookingSummary);

document.addEventListener('DOMContentLoaded', () => {
  setMinDates();
  updateRoomSelectOptions();
  renderRooms();
  renderReservations();
  updateBookingSummary();
});
