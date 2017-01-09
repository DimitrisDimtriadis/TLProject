package org.tl2project.model;

import java.math.BigDecimal;

public class Quest {
  
    private BigDecimal latitude;
    
    private BigDecimal longitude; 
     
    private Riddle riddle;
    
    public Quest(){}
  
    public Quest(BigDecimal latitude, BigDecimal longitude, Riddle riddle) {
      super();
      this.latitude = latitude;
      this.longitude = longitude;
      this.riddle = riddle;
    }
  
    public BigDecimal getLongitude() {
      return longitude;
    }
  
    public void setLongitude(BigDecimal longitude) {
      this.longitude = longitude;
    }
  
    public BigDecimal getLatitude() {
      return latitude;
    }
  
    public void setLatitude(BigDecimal latitude) {
      this.latitude = latitude;
    }
  
    public Riddle getRiddle() {
      return riddle;
    }
  
    public void setRiddle(Riddle riddle) {
      this.riddle = riddle;
    }
  
}
