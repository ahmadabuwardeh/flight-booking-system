CREATE TABLE IF NOT EXISTS flight (
                                      flight_code VARCHAR(255) PRIMARY KEY,
    origin VARCHAR(255),
    destination VARCHAR(255),
    departure_date DATE,
    available_seats INT
    );

INSERT INTO flight (flight_code, origin, destination, departure_date, available_seats)
VALUES
    ('BA-101', 'AMM', 'DXB', '2024-09-22', 50),
    ('BA-102', 'AMM', 'DXB', '2024-09-22', 50),
    ('BA-201', 'DXB', 'AMM', '2024-09-29', 50),
    ('BA-202', 'DXB', 'AMM', '2024-09-29', 50);
