//By LLW(LWL/AXhing-LLW)


import java.util.Random;

public class RandomUtils 
{

	public static int[]  getRandoms(int count, int range) 
	{
		int[] randoms = new int[count];
		Random random = new Random();
		int[] flags = new int[range];
		int i = 0;
		while(i < count) 
		{
			int r = random.nextInt(range);
			while(flags[r] == 1) 
			{
				r = (r + 1) % range;
			}
			flags[r] = 1;
			randoms[i] = r;
			i++;
		}
		return randoms;
	}
	
	public static int[] getRandomsCoverNum(int num, int range, int count) 
	{
        if(num >= range || count > range - 1 || num < 0 || range < 0 || count < 0) 
        {
            return new int[0];
        }
        int[] randoms = new int[count];
        int[] flags = new int[range];
        flags[num] = 1;
        int i = 0;
        Random random = new Random();
        while(i < count) 
        {
            int r = random.nextInt(range);
            while(flags[r] == 1) 
            {
                r = (r + 1) % range;
            }
            flags[r] = 1;
            randoms[i] = r;
            i++;
        }
        return randoms;
    }


	public static int getRandom(int range) 
	{
		if(range <= 0) 
		{
			return 0;
		}
		Random random = new Random();
		return random.nextInt(range);
	}
}
