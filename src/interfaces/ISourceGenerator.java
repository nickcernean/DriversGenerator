package interfaces;

import model.ControlSequence;

public interface ISourceGenerator {
    Object[] generateSourceSequence();
    Object[][] generateMatrixSourceSequence();
}
