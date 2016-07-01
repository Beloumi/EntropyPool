package cologne.eck.dr.op.entropy_pool;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Simple frame to test the EntropyPool class
 * 
 * @author Axel von dem Bruch
 *
 */


@SuppressWarnings("serial")
public class TestFrame extends JFrame {
	
	public TestFrame() {
		
		this.setTitle("test frame for seed collector");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel)this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(Box.createVerticalStrut(20));
		
		// use frame to collect random values: 
		contentPane.addMouseMotionListener(new MouseRandomCollector() );
		
		JLabel label = new JLabel("Type text here:");
		contentPane.add(label);
		
		JTextField textField = new JTextField();
		textField.setMaximumSize(new Dimension(400,30));
		// use field to collect random values: 
		textField.addKeyListener(new KeyRandomCollector() );
		contentPane.add(textField);
			
		contentPane.add(Box.createVerticalStrut(20));
		
		JLabel mouseLabel = new JLabel("Mouse: move around inside the frame");
		mouseLabel.setPreferredSize(new Dimension(500, 500));
		mouseLabel.addMouseMotionListener(new MouseRandomCollector() );
		contentPane.add(mouseLabel);
		
		this.setLocation(50,50);
		this.pack();
	}

	public static void main(String[] args) {
		
		TestFrame test = new TestFrame();
		test.setVisible(true);
	}
}
