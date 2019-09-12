package com.youyuan.mybatis.test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.youyuan.mybatis.dao.EmployeeMapper;
import com.youyuan.mybatis.domain.Employee;
import com.youyuan.mybatis.domain.EmployeeExample;
import com.youyuan.mybatis.domain.EmployeeExample.Criteria;

/**
 * mybatis逆向工程(mbg)代码生成器测试(generator)
 * @author zhangyu
 * @date 2018-3-20 下午6:05:10
 */
public class MyBatisTest {
	
	/**
	 * 通过mbg配置文件(代码生成器配置文件),逆向生成配置的mybatis的javabean、sql映射文件、mapper文件
	 */
	@Test
	public void testMbgGenerator(){
		try{
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File configFile = new File("mbg.xml");//指定mbg文件
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * mybatis的mbg逆向工程generator(代码生成器),生成带条件的增删改查
	 */
	@Test
	public void testMbgMybatisGenerator(){
		SqlSession sqlSession=getSqlSession();
		try{
			EmployeeMapper empMapper=sqlSession.getMapper(EmployeeMapper.class);
			//将查询条件封装成实体对象,例如查询username包含'张'且gender是男的用户,或者email中包含youyuan的哟用户
			//select * from EMPLOYE where (USER_NAME like '"%张%"' and GENDER=1) or EMAIL like '"%youyuan%"'
			EmployeeExample example=new EmployeeExample();
			Criteria criteria=example.createCriteria();
			criteria.andUserNameLike("%张%");
			criteria.andGenderEqualTo("1");
	
			Criteria criteria2=example.createCriteria();
			criteria2.andEmailLike("%youyuan%");
			
			example.or(criteria2);
			List<Employee> empList=empMapper.selectByExample(example);
			for(Employee emp:empList){
				System.out.println(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 获取非自动提交事务的sqlSession
	 * @return
	 */
	public static SqlSession getSqlSession(){
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
	public static SqlSession getAutoSqlSession(){
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
