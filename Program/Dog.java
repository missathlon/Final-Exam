import java.time.LocalDate;

public class Dog extends DomesticAnimal{
        public Dog (String name, LocalDate birth, String type, String command){
        super(name, birth, "Dog", command);
    }
}
