package players;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;


import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import cards.HandCards;
import videopoker.Result;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import java.awt.Font;
import java.awt.Cursor;

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
	

	private JLabel credit;
	
	/**
	 * Create the application.
	 */
	public GuiPlayer() {
		super(1000);
		initialize();
		message = new JLabel("");
		message.setBorder(null);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.WHITE);
		message.setFont(new Font("URW Bookman L", Font.BOLD, 35));
		message.setBounds(112, 430, 773, 50);
		frame.getContentPane().add(message);		
		JLabel backGround = new JLabel(new ImageIcon(GuiPlayer.class.getResource("/images/backGround.png")));
		backGround.setSize(1000, 700);
		backGround.setBackground(new Color(238, 238, 238));
		frame.getContentPane().add(backGround);
		
		
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
	
	private void showCards(Result res){
		HandCards h = res.getHand();
		for(int i=0; i<5;i++){
			if(holdCards[i]==false){
				cards[i].setIcon(new ImageIcon(new ImageIcon("src/images/cardBack.png").getImage()));
			}
		}
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if(holdCards[timerAux]==false){
            		cards[timerAux].setIcon(new ImageIcon(new ImageIcon("src/images/"+h.getCardN(timerAux)+".png").getImage()));
            		cards[timerAux].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/"+h.getCardN(timerAux)+"on.png")));
            		cards[timerAux].setBounds(cardsXpos[timerAux], 180, 145, 216);
            	}else{
            		downCards(timerAux);
            	}
            	timerAux++;
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
					message.setVisible(true);
				}catch(IndexOutOfBoundsException err){}
				credit.setText(game.credit().toString());				
				}	
            }
		};
		timer = new Timer(250, taskPerformer);
		timer.start();
	}
	
	private void upCards(int index){
		int upCrad= 50;
		cards[index].setBounds(cardsXpos[index],  180 - upCrad, 145, 216);
		holdCards[index] = false;
	}
	
	private void downCards(int index){
		cards[index].setBounds(cardsXpos[index],  180, 145, 216);
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
	
	private void InitializeCards(){
		for(int i=0; i<5;i++){
			int a=i;
			cards[i] = new JButton("");
			//card0.setIcon(new ImageIcon(GuiPlayerv2.class.getResource("/images/cardBack.png")));
			
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
			cards[i].setBounds(cardsXpos[i], 180, 145, 216);
			cards[i].setOpaque(false);
			cards[i].setContentAreaFilled(false);
			cards[i].setBorderPainted(false);
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
			chips[i].setIcon(new ImageIcon(GuiPlayer.class.getResource("../images/chips.png")));
			chips[i].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("src/images/chipson.png")));
			chips[i].setBounds(500+(80*i), 60, 70, 50);
			frame.getContentPane().add(chips[i]);
			chips[i].addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		int j;
	        		if(chipsAux[a]==false){
		        		for(j=0; j<=a;j++){
		        			chips[j].setIcon(new ImageIcon(new ImageIcon("src/images/chips.png").getImage()));
		        			chips[j].setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("src/images/chipson.png")));
		        			chips[j].setBounds(500+(80*j), 60, 70, 50);
		        			chipsAux[j]=true;
		        		}
		        		
		        		for(int aux=j; aux<=4; aux++){
		        			chips[aux].setIcon(new ImageIcon(new ImageIcon("src/images/chipsBlack.png").getImage()));
		        			chips[aux].setRolloverIcon(null);
							chips[aux].setBounds(500+(80*aux), 60, 70, 50);
							chipsAux[aux]=false;
						}
	        		}else{
	        			for(j=a+1; j<5; j++){
		        			chips[j].setIcon(new ImageIcon(new ImageIcon("src/images/chipsBlack.png").getImage()));
		        			chips[j].setRolloverIcon(null);
							chips[j].setBounds(500+(80*j), 60, 70, 50);
							chipsAux[j]=false;
						}
	        		}
		        	betValue=a+1;
	        	}
	        });
		}
	}
	
	private void InitializeCreditMessage(){
		
		credit = new JLabel(game.credit().toString());
		credit.setHorizontalAlignment(SwingConstants.LEFT);
		credit.setForeground(Color.WHITE);
		credit.setFont(new Font("URW Bookman L", Font.BOLD, 25));
		credit.setBounds(112, 60, 400, 50);
		frame.getContentPane().add(credit);
		
	}
	
	private void InitializeButtons(){
		
		btnDeal = new JButton("");
		btnDeal.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonDOff.png")));
		btnDeal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnDeal.setBorder(null);
		btnDeal.setOpaque(false);
		btnDeal.setContentAreaFilled(false);
		btnDeal.setBorderPainted(false);
		btnDeal.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_deal.png")));
		btnDeal.setBounds(100, 530, 220, 60);
		frame.getContentPane().add(btnDeal);
		
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arrays.fill(holdCards,false);
				game.bet(betValue);
				Result res = game.deal();
				showCards(res);
				btnDeal.setEnabled(false);
				btnHold.setEnabled(true);
				btnAdvice.setEnabled(true);
				credit.setText(game.credit().toString());
				message.setVisible(false);
			}
		});
		
		btnHold = new JButton("");
		btnHold.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonHOff.png")));
		btnHold.setBorder(null);
		btnHold.setOpaque(false);
		btnHold.setContentAreaFilled(false);
		btnHold.setBorderPainted(false);
		btnHold.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_hold.png")));
		btnHold.setEnabled(false);
		
		btnHold.setBounds(380, 530, 220, 60);
		frame.getContentPane().add(btnHold);
		
		btnHold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Result res = game.hold(holdCards);
				showCards(res);
				btnDeal.setEnabled(true);
				btnHold.setEnabled(false);
				btnAdvice.setEnabled(false);


			}
		});
		
		btnAdvice = new JButton("");
		btnAdvice.setRolloverIcon(new ImageIcon(GuiPlayer.class.getResource("/images/buttonAOff.png")));
		btnAdvice.setBorder(null);
		btnAdvice.setIcon(new ImageIcon(GuiPlayer.class.getResource("/images/button_advice.png")));
		btnAdvice.setEnabled(false);
		btnAdvice.setOpaque(false);
		btnAdvice.setContentAreaFilled(false);
		btnAdvice.setBorderPainted(false);
		btnAdvice.setBounds(640, 530, 220, 60);
		frame.getContentPane().add(btnAdvice);
		
		btnAdvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result res = game.advice();
				holdCards = res.getHoldcards();
				adviceUp();

			}
		});	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Arrays.fill(chipsAux,true);	
		InitializeCards(); 
		InitializeChips();
		InitializeCreditMessage();
		InitializeButtons();	
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GuiPlayer jogo = new GuiPlayer();
		jogo.Play();
	}
}
