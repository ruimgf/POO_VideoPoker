package players;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import cards.HandCards;

import videopoker.*;
import ourvideopoker.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class that allow you to play the {@link videopoker.Videopoker} with an graphic interface
 * @author Alexandre Candeias, Pedro Martinho, Rui Figueiredo 
 */
public class GuiPlayer extends Player {

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
	JLabel [] discard = new JLabel[5];
	int initCredit=0;
	boolean cardsEnabled=true;
	boolean chipsEnabled=true;
	
	
	
	/**
	 * Create the application.
	 */
	public GuiPlayer() {
		super();		
		initialize();	
				
	}
	
	/**
	* Initialize play method
	*/
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
	* Initialize the label that informs the player if he lost or won
	*/
	private void InitializeMessage(){
		message = new JLabel("");
		message.setBorder(null);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.WHITE);
		message.setFont(new Font("URW Bookman L", Font.BOLD, 35));
		message.setBounds(112, 520, 773, 50);
		frame.getContentPane().add(message);
	}
	
	/**
	* Initialize the discard information
	*/
	private void InitializeDiscard(){
		for(int i=0; i<5;i++){
			discard[i] = new JLabel("");
			discard[i].setHorizontalAlignment(SwingConstants.CENTER);
			discard[i].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/discard.png")));
			discard[i].setBounds(cardsXpos[i], 240, 145, 216);
			frame.getContentPane().add(discard[i]);
			discard[i].setVisible(false);
		}
	}
	
	/**
	* Initialize the cards that are present in the table
	*/
	private void InitializeCards(){
		for(int i=0; i<5;i++){
			int a=i;
			cards[i] = new JButton("");
			//card0.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/cardBack.png")));
			
			cards[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(cardsEnabled==true){
						if(holdCards[a]){
							upCards(a);
						}else{
							downCards(a);
						}
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
			
	/**
	* Initialize the chips icons
	*/
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
	        		if(chipsEnabled==true){
	        			if(chipsAux[a]==false){
			        		for(j=0; j<=a;j++){
			        			chips[j].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/chips.png")));
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
	        	}
	        });
		}
	}
	
	/**
	* Initialize the statistics text
	*/
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
	
	/**
	* Initialize the credit message
	*/
	private void InitializeCreditMessage(){
	
		
		credit = new JLabel("");
		credit.setHorizontalAlignment(SwingConstants.LEFT);
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("URW Bookman L", Font.BOLD, 30));
		credit.setBounds(112, 70, 500, 50);
		credit.setVisible(false);
		frame.getContentPane().add(credit);
		
	}
	
	/**
	* Initialize the deal button
	*/
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
				
				try {
					game.bet(betValue);
					DealResult res = game.deal();
					showCards(res);
					credit.setText(game.credit().toString());
					initMessageError.setVisible(false);
					message.setVisible(false);
					cardsEnabled=true;
					chipsEnabled=false;
				} catch (InvalidPlayException e1) {
					
					
				}
				
			}
		});
	}
	
	/**
	* Initialize the hold button
	*/
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
				HoldResult res;
				try {
					res = game.hold(holdCards);
					showCards(res);
					cardsEnabled=false;
					chipsEnabled=true;
				} catch (InvalidPlayException e1) {
					// TODO INSERT CODE TO INVALID HOLD
					e1.printStackTrace();
				}
				
				

			}
		});
	}
	
	/**
	* Initialize the advice button
	*/
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
				AdviceResult res;
				try {
					res = game.advice();
					holdCards = res.getHoldCards();
					adviceUp();
				} catch (InvalidPlayException e1) {
					e1.printStackTrace();
				}
			}
		});	
	}
	
	/**
	* Initialize the button to be pressed to verify the credit input
	*/
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
				int currentCredit=5;
				try{
					initCredit=Integer.parseInt(userCreditText.getText()); 
					game = new OurVideoPoker(initCredit, new DoubleBonus10_7());
					try {
						PrintStatistics(game.statistics());
					} catch (InvalidPlayException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						credit.setText(game.credit().toString());
					} catch (InvalidPlayException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					GameVisible();
					creditButton.setVisible(false);
					initMessage.setVisible(false);
					initMessageError.setVisible(false);
					userCreditText.setVisible(false);
					try{
						if((currentCredit=game.credit().getCredits())<5){
							initMessageError.setVisible(true);
							initMessageError.setText("Max value you can bet is "+String.valueOf(currentCredit));
							if((currentCredit=game.credit().getCredits())==0){
								message.setText("GAME OVER");
								message.setFont(new Font("URW Bookman L", Font.BOLD, 60));
								message.setBounds(112, 300, 773, 50);
								GameNotVisible();
								
							}
						}
					}catch (InvalidPlayException e1){
						
					}
				}catch (NumberFormatException getError){
					initMessageError.setText("(Invalid format. Please insert valid credit)");
				}

			}
		});
	}
	
	/**
	* Initialize the frame where the game is going to be played
	*/
	
	private void InitializeFrame(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	* Initialize the initial message
	*/
	private void InitializeInitMessage(){
		initMessage = new JLabel("Insert your credit here:");
		initMessage.setHorizontalAlignment(SwingConstants.LEFT);
		initMessage.setForeground(Color.WHITE);
		initMessage.setFont(new Font("URW Bookman L", Font.BOLD, 40));
		initMessage.setBounds(100, 130, 700, 50);
		frame.getContentPane().add(initMessage);
	}
	
	/**
	* Initialize the error message that is displayed when the user has an invalid credit input
	*/
	private void InitializeInitMessageError(){
		initMessageError = new JLabel("");
		initMessageError.setHorizontalAlignment(SwingConstants.LEFT);
		initMessageError.setForeground(Color.WHITE);
		initMessageError.setFont(new Font("URW Bookman L", Font.BOLD, 20));
		initMessageError.setBounds(100, 165, 700, 50);
		frame.getContentPane().add(initMessageError);
	}
	
	/**
	* Initialize the box to insert the initial credit
	*/
	private void InitializeCreditGeter(){
		
		userCreditText = new JTextField();

		userCreditText.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		userCreditText.setForeground(Color.BLACK);
		userCreditText.setOpaque(false);
		userCreditText.setFont(new Font("URW Bookman L", Font.BOLD, 40));
		userCreditText.setBounds(380, 250, 240, 60);
		frame.getContentPane().add(userCreditText);
		
	}
	
	/**
	* Initialize the box to insert the initial credit
	*/
	private void InitializeTable(){
		
		JLabel backGround = new JLabel(new ImageIcon(GuiPlayer.class.getResource("/images/backGround.png")));
		backGround.setLocation(0, 0);
		backGround.setSize(1000, 700);
		backGround.setBackground(new Color(238, 238, 238));
		frame.getContentPane().add(backGround);
		
	}


	
	/**
	* Method responsible for showing the cards after a hold or deal press been pressed
	*/
	private void showCards(ResultWithHand res){
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
            	int currentCredit=5;
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
						
						try {
							PrintStatistics(game.statistics());
						} catch (InvalidPlayException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try{
							if((currentCredit=game.credit().getCredits())<5){
								initMessageError.setVisible(true);
								initMessageError.setText("Max value you can bet is "+String.valueOf(currentCredit));
								if((currentCredit=game.credit().getCredits())==0){
									GameNotVisible();
									message.setFont(new Font("URW Bookman L", Font.BOLD, 60));
									message.setBounds(112, 300, 773, 50);
									message.setText("GAME OVER");
								}
							}
						}catch (InvalidPlayException e1){
							
						}
					}catch(IndexOutOfBoundsException err){
						btnDeal.setEnabled(false);
						btnHold.setEnabled(true);
						btnAdvice.setEnabled(true);
						for(int x=0; x<5;x++){
							cards[x].setEnabled(true);
						}
					}
					
					try {
						credit.setText(game.credit().toString());
					} catch (InvalidPlayException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}else{	
	            	if(holdCards[timerAux]==false){
	            		cards[timerAux].setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/"+h.getCardN(timerAux)+".png")));
	            		cards[timerAux].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/"+h.getCardN(timerAux)+"on.png")));
	            		cards[timerAux].setBounds(cardsXpos[timerAux], 290, 145, 216);
	            		discard[timerAux].setVisible(false);
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
	
	/**
	* Method responsible for put a certain card up 
	*/
	private void upCards(int index){
		int upCrad= 50;
		cards[index].setBounds(cardsXpos[index],  290 - upCrad, 145, 216);
		holdCards[index] = false;
		discard[index].setVisible(true);
	}
	
	/**
	* Method responsible for put a certain card down 
	*/
	private void downCards(int index){
		cards[index].setBounds(cardsXpos[index],  290, 145, 216);
		holdCards[index] = true;
		discard[index].setVisible(false);
	}
	
	/**
	* Method responsible for expose the cards as they should be to do the best move 
	*/
	private void adviceUp(){
		for(int i=0; i<5; i++){
			if(holdCards[i]){
				downCards(i);
			}else{
				upCards(i);
			}
		}
	}

	/**
	* Method responsible for actualize the statistics
	*/
	private void PrintStatistics(Result res){
		statistics.setText(""+res);
	}
	
	/**
	* Method that allow the main game to become visible
	*/
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
	
	/**
	* Method that allow the main game to become visible
	*/
	private void GameNotVisible(){
		
		for(int i=0; i<5; i++){
			chips[i].setVisible(false);
			cards[i].setVisible(false);
		}
		statistics.setVisible(false);
		btnAdvice.setVisible(false);
		btnHold.setVisible(false);
		btnDeal.setVisible(false);
		credit.setVisible(false);
		initMessageError.setVisible(false);
		
		
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
		InitializeDiscard();
		InitializeCards(); 
		
		InitializeChips();
		InitializeCreditMessage();
		InitializeButtonDeal();
		InitializeButtonHold();
		InitializeButtonAdvice();	
		InitializeStatistics();
		InitializeTable();
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GuiPlayer jogo = new GuiPlayer();
		jogo.Play();
	}
}
