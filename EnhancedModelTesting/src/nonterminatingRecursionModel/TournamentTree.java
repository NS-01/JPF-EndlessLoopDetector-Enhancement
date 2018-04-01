package nonterminatingRecursionModel;

import java.util.ArrayList;
import java.util.Random;

public class TournamentTree {
	public static void main(String[] args) {
		TournamentTree tt = new TournamentTree();
		int[] data = { 0, 34, 346, 80, 235, 9, 1, 54, 3, 75 };
		Random random = new Random();
		tt.tournamentTreeKSelection(data, random.nextInt());
	}

	public int tournamentTreeKSelection(int[] data, int k) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>(list);

		for (int i = 0; i < data.length - 1; i += 2) {
			list.add(max(data[i], data[i + 1]));
		}

		if (list.size() % 2 != 0)
			list.add(-1);

		if (k > 1 && list.size() == 1) {
			for (int i = 0; i < list2.size(); i++)
				if (list2.get(i) == list.get(0))
					list2.remove(i);

			return tournamentTreeKSelection(listToArray(list2), --k);
		}

		if (list.size() == 1)
			return list.get(0);
		System.out.println("list" + list.toString());
		return tournamentTreeKSelection(listToArray(list), k);

	}

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static int[] listToArray(ArrayList<Integer> arr) {
		int[] arr2 = new int[arr.size()];
		for (int i = 0; i < arr.size(); i++)
			arr2[i] = arr.get(i);

		return arr2;
	}

}