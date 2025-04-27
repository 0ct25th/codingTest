import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();

		int[] nums = new int[10];
		int result = 0;
		for (char n : N.toCharArray()) {
			int i = n - '0';
			switch (i) {
			case 6:
				if (nums[6] <= nums[9])
					nums[6]++;
				else
					nums[9]++;

				break;

			case 9:
				if (nums[9] <= nums[6])
					nums[9]++;
				else
					nums[6]++;
				break;

			default:
				nums[i]++;
				break;
			}
		}

		for (int num : nums)
			result = Math.max(result, num);

		System.out.println(result);
	}
}
