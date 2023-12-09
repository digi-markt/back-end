--- Queries for creating the database tables:

1. Roles:

CREATE TABLE mysql_test.Roles (
ID INT PRIMARY KEY,
RoleName VARCHAR(255) UNIQUE
);

2. Users:

CREATE TABLE mysql_test.Users (
UserID INT AUTO_INCREMENT,
Username VARCHAR(255),
Password VARCHAR(255),
Email VARCHAR(255),
RoleID INT,
Permission VARCHAR(255),
Rating INT,
Create_at TIMESTAMP,
Modified_at TIMESTAMP,
PRIMARY KEY (UserID),
FOREIGN KEY (RoleID) REFERENCES Roles(ID)
);

3.


