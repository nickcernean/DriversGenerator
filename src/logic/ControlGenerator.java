package logic;

import interfaces.IControlGenerator;
import model.ControlSequence;
import model.Sequence;
import tools.Generators;

public class ControlGenerator implements IControlGenerator {

    private Generators<ControlSequence> generators;
    private ControlSequence controlSequence;

    public ControlGenerator(ControlSequence controlSequence) {
        this.generators = new Generators<>();
        this.controlSequence = controlSequence;
    }

    @Override
    public Object[] generateControlSequence() {
        return generators.sequenceGenerator(controlSequence, controlSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixControlSequence() {
        return generators.matrixSequenceGenerator(controlSequence, controlSequence.getRows(), controlSequence.getColumns());
    }
}
