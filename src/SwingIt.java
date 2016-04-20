import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SwingIt {
	
	JFrame frame;
	JTextField url;
	JTextField depth;
	JTextField patterns;
	JTextField dir;
	JButton button;
	TextArea textArea;
	JLabel status;
	
	private void prepareGUI() {
		frame = new JFrame("Spidy1.0");
		frame.setSize(500, 450);
		FlowLayout f = new FlowLayout();
		f.setVgap(20);
		f.setHgap(30);
		frame.setLayout(f);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		button = new JButton("Crawl!");
		button.addActionListener(new Actionss());
		textArea = new TextArea();
		url = new JTextField("Enter the URL");
		url.setColumns(27);
		depth = new JTextField("Enter the depth");
		depth.setColumns(10);
		patterns = new JTextField("Enter the words separated by ;");
		patterns.setColumns(40);
		dir = new JTextField("Enter the location of the folder where you want to store the results");
		dir.setColumns(40);
		status = new JLabel("Pages crawled : 0");
		frame.add(url);
		frame.add(depth);
		frame.add(patterns);
		frame.add(dir);
		frame.add(button);
		frame.add(textArea);
		frame.add(status);
		frame.setVisible(true);		
	}
	
	public static void main(String args[]) {
		new SwingIt().prepareGUI();
	}
	
	private class Actionss implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Runnable r = new DynamicUpdate();
			//Thread is needed, so that the logs shown in the textArea view is updated dynamically
			Thread th = new Thread(r);
			th.start();
		}
	}
	
	public class DynamicUpdate implements Runnable {
		public void run() {
			//Run a simple BFS from the starting URL given by the user
			 String u = url.getText();
			 int limit = Integer.parseInt(depth.getText());
	         CrawlAndSave helper = new CrawlAndSave(patterns.getText(),dir.getText());
	         int c = 0;
	         Queue<String> Q = new LinkedList<String>();
	         Q.add(u);
	         HashMap<String, Integer> map = new HashMap<String, Integer> (); 
	         map.put(u, new Integer(0));
	         textArea.setText("Let's begin\n\n");
	         status.setText("Pages crawled : 0");
	         while(!Q.isEmpty()) {
	        	 String s = null;
	        	 Vector<String> n = null;
	        	 s = Q.remove();
	        	 textArea.append(s+"\n");
	        	 try {
	        		 n = helper.crawl(s);
	        		 helper.save(s);
	        	 } catch(Exception e1) {
	        		 textArea.append("FAILED!\n\n");
	        		 e1.printStackTrace();
	        		 continue;
	        	 }
	        	 textArea.append("SUCCESSFULL!\n\n");
	        	 if(map.get(s) < limit ) {
	        		 for(String t: n) {
	        			if(!map.containsKey(t)) {
	        				 map.put(t,map.get(s)+1);
	        				 Q.add(t);
	        			 }
	        		 }
	        	 }
	        	 c++;
	        	 status.setText("Pages crwaled : "+c);
	         }
	         textArea.append("Finished crawling.\n\n");
		}
	}
}
