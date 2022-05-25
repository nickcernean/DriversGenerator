package logic;

import interfaces.ISequencesGenerator;
import model.ControlSequence;
import tools.Generators;

public class ControlGenerator implements ISequencesGenerator {

    private final Generators<ControlSequence> generators;
    private final ControlSequence controlSequence;

    public ControlGenerator(ControlSequence controlSequence) {
        this.generators = new Generators<>();
        this.controlSequence = controlSequence;
    }

    @Override
    public Object[] generateSequence() {
        return generators.sequenceGenerator(controlSequence, controlSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixSequence() {
        return generators.matrixSequenceGenerator(controlSequence, controlSequence.getRows(), controlSequence.getColumns());
    }
}
