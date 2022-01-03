package pack.LoginSystem;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Cracker {
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/*
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}

	// for given string outputs sha ecoding value
	public static String translateToSHA(String passwordString){

		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] hexBytes = passwordString.getBytes("UTF-8");
			byte[] hash = md.digest(hexBytes);
			String encode = hexToString(hash);
			return encode;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}
	// computes and returns cumulative distribution of numbers given in array
	public static ArrayList<Integer> cumulative(ArrayList<Integer> distribution){
		ArrayList<Integer> outputArray = (ArrayList<Integer>) distribution.clone();
		IntStream.range(0,distribution.size() -1 )
				.forEach(i -> IntStream.range(i+1,distribution.size())
						.forEach(j -> outputArray.set(j,outputArray.get(j)+distribution.get(i))));
		return outputArray;
	}
	// retruns distributed range for threads that meets constraints of user input
	public static ArrayList<Integer> getDistribution(int threadNum){
		ArrayList<Integer> distribution = new ArrayList<>();
		IntStream.range(0,threadNum).forEach(i -> distribution.add(CHARS.length/threadNum));
		if(CHARS.length%threadNum != 0)
			IntStream.range(0,CHARS.length%threadNum).forEach(i -> distribution.set(i,distribution.get(i)+1));
		return cumulative(distribution);
	}
	// launcher threads and gives them charachers to process
	public static void processCharacters(ArrayList<Integer> distribution , int len, String hexOfPassword, CountDownLatch latch){
		Thread sampleThread = new Thread(new CrackWorker(0,distribution.get(0),0,len,hexOfPassword,latch));
		sampleThread.start();
		if(distribution.size() != 1) {
			for (int sampler : IntStream.range(1, distribution.size()).toArray()) {
				Thread sampleLoopThread = new Thread(new CrackWorker(distribution.get(sampler-1), distribution.get(sampler), sampler, len,hexOfPassword, latch));
				sampleLoopThread.start();
			}
		}
	}

	// keep in mind as for a given hash there maybe many string outputs computation doesnt haults unless all possibilites were found
	// if user is tired of waiting for another possible string (if any) he/she may press CRTL+C to cancel the process
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Args: target length [workers]");
			System.exit(1);
		}
		String targ = args[0];
		// if 1 input then  sha output is printed
		if(args.length == 1){
			System.out.println(translateToSHA(targ));
			System.exit(1);
		}

		int len = Integer.parseInt(args[1]);		
		int num = 1;
		if (args.length>2) {
			num = Integer.parseInt(args[2]);
		}
		
		ArrayList<Integer> distribution = getDistribution(num);
		CountDownLatch latch = new CountDownLatch(num);
		processCharacters(distribution,len,targ,latch);
		try {
			latch.await();
			System.out.println("Computation Done");  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// CrackWorker class that implements runnable interface
	// overrides runnables run funtion that is called when thread is started
	static class CrackWorker implements Runnable{
		private int rangeFrom;
		private int rangeTo;
		private int passwordLength;
		private String hexOfPassword;
		private CountDownLatch latch;

		// constructor
		public CrackWorker(int rangeFrom, int rangeTo, int id,int passwordLength,String hexOfPassword,CountDownLatch latch){
			this.rangeFrom = rangeFrom;
			this.rangeTo = rangeTo;
			this.passwordLength = passwordLength;
			this.hexOfPassword = hexOfPassword;
			this.latch = latch;
		}
		// cracing algorithm that uses recurtion 
		public void tryCrack(String alreadyBuilt){
			if(alreadyBuilt.length() > passwordLength) return;
			if(translateToSHA(alreadyBuilt).equals(hexOfPassword)) System.out.println(alreadyBuilt);
			IntStream.range(0,CHARS.length)
					.forEach(i -> tryCrack(alreadyBuilt + CHARS[i]));
		}

		// overriding run method
		@Override
		public void run() {
			IntStream.range(rangeFrom,rangeTo)
					.forEach(i -> this.tryCrack(CHARS[i]+""));
			latch.countDown();
		}
	}
}