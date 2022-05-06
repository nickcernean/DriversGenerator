package interfaces;

import model.ControlSequence;

public interface ISourceGenerator {
    Object[] generateControlSequence();
    Object[][] generateMatrixControlSequence();
}
