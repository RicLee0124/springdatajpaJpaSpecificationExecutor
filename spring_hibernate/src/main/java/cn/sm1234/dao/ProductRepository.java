package cn.sm1234.dao;

import cn.sm1234.domain.Product;

public interface ProductRepository {

	public Product queryById(Integer id);
}
