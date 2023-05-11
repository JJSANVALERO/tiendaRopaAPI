CREATE DATABASE shopcloth;
CREATE USER myshopuser IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON shopcloth.* TO myshopuser;