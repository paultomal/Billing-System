-- Create the Organization table
CREATE TABLE billing_system.organization
(
    id                bigint not null auto_increment primary key,
    name              varchar(255),
    org_code          varchar(255) unique,
    address           varchar(255),
    contact           varchar(255),
    type              enum ('HOSPITAL','PHARMACY','DIAGNOSTIC_CENTER','CHAMBER'),
    email             varchar(255),
    emergency_contact varchar(255),
    operating_hour    varchar(255)
);

-- Create the Specialist table
CREATE TABLE speciality
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    med_spec_name VARCHAR(255) NOT NULL
);


-- Create the UserInfo table
create table billing_system.user_info
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

-- Create the BaseEntity table
CREATE TABLE billing_system.base_entity
(
    id             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    creationTime   DATETIME,
    lastUpdateTime DATETIME
);

-- Create the Doctors table
CREATE TABLE billing_system.doctors
(
    id              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255),
    degrees         VARCHAR(255),
    contact         VARCHAR(255),
    email           VARCHAR(255),
    followUp        VARCHAR(255),
    consultationFee VARCHAR(255),
    minDiscount     VARCHAR(255),
    maxDiscount     VARCHAR(255),
    day             ENUM ('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'),
    time            VARCHAR(255),
    org_id          BIGINT,
    sp_id           BIGINT,
    creationTime    DATETIME,
    lastUpdateTime  DATETIME,
    CONSTRAINT fk_org_doc FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_doc FOREIGN KEY (sp_id) REFERENCES speciality (id)
);

-- Create the Investigation table
CREATE TABLE billing_system.investigation
(
    id             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    serviceName    VARCHAR(255) NOT NULL,
    serviceCharge DOUBLE,
    org_id         BIGINT,
    sp_id          BIGINT,
    CONSTRAINT fk_org_inv FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_inv FOREIGN KEY (sp_id) REFERENCES speciality (id)
);

-- Create the Patients table
CREATE TABLE billing_system.patients
(
    id             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    contact        VARCHAR(255),
    since          DATE,
    org_id         BIGINT,
    sp_id          BIGINT,
    creationTime   DATETIME,
    lastUpdateTime DATETIME,
    CONSTRAINT fk_org_pat FOREIGN KEY (org_id) REFERENCES organization (id),
    CONSTRAINT fk_sp_pat FOREIGN KEY (sp_id) REFERENCES speciality (id)
);


/*step by step condition match*/
select s.service_name
from services_info s
         join department_services ds on s.id = ds.services_id
where s.id = 1;
select s.service_name
from services_info s
         join department_services ds on s.id = ds.services_id
         join department d on d.id = ds.department_id
where d.id = 1;
select s.service_name
from services_info s
         join department_services ds on s.id = ds.services_id
         join department d on d.id = ds.department_id
         join organization_department od on d.id = od.department_id wher
e d.id =1;
select s.service_name
from services_info s
         join department_services ds on s.id = ds.services_id
         join department d on d.id = ds.department_id
         join organization_department od on d.id = od.department_id
         JOIN organization o on od.organization_id = o.id
where o.id = 1;


