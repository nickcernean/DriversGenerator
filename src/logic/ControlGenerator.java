package logic;

import interfaces.IControlGenerator;
import model.ControlSequence;
import tools.Generators;

public class ControlGenerator implements IControlGenerator {

    private final Generators<ControlSequence> generators;
    private final ControlSequence controlSequence;

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
