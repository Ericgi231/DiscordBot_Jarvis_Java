package me.ericgi231.helper;

public final class GlobalHelper {
    /**
     * Set string to lowercase and remove all [-_ ] characters
     * @param string String to clean
     * @return Cleaned string
     */
    public static String cleanString(String string) {
        return string.toLowerCase().replaceAll("[ _\\-]", "");
    }
}
