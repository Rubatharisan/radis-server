package domain;

import java.io.Serializable;
import java.sql.Date;


public class Order implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private int product;
	private int quantity;
	private String startDate;
	private Date finishDate;
	private int status;
	
	

	public Order(){
		product = 0;
		quantity = 0;
		startDate = null;
		finishDate = null;
	}
	
	public Order(int product, int quantity, String startDate, Date finishDate){
		this.product = product;
		this.quantity = quantity;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product2) {
		this.product = product2;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String orderDate) {
		this.startDate = orderDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
//	public String getProductName(int id){
//		ProductDAO dao = new ProductDAO();
//		String name = dao.getProductById(id).getName();
//		return name;
//	}
	
}
