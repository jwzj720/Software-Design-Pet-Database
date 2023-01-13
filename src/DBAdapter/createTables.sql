CREATE TABLE Energy (
    energylevel VARCHAR(128) PRIMARY KEY,
);
INSERT INTO Energy (energyLevel) VALUES ("high");
INSERT INTO Energy (energyLevel) VALUES ("medium");
INSERT INTO Energy (energyLevel) VALUES ("low");

CREATE TABLE IdealBreed (
    IdealBreed_pk SERIAL PRIMARY KEY,
    breedOne      VARCHAR(128),
    breedTwo      VARCHAR(128),
    breedThree    VARCHAR(128), 
)

CREATE TABLE Family (
    family_pk       SERIAL PRIMARY KEY,
    adoptedPet_fk   INTEGER REFERENCES Pet(pet_pk),
    name            VARCHAR(128),
    idealSpecies    VARCHAR(128),
    allergies       BOOLEAN,
    familySize      INTEGER,
    existingPets    BOOLEAN,
    marritalStatus  BOOLEAN,
    willFoster      BOOLEAN,
    energyLevel     VARCHAR(128) REFERENCES Energy(energyLevel),
    breed           INTEGER REFERENCES IdealBreed(IdealBreed_pk),
    idealAge        INTEGER,
    hasKids         BOOLEAN,
    photo           VARCHAR(255),
);


CREATE TABLE HealthConditions(
    healthConditions_pk    SERIAL PRIMARY KEY,
    nameOne                VARCHAR(128),
    nameTwo                VARCHAR(128),
    nameThree              VARCHAR(128),
    nameFour               VARCHAR(128),
    nameFive               VARCHAR(128),
    nameSix                VARCHAR(128),
    nameSeven              VARCHAR(128),
    nameEight              VARCHAR(128),
    nameNine               VARCHAR(128),

);

CREATE TABLE Certifications(
    certifications_pk    SERIAL PRIMARY KEY,
    nameOne              VARCHAR(128),
    nameTwo              VARCHAR(128),
    nameThree            VARCHAR(128),
    nameFour             VARCHAR(128),
    nameFive             VARCHAR(128),
    nameSix              VARCHAR(128),
    nameSeven            VARCHAR(128),
    nameEight            VARCHAR(128),
    nameNine             VARCHAR(128),
);


CREATE TABLE sexes (
	sex char(1) PRIMARY KEY
);
INSERT INTO sexes (sex) VALUES ('M');
INSERT INTO sexes (sex) VALUES ('F');

CREATE TABLE sizes (
    size VARCHAR(128) PRIMARY KEY
);
INSERT INTO sizes (size) VALUES ("big");
INSERT INTO sizes (size) VALUES ("medium");
INSERT INTO sizes (size) VALUES ("small");

CREATE TABLE Pet (
    pet_pk               SERIAL PRIMARY KEY,
    previousFoster       ??,
    name                 VARCHAR(128),
    species              VARCHAR(128),
    breed                VARCHAR(128),
    sex                  CHAR(1) REFERENCES sexes(sex),
    size                 INTEGER REFERENCES sizes(size),
    color                VARCHAR(128),
    energyLevel          VARCHAR(128) REFERENCES Energy(energyLevel),
    notes                VARCHAR(255),
    listHealthConditions INTEGER REFERENCES HealthConditions(healthConditions_pk),
    listCertifications   INTEGER REFERENCES Certifications(certifications_pk),
    fosteredBefore       BOOLEAN,
    pottyTrained         BOOLEAN,
    goodWithKids         BOOLEAN,
    goodWithPets         BOOLEAN,
    age                  INTEGER, 
    photo                VARCHAR(255),
);


CREATE TABLE Placements (
    pet_fk                 INTEGER REFERENCES Pet(pet_pk),
    family_fk              INTEGER REFERENCES Family(family_pk),
    foster_start_date      DATE --format YYYY-MM-DD
    foster_end_date        DATE
    adopt_start_date       DATE
    adopt_end_date         DATE
);