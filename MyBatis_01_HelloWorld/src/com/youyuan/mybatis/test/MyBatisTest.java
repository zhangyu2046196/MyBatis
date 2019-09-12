package com.youyuan.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.youyuan.mybatis.dao.EmployeeMapper;
import com.youyuan.mybatis.domain.Employee;

/**
 * hello word 类
 * 1、接口式编程
 * 2、sqlsession不是线程安全的，所以每次用都需要重新获取
 * 3、两个配置文件
 * 	1)全局配置文件，包含数据源运行环境信息
 * 	2)sql映射文件   namespace为接口全路径   id为接口中方法名
 * @author zhangyu
 * @date 2018-3-8 下午10:34:14
 */
public class MyBatisTest {
	
	/**
	 * 1、根据全局配置文件mybatis-config.xml配置文件创建sqlsessionfactory工厂
	 * 	   全局配置文件包含数据源运行信息
	 * 2、创建sql映射文件，映射文件包含javabean和sql语句
	 * 3、将sql映射文件注册到全局配置文件中
	 * 4、写代码 
	 * 	     1)根据全局配置文件创建sqlsessionfactory工厂
	 *       2)根据sqlsession工厂创建sqlsession对象
	 *       3)进行增删改查操作，关闭sqlsession对象实体
	 * @throws IOException 
	 */
	@Test
	public void test() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//第一个参数命名空间+映射sql的id
		//第二个参数  参数实体
		SqlSession opensession = sqlSessionFactory.openSession();
		try {
			Employee employee=opensession.selectOne("com.youyuan.mybatis.employeeMapper.selectEmp", 1);
			System.out.println("employee:"+employee.toString());
		} finally {
			opensession.close();
		}
	}
	
	/**
	 * 接口式编程
	 */
	@Test
	public void test1(){
		SqlSession openSession=getSqlSession(); //获取sqlsession对象
		try{
			//获取接口实例对象     参数为接口class
			EmployeeMapper employeeMapper= openSession.getMapper(EmployeeMapper.class);
			//调用接口方法，传递参数获取查询javabean
			Employee employee=employeeMapper.getEmployeeById(2L);
			System.out.println("employee:"+employee.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	
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
}
