public class RegexMatching {

    class Solution {

        private boolean match(String s, String p, int sLength, int pLength) {
            if (s.length() == sLength && p.length() == pLength)
                return true;
            if (p.length() == pLength)
                return false;
            if (pLength == p.length() - 1 || p.charAt(pLength + 1) != '*') {
                if (sLength < s.length() && (p.charAt(pLength) == '.' || s.charAt(sLength) == p.charAt(pLength)))
                    return match(s, p, sLength + 1, pLength + 1);
                return false;
            }
            if (match(s, p, sLength, pLength + 2))
                return true;
            return sLength < s.length() && (p.charAt(pLength) == '.' || s.charAt(sLength) == p.charAt(pLength))
                    && match(s, p, sLength + 1, pLength);
        }


        public boolean isMatch(String s, String p) {

            return match(s, p, 0, 0);
        }
    }

}
