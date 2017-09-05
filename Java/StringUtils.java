public class StringUtils {

    /**
     * 字符串中是否包含emoji表情
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        if (source == null || "".equals(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                // 判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    /**
     * 是否是emoji字符
     * @param codePoint
     * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 过滤emoji表情
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        // 如果不包含，直接返回
        if (!containsEmoji(source)) {
            return source;
        }

        StringBuilder buf = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            }
        }

        return buf.toString().trim();
    }
}
