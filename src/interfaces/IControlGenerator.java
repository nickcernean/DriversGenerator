package interfaces;

import model.ControlSequence;

public interface IControlGenerator {
    Object[] generateControlSequence();
    String generateMatrixControlSequence(ControlSequence controlSequence);

}
