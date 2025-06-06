package model;

import java.sql.Date;
import java.util.Objects;

public class Order {
	private String orderId;
	private Customer customer;
	private String deliveryAddress;
	private String orderState;
	private String paymentMethod;
	// Thiếu or đủ Tổng Tiền
	private Date createDate;
	private Date deliveryDate;
	
	public Order() {
		super();
	}

	public Order(String orderId, Customer customer, String deliveryAddress, String orderState, String paymentMethod,
			Date createDate, Date deliveryDate) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.orderState = orderState;
		this.paymentMethod = paymentMethod;
		this.createDate = createDate;
		this.deliveryDate = deliveryDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId);
	}
	
	
	
	
	
}
