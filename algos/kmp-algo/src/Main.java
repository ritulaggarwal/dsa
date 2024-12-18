import java.util.ArrayList;
import java.util.List;

/**
 * TC: O(M+N)
 * SC: O(M)
 */

public class Main {
    public static void main(String[] args) {
        String text = "aabaacaadaabaaba";
        String pattern = "aaba";
        List<Integer> res = search(pattern, text);
        res.forEach((element) -> System.out.println(element));
    }

    private static List<Integer> search(String pattern, String text) {
        List<Integer> ans = new ArrayList<>();
        int i=0,j=0;
        int n = text.length();
        int m = pattern.length();
        int [] lps = new int[m];
        constructLPS(pattern, lps);
        while(i<n) {
            if(text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if(j==m) {
                    ans.add(i - j);
                    j = lps[j-1];
                }

            } else {
                if(j!=0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
        }


        return ans;
    }



    // It will construct Longest Prefix Suffix
    private static void constructLPS(String pattern, int[] lps) {
        int i=1;
        int len=0;
        while(i<pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len!=0) {
                    len=lps[len-1];
                }else {
                    lps[i]=0;
                    i++;
                }
            }
        }
    }
}

