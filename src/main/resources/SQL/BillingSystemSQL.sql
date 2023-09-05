/*create compounders table*/
CREATE TABLE compounders
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255),
    designation VARCHAR(255)
);
/*create service table*/
CREATE TABLE services_info
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    serviceName VARCHAR(255),
    serviceCharge DOUBLE (255)
);
/*create department table*/
CREATE TABLE department
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    deptName     VARCHAR(255),
    noOfPatients BIGINT,
    service_id   BIGINT
)
/*create patients table*/
CREATE TABLE patients
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age  INT
)
/*create organization table*/

/*step by step condition match*/
select s.service_name from services_info s join department_services ds on s.id =ds.services_id where s.id = 1;
select s.service_name from services_info s join department_services ds on s.id =ds.services_id join department d on d.id = ds.department_id where d.id =1;
select s.service_name from services_info s join department_services ds on s.id =ds.services_id join department d on d.id = ds.department_id join organization_department od on d.id = od.department_id wher
e d.id =1;
select s.service_name from services_info s join department_services ds on s.id =ds.services_id join department d on d.id = ds.department_id join organization_department od on d.id = od.department_id JOIN organization o on od.organization_id = o.id where o.id =1;


