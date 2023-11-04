-- Insert roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

-- Insert users
INSERT INTO users (first_name, middle_name, last_name, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('John', NULL, 'Doe', 'johndoe', '$2a$10$FgG0KCXYj0ITRx44WX/Gr.E4QdEwNrAaUTke4jSfjvvUYz3DQhtSi', 'john.doe@example.com', true, true, true, true);

INSERT INTO users (first_name, middle_name, last_name, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Alice', 'M', 'Johnson', 'alicejohnson', '$2a$10$FgG0KCXYj0ITRx44WX/Gr.E4QdEwNrAaUTke4jSfjvvUYz3DQhtSi', 'alice.j@example.com', true, true, true, true);

-- Insert user roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- John Doe is an ADMIN
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2); -- John Doe is also a USER
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- Alice Johnson is a USER



INSERT  INTO airport(code, location, name) VALUES ('CNA', '12 Brookhust Str, Cedar Rapids, IA', 'Cedar');
INSERT  INTO airport(code, location, name) VALUES ('SCA', '111 First Str, Santa Anna, CA', 'Wayne');
INSERT INTO airport(code, location, name) VALUES ('OHARE', '123 Elm St, wheeling, IL', 'Chicago');
INSERT INTO airport(code, location, name) VALUES ('SKY', '456 Oak Ave, phoenix, AZ', 'Arizona');


INSERT  INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('AMA123', 'American Airline', 'Airbus 777', 150);
INSERT  INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('JPA123', 'Japanese Airline', 'Boeing 878', 300);
INSERT  INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('KRA123', 'Korean Airline', 'Airbus 777', 150);

INSERT INTO Schedule (arrival_date, departure_date, dstn_airport_code, src_airport_code)
VALUES ('2023-11-01 08:00:00', '2023-11-01 10:00:00', 'CNA', 'SCA'),
       ('2023-11-02 09:00:00', '2023-11-02 11:00:00', 'SKY', 'OHARE');


INSERT INTO scheduled_flight (available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
VALUES (50, 50000, 30, 'AMA123', 5);

INSERT INTO scheduled_flight (available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
VALUES (20, 10000, 70, 'JPA123', 6);

INSERT INTO booking (booking_code, booking_date, no_of_passengers, scheduled_flight_schedule_flight_id)
VALUES ('BK123', '2023-11-01T12:00:00+00:00', 1, 1);

INSERT INTO booking (booking_code, booking_date, no_of_passengers, scheduled_flight_schedule_flight_id)
VALUES ('BK456', '2023-11-02T14:00:00+00:00', 2, 2);

INSERT INTO payment (payment_code, last4digit_payment_card, booking_user_fullname, booking_user_email, total_price, booking_id)
VALUES ('PAY123', '6789', 'John Smith', 'john@example.com', 500.00, 1);

INSERT INTO payment (payment_code, last4digit_payment_card, booking_user_fullname, booking_user_email, total_price, booking_id)
VALUES ('PAY456', '1234', 'Alice Johnson', 'alice@example.com', 700.00, 2);

INSERT INTO passenger (passenger_name, date_of_birth, luggage, booking_id)
VALUES ('John Smith', '1990-05-15', 1, 1);

INSERT INTO passenger (passenger_name, date_of_birth, luggage, booking_id)
VALUES ('Alice Johnson', '1985-09-22', 2, 2);
