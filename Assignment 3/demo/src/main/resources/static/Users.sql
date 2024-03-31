CREATE TABLE Users (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL UNIQUE,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    PhoneNumber VARCHAR(20),
    FullName VARCHAR(255),
    Address TEXT,
    ProfilePicPath VARCHAR(255),
    TwoFactorAuth BOOLEAN NOT NULL DEFAULT FALSE,
    DateOfBirth DATE,
    PreferredLanguage ENUM('English', 'Spanish', 'French', 'German', 'Chinese')
);