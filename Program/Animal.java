import java.time.LocalDate;
import java.util.ArrayList;

class Animal {
    private String name;
    private LocalDate birth;
    private String type;
    private ArrayList<String> commands;

    public Animal(String name, LocalDate birth, String type, String command){
        this.name = name;
        this.birth = birth;
        this.type = type;
        this.commands = new ArrayList<>();
        if (command != null) {
            this.commands.add(command);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }

    public void addCommands(String command) {
        this.commands.add(command);
    }

    @Override
    public String toString() {
        return "Animal [name=" + name + ", birth=" + birth + ", type=" + type + ", commands=" + commands + "]";
    }
    
}
