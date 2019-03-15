package com.opinous.utils;

import com.opinous.constants.LogErrorMessage;
import com.opinous.exception.NullParameterException;

public class PreCondition {

    public static void checkNotNull(Object obj, String message) throws NullParameterException {
        if(obj == null) {
            throw new NullParameterException(String.format(LogErrorMessage.NULL_VALUE, message));
        }
    }
}
