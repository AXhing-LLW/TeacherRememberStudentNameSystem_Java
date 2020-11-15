//By LLW(LWL/AXhing-LLW)


public class Student 
{
	private Integer sid;
	private String lastname;
	private String firstname;
	private String nickname;
	private String photo;
	
	private Integer ebbinghaus_level = 0;
	private String time = "";


	public Student(Integer sid, String lastname, String firstname, String nickname, String photo) 
	{
		this.sid = sid;
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.photo = photo;
	}

	public Integer getSid() 
	{
		return sid;
	}

	public void setSid(Integer sid) 
	{
		this.sid = sid;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getNickname() 
	{
		return nickname;
	}

	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}

	public Integer getEbbinghaus_level() 
	{
		return ebbinghaus_level;
	}

	public void setEbbinghaus_level(Integer ebbinghaus_level) 
	{
		this.ebbinghaus_level = ebbinghaus_level;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getPhoto() 
	{
		return photo;
	}

	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public String toString() 
	{
		return "Student [sid=" + sid + ", lastname=" + lastname + ", firstname=" + firstname + ", nickname=" + nickname
				+ ", photo=" + photo + ", ebbinghaus_level=" + ebbinghaus_level + ", time=" + time + "]";
	}

}
