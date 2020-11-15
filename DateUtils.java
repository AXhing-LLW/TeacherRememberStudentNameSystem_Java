//By LLW(LWL/AXhing-LLW)

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils 
{
	
	private static final int SECOND = 1000;
    private static final int MINUTE = SECOND * 60;
    private static final int HOUR = MINUTE * 60;
    private static final int DAY = 24 * HOUR;
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int[] EbbinghausTimeTest = new int[]
    {
            1 * SECOND, 4 * SECOND, 8 * SECOND, 20 * SECOND, 40 * SECOND, 80 * SECOND, 120 * SECOND, 200 * SECOND
    };

    private static final int[] EbbinghausTime = new int[]
    {
            5 * MINUTE, 30 * MINUTE, 12 * HOUR,
            1 * DAY, 2 * DAY, 4 * DAY, 7 * DAY, 15 * DAY
    };

    public static Date getNoticeTimeByEbbinghaus(int level, Date date) 
    {
        Date res = new Date(date.getTime());
        if (level >= 1 && level <= 8) 
        {
            res.setTime(res.getTime() + EbbinghausTime[level - 1]);
        }
        return res;
    }

    public static String dateToString(Date date) 
    {
    	if(date == null) 
    	{
    		return "";
    	}
        return sf.format(date);
    }

    public static Date stringToDate(String dateStr) 
    {
        Date date = null;
        try 
        {
			date = sf.parse(dateStr);
		} catch (ParseException e) 
        {
			e.printStackTrace();
		}
        return date;
    } 
    
    public static String dateToStringYMD(Date date) 
    {
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
        return sft.format(date);
    }
    
    public static boolean isSameDay(Date t1, Date t2) 
    {
    	int len = "yyyy-MM-dd".length();
    	String t1Str = dateToString(t1).substring(0, len);
    	String t2Str = dateToString(t2).substring(0, len);;
    	System.out.println(t1Str);
    	System.out.println(t2Str);
    	return t1Str.equals(t2Str);
    }
}
