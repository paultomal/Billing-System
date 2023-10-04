create
database billing_system;

use
billing_system;

CREATE TABLE organization
(
    id                BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    org_code          VARCHAR(255) NOT NULL UNIQUE,
    address           VARCHAR(255) NOT NULL,
    contact           VARCHAR(255),
    type              ENUM ('HOSPITAL','ROOT', 'CHAMBER', 'DIAGNOSTIC_CENTER','PHARMACY'), -- Replace with actual enum values
    email             VARCHAR(255) NOT NULL UNIQUE,
    emergency_contact VARCHAR(255),
    operating_hour    VARCHAR(255)
);
create table user_info
(
    id       bigint not null auto_increment primary key,
    contact  varchar(255),
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    roles    enum ('ROLE_ADMIN','ROLE_ORG_ADMIN','ROLE_ROOT'),
    username varchar(255),
    org_id   bigint,
    CONSTRAINT fk_org FOREIGN KEY (org_id) REFERENCES organization (id)
);
ALTER TABLE user_info
    ADD COLUMN org_id BIGINT;
ALTER TABLE user_info
    ADD CONSTRAINT fk_user_org FOREIGN KEY (org_id) REFERENCES organization (id);

-- Create the Speciality table
CREATE TABLE speciality
(
    id            BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    med_spec_name VARCHAR(255),
    icon_url      VARCHAR(255),
    description   VARCHAR(255)
);



CREATE TABLE base_entity
(
    id             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    creationTime   DATETIME,
    lastUpdateTime DATETIME
);

CREATE TABLE doctor
(
    id               BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255),
    degrees          VARCHAR(255),
    contact          VARCHAR(255),
    email            VARCHAR(255),
    followUp         VARCHAR(255),
    consultation_fee VARCHAR(255),
    min_discount     VARCHAR(255),
    max_discount     VARCHAR(255),
    day              ENUM ('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'),
    time             VARCHAR(255),
    org_id           BIGINT,
    sp_id            BIGINT,
    CONSTRAINT fk_org_doc FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_doc FOREIGN KEY (sp_id) REFERENCES speciality (id)
);

CREATE TABLE investigation
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    serviceName VARCHAR(255) NOT NULL,
    serviceCharge DOUBLE,
    org_id      BIGINT,
    sp_id       BIGINT,
    CONSTRAINT fk_org_inv FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_inv FOREIGN KEY (sp_id) REFERENCES speciality (id)
);

/*create patient table*/
CREATE TABLE patient
(
    id      BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    contact VARCHAR(255),
    since   DATE,
    org_id  BIGINT,
    sp_id   BIGINT,
    CONSTRAINT fk_org_pat FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_pat FOREIGN KEY (sp_id) REFERENCES speciality (id)
);

-- Create the AppointmentBooking table
CREATE TABLE appointment_booking
(
    id               BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    org_id           BIGINT NOT NULL,
    patient_id       BIGINT NOT NULL,
    doctor_id        BIGINT NOT NULL,
    consultation_fee VARCHAR(255),
    discount         VARCHAR(255),
    slot             VARCHAR(255),
    total_fees DOUBLE NOT NULL,
    CONSTRAINT fk_org_app_booking FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_patient_app_booking FOREIGN KEY (patient_id) REFERENCES patient (id),
    CONSTRAINT fk_doctor_app_booking FOREIGN KEY (doctor_id) REFERENCES doctor (id)
);

-- Create the DoctorSlot table
CREATE TABLE doctor_slot
(
    id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    day  ENUM ('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'),
    time VARCHAR(255)
);

-- Create the Generic table
CREATE TABLE generic
(
    id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
-- Create the DrugVendor table
CREATE TABLE drug_vendor
(
    id   BIGINT              NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

-- Create the DrugStrength table
CREATE TABLE drug_strength
(
    id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- Create the DrugFormation table
CREATE TABLE drug_formation
(
    id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- Create the Drug table
CREATE TABLE drug
(
    id           BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brandName    VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    vendor_id    BIGINT       NOT NULL,
    generic_id   BIGINT       NOT NULL,
    formation_id BIGINT       NOT NULL,
    strength_id  BIGINT       NOT NULL,
    CONSTRAINT fk_vendor FOREIGN KEY (vendor_id) REFERENCES drug_vendor (id),
    CONSTRAINT fk_generic FOREIGN KEY (generic_id) REFERENCES generic (id),
    CONSTRAINT fk_formation FOREIGN KEY (formation_id) REFERENCES drug_formation (id),
    CONSTRAINT fk_strength FOREIGN KEY (strength_id) REFERENCES drug_strength (id)
);

-- Create the DrugOrder table
CREATE TABLE drug_order
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    org_id     BIGINT NOT NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient (id),
    CONSTRAINT fk_orgid FOREIGN KEY (org_id) REFERENCES organization (id)
);

-- Create the order_drug table (for the ManyToMany relationship)
CREATE TABLE order_drug
(
    order_id BIGINT NOT NULL,
    drug_id  BIGINT NOT NULL,
    CONSTRAINT pk_order_drug PRIMARY KEY (order_id, drug_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES drug_order (id),
    CONSTRAINT fk_drug FOREIGN KEY (drug_id) REFERENCES drug (id)
);

-- Create the InvestigationBooking table
CREATE TABLE investigation_booking
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    org_id     BIGINT,
    patient_id BIGINT,
    total DOUBLE,
    CONSTRAINT fk_org_inv_booking FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_patient_inv_booking FOREIGN KEY (patient_id) REFERENCES patient (id)
);

-- Create the book_investigation table (for the ManyToMany relationship)
CREATE TABLE book_investigation
(
    book_id         BIGINT NOT NULL,
    invastigaion_id BIGINT NOT NULL,
    CONSTRAINT pk_book_investigation PRIMARY KEY (book_id, invastigaion_id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES investigation_booking (id),
    CONSTRAINT fk_investigation FOREIGN KEY (invastigaion_id) REFERENCES investigation (id)
);

-- Create the OrgDrugPriceQuantity table
CREATE TABLE org_drug_price_quantity
(
    id       BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    org_id   BIGINT,
    drug_id  BIGINT,
    price DOUBLE,
    quantity BIGINT,
    CONSTRAINT fk_org_price_qty FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_drug_price_qty FOREIGN KEY (drug_id) REFERENCES drug (id)
);

-- Create the OrgInvestigationPrice table
CREATE TABLE org_investigation_price
(
    id               BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    serviceCharge DOUBLE,
    org_id           BIGINT,
    investigation_id BIGINT,
    CONSTRAINT fk_org_inv_price FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_investigation_inv_price FOREIGN KEY (investigation_id) REFERENCES investigation (id)
);









