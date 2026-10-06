select upper(doc_name) from Doctors;
select concat('patient',patient_name,'lives in',city),patient_name,city from Patients;
select patient_name from Patients where  city='gaza' and patient_name like 'A%';

select avg(salary) from  Doctors;
select dept_id ,count(doc_name) from Doctors group by dept_id;
select dept_id , max(salary) from Doctors group by dept_id having max(salary)>3000;

select Doctors.doc_name ,Departments.dept_name from Doctors Inner Join Departments on Doctors.dept_id=Departments.dept_id;
select Departments.dept_name ,Doctors.doc_name from Departments left join Doctors on Departments.dept_id=Doctors.dept_id;
select doc_name from Doctors union select patient_name from Patients; 
