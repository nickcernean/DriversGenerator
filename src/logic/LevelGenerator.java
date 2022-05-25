package logic;

import interfaces.ISequencesGenerator;
import model.LevelSequence;
import tools.Generators;

public class LevelGenerator implements ISequencesGenerator {


    private final Generators<LevelSequence> generators;
    private final LevelSequence levelSequence;

    public LevelGenerator(LevelSequence levelSequence) {
        this.generators = new Generators<>();
        this.levelSequence = levelSequence;
    }

    @Override
    public Object[] generateSequence() {
        return generators.sequenceGenerator(levelSequence, levelSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixSequence() {
        return generators.matrixSequenceGenerator(levelSequence, levelSequence.getRows(), levelSequence.getColumns());
    }
}
