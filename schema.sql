create table trade(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	unique_hash VARCHAR(256) NOT NULL UNIQUE,
	contract_address VARCHAR(256),
	owner_address VARCHAR(256),
	advance_amount VARCHAR(256) NOT NULL,
	realization_amount VARCHAR(256) NOT NULL,
	advance_date DATE,
	realization_date DATE,
	is_advance_paid BIT NOT NULL,
	is_realization_paid BIT NOT NULL
);