package logic;

import interfaces.ILevelGenerator;
import model.LevelSequence;
import model.SourceSequence;
import tools.Generators;

public class LevelGenerator implements ILevelGenerator {


    private final Generators<LevelSequence> generators;
    private final LevelSequence levelSequence;

    public LevelGenerator(LevelSequence levelSequence) {
        this.generators = new Generators<>();
        this.levelSequence = levelSequence;
    }

    @Override
    public Object[] generateLevelSequence() {
        return generators.sequenceGenerator(levelSequence, levelSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixLevelSequence() {
        return generators.matrixSequenceGenerator(levelSequence, levelSequence.getRows(), levelSequence.getColumns());
    }
}
