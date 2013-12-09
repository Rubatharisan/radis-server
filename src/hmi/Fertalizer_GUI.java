package hmi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import components.Fertilizer_sim;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Fertalizer_GUI extends JFrame {

	private Fertilizer_sim simulator;
	private Thread thread;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JTextArea textArea;
	private Timer btnTimer;
	private JButton btnReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fertalizer_GUI frame = new Fertalizer_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fertalizer_GUI() {
		simulator = new Fertilizer_sim();
		thread = new Thread(simulator);
		thread.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.scrollPane = new JScrollPane();
		this.contentPane.add(this.scrollPane, BorderLayout.CENTER);
		
		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);
		this.textArea.setCaretPosition(textArea.getCaretPosition());
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.EAST);
		
		this.btnNewButton = new JButton("Add Fertalizer");
		this.btnNewButton.addActionListener(new BtnNewButtonActionListener());
		this.panel.setLayout(new BorderLayout(0, 0));
		this.btnNewButton.setPreferredSize(new Dimension(150, 105));
		this.panel.add(this.btnNewButton, BorderLayout.NORTH);
		
		this.btnReturn = new JButton("return");
		this.btnReturn.setPreferredSize(new Dimension(150, 105));
		this.btnReturn.addActionListener(new BtnReturnActionListener());
		this.panel.add(this.btnReturn, BorderLayout.SOUTH);
		
		Timer updater = new Timer(30000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    	Calendar cal = Calendar.getInstance();
				textArea.append("Fertalize Nr: "+simulator.Fertalizer_getCount()+" ----------- Fertalized" +
						" at: " + dateFormat.format(cal.getTime())+"\n");
			}
		});
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
		textArea.append("Fertalize Nr: "+simulator.Fertalizer_getCount()+" ----------- Fertalized" +
				" at: " + dateFormat.format(cal.getTime()) +"\n");
		updater.start();
	}
	
	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			simulator.Fertalizer_addFertalizer();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	Calendar cal = Calendar.getInstance();
	    	
			textArea.append("MANUALLY ADDED FERTALIZER: \n"+"Fertalize Nr: "+simulator.Fertalizer_getCount()+" ----------- Fertalized" +
					" at: " + dateFormat.format(cal.getTime()) +"\n");
			
			btnNewButton.setEnabled(false);
			Timer btnTimer = new Timer(3*1000, new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btnNewButton.setEnabled(true);
				}
				
			});
			btnTimer.start();
			
		}
	}
	private class BtnReturnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	


}
