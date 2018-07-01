package cn.sm1234.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sm1234.domain.Product;

public interface IProductDao extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>,ProductRepository{

}
