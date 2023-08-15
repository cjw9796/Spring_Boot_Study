CREATE TABLE rentcar(
	no INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    category INT,
    price INT,
    use_people INT,
    company VARCHAR(50),
    img VARCHAR(50),
    info VARCHAR(500),
    PRIMARY KEY (no)
);