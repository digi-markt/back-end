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

3.	Category (Static Table)
CREATE TABLE Category (
ID INT PRIMARY KEY,
Name VARCHAR(255)
);

4.Status Table (Static Table)

CREATE TABLE Status (
ID INT PRIMARY KEY,
Status VARCHAR(255)
);

5.Media Entity
CREATE TABLE Media (
MediaID INT AUTO_INCREMENT,
Name VARCHAR(255),
Description TEXT,
Price DECIMAL(10, 2),
Flag Boolean,
Image BLOB,
SellerID INT,
ApprovalAdminID INT,
ApprovalAdminName VARCHAR(255),
MediaCategoryID INT,
Created_on TIMESTAMP,
Modified_on TIMESTAMP,
StatusID INT,
PRIMARY KEY (MediaID),
FOREIGN KEY (MediaCategoryID) REFERENCES Category(ID),
FOREIGN KEY (StatusID) REFERENCES Status(ID)
);


