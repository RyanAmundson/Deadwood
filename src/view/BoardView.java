package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.awt.event.*;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.imageio.*;

import model.Board;
import model.Player;
import model.Role;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.Queue;

import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import controller.Controller;
import controller.Deadwood;
import controller.Game;
import controller.MyActionListener;






/* So here is my main view class... it is very scary so be prepared...
 * 
 * BoardView.java
 * 
 * 
 * This class is the board view Jlabel which sits in the mainframe created in Game.
 * it is responsible for updating all the swing elements
 * it receives resources from resources.java but also does some parsing that should be done in resources.java but I was too afraid to move to after I realized
 * the xml parsing part is surrounded partitioned with 2 lines of equal signs just so you can see where it starts and ends
 * 
 *  
 * 1.the first part of this file does alot of jlabel creation for the info panel as well as collection of resources
 * 
 * 2.the next section is the xml parsing as stated above
 * 
 * 3. toward the bottom I have all my methods that are very similar to those in the model but instead change the visual aspect not the logic
 * 
 * I ended up using quite a bit of poor coding standards for time but am planning on going back and improving on it later on as I really liked this project it was just very
 * challenging and time consuming
 * 
 *
 */




public class BoardView extends JPanel {

	private JLabel success;
	private JLabel day;
	private JLabel failure;
	private JButton endTurn;
	private JLabel background;
//	private JLabel infoPanel;
	private InfoPanel infoPanel;
	private JLabel location;
	private JLabel cash;
	private JLabel credits;
	private JLabel rehearseCount;
	private JLabel currentPlayerLabel;
	private JLabel rollBox;
	private JButton howToPlay;
	static BoardView instance;
	
	
	private Player currentPlayer;
	
	
	
	HashMap<String, JLabel> rollingDice = new HashMap<String, JLabel>();
	HashMap<String, ImageIcon> playerDiceImages = new HashMap<String, ImageIcon>();
	HashMap<String, JLabel> roleLabels = new HashMap<String, JLabel>();
	HashMap<String, JLabel> diceLabels = new HashMap<String, JLabel>();
	HashMap<String, JLabel> cardLabels = new HashMap<String, JLabel>();
	HashMap<String, JLabel> setLabels = new HashMap<String, JLabel>();
	HashMap<String, JLabel> takeLabels = new HashMap<String, JLabel>();
	List<JLabel> extras = new ArrayList<JLabel>();
	List<JLabel> rooms = new ArrayList<JLabel>();
	private String[] roomNames = new String[] {"trailer","office","main street","saloon","jail","train station","general store","bank","ranch","hotel","church","secret hideout"};
	private String[] colors = new String[] { "red", "blue", "green", "cyan","orange", "pink", "purple", "yellow" };
	ImageIcon[] diceFaces;
	public Timer rollTimer;
	Queue<Timer> animationQ = new Queue<Timer>();

	// private List<Listener> listeners;

	
	
	
	public BoardView() throws Exception {

		super(null);
		this.instance = this;
		
		for( String c : colors){
			playerDiceImages.put(c, Resources.getInstance().getNewDiceImage(c.charAt(0),1));
		}
		diceFaces = new ImageIcon[7];
		for( int i = 0; i < 6; i++){
			diceFaces[i] = Resources.getInstance().getNewDiceImage('r',i+1);
		}
		
		int diceBoxX = 1220;
		int diceBoxY= 425;
		int length = 40;
		for( int i = 1; i < 6; i++){
			rollingDice.put(Integer.toString(i),new JLabel(diceFaces[i]));
			JLabel temp = rollingDice.get(Integer.toString(i));
			temp.setBounds(diceBoxX,diceBoxY,length, length);
			temp.setVisible(false);
			add(temp);
			diceBoxX = diceBoxX + 40;
		}
		
//		rollBox = new JLabel();
//		rollBox.setBackground(Color.darkGray);
//		rollBox.setVisible(true);
//		rollBox.setOpaque(true);
//		//rollBox.setBorder(new Border(new BorderStroke()));
//		rollBox.setBounds(1200,375,250, 150);
//		add(rollBox);
//		

		
		JLabel cash4 = new JLabel();
		JLabel cash10 = new JLabel();
		JLabel cash18 = new JLabel();
		JLabel cash28 = new JLabel();
		JLabel cash40 = new JLabel();
		cash4.setBounds(96,540,37,23);
		cash10.setBounds(96,561,37,23);
		cash18.setBounds(96,584,37,23);
		cash28.setBounds(96,606,37,23);
		cash40.setBounds(96,629,37,23);
		
		cash4.setVisible(true);
		cash10.setVisible(true);
		cash18.setVisible(true);
		cash28.setVisible(true);
		cash40.setVisible(true);
		
		cash4.setName("cash4");
		cash10.setName("cash10");
		cash18.setName("cash18");
		cash28.setName("cash28");
		cash40.setName("cash40");
		
		
		cash4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		cash10.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		cash18.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		cash28.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		cash40.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		
		JLabel credit5 = new JLabel();
		JLabel credit10 = new JLabel();
		JLabel credit15 = new JLabel();
		JLabel credit20 = new JLabel();
		JLabel credit25 = new JLabel();
		
		credit5.setBounds(133,540,37,23);
		credit10.setBounds(133,561,37,23);
		credit15.setBounds(133,584,37,23);
		credit20.setBounds(133,606,37,23);
		credit25.setBounds(133,629,37,23);
		
		credit5.setVisible(true);
		credit10.setVisible(true);
		credit15.setVisible(true);
		credit20.setVisible(true);
		credit25.setVisible(true);
		
		credit5.setName("credit5");
		credit10.setName("credit10");
		credit15.setName("credit15");
		credit20.setName("credit20");
		credit25.setName("credit25");
		
		credit5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		credit10.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		credit15.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		credit20.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		credit25.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				test(e);
				Controller.mouseClick(e);
			}
		});
		add(cash4);
		add(cash10);
		add(cash18);
		add(cash28);
		add(cash40);
		add(credit5);
		add(credit10);
		add(credit15);
		add(credit20);
		add(credit25);
		
		
		Resources r = Resources.getInstance();

		setPreferredSize(new Dimension(1900, 1200));
		setDoubleBuffered(true);

		ImageIcon[] diceImage = r.getDice();
		ImageIcon shotCounter = r.getShotCounter();

		background = new JLabel(r.getBoard());
//		infoPanel = new JLabel();
		infoPanel = new InfoPanel();
		infoPanel.setOpaque(true);
//		infoPanel.setHorizontalAlignment(WIDTH / 2);
		infoPanel.setBackground(Color.GRAY);
		add(infoPanel);
//		infoPanel.setBounds(1200, 0, 250, 500);
//======================================================================================================		
//this is all just parsing and creation of Jlabels
//for the next 200 lines...
// =====================================================================================================
		FileInputStream fin = new FileInputStream("resources/cards.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = (Document) builder.parse(fin);
		doc.getDocumentElement().normalize();
		Element cardsE = doc.getDocumentElement();
		NodeList cardsNL = cardsE.getChildNodes();
		for (int i = 0; i < cardsNL.getLength(); ++i) {
			Node cardN = cardsNL.item(i);
			if (cardN.getNodeType() == Node.ELEMENT_NODE) {

			}
		}
		fin = new FileInputStream("resources/board.xml");
		doc = (Document) builder.parse(fin);
		doc.getDocumentElement().normalize();
		Element boardE = doc.getDocumentElement();
		NodeList boardNL = boardE.getChildNodes();
		for (int i = 0; i < boardNL.getLength(); ++i) {
			Node roomN = boardNL.item(i);
			if (roomN.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl = roomN.getChildNodes();
				Node areaN = nl.item(3);
				Element areaE = (Element) areaN;
				int x = Integer.parseInt(areaE.getAttribute("x"));
				int y = Integer.parseInt(areaE.getAttribute("y"));
				int h = Integer.parseInt(areaE.getAttribute("h"));
				int w = Integer.parseInt(areaE.getAttribute("w"));
				JLabel areaJP = new JLabel();
				
				if (roomN.getNodeName().equals("trailer")) {
					areaJP.setName("Trailer");
					areaJP.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							test(e);
							Controller.mouseClick(e);
						}
					});
					int diceY = y + 10;
					for (int j = 0; j < 8; j++) {
						int diceX = x + j * 17 - 4;
						String key = new String(areaJP.getName() + colors[j]);
						key = key.toLowerCase();
						JLabel dL = new JLabel(diceImage[j]);
						dL.setName(key);
						diceLabels.put(key,dL);

						diceLabels.get(key).setBounds(diceX, diceY, 40, 40);
						diceLabels.get(key).setVisible(true);
						add(diceLabels.get(key));

						diceLabels.get(key).addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								test(e);
								Controller.mouseClick(e);
							}
						});
					}
				} else if (roomN.getNodeName().equals("office")) {
					areaJP.setName("Office");
					areaJP.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							test(e);
							Controller.mouseClick(e);
						}
					});
					int diceY = y + 10;
					for (int j = 0; j < 8; j++) {
						int diceX = x + j * 17 - 4;
						String key = new String(areaJP.getName() + colors[j]);
						key = key.toLowerCase();
						JLabel dL = new JLabel(diceImage[j]);
						dL.setName(key);
						diceLabels.put(key,dL);

						diceLabels.get(key).setBounds(diceX, diceY, 40, 40);
						diceLabels.get(key).setVisible(false);
						add(diceLabels.get(key));

						diceLabels.get(key).addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								test(e);
								Controller.mouseClick(e);
							}
						});
					}
				} else {
					cardLabels.put(((Element) roomN).getAttribute("name"), areaJP);
					cardLabels.get(((Element) roomN).getAttribute("name")).setName(((Element) roomN).getAttribute("name"));
					cardLabels.get(((Element) roomN).getAttribute("name")).addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							test(e);
							Controller.mouseClick(e);
						}
					});
					int diceY = y + h + 5;
					for (int j = 0; j < 8; j++) {// other way with hashmap
						int diceX = x + j * 17 - 4;
						String key = new String(areaJP.getName() + colors[j]);
						key = key.toLowerCase();
						
						
						JLabel dL = new JLabel(diceImage[j]);
						dL.setName(key);
						diceLabels.put(key,dL);
						
						
						diceLabels.get(key).setBounds(diceX, diceY, 40, 40);
						diceLabels.get(key).setVisible(false);
						add(diceLabels.get(key));
						diceLabels.get(key).addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								test(e);
								Controller.mouseClick(e);
							}
						});
					}
				}
				areaJP.setBounds(x, y, w, h);
				areaJP.setVisible(true);
				// areaJP.setOpaque(true);
				add(areaJP);

				if (roomN.getNodeName().equals("set")) {
					Node takesN = nl.item(5);
					Element takesE = (Element) takesN;
					NodeList takesNL = takesE.getChildNodes();
					for (int j = 0; j < takesNL.getLength(); ++j) {
						Node takeN = takesNL.item(j);
						if (takeN.getNodeType() == Node.ELEMENT_NODE) {
							NodeList takeNL = takeN.getChildNodes();
							Node takeareaN = takeNL.item(0);
							Element takeareaE = (Element) takeareaN;
							int takex = Integer.parseInt(takeareaE
									.getAttribute("x"));
							int takey = Integer.parseInt(takeareaE
									.getAttribute("y"));
							int takeh = Integer.parseInt(takeareaE
									.getAttribute("h"));
							int takew = Integer.parseInt(takeareaE
									.getAttribute("w"));
							
							JLabel takeJP = new JLabel(shotCounter);
							
							takeJP.setName(areaJP.getName()
									+ ((Element) takeN).getAttribute("number"));
							takeLabels.put(takeJP.getName(),takeJP);
							takeJP.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									test(e);
									Controller.mouseClick(e);
								}
							});

							takeJP.setBounds(takex, takey, takew, takeh);
							takeJP.setVisible(true);

							add(takeJP);
						}
					}
					Node partsN = nl.item(7);
					Element partsE = (Element) partsN;
					NodeList partsNL = partsE.getChildNodes();
					for (int j = 0; j < partsNL.getLength(); ++j) {
						Node partN = partsNL.item(j);
						if (partN.getNodeType() == Node.ELEMENT_NODE) {
							Element partE = (Element) partN;
							NodeList partNL = partE
									.getElementsByTagName("area");
							;
							Node partareaN = partNL.item(0);
							Element partareaE = (Element) partareaN;
							int partx = Integer.parseInt(partareaE
									.getAttribute("x"));
							int party = Integer.parseInt(partareaE
									.getAttribute("y"));
							int parth = Integer.parseInt(partareaE
									.getAttribute("h"));
							int partw = Integer.parseInt(partareaE
									.getAttribute("w"));
							JLabel partJP = new JLabel();
							partJP.setName("role "
									+ ((Element) partN).getAttribute("name"));
							roleLabels.put(partJP.getName(),partJP);
							partJP.addMouseListener(new MyActionListener());
							
							
							partJP.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									test(e);
									Controller.mouseClick(e);
								}
							});

							partJP.setBounds(partx, party, partw, parth);
							partJP.setVisible(true);
							// partJP.setOpaque(true);
							add(partJP);
						}
					}
				}
			}
		}
		fin.close();

		add(background);
		background.setBounds(0, 0, 1200, 900);

		//setVisible(true);
		setFocusable(true);

	}
//=========================================================================================================
	public void checkTimerQueue(){
		Timer current = rollTimer;
		while(!animationQ.isEmpty() && !current.isRunning()){
			try {
				animationQ.dequeue().start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void test(MouseEvent e) {
//		infoPanel.setText(e.getComponent().getName());
	}
//
	public void showCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		infoPanel.showCurrentPlayer(currentPlayer);
//		currentPlayerLabel.setText("Current Turn: " + currentPlayer.getColor());
//		location.setText("Location: " + currentPlayer.getCurrentRoom().getName());
//		cash.setText("Cash: " + Integer.toString(currentPlayer.getCash()));
//		credits.setText("Credits: " + Integer.toString(currentPlayer.getCredits()));
//		rehearseCount.setText("rehearsal bonus: " +Integer.toString(currentPlayer.getRehearseCount()));
//		//if(Deadwood.currentDay == 5){
//		//	day.setText("GAME OVER!!!");
//		//}else{
//		day.setText("Day: " +Integer.toString(Game.currentDay).toUpperCase());
//		//}
//		
//		Color color = new Color(0);
//		
//		
//		switch(currentPlayer.getColor()){
//			case "red":
//				color = Color.RED;
//				break;
//			case "blue":
//				color = Color.BLUE;
//				break;
//			case "green":
//				color = Color.GREEN;
//				break;
//			case "yellow":
//				color = Color.YELLOW;
//				break;
//			case "cyan":
//				color = Color.CYAN;
//				break;
//			case "orange":
//				color = Color.ORANGE;
//				break;
//			case "purple":
//				color = Color.RED;
//				break;
//			case "pink":
//				color = Color.PINK;
//				break;
//			default:
//				break;
//				
//		}
//		infoPanel.setBackground(color);
//		currentPlayerLabel.setBackground(color);
//		
	}

	public void leftRoom(String location, String color) {
		String key = new String(location + color);
		key = key.toLowerCase();
		diceLabels.get(key).setVisible(false);
	}

	public void enteredRoom(String location, String color) {
		String key = new String(location + color);
		key = key.toLowerCase();
		diceLabels.get(key).setVisible(true);
	}
	public void moveToRole(String color, String room, String roleName){
		System.out.println("attempting to move to role " + roleName);
		roleLabels.get("role "+roleName).setIcon(playerDiceImages.get(color));
		diceLabels.get(new String(room + color).toLowerCase()).setVisible(false);
		
		
		
	}
	public void rankUp(String color,int level){
	
		ImageIcon die =Resources.getInstance().getNewDiceImage(color.charAt(0), level);
		playerDiceImages.put(color, die);
		
		for(int i =0; i < 12; i++){
			String key = new String(roomNames[i]+color);
			diceLabels.get(key).setIcon(die);
		}
		
	}
	public void shotSuccess(String name,int takesLeft){
			//this.success.setVisible(true);
		     // int delay = 1500; //milliseconds
		      //ActionListener taskPerformer = new ActionListener() {
		      //    public void actionPerformed(ActionEvent evt) {
		       // 	  success.setVisible(false);
		      //      }
		        //};
		       // javax.swing.Timer tick=new javax.swing.Timer(delay,taskPerformer);
		       // tick.setRepeats(false);
		        //tick.start();

	}
	public void shotFailure(){
		//this.failure.setVisible(true);
	      //int delay = 1500; //milliseconds
	      //ActionListener taskPerformer = new ActionListener() {
	      //    public void actionPerformed(ActionEvent evt) {
	      //  	  failure.setVisible(false);
	       //     }
	      //  };
	      //  javax.swing.Timer tick=new javax.swing.Timer(delay,taskPerformer);
	      //  tick.setRepeats(false);
	      //  tick.start();
	}
	public void drawCard(String name,String cardNum, String[] partInfo, String[] partNames){

		cardLabels.get(name).setIcon(Resources.getInstance().getCard(cardNum));
		cardLabels.get(name).setVisible(true);
		for(int i = 0; (i != 12) && !(partInfo[i] == null); i = i + 4){
			//System.out.println(partInfo[i]+ partInfo[i+1]+ partInfo[i+2]+ partInfo[i+3]);
			JLabel part = new JLabel();
			cardLabels.get(name).add(part);
			part.setBounds(Integer.parseInt(partInfo[i]),Integer.parseInt(partInfo[i+1]),Integer.parseInt(partInfo[i+2]),Integer.parseInt(partInfo[i+3]));
			part.setName("role "+partNames[i/4]);
			roleLabels.put(part.getName(),part);
			part.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					test(e);
					Controller.mouseClick(e);
				}
			});
			part.setVisible(true);
			//part.setOpaque(true);
		}
	}
	
	
	
	public void wrapScene(String roomName, String cardName, LinkedList<Player> playersInRoom, LinkedList<Role> rolesInRoom){
		for(Player p : playersInRoom){
			String pl = new String(roomName + p.getColor()).toLowerCase();
			diceLabels.get(pl).setVisible(true);
		}
		for(Role r : rolesInRoom){
			roleLabels.get("role "+r.getName()).setVisible(false);
		}
		cardLabels.get(roomName).setIcon(null);
		cardLabels.get(roomName).setOpaque(true);
		cardLabels.get(roomName).setBackground(Color.black);
		cardLabels.get(roomName).removeAll();
	}
	
	public void rollDice(int result, String roomName, int takesLeft){
		
		
		if(result > 0){
			String take = new String(roomName + (takesLeft+1));
			takeLabels.get(take).setVisible(false);	
			
			infoPanel.rollDice(currentPlayer);
		}

		
		
		
//		rollingDice.get("1").setVisible(true);
//		JLabel temp = rollingDice.get("1");
//		revalidate();
//		repaint();
//			
//		System.out.println("result: "+result);
//		
//		
//		rollTimer = new Timer( 200, new ActionListener(){
//			Random r = new Random();
//			int time = 0;
//			int currentImage = 0;
//
//			public void actionPerformed(ActionEvent evt) {
//				temp.setIcon(diceFaces[currentImage]);
//				currentImage = r.nextInt(5);
//				rollingDice.get("1").revalidate();
//				rollingDice.get("1").repaint();
//				time++;
//				//System.out.println(Thread.currentThread());
//				if (time > 30) {
//					System.out.println("timer ended!");
//					if(result > 0){
//						temp.setIcon(diceFaces[result - 1]);
//						String take = new String(roomName + (takesLeft+1));
//						takeLabels.get(take).setVisible(false);	
//					}
//					time = 0;
//
//					rollTimer.stop();
//					
//				}
//			}    
//		});
//		animationQ.enqueue(rollTimer);
//		System.out.println("running: "+rollTimer.isRunning());
//		checkTimerQueue();

	}
	
		
	public static BoardView getInstance() {
		return instance;
	}
	public static void setInstance(BoardView i){
		instance = i;
	}
	
}
