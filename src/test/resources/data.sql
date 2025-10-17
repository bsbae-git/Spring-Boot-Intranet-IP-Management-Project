-- Insert a default admin user for testing purposes if it doesn't exist.
-- The password is 'adminpassword'.
MERGE INTO users (username, password, role)
KEY(username)
VALUES ('admin', '$2a$10$8.A/aV6C3.9KzH.Yx.b12.aJ.g4s/G5G5G5G5G5G5G5G5G5G5G5', 'ROLE_ADMIN');