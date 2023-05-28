package com.digitalhouse.clinicaodontologicag6.exception;

import java.util.List;

public class VariableNullException extends Exception {

    private final String message;
    private final List<String> variables;

    public VariableNullException(String message, List<String> variables) {
        this.message = message;
        this.variables = variables;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getListVariable() {
        return variables;
    }

}
