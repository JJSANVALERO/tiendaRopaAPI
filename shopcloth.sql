CREATE DATABASE shopcloth;
CREATE USER 'myshopuser'@'localhost' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON shopcloth.* TO myshopuser;