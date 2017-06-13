package test.lx.mybatis.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.lx.mybatis.po.Items;
import test.lx.mybatis.po.ItemsExample;

/**
 * 切记：
 * 不管是查询还是更新，只要涉及到多条记录 不带withBlobs后缀的方法 默认都不会对大文本字段进行操作
 * 接口方法名中包含Selective的 表示有选择性的进行操作(只查询、更新、添加为参数复制过的属性对应的字段)
 * 接口方法名中包含Example的 表示可以带有条件筛选
 * 
 * @author liuxun
 *
 */
public class ItemsMapperTest {
	private ApplicationContext applicationContext;

	private ItemsMapper itemsMapper;

	@Before
	public void setUp() throws Exception {
		// 创建Spring容器
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");
	}

	// 自定义条件筛选数量查询
	@Test
	public void testCountByExample() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andPriceBetween(300.f, 10000.f);
		int count = itemsMapper.countByExample(itemsExample);
		System.out.println(count);
	}

	// 自定义条件删除
	@Test
	public void testDeleteByExample() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameLike("%冰箱%"); // 使用like相关属性时 参数外要包括 %%
		itemsMapper.deleteByExample(itemsExample);
	}

	// 根据主键进行删除
	@Test
	public void testDeleteByPrimaryKey() {
		itemsMapper.deleteByPrimaryKey(6);
	}

	// 表示插入全部字段，若某字段对应属性没有复制，默认插为NULL(自增主键例外)
	@Test
	public void testInsert() {
		Items items = new Items();
		items.setName("电视机");
		items.setPrice(3000.f);
		items.setDetail("乐视高清");

		itemsMapper.insert(items);
	}

	// 选择性插入，插入记录时只对赋值属性对应的字段进行插入
	@Test
	public void testInsertSelective() {
		Items items = new Items();
		items.setName("电冰箱");
		items.setPrice(2500.f);
		items.setDetail("三年包换");

		itemsMapper.insertSelective(items);
	}

	// 自定义条件查询多条记录，包含大文本字段
	@Test
	public void testSelectByExampleWithBLOBs() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameIsNotNull();
		List<Items> list = itemsMapper.selectByExampleWithBLOBs(itemsExample);
		for (Items items : list) {
			System.out.println(items.getDetail());
		}
	}

	// 自定义条件查询多条记录，不对大文本字段进行查询
	@Test
	public void testSelectByExample() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameIsNotNull();
		List<Items> list = itemsMapper.selectByExample(itemsExample);
		for (Items items : list) {
			System.out.println(items.getDetail()); // 大文本字段值为null
		}

	}

	// 按照主键值进行查询单条记录
	@Test
	public void testSelectByPrimaryKey() {
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items.getDetail());
	}

	// 自定义条件更新(为POJO赋值过的属性对应的字段)，不对PO类中的大文本字段进行更新
	// 如果PO类对象中的一些属性未赋值，不做任何改变，只更新赋值过的属性 即有选择性的更新
	@Test
	public void testUpdateByExampleSelective() {
		// 此处方法名后缀Selective：表示只对参数record中设置过的属性对应的字段进行更新 没有设置过的不做任何改变
        // record:封装更新后的结果值 
		// example: 封装 筛选更新记录条件
		ItemsExample example = new ItemsExample();
		ItemsExample.Criteria  criteria = example.createCriteria();
		Items record = new Items();
		record.setPrice(2500.f);
		criteria.andNameLike("%机%");
		itemsMapper.updateByExampleSelective(record, example);
	}

	// 根据外键强制全部更新数据(没有赋值的映射为NULL) 包含大文本字段
	@Test
	public void testUpdateByExampleWithBLOBs() {

	}

	// 根据外键强制全部更新数据(没有赋值的映射为NULL) 不包含大文本字段
	@Test
	public void testUpdateByExample() {

	}

	// 根据外键有选择性的更新数据 不包含大文本字段 必须为参数items设置主键值
	@Test
	public void testUpdateByPrimaryKeySelective() {

	}
	// 根据外键全部更新数据(没有赋值的映射为NULL) 包含大文本字段 必须为参数items设置主键值
	@Test
	public void testUpdateByPrimaryKeyWithBLOBs() {

	}
	// 根据外键有全部更新数据(没有赋值的映射为NULL) 不包含大文本字段 必须为参数items设置主键值
	@Test
	public void testUpdateByPrimaryKey() {

	}

}
