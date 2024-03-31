CREATE TABLE Players (
    PlayerId INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Age INT NOT NULL CHECK (Age BETWEEN 18 AND 50),
    Position ENUM('Forward', 'Midfielder', 'Defender', 'Goalkeeper') NOT NULL,
    Height INT NOT NULL CHECK (Height BETWEEN 100 AND 250),
    Weight INT NOT NULL CHECK (Weight BETWEEN 40 AND 200),
    Nationality VARCHAR(255) NOT NULL,
    Description TEXT,
    PicturePath VARCHAR(255)
	AddedBy INT,
	ADD FOREIGN KEY (AddedBy) REFERENCES Users(UserId);
);