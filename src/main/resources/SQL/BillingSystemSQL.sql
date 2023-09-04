/*create staff table*/
CREATE TABLE staffs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    designation VARCHAR(255)
);
/*create service table*/
CREATE TABLE staffs (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     serviceName VARCHAR(255),
     serviceCharge DOUBLE(255)
);
/*create department table*/
CREATE TABLE department(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    deptName VARCHAR(255),
    noOfPatients BIGINT,
    service_id BIGINT
)
