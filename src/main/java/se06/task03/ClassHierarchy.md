Определена иерархия классов:
```
class MedicalStaff{}
class Doctor extends MedicalStaff{}
class Nurse extends MedicalStaff{}
class HeadDoctor extends Doctor{}
```
Укажите корректные и некорректные операторы. Дайте ответу пояснение.

 Expression                                             | Correct | Not correct | Note 
--------------------------------------------------------|---------|-------------|-------------------------------------------
Doctor doctor1 = new Doctor();                          | X       |             | Ссылке класса Doctor присваивается объект класса Doctor. 
Doctor doctor2 = new MedicalStaff();                    |         | X           | Ссылке класса-наследника присваивать объект класса-родителя нельзя. Нисходящее преобразование.           
Doctor doctor3 = new HeadDoctor();                      | X       |             | Ссылке класса-родителя присваивать объект класса-наследника можно. Восходящее преобразование.           
Object object1 = new HeadDoctor();                      | X       |             | HeadDoctor - наследник класса Object, поэтому то же что и выше. Восходящее преобразование.           
HeadDoctor doctor5 = new Object();                      |         | X           | Ссылке класса-наследника присваивать объект класса-родителя нельзя. Нисходящее преобразование.          
Doctor doctor6  = new Nurse();                          |         | X           | У Doctor и Nurse общий родитель - MedicalStaff.          
Nurse nurse = new Doctor();                             |         | X           | То же, что и выше.           
Object object2 = new Nurse();                           | X       |             | Nurse - наследница класса Object. Восходящее преобразование.
List\<Doctor> list1= new ArrayList\<Doctor>();          | X       |             | ArrayList реализует интерфейс List. Типы одинаковые.            
List\<MedicalStaff> list2 = new ArrayList\<Doctor>();   |         | X           | Связи наследования в Дженериках не учитываются. Типы должны быть одинаковыми.           
List\<Doctor> list3 = new ArrayList\<MedicalStaff>();   |         | X           | Даже если бы связи учитывались, это бы не прошло - Doctor наследник MedicalStaff           
List\<Object> list4 = new ArrayList\<Doctor>();         |         | X           | Связи наследования в Дженериках не учитываются. Типы должны быть одинаковыми.    
List\<Object> list5 = new ArrayList\<Object();          | X       |             | ArrayList реализует интерфейс List. Типы одинаковые.
