import java.util.*;
import java.io.*;

/*
ID: kevinyl1
LANG: JAVA
TASK: fence9
*/

public class fence9 {

	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		f.close();

		int ans = m * p / 2 + 1;
		ans -= (gcd(n, m) + gcd(Math.abs(p - n), m) + gcd(p, 0)) / 2;

		PrintWriter pw = new PrintWriter(new File("fence9.out"));
		pw.println(ans);
		pw.close();
		System.exit(0);

	}

}
