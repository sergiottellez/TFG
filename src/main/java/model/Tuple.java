/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sergiottellez
 */
public class Tuple<X, Y> { 
  public  X x; 
  public  Y y; 

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  } 

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }
} 