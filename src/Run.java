import model.ControlSequence;
import tools.Generator;

public class Run {


    public static void main(String[] args) {

        String smth = Generator.sequenceNameGenerator();
        System.out.println(smth + "\n " + smth.length());
        ControlSequence controlSequence = new ControlSequence(12, 0, "Turn smth on", null, true, true);
        System.out.println(controlSequence.sequenceGenerator());
    }

}
