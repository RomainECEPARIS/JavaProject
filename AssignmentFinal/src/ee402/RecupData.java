package ee402;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.util.stream.Stream;
*/

public class RecupData 
{

	  
    private String t_temp;
    private String t_time;
	String[] args;
	
	// Constructor
	public RecupData() 
	{
		
	}
	
	
	//We read the temperature value in the file
	public RecupData getTempDate() 
	{
		DateTimeService datatime=new DateTimeService();
		t_time= datatime.getDateAndTime();
		
		
		try {
			File file = new File("/sys/class/thermal/thermal_zone0/temp");
			Scanner scan = new Scanner(file);
			t_temp= scan.nextLine();
			System.out.println(t_temp);
			scan.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Error");
			e.printStackTrace();
		}
	
		/*	 
		String fileName = "/sys/class/thermal/thermal_zone0/temp";
		
		try (Stream stream = Files.lines(Paths.get(fileName))) 
		{
			stream.forEach(System.out::println);
		}

		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
*/
		
		return this;
	}
	
	
	public String get_temp_time() {return (t_temp+"/"+t_time);}
	public String get_temp() {return (t_temp);}
	public String get_time() {return (t_time);}
	
	
}
