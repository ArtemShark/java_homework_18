CREATE TABLE Drinks (
    DrinkID SERIAL PRIMARY KEY,
    Name_EN VARCHAR(255) NOT NULL,
    Name_OtherLanguage VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL
);
CREATE TABLE Desserts (
    DessertID SERIAL PRIMARY KEY,
    Name_EN VARCHAR(255) NOT NULL,
    Name_OtherLanguage VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL
);
CREATE TABLE Staff (
    StaffID SERIAL PRIMARY KEY,
    FullName VARCHAR(255) NOT NULL,
    Phone VARCHAR(20),
    Email VARCHAR(255),
    Position VARCHAR(100) NOT NULL
        CHECK (Position IN ('Бариста', 'Официант', 'Кондитер'))
);
CREATE TABLE Clients (
    ClientID SERIAL PRIMARY KEY,
    FullName VARCHAR(255) NOT NULL,
    BirthDate DATE NOT NULL,
    Phone VARCHAR(20),
    Email VARCHAR(255),
    Discount INT
);
CREATE TABLE Schedule (
    ScheduleID SERIAL PRIMARY KEY,
    StaffID INT NOT NULL,
    WorkDate DATE NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);
CREATE TABLE Orders (
    OrderID SERIAL PRIMARY KEY,
    ClientID INT NOT NULL,
    StaffID INT NOT NULL,
    OrderDate DATE NOT NULL,
    TotalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);
CREATE TABLE OrderDetails (
    OrderDetailID SERIAL PRIMARY KEY,
    OrderID INT NOT NULL,
    ItemType VARCHAR(20) NOT NULL,
    ItemID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    CHECK (ItemType IN ('Drink', 'Dessert'))
);