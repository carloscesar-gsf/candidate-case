package br.com.carloscesargsf.candidatecase.exceptions;

public class MethodIllegalArgumentException extends BaseException {

    private static final long serialVersionUID = -6578244431781746432L;

    public MethodIllegalArgumentException(String message) {
        super(message);
    }

    public MethodIllegalArgumentException(String message, Object... params) {
        super(message, params);
    }

}
