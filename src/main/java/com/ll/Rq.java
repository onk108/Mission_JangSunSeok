package com.ll;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryString;
    List<String> paramNames;
    List<String> paramValues;

    Rq(String cmd) {
        this.cmd = cmd;
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        String[] cmdBits = cmd.split("\\?",2);
        action = cmdBits[0];

        if(cmdBits.length==1) {
            return;
        }

        queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        for(int i=0; i<queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }
    String getAction() {
        return action;
    }
    public int getParamAsInt(String paramName, int defaultValue) {
        int index = paramNames.indexOf(paramName);

        if(index==-1) return defaultValue;

        String paramValue = paramValues.get(index);

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
