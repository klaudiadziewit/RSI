/**
 * This is a simple support class that allows to keep aggregated some
 * pieces of information related to a Book
**/
package bookTrading.buyer;

import java.util.Date;

class BookInfo {
  String title;
  int maxPrice;
  int numberOfPages;
  Date deadline;

  public int getNumberOfPages() {return numberOfPages;}
  public void setNumberOfPages(int number) {this.numberOfPages = number;}
  
  public String getTitle() { return title; }
  public void setTitle(String title) {this.title = title;}

  public int getMaxPrice() { return maxPrice; }
  public void setMaxPrice(int maxPrice) {this.maxPrice = maxPrice;}

  public Date getDeadline() { return deadline;}
  public void setDeadline(Date deadline) {this.deadline = deadline;}
}