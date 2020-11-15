//By LLW(LWL/AXhing-LLW)

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class PhotoUtils 
{
	
	public static final String DEAFULT_PHOTO = "DeafultPhoto/Deafult.png";
	public static final String SAVE_DRICT = "SavePhoto/";

	public static boolean isIllPhoto(String dirct) 
	{
		if(StringUtils.isBlank(dirct) || !dirct.endsWith(".png")) 
		{
			return false;
		}
		return new File(dirct).exists();
	}
	
	public static String getServerPath(Student student) 
	{
		String oldStr = student.getPhoto();
		StringBuilder realSb = new StringBuilder();
		if(!isIllPhoto(oldStr)) 
		{
			realSb.append(DEAFULT_PHOTO);
		} 
		else 
		{
			realSb.append(SAVE_DRICT + student.getSid() + ".png");
		}
		return realSb.toString();
	}
	
	public static void uploadPhoto(String oldPath, String serverPath) throws IOException
	{
		if(!isIllPhoto(oldPath)) 
		{
			throw new IOException("Uploaded image does not exist");
		}
		if(oldPath.equals(DEAFULT_PHOTO) || serverPath.equals(DEAFULT_PHOTO)) 
		{
			return;
		}
		FileInputStream fis = new FileInputStream(oldPath);
		FileOutputStream  fos = new FileOutputStream(serverPath);
		int len = 0;
		while((len = fis.read()) != -1) 
		{
			fos.write(len);
		}
	}
	
	public static void deletePhoto(String str) 
	{
		if(isIllPhoto(str)) 
		{
			if(str.startsWith(SAVE_DRICT)) 
			{
				File file = new File(str);
				file.delete();
			}
		}
	}
}
