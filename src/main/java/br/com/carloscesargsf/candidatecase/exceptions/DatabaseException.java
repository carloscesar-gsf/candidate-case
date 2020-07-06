package br.com.carloscesargsf.candidatecase.exceptions;

public class DatabaseException extends BaseException {

    private static final long serialVersionUID = 5743348830245315569L;

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Object... params) {
        super(message, params);
    }

}
