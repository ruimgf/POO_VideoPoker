package players;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import cards.HandCards;
import videopoker.DoubleBonus10_7;
import videopoker.OurVideoPoker;
import videopoker.Result;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class GuiPlayer extends Player10_7 {

	private JFrame frame;
	JButton [] cards = new JButton[5];
	int [] cardsXpos = {112, 269, 426, 583,740};
	int betValue=5;
	JButton btnDeal;
	JButton btnHold;
	JButton btnAdvice;
	boolean [] holdCards = new boolean[5];
	JLabel message;
	JLabel label;
	JButton [] chips = new JButton[5];
	boolean [] chipsAux = new boolean[5];
	Timer timer;
	int timerAux=0;
	JLabel credit;
	JTextArea statistics;
	JTextField userCreditText;
	JLabel initMessage;
	JLabel initMessageError;
	int initCredit=0;
	
	
	/**
	 * Create the application.
	 */
	public GuiPlayer() {
		super();		
		initialize();	
				
		
		
	}
	
	public void Play(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	* eSTA FUNÇAO MEXE EM COISAS
	* @param res o que recebes
	*
	*/
	private void showCards(Result res){
		HandCards h = res.getHand();
		btnDeal.setEnabled(false);
		btnHold.setEnabled(false);
		btnAdvice.setEnabled(false);
		for(int i=0; i<5;i++){
			if(holdCards[i]==false){
				cards[i].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/cardBack.png")));
				cards[i].setRolloverIcon(null);
			}
		}
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if(timerAux==5){
					timer.stop();
					timerAux=0;
					Arrays.fill(holdCards,true);
					
					try{	
						String [] finalHand =res.toString().split("\n");
						if(finalHand[1].contains("loses")){
							message.setText("Player loses!");
						}else{
							finalHand =finalHand[1].split(" and ");
							message.setText(finalHand[0]);
						}
						btnDeal.setEnabled(true);
						btnHold.setEnabled(false);
						btnAdvice.setEnabled(false);
						message.setVisible(true);
						PrintStatistics(game.statistics());
					}catch(IndexOutOfBoundsException err){
						btnDeal.setEnabled(false);
						btnHold.setEnabled(true);
						btnAdvice.setEnabled(true);
					}
					
					credit.setText(game.credit().toString());				
				}else{	
	            	if(holdCards[timerAux]==false){
	            		cards[timerAux].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/"+h.getCardN(timerAux)+".png")));
	            		cards[timerAux].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/"+h.getCardN(timerAux)+"on.png")));
	            		cards[timerAux].setBounds(cardsXpos[timerAux], 290, 145, 216);
	            	}else{
	            		downCards(timerAux);
	            	}
	            	timerAux++;
				}
				
            }
		};
		timer = new Timer(100, taskPerformer);
		timer.start();
	}
	
	private void upCards(int index){
		int upCrad= 50;
		cards[index].setBounds(cardsXpos[index],  290 - upCrad, 145, 216);
		holdCards[index] = false;
	}
	
	private void downCards(int index){
		cards[index].setBounds(cardsXpos[index],  290, 145, 216);
		holdCards[index] = true;
	}
	
	private void adviceUp(){
		for(int i=0; i<5; i++){
			if(holdCards[i]){
				downCards(i);
			}else{
				upCards(i);
			}
		}
	}
	
	private void InitializeMessage(){
		message = new JLabel("");
		message.setBorder(null);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.WHITE);
		message.setFont(new Font("URW Bookman L", Font.BOLD, 35));
		message.setBounds(112, 520, 773, 50);
		frame.getContentPane().add(message);
	}
	
	private void InitializeCards(){
		for(int i=0; i<5;i++){
			int a=i;
			cards[i] = new JButton("");
			//card0.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/cardBack.png")));
			
			cards[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(holdCards[a]){
						upCards(a);
					}else{
						downCards(a);
					}
				}
			
			});
			cards[i].setBounds(cardsXpos[i], 290, 145, 216);
			cards[i].setOpaque(false);
			cards[i].setContentAreaFilled(false);
			cards[i].setBorderPainted(false);
			cards[i].setVisible(false);
			//cards[i].setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(cards[i]);
		}
	}
			
	private void InitializeChips(){
		for(int i=0; i<5;i++){
			int a=i;
			chips[i] = new JButton("");
			chips[i].setOpaque(false);
			chips[i].setContentAreaFilled(false);
			chips[i].setBorderPainted(false);
			chips[i].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chips.png")));
			chips[i].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipson.png")));
			chips[i].setBounds(112+(80*i), 130, 70, 50);
			chips[i].setVisible(false);
			frame.getContentPane().add(chips[i]);
			chips[i].addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		int j;
	        		if(chipsAux[a]==false){
		        		for(j=0; j<=a;j++){
		        			chips[j].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipson.png")));
		        			chips[j].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipson.png")));
		        			chipsAux[j]=true;
		        		}
		        		
		        		for(int aux=j; aux<=4; aux++){
		        			chips[aux].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipsBlackon.png")));
		        			chips[aux].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipsBlackon.png")));
							chipsAux[aux]=false;
						}
	        		}else{
	        		
		        			for(j=a+1; j<5; j++){
			        			chips[j].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipsBlackon.png")));
			        			chips[j].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chipsBlackon.png")));
								chipsAux[j]=false;
		        			}
		        		
	        		}
		        	betValue=a+1;
	        	}
	        });
		}
	}
	
	
	
	private void PrintStatistics(Result res){
		statistics.setText(""+res);
	}
	
	private void InitializeStatistics(){
		statistics= new JTextArea();
		statistics.setLineWrap(true);
		statistics.setTabSize(1);
		statistics.setFont(new Font("Monospaced", Font.BOLD, 10));
		statistics.setBackground(new Color(0,0,0,0));
		statistics.setForeground(Color.WHITE);
		statistics.setEditable(false);
		statistics.setBounds(685, 24, 287, 222);
		statistics.setVisible(false);
		frame.getContentPane().add(statistics);
	}
	

	

	
	private void InitializeCreditMessage(){
		
		credit = new JLabel("");
		credit.setHorizontalAlignment(SwingConstants.LEFT);
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("URW Bookman L", Font.BOLD, 30));
		credit.setBounds(112, 70, 500, 50);
		credit.setVisible(false);
		frame.getContentPane().add(credit);
		
	}
	
	private void InitializeButtonDeal(){
		
		btnDeal = new JButton("");
		btnDeal.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonDOff.png")));
		btnDeal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnDeal.setBorder(null);
		btnDeal.setOpaque(false);
		btnDeal.setContentAreaFilled(false);
		btnDeal.setBorderPainted(false);
		btnDeal.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_deal.png")));
		btnDeal.setBounds(100, 600, 220, 60);
		btnDeal.setVisible(false);
		frame.getContentPane().add(btnDeal);
		
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arrays.fill(holdCards,false);
				game.bet(betValue);
				Result res = game.deal();
				showCards(res);
				credit.setText(game.credit().toString());
				message.setVisible(false);
			}
		});
	}
		
	private void InitializeButtonHold(){
		btnHold = new JButton("");
		btnHold.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonHOff.png")));
		btnHold.setBorder(null);
		btnHold.setOpaque(false);
		btnHold.setContentAreaFilled(false);
		btnHold.setBorderPainted(false);
		btnHold.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_hold.png")));
		btnHold.setEnabled(false);
		
		btnHold.setBounds(380, 600, 220, 60);
		btnHold.setVisible(false);
		frame.getContentPane().add(btnHold);
		
		btnHold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Result res = game.hold(holdCards);
				showCards(res);

			}
		});
	}
		
	private void InitializeButtonAdvice(){	
		btnAdvice = new JButton("");
		btnAdvice.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonAOff.png")));
		btnAdvice.setBorder(null);
		btnAdvice.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_advice.png")));
		btnAdvice.setEnabled(false);
		btnAdvice.setOpaque(false);
		btnAdvice.setContentAreaFilled(false);
		btnAdvice.setBorderPainted(false);
		btnAdvice.setBounds(640, 600, 220, 60);
		btnAdvice.setVisible(false);
		frame.getContentPane().add(btnAdvice);
		
		btnAdvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result res = game.advice();
				holdCards = res.getHoldcards();
				adviceUp();

			}
		});	
	}
	
	private void InitializeCreditButton(){	
		JButton creditButton = new JButton("");
		creditButton.setVerticalAlignment(SwingConstants.BOTTOM);
		creditButton.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/play.png")));
		creditButton.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/playon.png")));
		creditButton.setBounds(410, 340, 190, 290);
		creditButton.setOpaque(false);
		creditButton.setContentAreaFilled(false);
		creditButton.setBorderPainted(false);
		frame.getContentPane().add(creditButton);
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					initCredit=Integer.parseInt(userCreditText.getText()); 
					System.out.println("Olá");
					
					game = new OurVideoPoker(initCredit, new DoubleBonus10_7());
					PrintStatistics(game.statistics());
					credit.setText(game.credit().toString());
					GameVisible();
					creditButton.setVisible(false);
					initMessage.setVisible(false);
					initMessageError.setVisible(false);
					userCreditText.setVisible(false);
				}catch (NumberFormatException getError){
					System.out.println("Adeus");
					initMessageError.setText("(Invalid format. Please insert valid credit)");
				}

			}
		});
	}
	
	private void InitializeFrame(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void GameVisible(){
		
		for(int i=0; i<5; i++){
			chips[i].setVisible(true);
			cards[i].setVisible(true);
		}
		statistics.setVisible(true);
		btnAdvice.setVisible(true);
		btnHold.setVisible(true);
		btnDeal.setVisible(true);
		credit.setVisible(true);
		message.setVisible(true);
		
	}
	
	private void InitializeInitMessage(){
		initMessage = new JLabel("Insert your credit here:");
		initMessage.setHorizontalAlignment(SwingConstants.LEFT);
		initMessage.setForeground(Color.WHITE);
		initMessage.setFont(new Font("URW Bookman L", Font.BOLD, 40));
		initMessage.setBounds(100, 125, 700, 50);
		frame.getContentPane().add(initMessage);
	}
	
	private void InitializeInitMessageError(){
		initMessageError = new JLabel("");
		initMessageError.setHorizontalAlignment(SwingConstants.LEFT);
		initMessageError.setForeground(Color.WHITE);
		initMessageError.setFont(new Font("URW Bookman L", Font.BOLD, 20));
		initMessageError.setBounds(100, 165, 700, 50);
		frame.getContentPane().add(initMessageError);
	}
	
	private void InitializeCreditGeter(){
		userCreditText = new JTextField();
		userCreditText.setSelectedTextColor(Color.WHITE);
		userCreditText.setSelectionColor(Color.WHITE);
		userCreditText.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		userCreditText.setForeground(Color.WHITE);
		userCreditText.setOpaque(false);
		
		userCreditText.setFont(new Font("URW Bookman L", Font.BOLD, 40));
		userCreditText.setBounds(380, 250, 240, 60);
		frame.getContentPane().add(userCreditText);
		
	}
	
	private void InitializeButtons(){
		InitializeButtonDeal();
		InitializeButtonHold();
		InitializeButtonAdvice();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		InitializeFrame();
		InitializeInitMessage();
		InitializeInitMessageError();
		InitializeCreditGeter();
		InitializeCreditButton();
		Arrays.fill(chipsAux,true);
		InitializeMessage();
		InitializeCards(); 
		InitializeChips();
		InitializeCreditMessage();
		InitializeButtons();	
		InitializeStatistics();
		JLabel backGround = new JLabel(new ImageIcon(GuiPlayer.class.getResource("/images/backGround.png")));
		backGround.setLocation(0, 0);
		backGround.setSize(1000, 700);
		backGround.setBackground(new Color(238, 238, 238));
		frame.getContentPane().add(backGround);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GuiPlayer jogo = new GuiPlayer();
		jogo.Play();
	}
}
