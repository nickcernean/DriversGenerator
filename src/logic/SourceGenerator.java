package logic;

import interfaces.ISourceGenerator;
import model.ControlSequence;
import model.SourceSequence;
import tools.Generators;

public class SourceGenerator implements ISourceGenerator {

    private final Generators<SourceSequence> generators;
    private final SourceSequence sourceSequence;

    public SourceGenerator(SourceSequence sourceSequence) {
        this.generators = new Generators<>();
        this.sourceSequence = sourceSequence;
    }


    @Override
    public Object[] generateSourceSequence() {
        return generators.sequenceGenerator(sourceSequence, sourceSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixSourceSequence() {
        return generators.matrixSequenceGenerator(sourceSequence, sourceSequence.getRows(), sourceSequence.getColumns());
    }
}
