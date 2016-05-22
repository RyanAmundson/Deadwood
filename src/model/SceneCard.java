package model;

import java.io.*;
import java.lang.Object;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
public class SceneCard{

  private String name;
  private Role[] roles;
  //private String[] values;
  private int budget;
  private String rewardTier;
  private Room roomLocation = null;
  private String imageNum;
  private String[] partInfo;
  private String[] partNames;

  public SceneCard(String name,int budget,Role[] roles, String imageNum,String[] partInfo, String[] partNames){
    this.name = name;
    this.budget = budget;
    this.roles = roles;
    this.imageNum = imageNum;
    this.partInfo = partInfo;
    this.partNames = partNames;
  }

  public void reward(){

  }
  public String getName(){
    return name;
  }
  public int getBudget(){
    return budget;
  }
  public Role[] getRoles(){
    return roles;
  }
  public String getImageNum(){
	  return imageNum;
  }
  public String[] getPartInfo(){
	  return partInfo;
  }
  public String[] getPartNames(){
	  return partNames;
  }
  public Role getRoleByName(String name){
	  Role result = null;
	  for(Role r : roles){
		  if(r.getName().toLowerCase().equals(name)){
			  result = r;
		  }
	  }
	  return result;
  }







}
