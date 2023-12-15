---table data entry queries:

INSERT INTO Roles
VALUES (1, 'ADMIN'), (2, 'SELLER'), (3, 'BUYER'), (4, 'VISITOR');



INSERT INTO Users (Username, Password, Email, RoleID, Permission, Rating, Create_at, Modified_at)
VALUES ('Hasan', '1234', 'example@gmail.com', 4, 'READ', 5, NOW(), NOW()),
       ('Bhavin', '1234', 'example@gmail.com', 2, 'READ', 5, NOW(), NOW()),
       ('Ritty', '1234', 'example@gmail.com', 3, 'READ', 5, NOW(), NOW());


 INSERT INTO Category VALUES
 (1, 'Category1');

 INSERT INTO Status VALUES
 (1, 'Status1');

INSERT INTO Media (Name, Description, Price, Flag, SellerID, ApprovalAdminID, ApprovalAdminName, MediaCategoryID, Created_on, Modified_on, StatusID)
VALUES
('Media1', 'Description1', 19.99, true, 1, 2, 'Admin1', 1, NOW(), NOW(), 1),



