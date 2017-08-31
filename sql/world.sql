DROP TABLE mayor;
DROP TABLE city;
DROP TABLE ctry;
DROP TABLE bord;
DROP TABLE ocean;
DROP TABLE cont;
DROP TABLE state;
DROP TABLE lang;

DROP SEQUENCE mayor_seq1;
DROP SEQUENCE city_seq1;
DROP SEQUENCE ctry_seq1;
DROP SEQUENCE ocean_seq1;
DROP SEQUENCE cont_seq1;
DROP SEQUENCE state_seq1;
DROP SEQUENCE lang_seq1;

----------------------------------------------------------- continent

CREATE TABLE cont (
  cont_id         NUMBER,
  cont_name       VARCHAR2(50)  NOT NULL,
  CONSTRAINT cont_pk  PRIMARY KEY (cont_id),
  CONSTRAINT cont_uk1 UNIQUE (cont_name)
);

CREATE SEQUENCE cont_seq1;
CREATE OR REPLACE TRIGGER cont_trg1
  BEFORE INSERT ON cont FOR EACH ROW
BEGIN 
    IF :NEW.cont_id IS NULL THEN
      SELECT cont_seq1.NEXTVAL INTO :NEW.cont_id FROM DUAL;
    END IF;
END;
/

INSERT INTO cont (cont_name) VALUES('Africa');
INSERT INTO cont (cont_name) VALUES('Asia');
INSERT INTO cont (cont_name) VALUES('Europe');
INSERT INTO cont (cont_name) VALUES('North America');
INSERT INTO cont (cont_name) VALUES('South America');
INSERT INTO cont (cont_name) VALUES('Oceania');
INSERT INTO cont (cont_name) VALUES('Antarctica');

COMMIT;

----------------------------------------------------------- ocean

CREATE TABLE ocean (
  ocean_id        NUMBER,
  ocean_name      VARCHAR2(10)  NOT NULL,
  ocean_area      NUMBER        NOT NULL,
  CONSTRAINT ocean_pk  PRIMARY KEY (ocean_id),
  CONSTRAINT ocean_uk1 UNIQUE (ocean_name)
);

CREATE SEQUENCE ocean_seq1;
CREATE OR REPLACE TRIGGER ocean_trg1
  BEFORE INSERT ON ocean FOR EACH ROW
BEGIN 
    IF :NEW.ocean_id IS NULL THEN
      SELECT ocean_seq1.NEXTVAL INTO :NEW.ocean_id FROM DUAL;
    END IF;
END;
/

INSERT INTO ocean (ocean_name, ocean_area) VALUES('Artic', 5426000);
INSERT INTO ocean (ocean_name, ocean_area) VALUES('Atlantic', 29630000);
INSERT INTO ocean (ocean_name, ocean_area) VALUES('Indian', 26463000);
INSERT INTO ocean (ocean_name, ocean_area) VALUES('Pacific', 60045000);
INSERT INTO ocean (ocean_name, ocean_area) VALUES('Southern', 7846000);

COMMIT;

----------------------------------------------------------- border

CREATE TABLE bord (
  cont_id         NUMBER,
  ocean_id        NUMBER,
  CONSTRAINT bord_pk  PRIMARY KEY (cont_id, ocean_id),
  CONSTRAINT bord_fk1 FOREIGN KEY (cont_id) REFERENCES cont,
  CONSTRAINT bord_fk2 FOREIGN KEY (ocean_id) REFERENCES ocean
);

-- Indian
INSERT INTO bord (cont_id, ocean_id) VALUES(1, 3);
INSERT INTO bord (cont_id, ocean_id) VALUES(2, 3);
INSERT INTO bord (cont_id, ocean_id) VALUES(6, 3);

-- Pacific
INSERT INTO bord (cont_id, ocean_id) VALUES(2, 4);
INSERT INTO bord (cont_id, ocean_id) VALUES(4, 4);
INSERT INTO bord (cont_id, ocean_id) VALUES(5, 4);
INSERT INTO bord (cont_id, ocean_id) VALUES(6, 4);
INSERT INTO bord (cont_id, ocean_id) VALUES(7, 4);

--Atlantic
INSERT INTO bord (cont_id, ocean_id) VALUES(1, 2);
INSERT INTO bord (cont_id, ocean_id) VALUES(3, 2);
INSERT INTO bord (cont_id, ocean_id) VALUES(4, 2);
INSERT INTO bord (cont_id, ocean_id) VALUES(5, 2);

COMMIT;

----------------------------------------------------------- country

CREATE TABLE ctry (
  ctry_id         NUMBER,
  ctry_name       VARCHAR2(50)  NOT NULL,
  ctry_area       NUMBER        NOT NULL,
  ctry_pop        NUMBER        NOT NULL,
  cont_id         NUMBER        NOT NULL,
  CONSTRAINT ctry_pk  PRIMARY KEY (ctry_id),
  CONSTRAINT ctry_uk1 UNIQUE (ctry_name),
  CONSTRAINT ctry_fk1 FOREIGN KEY (cont_id) REFERENCES cont
  
);

CREATE SEQUENCE ctry_seq1;
CREATE OR REPLACE TRIGGER ctry_trg1
  BEFORE INSERT ON ctry FOR EACH ROW
BEGIN 
    IF :NEW.ctry_id IS NULL THEN
      SELECT ctry_seq1.NEXTVAL INTO :NEW.ctry_id FROM DUAL;
    END IF;
END;
/

INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Germany', 137847, 82046000, 3);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Ghana', 92098, 23837000, 1);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Australia', 2966200, 21884000, 6);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Greece', 50949, 11257285, 3);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Georgia', 26900, 4382100, 3);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('New Zealand', 104454, 4320300, 6);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Gambia', 4361, 1705000, 1);
INSERT INTO ctry (ctry_name, ctry_area, ctry_pop, cont_id)
  VALUES('Gabon', 103347, 1475000, 1);
  
COMMIT;

----------------------------------------------------------- city

CREATE TABLE city (
  city_id         NUMBER,
  city_name       VARCHAR2(50)  NOT NULL,
  ctry_id         NUMBER        NOT NULL,
  CONSTRAINT city_pk  PRIMARY KEY (city_id),
  CONSTRAINT city_fk1 FOREIGN KEY (ctry_id) REFERENCES ctry
);

CREATE SEQUENCE city_seq1;
CREATE OR REPLACE TRIGGER city_trg1
  BEFORE INSERT ON city FOR EACH ROW
BEGIN 
    IF :NEW.city_id IS NULL THEN
      SELECT city_seq1.NEXTVAL INTO :NEW.city_id FROM DUAL;
    END IF;
END;
/

INSERT INTO city (city_name, ctry_id)  VALUES('Berlin', 1);
INSERT INTO city (city_name, ctry_id)  VALUES('Hamburg', 1);
INSERT INTO city (city_name, ctry_id)  VALUES('Munich', 1);
INSERT INTO city (city_name, ctry_id)  VALUES('Accra', 2);
INSERT INTO city (city_name, ctry_id)  VALUES('Kumasi', 2);
INSERT INTO city (city_name, ctry_id)  VALUES('Tamale', 2);
INSERT INTO city (city_name, ctry_id)  VALUES('Aukland', 6);
INSERT INTO city (city_name, ctry_id)  VALUES('Christchurch', 6);
INSERT INTO city (city_name, ctry_id)  VALUES('Wellington', 6);

COMMIT;

----------------------------------------------------------- mayor

CREATE TABLE mayor (
  mayor_id         NUMBER,
  mayor_name       VARCHAR2(50)  NOT NULL,
  city_id          NUMBER        NOT NULL, 
  CONSTRAINT mayor_pk  PRIMARY KEY (mayor_id),
  CONSTRAINT mayor_uk1 UNIQUE (city_id),
  CONSTRAINT mayor_fk1 FOREIGN KEY (city_id) REFERENCES city
);

CREATE SEQUENCE mayor_seq1;
CREATE OR REPLACE TRIGGER mayor_trg1
  BEFORE INSERT ON mayor FOR EACH ROW
BEGIN 
    IF :NEW.mayor_id IS NULL THEN
      SELECT mayor_seq1.NEXTVAL INTO :NEW.mayor_id FROM DUAL;
    END IF;
END;
/

INSERT INTO mayor (mayor_name, city_id)  VALUES('Klaus Wowereit', 1);
INSERT INTO mayor (mayor_name, city_id)  VALUES('Dr. Alfred Oko Vanderpuije', 4);
INSERT INTO mayor (mayor_name, city_id)  VALUES('Nicholas C Geary', 8);

COMMIT;

----------------------------------------------------------- state

CREATE TABLE state (
  state_id         NUMBER,
  state_name       VARCHAR2(50)   NOT NULL,
  state_abbr       VARCHAR2(2)    NOT NULL,
  capital          VARCHAR2(50)   NOT NULL,
  capital_since    NUMBER         NOT NULL, 
  CONSTRAINT state_pk  PRIMARY KEY (state_id)
);

CREATE SEQUENCE state_seq1;
CREATE OR REPLACE TRIGGER state_trg1
  BEFORE INSERT ON state FOR EACH ROW
BEGIN 
    IF :NEW.state_id IS NULL THEN
      SELECT state_seq1.NEXTVAL INTO :NEW.state_id FROM DUAL;
    END IF;
END;
/

INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Alabama', 'AL', 'Montgomery', 1846);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Alaska', 'AK', 'Juneau', 1906);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Arizona', 'AZ', 'Phoenix', 1889);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Arkansas', 'AR', 'Little Rock', 1821);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('California', 'CA', 'Sacramento', 1854);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Colorado', 'CO', 'Denver', 1867);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Connecticut', 'CT', 'Hartford', 1875);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Delaware', 'DE', 'Dover', 1777);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Florida', 'FL', 'Tallahassee', 1824);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Georgia', 'GA', 'Atlanta', 1868);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Hawaii', 'HI', 'Honolulu', 1845);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Idaho', 'ID', 'Boise', 1865);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Illinois', 'IL', 'Springfield', 1837);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Indiana', 'IN', 'Indianapolis', 1825);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Iowa', 'IA', 'Des Moines', 1857);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Kansas', 'KS', 'Topeka', 1856);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Kentucky', 'KY', 'Frankfort', 1792);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Louisiana', 'LA', 'Baton Rouge', 1880);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Maine', 'ME', 'Augusta', 1832);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Maryland', 'MD', 'Annapolis', 1694);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Massachusetts', 'MA', 'Boston', 1630);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Michigan', 'MI', 'Lansing', 1847);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Minnesota', 'MN', 'Saint Paul', 1849);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Mississippi', 'MS', 'Jackson', 1821);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Missouri', 'MO', 'Jefferson City', 1826);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Montana', 'MT', 'Helena', 1875);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Nebraska', 'NE', 'Lincoln', 1867);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Nevada', 'NV', 'Carson City', 1861);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('New Hampshire', 'NH', 'Concord', 1808);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('New Jersey', 'NJ', 'Trenton', 1784);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('New Mexico', 'NM', 'Santa Fe', 1610);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('New York', 'NY', 'Albany', 1797);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('North Carolina', 'NC', 'Raleigh', 1792);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('North Dakota', 'ND', 'Bismarck', 1883);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Ohio', 'OH', 'Columbus', 1816);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Oklahoma', 'OK', 'Oklahoma City', 1910);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Oregon', 'OR', 'Salem', 1855);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Pennsylvania', 'PA', 'Harrisburg', 1812);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Rhode Island', 'RI', 'Providence', 1900);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('South Carolina', 'SC', 'Columbia', 1786);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('South Dakota', 'SD', 'Pierre', 1889);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Tennessee', 'TN', 'Nashville', 1826);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Texas', 'TX', 'Austin', 1839);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Utah', 'UT', 'Salt Lake City', 1858);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Vermont', 'VT', 'Montpelier', 1805);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Virginia', 'VA', 'Richmond', 1780);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Washington', 'WA', 'Olympia', 1853);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('West Virginia', 'WV', 'Charleston', 1885);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Wisconsin', 'WI', 'Madison', 1838);
INSERT INTO state(state_name, state_abbr, capital, capital_since) VALUES('Wyoming', 'WY', 'Cheyenne', 1869);

COMMIT;

----------------------------------------------------------- language

CREATE TABLE lang (
  lang_id        NUMBER,
  lang_name      VARCHAR2(20)  NOT NULL,
  percentage     NUMBER        NOT NULL,
  CONSTRAINT lang_pk  PRIMARY KEY (lang_id),
  CONSTRAINT lang_uk1 UNIQUE (lang_name)
);

CREATE SEQUENCE lang_seq1;
CREATE OR REPLACE TRIGGER lang_trg1
  BEFORE INSERT ON lang FOR EACH ROW
BEGIN 
    IF :NEW.lang_id IS NULL THEN
      SELECT lang_seq1.NEXTVAL INTO :NEW.lang_id FROM DUAL;
    END IF;
END;
/

INSERT INTO lang (lang_name, percentage) VALUES('Mandarin', 12.44);
INSERT INTO lang (lang_name, percentage) VALUES('Spanish',	4.85);
INSERT INTO lang (lang_name, percentage) VALUES('English',	4.83);
INSERT INTO lang (lang_name, percentage) VALUES('Arabic',	3.25);
INSERT INTO lang (lang_name, percentage) VALUES('Hindi',	2.68);
INSERT INTO lang (lang_name, percentage) VALUES('Bengali',	2.66);
INSERT INTO lang (lang_name, percentage) VALUES('Portuguese',	2.62);
INSERT INTO lang (lang_name, percentage) VALUES('Russian',	2.12);
INSERT INTO lang (lang_name, percentage) VALUES('Japanese',	1.80);
INSERT INTO lang (lang_name, percentage) VALUES('German',	1.33);
INSERT INTO lang (lang_name, percentage) VALUES('Javanese',	1.25);
INSERT INTO lang (lang_name, percentage) VALUES('Others',	61.17);

COMMIT;