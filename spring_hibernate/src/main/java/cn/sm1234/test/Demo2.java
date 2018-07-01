package cn.sm1234.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sm1234.dao.IProductDao;
import cn.sm1234.domain.Product;

/**
 * 演示JpaSpecificationExecutor接口的使用
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo2 {

	@Resource
	private IProductDao productDao;
	
	/**
	 * 条件查询 - 一个条件的查询
	 */
	@Test
	public void test1(){
		Specification<Product> spec = new Specification<Product>() {

			//需求：查询商品的名称“小米手机”
			/**
			 * @param root 查询的根对象，用于指定查询的属性
			 * @param query 实现基本的查询，一般不使用
			 * @param cb 用于构造查询的条件 
			 * @return Predicate  最终查询的条件
			 */
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pre = null;
				
				pre = cb.equal(root.get("name").as(String.class), "小米手机");
				
				return pre;
			}
			
		};
		List<Product> list = productDao.findAll(spec);
		for (Product product : list) {
			System.out.println(product);
		}
	}
	
	/**
	 * 条件查询 -- 多个条件查询
	 */
	@Test
	public void test2(){
		Specification<Product> spec = new Specification<Product>() {

			//需求：查询商品的名称包含“小米”,且价格在1000元以上的商品
			/**
			 * @param root 查询的根对象，用于指定查询的属性
			 * @param query 实现基本的查询，一般不使用
			 * @param cb 用于构造查询的条件 
			 * @return Predicate  最终查询的条件
			 */
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> preList = new ArrayList<Predicate>();
				
				//商品的名称包含“小米”
				preList.add( cb.like(root.get("name").as(String.class), "%小米%") );
				
				//价格在1000元以上的商品
				preList.add(  cb.greaterThan(root.get("price").as(Double.class), 1000D) );
				
				
				Predicate[] preArray = new Predicate[preList.size()];
				return cb.and(preList.toArray(preArray));
			}
			
		};
		List<Product> list = productDao.findAll(spec);
		for (Product product : list) {
			System.out.println(product);
		}
	}
	
	
	/**
	 * 同时进行分页和条件查询
	 */
	@Test
	public void test3(){
		//条件
		Specification<Product> spec = new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre = null;
				
				pre = cb.like(root.get("name").as(String.class), "%手机%");
				
				return pre;
			}
			
		};
		
		
		//分页
		Pageable pageable = new PageRequest(0,3);
		
		
		Page<Product> pageData = productDao.findAll(spec, pageable);
		
		
		List<Product> list = pageData.getContent();
		for (Product product : list) {
			System.out.println(product);
		}
		
	}
}
