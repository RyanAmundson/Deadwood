package model;

import java.io.*;
import java.lang.Object;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.util.*;

public class Deck {

	private static SceneCard[] cards = new SceneCard[40];
	private static int cardPointer = 40;
	private static Deck instance;

	public Deck(SceneCard[] cards) {
		this.cards = cards;
		this.instance = this;

	}

	public Deck(Element cardxml) {
		for (int i = 0; i < cardxml.getElementsByTagName("card").getLength(); i++) {
			Element c = (Element) cardxml.getElementsByTagName("card").item(i);
			Role[] rL = new Role[c.getElementsByTagName("part").getLength()];
			String imageNum = c.getAttributes().item(1).getTextContent();
			String[] partNames = new String[3];
			String[] partInfo = new String[12];
			int q = 0;
			for (int j = 0; j < c.getElementsByTagName("part").getLength(); j++) {
				partNames[j] = c.getElementsByTagName("part").item(j).getAttributes().getNamedItem("name").getTextContent();

				Element area = (Element)((Element)c.getElementsByTagName("part").item(j)).getElementsByTagName("area").item(0);
				
				partInfo[q] = area.getAttributes().getNamedItem("x").getTextContent();
				partInfo[q+1] = area.getAttributes().getNamedItem("y").getTextContent();
				partInfo[q+2] = area.getAttributes().getNamedItem("h").getTextContent();
				partInfo[q+3] = area.getAttributes().getNamedItem("w").getTextContent();
				q = q + 4;
				
				rL[j] = new Role("actor", c.getElementsByTagName("part")
						.item(j).getAttributes().getNamedItem("name")
						.getTextContent(), Integer.parseInt(c
						.getElementsByTagName("part").item(j).getAttributes()
						.getNamedItem("level").getTextContent()));
			}

			cards[i] = new SceneCard(c.getAttributes().getNamedItem("name")
					.getTextContent(), Integer.parseInt(c.getAttributes()
					.getNamedItem("budget").getTextContent()), rL, imageNum,partInfo, partNames);
		}

	}

	public void shuffle() {
		Random rnd = new Random();
		for (int i = cardPointer; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			SceneCard a = cards[index];
			cards[index] = cards[i];
			cards[i] = a;
		}

	}

	public static SceneCard draw() {

		cardPointer--;
		return cards[cardPointer];
	}
	public static Deck getInstance(){
		return instance;
	}

	// public SceneCard drawCard(){
	// return cards[--cardPointer];
	// }

}
