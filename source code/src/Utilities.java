import java.io.RandomAccessFile;

public abstract class Utilities {

	// A method that is give a line and returns an array of values
	public static String[] parseLine(String line) {
		return line.replace("\t","").trim().split(":");
	}

	// A method that reads a line from a file and returns an array of values
	public static String[] parseLine(RandomAccessFile raf) throws Exception {
		return parseLine(raf.readLine());
	}

	// A method that reads a line from the file and returns just the data part
	public static String nextLineData(RandomAccessFile raf) throws Exception {
		try {
			return parseLine(raf.readLine())[1].trim();
		} catch(Exception e) {
			return "";
		}
	}

}
