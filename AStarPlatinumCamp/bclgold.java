import java.util.*;
import java.io.*;

public class bclgold {

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());

		char[] c = new char[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			c[i] = st.nextToken().charAt(0);
		}

		f.close();

		int ct = 0;

		int l = 0;
		int r = n - 1;
		while (l <= r) {

			if (c[l] < c[r]) {
				System.out.print(c[l++]);
			} else if (c[l] > c[r]) {
				System.out.print(c[r--]);
			} else {

				int tl = l + 1;
				int tr = r - 1;

				while (tl + 1 < tr && c[tl] == c[tr]) {
					tl++;
					tr--;
				}

				if (tl > 0 && tr > 0 && c[tl] < c[tr]) {
					System.out.print(c[l++]);
				} else {
					System.out.print(c[r--]);
				}

			}

			ct++;
			if (ct == 80) {
				ct = 0;
				System.out.println();
			}

		}

	}

}
