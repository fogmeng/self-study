package jio.ch03;

import java.io.IOException;

public class StreamPrinter {

	public static void main(String[] args) {

		try {
			while (true) {
				int datum = System.in.read();
				if (datum == -1)
					break;
				System.out.println(datum);
			}
		} catch (IOException ex) {
			System.err.println("Couldn't read from System.in!");
		}
	}
}
