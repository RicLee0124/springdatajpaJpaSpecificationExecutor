package cn.sm1234.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.sm1234.dao.IProductDao;
import cn.sm1234.domain.Product;

/**
 * 演示JpaRepository接口的使用
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1 {

	@Resource
	private IProductDao productDao;
	
	
	@Test
	public void test1(){
		List<Product> list = productDao.findAll();
		for (Product product : list) {
			System.out.println(product);
		}
	}

}
