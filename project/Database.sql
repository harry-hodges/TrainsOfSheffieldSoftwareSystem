CREATE TABLE Products (
  productCode varchar(100) NOT NULL,
  name varchar(100) DEFAULT NULL,
  brandName varchar(45) DEFAULT NULL,
  price decimal(8,2) DEFAULT NULL,
  quantity int DEFAULT NULL,
  gaugeScale enum('OO_GAUGE','TT_GAUGE','N_GAUGE') DEFAULT NULL,
  PRIMARY KEY (productCode)
);

CREATE TABLE OrderLines (
  orderLineNumber varchar(50) NOT NULL,
  productCode varchar(100) NOT NULL,
  orderID varchar(50) NOT NULL,
  productQuantity int DEFAULT NULL,
  orderLineCost decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (orderLineNumber),
  KEY productCode (productCode),
  KEY OrderLines_ibfk_1 (orderID),
  CONSTRAINT OrderLines_ibfk_1 FOREIGN KEY (orderID) REFERENCES Orders (orderID),
  CONSTRAINT OrderLines_ibfk_2 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE Orders (
  orderID varchar(50) NOT NULL,
  userID varchar(100) DEFAULT NULL,
  totalCost decimal(8,2) DEFAULT NULL,
  status enum('PENDING','CONFIRMED','FULFILLED','DECLINED') NOT NULL,
  date date DEFAULT NULL,
  PRIMARY KEY (orderID),
  KEY Orders_ib1fk_1_idx (userID),
  CONSTRAINT Orders_ib1fk_1 FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE Users (
  userID varchar(100) NOT NULL,
  forename varchar(100) DEFAULT NULL,
  surname varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  bankCardName varchar(100) DEFAULT NULL,
  cardHolderName varchar(100) DEFAULT NULL,
  expiryDate varchar(100) DEFAULT NULL,
  securityCode int DEFAULT NULL,
  accountLocked tinyint(1) DEFAULT NULL,
  postcode varchar(50) DEFAULT NULL,
  houseNumber varchar(50) DEFAULT NULL,
  PRIMARY KEY (userID),
  KEY postcode (postcode,houseNumber),
  CONSTRAINT Users_ibfk_1 FOREIGN KEY (postcode, houseNumber) REFERENCES Addresses (postcode, houseNumber),
  CONSTRAINT Users_ibfk_2 FOREIGN KEY (postcode, houseNumber) REFERENCES Addresses (postcode, houseNumber)
);

INSERT INTO Users (userID, forename, surname, email, password, bankCardName, cardHolderName, expiryDate, securityCode, accountLocked, postcode, houseNumber)
VALUES
    ('77ab0caf-2bfa-4da5-aa88-cf98e1459f2c', 'manager', 'manager', 'manager@manager.com', '5e762797813e48b30288cb2d78904cb0a78b47fe13e118fb3f139855c2781951', NULL, NULL, NULL, NULL, NULL, 'S12 L3', '4')  -- User: manager


CREATE TABLE Roles (
  userID varchar(50) NOT NULL,
  role enum('MANAGER','STAFF','USER') NOT NULL,
  PRIMARY KEY (userID,role),
  CONSTRAINT Roles_ibfk_1 FOREIGN KEY (userID) REFERENCES Users (userID)
);

INSERT INTO Roles (userID, role)
VALUES
    ('77ab0caf-2bfa-4da5-aa88-cf98e1459f2c', 'MANAGER')



CREATE TABLE Addresses (
  postcode varchar(100) NOT NULL,
  houseNumber varchar(50) NOT NULL,
  roadName varchar(100) DEFAULT NULL,
  cityName varchar(100) DEFAULT NULL,
  PRIMARY KEY (postcode,houseNumber)
);

INSERT INTO Addresses (postcode, houseNumber, roadName, cityName)
VALUES
    ('S12 L3', '4', 'Wilkinson Lane', 'Sheffield')

CREATE TABLE Locomotives (
  productCode varchar(100) NOT NULL,
  era varchar(50) DEFAULT NULL,
  dcc enum('ANALOGUE','DCC_READY','DCC_FITTED','DCC_SOUND') DEFAULT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT Locomotives_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE RollingStocks (
  productCode varchar(100) NOT NULL,
  era varchar(50) DEFAULT NULL,
  rollingStockType enum('CARRIAGE','WAGON') DEFAULT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT RollingStocks_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE Controllers (
  productCode varchar(100) NOT NULL,
  dcc enum('ANALOGUE','DIGITAL') DEFAULT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT Controllers_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE TrainSets (
  productCode varchar(100) NOT NULL,
  era varchar(50) DEFAULT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT TrainSets_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE TrackPacks (
  productCode varchar(100) NOT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT TrackPacks_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);

CREATE TABLE Tracks (
  productCode varchar(100) NOT NULL,
  PRIMARY KEY (productCode),
  CONSTRAINT Tracks_ibfk_1 FOREIGN KEY (productCode) REFERENCES Products (productCode)
);