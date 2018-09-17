CREATE TABLE payer (
    id IDENTITY ,
    email VARCHAR(255) NOT NULL
    );

CREATE TABLE amount (
    id IDENTITY ,
    val DOUBLE NOT NULL ,
    currency VARCHAR (255) NOT NULL
);

CREATE TABLE transacts (
    id IDENTITY ,
    external_id VARCHAR (255) ,
    description VARCHAR (255) ,
    amount_id INT NOT NULL ,
    FOREIGN KEY (amount_id) REFERENCES amount(id)
    );

CREATE TABLE payment (
    id IDENTITY ,
    intent ENUM('order') ,
    notification_url VARCHAR (255) ,
    payer_id INT NOT NULL ,
    FOREIGN KEY (payer_id) references payer(id) ,
    transaction_id INT NOT NULL ,
    FOREIGN KEY (transaction_id) references transacts(id)
);