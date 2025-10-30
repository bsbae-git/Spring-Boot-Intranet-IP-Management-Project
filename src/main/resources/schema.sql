CREATE TABLE network (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cidr VARCHAR(255) NOT NULL,
    network_address VARCHAR(255) NOT NULL,
    broadcast_address VARCHAR(255) NOT NULL,
    subnet_mask VARCHAR(255) NOT NULL
);

CREATE TABLE ip_address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ip_address VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL,
    network_id INT,
    FOREIGN KEY (network_id) REFERENCES network(id)
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE allocation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ip_id INT NOT NULL UNIQUE,
    user_id VARCHAR(255) NOT NULL,
    purpose VARCHAR(255),
    allocated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (ip_id) REFERENCES ip_address(id)
);
