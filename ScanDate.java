package A3;

import java.util.*;
import java.text.*;

public class ScanDate {

	public ScanDate() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		 
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Scanner sc = new Scanner(System.in);
 
      System.out.println("Eample: 2023-04-12 09:00:00");
      System.out.print("Enter date: ");
      String str = sc.nextLine();
 
      try {
         Date date = sdf.parse(str); 
 
         sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         System.out.println("Date: " + sdf.format(date));
      } catch (ParseException e) { 
         System.out.println("Parse Exception");
      }
   }
}

