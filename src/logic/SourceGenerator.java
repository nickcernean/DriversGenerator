package logic;

import interfaces.ISequencesGenerator;
import model.SourceSequence;
import tools.Generators;

public class SourceGenerator implements ISequencesGenerator {

    private final Generators<SourceSequence> generators;
    private final SourceSequence sourceSequence;

    public SourceGenerator(SourceSequence sourceSequence) {
        this.generators = new Generators<>();
        this.sourceSequence = sourceSequence;
    }


    @Override
    public Object[] generateSequence() {
        return generators.sequenceGenerator(sourceSequence, sourceSequence.getRows(),sourceSequence.getColumns());
    }

    @Override
    public Object[][] generateMatrixSequence() {
        return generators.matrixSequenceGenerator(sourceSequence, sourceSequence.getRows(), sourceSequence.getColumns());
    }
}
