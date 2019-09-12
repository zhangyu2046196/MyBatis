package com.youyuan.mybatis.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.youyuan.mybatis.dao.DepartmentMapper;
import com.youyuan.mybatis.dao.EmployeeDynamicSqlMapper;
import com.youyuan.mybatis.dao.EmployeeMapperPlus;
import com.youyuan.mybatis.domain.Department;
import com.youyuan.mybatis.domain.Employee;

/**
 * resultMap association collection返回结果集的测试
 * @author zhangyu
 * @date 2018-3-16 下午8:04:49
 */
public class MyBatisTest1 {
	
	/**
	 * 查询返回结果集collection
	 */
	@Test
	public void Test1(){
		SqlSession openSession=getSqlSession();
		try{
			DepartmentMapper depMapper=openSession.getMapper(DepartmentMapper.class);
			Department dep=depMapper.getDepartMentByIdPlus(1L);
			System.out.println("dep"+dep.getDepartmentName());
			System.out.println("emps"+dep.getEmps());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * collection使用select标签分步查询
	 */
	@Test
	public void Test2(){
		SqlSession openSession=getSqlSession();
		try{
			DepartmentMapper depMapper=openSession.getMapper(DepartmentMapper.class);
			Department dep=depMapper.getDepartMentStepById(1L);
			System.out.println("dep"+dep.getDepartmentName());
			System.out.println("emps"+dep.getEmps());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 根据部门id查询emp列表
	 */
	@Test
	public void Test3(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperPlus empMapperPlus=openSession.getMapper(EmployeeMapperPlus.class);
			List<Employee> empList=empMapperPlus.getEmpListByDepId(1L);
			System.out.println("empList"+empList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * discriminator鉴别器的测试,获取某列的值来改变resultMap返回值
	 */
	@Test
	public void Test5(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperPlus empMapperPlus=openSession.getMapper(EmployeeMapperPlus.class);
			Employee emp=empMapperPlus.getEmpByDisId(2L);
			System.out.println("emp"+emp.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 根据主键查询
	 */
	@Test
	public void Test6(){
		SqlSession openSession=getSqlSession();
		try{
			DepartmentMapper depMapper=openSession.getMapper(DepartmentMapper.class);
			Department dep=depMapper.getDepartMentById(1L);
			System.out.println("dep"+dep.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中if where标签
	 */
	@Test
	public void testDynamicSql(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeDynamicSqlMapper empDyMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> empList=empDyMapper.getEmpByDynamicSql(new Employee(0L, null, "1", null));
			System.out.println(empList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中trim截取字符串标签
	 */
	@Test
	public void testDynamicSqlTrim(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeDynamicSqlMapper empDySqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> empList=empDySqlMapper.getEmpByDynamicSqlTrim(new Employee(0L, "%张%", "1", null));
			System.out.println("empList"+empList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中choose分支查询标签
	 */
	@Test
	public void testDynamicSqlChoose(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeDynamicSqlMapper empDynamicSqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> empList=empDynamicSqlMapper.getEmpByDynamicSqlChoose(new Employee(0L, null, null, null));
			System.out.println(empList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中trim或set更新操作
	 */
	@Test
	public void testUpdateEmpByDynamicSqlTrimOrSet(){
		SqlSession openSession=getAutoCommitSqlSession();
		try{
			EmployeeDynamicSqlMapper empDynamicSqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			boolean isUpdate=empDynamicSqlMapper.updateEmpByDynamicSqlSetOrTrim(new Employee(11L, null, "0", null));
			System.out.println("isUpdate"+isUpdate);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中foreach查询操作
	 */
	@Test
	public void testGetEmpBydnmicSqlForeach(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeDynamicSqlMapper empDynamicSqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> empList=empDynamicSqlMapper.getEmpByDynamicSqlForeach(Arrays.asList(1L,2L,3L));
			System.out.println("empList"+empList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中foreach批量保存
	 */
	@Test
	public void testEmployeeDynamicSqlBatchSave(){
		SqlSession openSession=getAutoCommitSqlSession();
		try{
			EmployeeDynamicSqlMapper empDynamicSqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> emps=new ArrayList<Employee>();
			emps.add(new Employee(null, "啊勇", "1", "ayong@youyuan.com", new Department(1L)));
			emps.add(new Employee(null, "波哥", "0", "poge@youyuan.tech.com", new Department(2L)));
			boolean isInsert=empDynamicSqlMapper.addEmps(emps);
			System.out.println("isInsert"+isInsert);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 动态sql中bind绑定标签
	 */
	@Test
	public void testEmployeeDynamicSqlByBind(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeDynamicSqlMapper empDynamicSqlMapper=openSession.getMapper(EmployeeDynamicSqlMapper.class);
			List<Employee> emps=empDynamicSqlMapper.getEmpByBind(new Employee(null, "张", null, null));
			System.out.println("emps"+emps);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	
	/**
	 * 获取非自动提交事务sqlSession
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
	 * 获取自动提交事务sqlSeesion
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
