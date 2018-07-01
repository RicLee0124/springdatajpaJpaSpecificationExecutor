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
 * ��ʾJpaSpecificationExecutor�ӿڵ�ʹ��
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo2 {

	@Resource
	private IProductDao productDao;
	
	/**
	 * ������ѯ - һ�������Ĳ�ѯ
	 */
	@Test
	public void test1(){
		Specification<Product> spec = new Specification<Product>() {

			//���󣺲�ѯ��Ʒ�����ơ�С���ֻ���
			/**
			 * @param root ��ѯ�ĸ���������ָ����ѯ������
			 * @param query ʵ�ֻ����Ĳ�ѯ��һ�㲻ʹ��
			 * @param cb ���ڹ����ѯ������ 
			 * @return Predicate  ���ղ�ѯ������
			 */
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pre = null;
				
				pre = cb.equal(root.get("name").as(String.class), "С���ֻ�");
				
				return pre;
			}
			
		};
		List<Product> list = productDao.findAll(spec);
		for (Product product : list) {
			System.out.println(product);
		}
	}
	
	/**
	 * ������ѯ -- ���������ѯ
	 */
	@Test
	public void test2(){
		Specification<Product> spec = new Specification<Product>() {

			//���󣺲�ѯ��Ʒ�����ư�����С�ס�,�Ҽ۸���1000Ԫ���ϵ���Ʒ
			/**
			 * @param root ��ѯ�ĸ���������ָ����ѯ������
			 * @param query ʵ�ֻ����Ĳ�ѯ��һ�㲻ʹ��
			 * @param cb ���ڹ����ѯ������ 
			 * @return Predicate  ���ղ�ѯ������
			 */
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> preList = new ArrayList<Predicate>();
				
				//��Ʒ�����ư�����С�ס�
				preList.add( cb.like(root.get("name").as(String.class), "%С��%") );
				
				//�۸���1000Ԫ���ϵ���Ʒ
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
	 * ͬʱ���з�ҳ��������ѯ
	 */
	@Test
	public void test3(){
		//����
		Specification<Product> spec = new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pre = null;
				
				pre = cb.like(root.get("name").as(String.class), "%�ֻ�%");
				
				return pre;
			}
			
		};
		
		
		//��ҳ
		Pageable pageable = new PageRequest(0,3);
		
		
		Page<Product> pageData = productDao.findAll(spec, pageable);
		
		
		List<Product> list = pageData.getContent();
		for (Product product : list) {
			System.out.println(product);
		}
		
	}
}
