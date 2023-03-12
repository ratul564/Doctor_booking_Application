# 1. Framework and Language used
* I have created Spring Boot project with the help of Java language<br>
# 2. Functions used
* Functions used in Controller for Doctor model-<br>
  <br>
  saveDoctor( setDoctor, validateDoctor ), getDoctor, updateDoctor, deleteDoctor<br>
* Functions used in Controller for Patient model-<br>
  <br>
  savePatient( setPatient ), getPatients, updateDoctor, deletePatient<br>
  <br>
* Functions used in Service for Doctor model- saveDoctor, getDoctor, getDoctorById, updateDoctor, deleteDoctor<br>
  <br>
* Functions used in Service for Patient model-<br>
  <br>
  savePatient, getPatients, updatePatient, deletePatient<br>
  <br>
* In Dao layer I made interface Doctor model as DoctorRepository and extends it to JpaRepository,
  and for Patient model as PatientRepository and extends it to JpaRepository.
# 3. Data structure
* I have used H2 database in this project
# 4. project summery<br>
I created one application name as Doctor Application. Mention dependencies in below <br>
1. Spring Web,<br> 2. Spring Dev tools,<br>3. Lombok,<br>4. H2 database,<br>5. Json<br>