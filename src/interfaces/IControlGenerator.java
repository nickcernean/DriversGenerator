package interfaces;

import model.ControlSequence;

public interface IControlGenerator {
    Object[] generateControlSequence();
    Object[][] generateMatrixControlSequence();

}
