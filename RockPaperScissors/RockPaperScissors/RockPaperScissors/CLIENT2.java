package RockPaperScissors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CLIENT2 {

	public static void main(String[] args) {
		try {
			Socket testsocket = new Socket("localhost", 7274);
			testsocket.getInputStream();
			ObjectOutputStream out = new ObjectOutputStream(testsocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(testsocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
