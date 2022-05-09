# create databases
CREATE DATABASE IF NOT EXISTS `bankdb`;
CREATE DATABASE IF NOT EXISTS `loggerdb`;

# create root user and grant rights
CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
GRANT ALL ON *.* TO 'root'@'%';