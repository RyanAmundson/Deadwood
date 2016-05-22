package model;


public class Role{

  private String type;
  private String name;
  private int level;
  private int cashRewardSuccess;
  private int cashRewardFailure;
  private boolean occupied = false;
  private int creditRewardSuccess;
  //private int sceneWrapPayout;




  public Role(String type,String name,int level){
    this.name = name;
    this.level = level;
    this.type = type;
    if(type.equals("actor")){
      this.cashRewardSuccess = 0;
      this.cashRewardFailure = 0;
      this.creditRewardSuccess = 2;
    }
    else if(type.equals("extra")){
      this.cashRewardSuccess = 1;
      this.cashRewardFailure = 1;
      this.creditRewardSuccess = 1;
    }
  }

  public boolean rehearse(){
    return true;

  }
  public boolean act(){
    return true;
  }

  public int[] getRewardSuccess(){
    int[] reward = new int[2];
    reward[0] = creditRewardSuccess;
    reward[1] = cashRewardSuccess;
    System.out.println("rewards: "+creditRewardSuccess+" "+reward[1]);
    return reward;
  }
  public int[] getRewardFailure(){
    int[] reward = new int[1];
    reward[0] = cashRewardFailure;
    return reward;
  }
  public String getName(){
    return name;
  }
  public int getRank(){
    return level;
  }

  public String getType(){
    return type;
  }
  public void setOccupied(){
    occupied = true;
  }
  public boolean isOccupied(){
    return occupied;
  }
  
}
