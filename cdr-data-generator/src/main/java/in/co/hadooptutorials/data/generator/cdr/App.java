package in.co.hadooptutorials.data.generator.cdr;


import java.util.*;

import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;
/**
 * 
 * Telecom Call Data/Detail Record Generator.
 * @author Tanmay Deshpande
 * 
 */

public class App {
	public static void main(String[] args) {
		List<String> subscribers = new ArrayList<String>();
 		DataFactory df = new DataFactory();
		Random r = new Random();
		int nSubscribers = 10000;
		for (int i = 0; i < nSubscribers; i++) {
			subscribers.add(df.getNumberText(10));
		}

		int nCalls = 100000;
		for ( int i = 0; i < nCalls;  i++ ) {
			// Take any two random numbers
			UUID id = UUID.randomUUID();

			int caller_index = r.nextInt(nSubscribers - 1);
			int callee_index = r.nextInt(nSubscribers - 1);
			while (caller_index == callee_index)
				callee_index = r.nextInt(nSubscribers - 1);

			String calling_num = subscribers.get(caller_index);
			String called_num = subscribers.get(callee_index);

			long t1 = System.currentTimeMillis() + r.nextInt();
			long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
			DateTime d1 = new DateTime(t1);
			DateTime d2 = new DateTime(t2);
			String callType = "";
			if ( r.nextInt() % 3 != 0){
				callType = "VOICE";
			} else {
				callType = "SMS";
			}
			if ("SMS" == callType) {
				d2 = new DateTime(t1);
			}
			String callResult = "ANSWERED";
			if ((i % 10 )== 0 && callType == "VOICE") {
				callResult = "BUSY";
				d2 = new DateTime(t1);
			}
			Float charge = r.nextFloat();
			System.out.println(id.toString()+"|"+calling_num+"|"+called_num+"|"+d1.toString()+"|"+d2.toString()+"|"+callType+"|"+charge+"|"+callResult);

		}
	}
}
