package cn.sm1234.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cn.sm1234.domain.Product;

public class IProductDaoImpl implements ProductRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Product queryById(Integer id) {
		System.out.println("执行了自定义Repository");
		return em.find(Product.class, id);
	}

}
