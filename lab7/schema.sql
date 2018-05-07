CREATE TABLE Users (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE generators (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    description text,
    rate int,
    base_cost int,
    unlock_at int,
    created_by int,
    PRIMARY KEY (id),
    FOREIGN KEY (created_by) REFERENCES Users(id)
);


CREATE TABLE events (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    description text,
    trigger_at int,
    created_by int,
    PRIMARY KEY (id),
    FOREIGN KEY (created_by) REFERENCES Users(id)
);
CREATE TABLE quantities (
    generator_id int NOT NULL,
    token varchar(255),
    quantity int DEFAULT '0',
    FOREIGN KEY (generator_id) REFERENCES generators(id)
);

INSERT INTO Users
VALUES (1, 'admin', 'cs3220password'), (2, 'me', 'notapassword');

INSERT INTO generators
VALUES (1, 'Grandma', 'Grandma likes to make cookies', 5, 10, 10, 1),
       (2, 'Factory', 'Factory to produce cookies', 10, 50, 50, 1),
       (3, 'Mine', 'Mining cookies', 20, 200, 200, 2);

INSERT INTO events
VALUES (1, 'Grandma shows up', 'You always know grandma likes to make cookies', 10, 1),
    (2, 'You can construct factory now!', 'Factory to produce cookies', 50, 1),
    (3, 'We''ve found cookies in deep mountain ... in the mine?', 'Mining cookies', 200, 2),
    (4, 'sample event', 'This is a sample event. Please delete me', 99999, 2);
    
INSERT INTO quantities
VALUES (1, 'c7a69d44e0b9b415b2d9956cb26b944a', 2),
    (2, 'c7a69d44e0b9b415b2d9956cb26b944a', 1),
    (1, '80516ce4663c3bd0c8385309a2fe226e', 20),
    (2, '80516ce4663c3bd0c8385309a2fe226e', 30);




