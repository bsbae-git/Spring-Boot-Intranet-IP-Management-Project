-- Passwords are "password" encoded with BCrypt
INSERT INTO "user" (username, password, role) VALUES
('admin', '$2a$10$jgE8nlwIOSy3MoN8CR/wwu3nDAvjm1a/TSj8dx8CqdeqOJH7KqAcC', 'ROLE_ADMIN'),
('user', '$2a$10$jgE8nlwIOSy3MoN8CR/wwu3nDAvjm1a/TSj8dx8CqdeqOJH7KqAcC', 'ROLE_USER');
