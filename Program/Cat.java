import java.time.LocalDate;

public class Cat extends DomesticAnimal {
    public Cat (String name, LocalDate birth, String type, String command){
        super(name, birth, "Cat", command);
    }
}
