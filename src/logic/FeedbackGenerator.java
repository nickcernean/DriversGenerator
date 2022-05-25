package logic;

import interfaces.ISequencesGenerator;
import model.ControlSequence;
import model.FeedbackSequence;
import tools.Generators;

public class FeedbackGenerator implements ISequencesGenerator {



    private final Generators<FeedbackSequence> generators;
    private final FeedbackSequence feedbackSequence;

    public FeedbackGenerator(FeedbackSequence feedbackSequence) {
        this.generators = new Generators<>();
        this.feedbackSequence = feedbackSequence;
    }
    @Override
    public Object[] generateSequence() {
        return generators.sequenceGenerator(feedbackSequence, feedbackSequence.getRows());
    }

    @Override
    public Object[][] generateMatrixSequence() {
        return generators.matrixSequenceGenerator(feedbackSequence, feedbackSequence.getRows(), feedbackSequence.getColumns());
    }
}
