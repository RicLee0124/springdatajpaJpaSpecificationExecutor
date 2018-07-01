package cn.sm1234.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_product")
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //利用mysql的auto_increment
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;//名称
	@Column(name="price")
	private Double price;//价格
	@Column(name="store")
	private Integer store;//库存
	@Column(name="brand")
	private String brand;//品牌
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", store=" + store + ", brand=" + brand + "]";
	}
	
}
