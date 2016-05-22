package model;

import java.io.*;
import java.lang.Object;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import java.util.*;

public class CastingOffice extends Room {

  private static int[] creditForLevel = new int[]{5,10,15,20,25};
  private static int[] cashForLevel = new int[]{4,10,18,28,40};

  public CastingOffice(String name, LinkedList<String> neighborList){
    super(name, neighborList);
  }
  
  
  public static int getCreditRequirement(int level){
    return creditForLevel[level];
  }
  public static int getCashRequirement(int level){
    return cashForLevel[level];
  }




}
