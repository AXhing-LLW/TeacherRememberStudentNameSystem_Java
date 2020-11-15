//By LLW(LWL/AXhing-LLW)

public class StringUtils 
{
	public static boolean isBlank(String str) 
	{
		boolean isBlank = true;
		if(null != str && str.length() > 0) 
		{
			for(int i = 0; i < str.length(); i++) 
			{
				if(str.charAt(i) != ' ') 
				{
					isBlank = false;
				}
			}
		}
		return isBlank;
	}
}
