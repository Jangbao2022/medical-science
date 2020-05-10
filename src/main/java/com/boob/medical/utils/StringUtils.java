package com.boob.medical.utils;

/**
 * 字符串工具
 */
public class StringUtils {

    /**
     * 去除标志位得到关键字
     *
     * @param keyWord
     * @param signals
     * @return
     */
    public static String getKeyWordByRemoveSignal(String keyWord, String... signals) {

        for (String signal : signals) {
            keyWord = keyWord.replace(signal, "");
        }
        return keyWord;
    }

}
