package model;

import java.io.*;
import java.lang.Object;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

public class Trailer extends Room {

	public Trailer(String name, LinkedList<String> neighborList) {
		super(name, neighborList);
	}

}
