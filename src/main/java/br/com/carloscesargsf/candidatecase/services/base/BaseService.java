package br.com.carloscesargsf.candidatecase.services.base;

import br.com.carloscesargsf.candidatecase.properties.PreferencesDefaultProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.carloscesargsf.candidatecase.constants.LoggerConstants.LOGGER_MESSAGE;
import static br.com.carloscesargsf.candidatecase.constants.LoggerConstants.LOGGER_PARAM_SEPARATOR;

public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected PreferencesDefaultProperties preferencesDefaultProperties;

    protected void logCurrentMethodExecution(Object... params) {
        if (logger.isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format(LOGGER_MESSAGE, getCurrentMethodName(), getClass()));
            for (Object param : params) {
                stringBuilder.append(LOGGER_PARAM_SEPARATOR);
                stringBuilder.append(param);
            }
            logger.debug(stringBuilder.toString());
        }
    }

    private String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

}
