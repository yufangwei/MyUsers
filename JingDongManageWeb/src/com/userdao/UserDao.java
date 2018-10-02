package com.userdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.entity.Bulletin;
import com.entity.CheckOrders;
import com.entity.CusAddress;
import com.entity.CusInfo;
import com.entity.CusMan;
import com.entity.GoodsInfo;
import com.entity.Orders;
import com.util.DBUtil;
import com.util.PageUtil;

public class UserDao {
	DBUtil db = new DBUtil();
	
	//验证登录的方法
	public boolean yzLogin(Object[]obj){
		String sql = "select *from UserInfo where userName=? and userPwd =?";
		ResultSet rs = db.execQuery(sql, obj);
		try {
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	//分页查询所有订单的方法
	public PageUtil<Orders> billListAll(int pageOn,int pageSize,Object[]obj){
		String sql = "";
		if(obj==null){//判断，为空则查询全部
		  sql=" select top "+pageSize+" ss.orderid,ss.customerId,f.goodsid,f.goodsname,f.price,ss.quantity,ss.ordertime,ss.status from goodsinfo f,"+
				"(select g.orderId, g.goodsid, g.quantity ,o.customerId,o.status, o.orderTime from OrderInfo o,OrderGoodsInfo g where o.orderId = g.orderId)as ss"+
				" where f.goodsid = ss.goodsid and ss.orderId not in(select top (("+pageOn+"-1)*"+pageSize+")orderId from (select g.orderId, g.goodsid, g.quantity ,o.customerId,o.status,"+ 
				"o.orderTime from OrderInfo o,OrderGoodsInfo g where o.orderId = g.orderId)as ss)";
		  
		  
		}else{//否则条件查询
			sql = "select top "+pageSize+" ss.orderid,ss.customerId,f.goodsid,f.goodsname,f.price,ss.quantity,ss.ordertime,ss.status from goodsinfo f,"+
				"(select g.orderId, g.goodsid, g.quantity ,o.customerId,o.status, o.orderTime from OrderInfo o,OrderGoodsInfo g where o.orderId = g.orderId)as ss"+
				" where f.goodsid = ss.goodsid and  ss.customerId =? and goodsname =? and ss.status=? and"+
				" ss.orderId not in(select top (("+pageOn+"-1)*"+pageSize+")orderId from (select g.orderId, g.goodsid, g.quantity ,o.customerId,o.status,"+
				" o.orderTime from OrderInfo o,OrderGoodsInfo g where o.orderId = g.orderId)as ss)";
		}
		ResultSet rs = db.execQuery(sql, obj);
		PageUtil<Orders>pageOd = new PageUtil<Orders>();
		ArrayList<Orders>list = new ArrayList<Orders>();
		try {
			while(rs.next()){
				Orders od = new Orders();
				od.setOrderId(rs.getInt("orderid"));
				od.setCusId(rs.getInt("customerid"));
				od.setGoodsId(rs.getInt("goodsid"));
				od.setQuantity(rs.getInt("quantity"));
				od.setGoodsName(rs.getString("goodsname"));
				od.setPrice(rs.getFloat("price"));
				od.setOrderTime(rs.getString("ordertime"));
				String status = rs.getInt("status")==0?"未确认":"已确认";
				od.setStatus(status);
				list.add(od);
			}
			pageOd.setList(list);
			pageOd.setPageOn(pageOn);
			pageOd.setPageSize(pageSize);
			if(obj!=null){
				pageOd.setPageCount(pageCount(obj));
			}else{
				pageOd.setPageCount(pageCount());
			}
			
			return pageOd;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	//全部分页查询
	public int pageCount(){
		String sql = "select COUNT(*)from OrderGoodsInfo";
		ResultSet rs = db.execQuery(sql, null);
		int count = 0;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	//分页条件查询中所有的数据总数
	public int pageCount(Object[]obj){
		String sql = "select  count(*) from goodsinfo f,"+
"(select g.orderId, g.goodsid, g.quantity ,o.customerId,o.status, o.orderTime from"+
	"	OrderInfo o,OrderGoodsInfo g where o.orderId = g.orderId)as ss"+
" where f.goodsid = ss.goodsid and  ss.customerId =? and goodsname =? and ss.status=? ";
		ResultSet rs = db.execQuery(sql, obj);
		int count = 0;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			return count;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
	//修改订单的方法第一步,返回住址的结果集对象
	public CusAddress cusAddr(int cusId){
		String sql = "select * from cusDetailInfo where cusid ="+cusId+"";
		ResultSet rs = db.execQuery(sql, null);
		CusAddress ca = new CusAddress();
		try {
			if(rs.next()){
				ca.setId(rs.getInt("cusid"));
				ca.setName(rs.getString("name"));
				ca.setAddress(rs.getString("address"));
				ca.setPhone(rs.getString("telphone"));
				ca.setTelPhone(rs.getString("movePhone"));
			}
			return ca;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//修改地址的方法
	public int updateCusAdd(Object []obj){
		String sql = "update cusDetailinfo set name =? ,telphone=?,movePhone=?,address=? where cusid =?";
		return db.execUpdate(sql, obj);
	}
	
	//修改订单的方法
	public int updateOrder(Object[]obj){
		String sql = "update OrderInfo set status = ? where orderId=?";
		return db.execUpdate(sql, obj);
	}
	
	//修改订单商品信息的方法
	public int updateOrdersGoods(Object[]obj){
		String sql = "update OrderGoodsInfo set goodsid = ? , quantity =? where goodsid =? and orderId =?";
		return db.execUpdate(sql, obj);
	}
	
	
	//查看一条订单的详细方法
	public CheckOrders checkOrders(Object[]obj){
		String sql = "select o.orderId,o.goodsid,t.typename,g.goodsname,g.price,o.quantity,c.name,d.telphone,d.address,i.status,i.orderTime"+
" from OrderGoodsInfo o,goodsInfo g,OrderInfo i,cusinfo c,cusdetailinfo d,GoodsType t where o.orderId = i.orderId "+
"and i.customerId = c.id and d.cusid =c.id and g.goodsid = o.goodsid and t.typeId = g.typeid and o.orderId=? and o.goodsid=? ";
		ResultSet rs = db.execQuery(sql, obj);
		CheckOrders co = new CheckOrders();
		try {
			if(rs.next()){
				co.setOrderId(rs.getInt("orderId"));
				co.setGoodsId(rs.getInt("goodsid"));
				co.setTypeName(rs.getString("typeName"));
				co.setGoodsName(rs.getString("goodsName"));
				co.setPrice(rs.getFloat("price"));
				co.setQuantity(rs.getInt("quantity"));
				co.setName(rs.getString("name"));
				co.setTelPhone(rs.getString("telphone"));
				co.setAddress(rs.getString("address"));
				String status = rs.getInt("status")==0?"未确认":"已确认";
				co.setStatus(status);
				co.setOrderTime(rs.getString("orderTime"));
			}
			return co;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	//删除订单的方法
	public int delOrders(Object[]obj){
		String sql = "delete from OrderGoodsInfo where orderId = ? and goodsid = ?";
		return db.execUpdate(sql, obj);
	}
	
	
	//添加订单的方法，第一步,(先判断是否有地址)添加收货地址
	public int addOrdersOne(Object []obj,int cusId){
		String sql1 = "Select *from cusDetailinfo where cusid ="+cusId+"";
		ResultSet rs = db.execQuery(sql1, null);
		try {
			if(rs.next()){
				return 2;
			}else{
				String sql2 = "insert into cusDetailinfo values(?,?,?,?,?)";
				return db.execUpdate(sql2, obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	//添加订单的第二步，保存订单信息
	public int addOrderInfo(Object[]obj){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(new Date());
		String sql = "insert into OrderInfo values(?,?,'"+format+"')";
		return db.execUpdate(sql, obj);
	}
	
	//添加订单第三步，保存订单商品信息
	public int OrderGoodsInfo(Object[]obj){
		String sql1 = "select top 1 orderId from OrderInfo order by orderId desc";
		ResultSet rs = db.execQuery(sql1, null);
		int orderId = 0;
		try {
			if(rs.next()){
				orderId = rs.getInt("orderId");
			}
		} catch (Exception e) {
		}
		String sql2 = "insert into OrderGoodsInfo values("+orderId+",?,?)";
		return db.execUpdate(sql2, obj);
	}
	
	
	//查看公告的方法
	
//获取所有公告信息的方法,分页和模糊查询一起
	public PageUtil<Bulletin> getBbsAll(int pageSize,int pageOn,String word){
		String sql = "";
		if(word==null||word==""){
			sql = " select top "+pageSize+" * from (select bt.id ,bt.title,bt.content,ui.username,bt.createTime"+
			" from UserInfo ui,Bulletin bt where bt.userId=ui.id)as ss where ss.id not in(select top (("+pageOn+"-1)*"+pageSize+") id from (select bt.id ,bt.title,bt.content,ui.username,"+
			"bt.createTime from UserInfo ui,Bulletin bt where bt.userId=ui.id)as ss)order by ss.id";
		}else{
			sql = " select top "+pageSize+" * from (select bt.id ,bt.title,bt.content,ui.username,bt.createTime"+
					" from UserInfo ui,Bulletin bt where bt.userId=ui.id)as ss where ss.title like '%"+word+"%' and ss.id not in(select top (("+pageOn+"-1)*"+pageSize+") id from (select bt.id ,bt.title,bt.content,ui.username,"+
					"bt.createTime from UserInfo ui,Bulletin bt where bt.userId=ui.id and bt.title like '%"+word+"%')as ss)order by ss.id";
		}
			ArrayList<Bulletin>list = new ArrayList<Bulletin>();
			PageUtil<Bulletin> page = new PageUtil<Bulletin>();
			ResultSet rs = db.execQuery(sql, null);
			try {
				while(rs.next()){
					Bulletin bt = new Bulletin();
					bt.setId(rs.getInt("id"));
					bt.setTitle(rs.getString("title"));
					bt.setContent(rs.getString("content"));
					bt.setUsername(rs.getString("username"));
					bt.setCreateTime(rs.getString("createTime"));
					list.add(bt);
				}
				page.setList(list);
				page.setPageOn(pageOn);
				page.setPageSize(pageSize);
				page.setPageCount(bbsPageCount());
				return page;
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		return null;
	}
	
	
	//查询所有公告个数的方法
	public int bbsPageCount(){
		String sql = "select count(*) from Bulletin ";
		ResultSet rs = db.execQuery(sql, null);
		int count = 0;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//查询单条公告信息的方法
	public Bulletin checkNotice(int id){
		String sql = "select b.id,b.title,b.content,u.userName,b.createTime from Bulletin b,"
				+ "UserInfo u where b.userId=u.id and b.id = "+id+"";
		ResultSet rs = db.execQuery(sql, null);
		Bulletin bt = new Bulletin();
		try {
			if(rs.next()){
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setContent(rs.getString("content"));
				bt.setUsername(rs.getString("userName"));
				bt.setCreateTime(rs.getString("createTime"));
			}
			return bt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//把公告修改的数据存入数据库中,先找到author的id值
	public int findId(String author){
		String sql1 = "select u.id from UserInfo u where userName = '"+author+"'";
		Object[]obj = {author};
		ResultSet rs = db.execQuery(sql1, null);
		int id = 0;
		try {
			if(rs.next()){
				id= rs.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//把公告修改的数据存入数据库中
	public int updateBbs(Object[]obj){
		String sql = "update Bulletin set title= ?,content=?,userId=?,createTime=getDate() where id=?";
		return db.execUpdate(sql, obj);
	}
	
	
	//添加公告的方法
	public int addBbs(Object[]obj){
		String sql = "insert into Bulletin values (?,?,?,getDate())";
		int n = db.execUpdate(sql, obj);
		return n;
		
		
	} 
	
	//多选删除的方法
	public void delMore(String str){
		String []gstr = str.split(",");
		int id = 0;
		int []total = new int [str.length()];
		for(int i =0;i<gstr.length;i++){
			if(gstr!=null||gstr[i]!=""){
				id = Integer.parseInt(gstr[i]);
				Object []obj = {id};
				String sql = "delete from Bulletin where id = ?";
				int n = db.execUpdate(sql, obj);
				total[i] = n;
			}
		}
		for(int i=0;i<total.length;i++){
			if(total[i]>0){
				System.out.println("删除第"+total[i]+"条数据成功！");
			}
		}
	}
	
	
	//管理所有客户的方法
	public PageUtil<CusInfo> cusMan(int pageOn,int pageSize,String word){
		String sql = "";
		int count = 0;
		if(word==null||word==""){
			count = pageUtCount();
			sql = "select  top "+pageSize+" *from cusinfo where id not in(select top(("+pageOn+"-1)*"+pageSize+")id from cusinfo )";
		}else{
			count = pageUtCount(word);
			sql = "  select  top "+pageSize+" *from cusinfo where name like '%"+word+"%' and id not "
					+ "in(select top(("+pageOn+"-1)*"+pageSize+")id from cusinfo where name like '%"+word+"%'  )";
		}
		ResultSet rs = db.execQuery(sql, null);
		PageUtil<CusInfo>pageUt = new PageUtil<CusInfo>();
		ArrayList<CusInfo> list = new ArrayList<CusInfo>();
		try {
			while(rs.next()){
				CusInfo ci = new CusInfo();
				ci.setId(rs.getInt("id"));
				ci.setName(rs.getString("name"));
				ci.setPwd(rs.getString("pwd"));
				ci.setEmail(rs.getString("email"));
				ci.setRegisterTime(rs.getString("RegisterTime"));
				list.add(ci);
			}
			pageUt.setList(list);
			pageUt.setPageOn(pageOn);
			pageUt.setPageSize(pageSize);
			pageUt.setPageCount(count);
			return pageUt;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//查询用户的总条数
	public int pageUtCount(){
		String sql = "select count(*)from cusinfo";
		ResultSet rs = db.execQuery(sql, null);
		int count = 0;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	//模糊查询的用户总条数
	public int pageUtCount(String word){
		String sql = "select count(*)from cusinfo where name like '%"+word+"%'";
		ResultSet rs = db.execQuery(sql, null);
		int count = 0;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	//查询单个用户的注册信息
	public CusInfo cusMan(int id){
		String sql1 = "select *from cusinfo where id ="+id+"";
		ResultSet rs1 = db.execQuery(sql1, null);
		CusInfo ci = new CusInfo();
		try {
			if(rs1.next()){
				ci.setId(rs1.getInt("id"));
				ci.setName(rs1.getString("name"));
				ci.setPwd(rs1.getString("pwd"));
				ci.setEmail(rs1.getString("email"));
				ci.setRegisterTime(rs1.getString("registerTime"));
			}
			return ci;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//查询单个用户的收货地址信息
	public CusAddress cusAdreessMan(int id){
		String sql = "select * from cusDetailinfo where cusid="+id+"";
		ResultSet rs = db.execQuery(sql, null);
		CusAddress ca = new CusAddress();
		try {
			if(rs.next()){
				ca.setName(rs.getString("name"));
				ca.setPhone(rs.getString("telphone"));
				ca.setTelPhone(rs.getString("movePhone"));
				ca.setAddress(rs.getString("address"));
			}
			return ca;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//获取验证ajax的所有信息
		public ArrayList<String> getAjax(){
			 ArrayList<String> list = new ArrayList<String>();
			
			 String sql = " select *from cusinfo ";
			 String sql2 = "select *from userInfo";
			 ResultSet rs = db.execQuery(sql, null);
			 ResultSet rs2 = db.execQuery(sql2, null);
			 try {
				while(rs.next()){
					String str = "";
					str = rs.getString("name");
					list.add(str);
				 }
				while(rs2.next()){
					String str = "";
					str = rs2.getString("userName");
					list.add(str);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 return null;
		}
		
	
	//添加客户的方法
	public int addCus(Object[]obj){
		String sql = "insert into cusinfo values(?,?,?,getdate())";
		int n = db.execUpdate(sql, obj);
		return n;
	}
	
	//添加管理员用户的方法
	public int addUser(Object[]obj){
		String sql = "insert into UserInfo values(?,?)";
		return db.execUpdate(sql, obj);
		
	}
	
	//先找到刚刚添加的id值
	public int lastCusId(){
		String sql = "select top 1 id from cusinfo order by id desc";
		ResultSet rs = db.execQuery(sql, null);
		int id =0;
		try {
			if(rs.next()){
				id = rs.getInt("id");
			}
			return id;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	//添加地址的方法
	public int addAddressTwo(Object[]obj){
		String sql = "insert into cusDetailinfo values(?,?,?,?,?)";
		int n = db.execUpdate(sql, obj);
		return n;
	}
	
	//修改用户基本信息的方法
	public int updateCusTwo(Object[]obj){
		String sql = "update cusinfo set name=?,pwd=?,email=?  where id = ?";
		return db.execUpdate(sql, obj);
	}
	
	//修改用户的地址信息的基本方法
	public int updateAddTwo(Object[]obj){
		String sql = "update cusDetailinfo set name=?,telPhone=?,movePhone=?,address=? where cusid =? ";
		return db.execUpdate(sql, obj);
	}
	
	//删除客户的第一步，先得到客户的订单编号
	public ArrayList<Integer> getOrderId(int id ){
		String sql = "select orderId from OrderInfo where customerId = "+id+"";
		ResultSet rs = db.execQuery(sql, null);
		ArrayList<Integer>list = new ArrayList<Integer>();
		try {
			while(rs.next()){
				int g = 0;
				g = rs.getInt("orderid");
				list.add(g);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	//删除客户的第二步，根据客户的订单编号删除订单商品信息表
	public int delCusOrder(ArrayList<Integer> list){
		int elements = 0;
		for(int i=0;i<list.size();i++){
			String sql = "delete from OrderGoodsInfo where orderId ="+list.get(i)+"";
			int n = db.execUpdate(sql, null);
			elements++;
		}
		return elements;
	}
	
	//删除客户第三步，根据客户的id删除客户的所有订单表
	public int delCusOrders(int id){
		String sql = "delete from OrderInfo where customerId = "+id+"";
		return db.execUpdate(sql, null);
	}
	
	//删除客户的方法第四步，先删除客户的地址信息
	public int delAddress(int id){
		String sql = "delete from cusDetailinfo where cusid = "+id+"";
		return db.execUpdate(sql, null);
	}
	
	//删除客户的方法最后一步，后删除客户的基本信息
	public int delCusMan(int id){
		String sql = " delete from cusinfo where id = "+id+"";
		return db.execUpdate(sql, null);
	}
	
	
	//查看所有商品的方法
	public PageUtil<GoodsInfo> checkGoods(int pageOn,int pageSize,String word){
		String sql = "";
		ResultSet rs = null;
		PageUtil<GoodsInfo> page = new PageUtil<GoodsInfo>();
		ArrayList<GoodsInfo> list = new ArrayList<GoodsInfo>();
		if(word==null||word==""){
			sql = "select top "+pageSize+" * from (select g.goodsid,t.typename,g.goodsname,g.price,g.remark,g.photo,g.discount,"
					+ "g.isnew,g.isrecommend,g.status from goodsinfo g,goodstype t where t.typeid = g.typeid)"
					+ "as ss where ss.goodsid not in(select top (("+pageOn+"-1)*"+pageSize+") goodsid  from (select g.goodsid,"
					+ "t.typename,g.goodsname,g.price,g.remark,g.photo,g.discount,g.isnew,g.isrecommend,g.status "
					+"from goodsinfo g,goodstype t where t.typeid = g.typeid ) as ss)order by ss.goodsid";
		}else{
			sql = "select top "+pageSize+" * from (select g.goodsid,t.typename,g.goodsname,g.price,g.remark,g.photo,g.discount,"
					+ "g.isnew,g.isrecommend,g.status from goodsinfo g,goodstype t where t.typeid = g.typeid and g.goodsname="+word+")"
					+ "as ss where ss.goodsid not in(select top (("+pageOn+"-1)*"+pageSize+") goodsid  from (select g.goodsid,"
					+ "t.typename,g.goodsname,g.price,g.remark,g.photo,g.discount,g.isnew,g.isrecommend,g.status"
					+"from goodsinfo g,goodstype t where t.typeid = g.typeid and g.goodsName="+word+" ) as ss)order by ss.goodsid";
		}
		 rs = db.execQuery(sql, null);
		 try {
			while(rs.next()){
				GoodsInfo gi = new GoodsInfo();
				gi.setGoodsId(rs.getInt("goodsid"));
				gi.setGoodsName(rs.getString("goodsname"));
				gi.setTypeName(rs.getString("typename"));
				gi.setPhoto(rs.getString("photo"));
				gi.setPrice(rs.getFloat("price"));
				gi.setDiscount(rs.getInt("discount"));
				gi.setIsRecommend(rs.getInt("isrecommend"));
				gi.setIsNew(rs.getInt("isnew"));
				gi.setRemark(rs.getString("remark"));
				gi.setStatus(rs.getInt("status"));
				list.add(gi);
			}
			page.setList(list);
			page.setPageOn(pageOn);
			page.setPageSize(pageSize);
			page.setPageCount(getGoodCount(word));
			return page;
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return null;
	}
	
	//查询商品数量总数的方法
	public int getGoodCount(String word){
		String sql = "";
		ResultSet rs = null;
		int count = 0;
		if(word==null||word==""){
			sql = " Select count(*)from goodsinfo";
		}else{
			sql = " Select count(*)from goodsinfo where goodsname like '%"+word+"%'";	
		}
			rs = db.execQuery(sql, null);
			try {
				if(rs.next()){
					count = rs.getInt(1);
					return count ;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return 0;
	}
	
	
	//上传添加商品的方法
	public int addGoods(Object[]obj){
		String sql = "insert into GoodsInfo values(?,?,?,'',?,?,?,?,?)";
		int n = db.execUpdate(sql, obj);
		return n;
	}
	
	//删除商品的方法
	public int delGoodsOne(int id){
		String sql = "delete from goodsinfo where goodsid = "+id+"";
		return db.execUpdate(sql, null);
		
	}
	
	//修改密码的方法
	public String changePwd(String userName){
		String sql = "select *from UserInfo where userName= '"+userName+"'";
		ResultSet rs = db.execQuery(sql, null);
		String pwd = "";
		try {
			if(rs.next()){
				pwd = rs.getString("userPwd");
			}
			return pwd;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//修改密码的第二步
	public int changePwdTwo(Object[]obj){
		String sql = "update UserInfo set userPwd =? where userName =?";
		return db.execUpdate(sql, obj);
	}
}
