package jio.ch04;

import java.io.*;
import com.elharo.io.*;

public class FileTyper {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: java FileTyper filename");
      return;
    }
    typeFile(args[0]);
  }

  public static void typeFile(String filename) throws IOException {
    FileInputStream fin = new FileInputStream(filename);
    try {
        StreamCopier.copy(fin, System.out);
    }
    finally {
      fin.close();
    }
  }
}
