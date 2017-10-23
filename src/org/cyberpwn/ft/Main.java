package org.cyberpwn.ft;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Main
{
	public static void main(String[] a)
	{
		int k = 120000;
		long ms = System.currentTimeMillis();
		if(a.length == 1)
		{
			k = Integer.valueOf(a[0].split(":")[1]);
		}

		Set<String> ss = new HashSet<String>();

		long lt = 0;

		for(int i = 0; i < k; i++)
		{
			String vf = "";
			int rnum = (int) (Math.random() * k * 10);

			for(int j = 0; j < 4; j++)
			{
				char c = Alphabet.getAlphabet().get((int) ((Alphabet.getAlphabet().size() - 1) * Math.random()));
				vf += c;
			}

			long t = System.currentTimeMillis() - ms;
			vf += rnum + "";

			for(int j = 0; j < 2; j++)
			{
				char c = Alphabet.getAlphabet().get((int) ((Alphabet.getAlphabet().size() - 1) * Math.random()));
				vf += c;
			}

			if(t % 500 == 0)
			{
				if(Math.abs(t - lt) > 100)
				{
					double pct = (double) i / (double) k;
					pct *= 100;
					System.out.println("Writing: " + (int) pct + "% (" + (k - i) + " left)");
					lt = t;
				}
			}

			ss.add(vf);
		}

		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(new File("dictionary.txt")));
			int v = 0;

			for(String i : ss)
			{
				v++;
				pw.print(i + " ");

				if(v % 6 == 0)
				{
					pw.println();
				}
			}

			pw.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("Took " + (System.currentTimeMillis() - ms) + "ms");
	}
}
