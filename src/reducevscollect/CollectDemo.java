package reducevscollect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CollectDemo {



    @Getter
    @Setter
    @AllArgsConstructor
     class Employee {
        private String name;
        private String department;
        private double salary;
    }

}
