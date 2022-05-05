package logic;

import interfaces.IControlGenerator;
import model.ControlSequence;
import tools.Generator;

public class ControlGenerator implements IControlGenerator {

    private Generator<ControlSequence> generator;
    private ControlSequence controlSequence;

    public ControlGenerator(ControlSequence controlSequence) {
        this.generator = new Generator<>();
        this.controlSequence=controlSequence;
    }

    @Override
    public Object[] generateControlSequence() {
        return null;//generator.sequenceGenerator(controlSequence.sequenceCaptionGenerator(),rows);

    }

    @Override
    public String generateMatrixControlSequence(ControlSequence controlSequence) {
        return null;
    }
}
