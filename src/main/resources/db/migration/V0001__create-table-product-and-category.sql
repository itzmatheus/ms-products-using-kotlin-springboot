CREATE TABLE product (
  id varchar NOT NULL,
  price int,
  name varchar(100) NOT NULL,
  fk_categories_id varchar,
  created_at TIMESTAMP,
  PRIMARY KEY (id)
) ;

CREATE TABLE category (
  id varchar NOT NULL,
  name varchar(100) NOT NULL,
  created_at TIMESTAMP,
  PRIMARY KEY (id)
) ;
ALTER TABLE product ADD FOREIGN KEY ( fk_categories_id ) REFERENCES category( id ) ;