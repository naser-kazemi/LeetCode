public class ATOI {

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        boolean isNegative = s.charAt(0) == '-';
        int start = s.charAt(0) == '-' || s.charAt(0) == '+' ? 1 : 0;
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(\\d+)");
        java.util.regex.Matcher matcher = pattern.matcher(s.substring(start));
        int result = 0;
        String numberOnly = "";
        if (matcher.find())
            numberOnly = matcher.group(1);
        try {
            result = Integer.parseInt(numberOnly);
        } catch (NumberFormatException e) {
            if (numberOnly.length() == 0)
                return 0;
            if (isNegative)
                return -2147483648;
            return 2147483647;
        }
        if (isNegative)
            result = -result;
        return result;
    }
}
