package com.nextjob.base.util;

import java.util.HashMap;

@SuppressWarnings("serial")
public class CamelCaseMap extends HashMap<String, Object> {

    public CamelCaseMap() {
        super();
    }

    /**
     * Map의 put을 오버라이드해 key값을 camel 표기법으로 변환하여 put 한다.
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public Object put(String key, Object value) {
        return super.put(convertUnderscoreNameToPropertyName(key), value);
    }

    public String getString(String key) {
        return get(key) != null ? get(key).toString() : null;
    }

    public int getInt(String key) {
        return get(key) != null ? Integer.parseInt(get(key).toString()) : 0;
    }

    public long getLong(String key) {
        return get(key) != null ? Long.parseLong(get(key).toString()) : 0;
    }

    public float getFloat(String key) {
        return get(key) != null ? Float.parseFloat(get(key).toString()) : 0f;
    }

    public double getDouble(String key) {
        return get(key) != null ? Double.parseDouble(get(key).toString()) : 0d;
    }

    public static String convertUnderscoreNameToPropertyName(String name) {
        StringBuilder result = new StringBuilder();
        boolean nextIsUpper = false;
        if (name != null && name.length() > 0) {
            if (name.length() > 1 && name.substring(1,2).equals("_")) {
                result.append(name.substring(0, 1).toLowerCase());
            }
            else {
                result.append(name.substring(0, 1).toLowerCase());
            }
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals("_")) {
                    nextIsUpper = true;
                }
                else {
                    if (nextIsUpper) {
                        result.append(s.toUpperCase());
                        nextIsUpper = false;
                    }
                    else {
                        result.append(s.toLowerCase());
                    }
                }
            }
        }
        return result.toString();
    }
}
