package com.youyuan.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.youyuan.mybatis.dao.EmployeeMapper;
import com.youyuan.mybatis.dao.EmployeeMapperAnnotion;
import com.youyuan.mybatis.dao.EmployeeMapperPlus;
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
	
	/**
	 * 注解接口方法
	 */
	public void Test2(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperAnnotion employeeMapper=openSession.getMapper(EmployeeMapperAnnotion.class);
			if(employeeMapper!=null){
				Employee employee=employeeMapper.getEmployeeById(1);
				System.out.println("employee"+employee.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 增删改查
	 * 接口的返回值可以自定义为Integer Long Boolean,只需指定返回值,不需做特殊处理,mybatis 会自动处理
	 * sqlSessionFactory.openSession()======>需要手动提交事务
	 * sqlSessionFactory.openSession(true)=====>自动提交事务
	 */
	@Test
	public void Test3(){
		SqlSession openSession=getSqlSession();//获取非自动提交sqlSession 需要手动提交事务
		try{
			//添加
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			//boolean addEmp=employeeMapper.addEmp(new Employee(null, "马六", "1", "myliu@123.com"));
			//System.out.println("增加"+addEmp);
			//Integer updateEmp=employeeMapper.update(new Employee(2L, "哈哈", "0", "wangwu@231.com"));
			//System.out.println("修改"+updateEmp);
			Integer deleteEmp=employeeMapper.deleteByGuid(4L);
			System.out.println("删除"+deleteEmp);
			
			openSession.commit();//手动提交
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 获取自动提交事务的sqlsession测试增删改查
	 */
	@Test
	public void Test5(){
		SqlSession openSession=getAutoCommitSqlSession();//获取自动提交事务sqlSession
		try{
			//添加
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			Employee emp=new Employee(null, "张三", "1", "myliu@123.com");
			boolean addEmp=employeeMapper.addEmp(emp);
			System.out.println("增加"+addEmp+" 主键"+emp.getGuid());
			//Integer updateEmp=employeeMapper.update(new Employee(2L, "wangwu", "0", "wangwu@231.com"));
			//System.out.println("修改"+updateEmp);
			//Integer deleteEmp=employeeMapper.deleteByGuid(5L);
			//System.out.println("删除"+deleteEmp);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 查询某个字段
	 */
	@Test
	public void Test6(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			if(employeeMapper!=null){
				String userName=employeeMapper.getUserNameByGuid(1L);
				System.out.println("userName"+userName);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 测试单参数、多参数、命名参数
	 */
	@Test
	public void test7(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			if(employeeMapper!=null){
				Employee employee=employeeMapper.getEmployeeByGuidAndUserName(1L, "张三");
				System.out.println("employee"+employee.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 多参数查询(map)
	 */
	@Test
	public void Test9(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("guid", 1);
			paramMap.put("userName", "张三");
			Employee employee=employeeMapper.getEmployeeByMap(paramMap);
			System.out.println("employee"+employee.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 查询返回集合
	 */
	@Test
	public void Test8(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			List<Employee> list=employeeMapper.getEmployeeList("张三");
			for(Employee emp:list){
				System.out.println("emp"+emp.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 查询javabean转成map
	 */
	@Test
	public void Test10(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			Map<String, Object> map=employeeMapper.getEmpByIdToMap(1L);
			System.out.println("map"+map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 查询多条记录返回map  Map<Long,Employee>  key是实体bean主键  value实体javabean
	 */
	@Test
	public void Test11(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapper employeeMapper=openSession.getMapper(EmployeeMapper.class);
			Map<Long, Employee> mapList=employeeMapper.getEmpByUserNameToMap("%张%");
			System.out.println("mapLi st"+mapList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 自定义封装返回类型
	 */
	@Test
	public void Test12(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperPlus employeeMapperPlus=openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee=employeeMapperPlus.getEmpById(1L);
			System.out.println("employee"+employee.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(openSession!=null){
				openSession.close();
			}
		}
	}
	
	/**
	 * 多表关联查询结果返回封装resultMap
	 */
	@Test
	public void Test13(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperPlus employeeMapperPlus=openSession.getMapper(EmployeeMapperPlus.class);
			Employee emp=employeeMapperPlus.getEmpAndDepById(1L);
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
	 * 测试association的分步查询
	 */
	@Test
	public void Test15(){
		SqlSession openSession=getSqlSession();
		try{
			EmployeeMapperPlus empPlusMapper=openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee=empPlusMapper.getEmpAndDepStepById(1L);
			System.out.println("employee"+employee.getUserName());
			System.out.println("dep"+employee.getDep().getDepartmentName());
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
