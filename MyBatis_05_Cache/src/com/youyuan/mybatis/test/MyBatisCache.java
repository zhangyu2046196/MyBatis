package com.youyuan.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.youyuan.mybatis.dao.DepartmentMapper;
import com.youyuan.mybatis.dao.EmployeeMapper;
import com.youyuan.mybatis.domain.Department;
import com.youyuan.mybatis.domain.Employee;

/**
 * mybatis的cache测试类
 * @author zhangyu
 * @date 2018-3-18 下午9:38:27
 */
public class MyBatisCache {
	
	/**
	 * mybatis中的缓存分为两类
	 * 1、一级缓存:一级缓存也叫本地缓存,作用范围是sqlSession会话,一级缓存默认开启
	 * 		一级缓存失效的几种情况:
	 * 			1)两次查询使用的sqlSession不同(因为一级缓存作用于就是一个会话sqlSession )
	 * 			2)两次查询的查询条件不一致
	 * 			3)在两次查询之间进行了增删改的操作
	 * 			4)手动调用清除缓存操作(sqlSession.clearCache())
	 * 2、二级缓存:二级缓存作用范围在namespace , 二级缓存需要配置手动开启
	 * 			原理:
	 * 				二级缓存查询的数据首先放到一级缓存中,只有会话关闭(sqlSession)才会把查询结果放到二级缓存中
	 * 				sqlSession=======EmployeeMapper======>Employee
	 * 							 DepartmentMapper=======>Department
	 * 			使用步骤:
	 * 				1、需要在全局配置文件(mybatis-config.xml)中开启使用二级缓存配置(默认是开启的)<setting name="cacheEnabled" value="true"/>
	 * 				2、需要在mapper.xml(sql映射文件中使用cach标签来表示使用二级缓存)<cache></cache>
	 * 				3、需要是pojo(javabean)实现序列化接口(Serializable)
	 * 
	 * 3、缓存的属性
	 * 		1)<setting name="cacheEnabled" value="true"/> 全局配置文件中开启缓存,只影响二级缓存
	 * 		2)selct标签(查询标签)中的useCache(true:使用缓存  false:不使用缓存),只影响二级缓存
	 * 		3)sqlSession.clearCache方法清除缓存只会影响一级缓存
	 * 		4)insert、delete、update、select(增删改查标签)中的flushCache清除缓存标签(true:清除  false:不清除),一级缓存和二级缓存都会清除
	 * 
	 * 4、mybatis整合ehcache步骤
	 * 		1)引入ehcache的jar包
	 * 		2)引入mybatis与ehcache的整合包
	 * 		3)在mapper(sql映射文件)文件中通过cache标签引入ehcache
	 * 			<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
	 * 			
	 * 			<cache-ref namespace="com.youyuan.mybatis.dao.EmployeeMapper"/>(可以引用其它命名空间的cache,例如在DepartmentMapper中引入EmployeeMapper中的文件)
	 * 		
	 */

	/**
	 * 测试mybatis的一级缓存
	 */
	@Test
	public void testCache(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper empMapper=openSession.getMapper(EmployeeMapper.class);
			Employee emp1=empMapper.getEmployeeById(1L);
			System.out.println("emp1"+emp1);
			openSession.clearCache();
			Employee emp2=empMapper.getEmployeeById(1L);
			System.out.println("emp2"+emp2);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * mybatis中的二级缓存
	 */
	@Test
	public void testSecondLeveCache(){
		SqlSession openSession1=getSqlSession();
		SqlSession openSession2=getSqlSession();
		try{
			EmployeeMapper empMapper1=openSession1.getMapper(EmployeeMapper.class);
			EmployeeMapper empMapper2=openSession2.getMapper(EmployeeMapper.class);
			Employee emp1=empMapper1.getEmployeeById(1L);
			System.out.println("emp1"+emp1);
			openSession1.close();//只有关闭会话(sqlSession)mybatis才会把查询结果从一级缓存放到二级缓存
			Employee emp2=empMapper2.getEmployeeById(1L);
			System.out.println("emp2"+emp2);
			openSession2.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * mybatis中的二级缓存
	 */
	@Test
	public void testSecondLevelCacheDepartment(){
		SqlSession sqlSession1=getSqlSession();
		SqlSession sqlSession2=getSqlSession();
		try{
			DepartmentMapper depMapper1=sqlSession1.getMapper(DepartmentMapper.class);
			DepartmentMapper depMapper2=sqlSession2.getMapper(DepartmentMapper.class);
			Department dep1=depMapper1.getDepartMentById(1L);
			System.out.println("dep1"+dep1);
			sqlSession1.close();
			Department dep2=depMapper2.getDepartMentById(1L);
			System.out.println("dep2"+dep2);
			sqlSession2.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取非自动提交事务的sqlSession
	 * @return
	 */
	public SqlSession getSqlSession(){
		try{
			String resource="mybatis-config.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			if(sqlSessionFactory!=null){
				return sqlSessionFactory.openSession();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取自动提交事务的sqlSession
	 * @return
	 */
	public SqlSession getAutoCommitSqlSession(){
		try{
			String resource="mybatis-config.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			if(sqlSessionFactory!=null){
				return sqlSessionFactory.openSession(true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
