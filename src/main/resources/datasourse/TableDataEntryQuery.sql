---table data entry queries:

INSERT INTO Roles
VALUES (1, 'ADMIN'), (2, 'SELLER'), (3, 'BUYER'), (4, 'VISITOR');



INSERT INTO Users (Username, Password, Email, RoleID, Permission, Rating, Create_at, Modified_at)
VALUES ('Hasan', '1234', 'example@gmail.com', 4, 'READ', 5, NOW(), NOW()),
       ('Bhavin', '1234', 'example@gmail.com', 2, 'READ', 5, NOW(), NOW()),
       ('Ritty', '1234', 'example@gmail.com', 3, 'READ', 5, NOW(), NOW());


