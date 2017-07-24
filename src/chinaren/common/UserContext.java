package chinaren.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import chinaren.dao.UserDao;
import chinaren.model.Result;
import chinaren.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 用户数据动态维护类
 * @ClassName UserContext 
 * @author 李浩然
 * @date 2017年7月22日
 * @version 1.0
 */
public class UserContext {

	/**
	 * 已登录的用户集
	 */
	private Map<Long, User> loginUsers;
	
	/**
	 * 待验证的邮箱验证码
	 */
	private Map<String, String> emailCode;

	@Autowired
	private UserDao userDao;
	
	/**
	 * 维护单例对象的静态内部类
	 * @ClassName UserContextHolder 
	 * @author 李浩然
	 * @date 2017年7月22日
	 * @version 1.0
	 */
	private static class UserContextHolder {
		private static final UserContext INSTANCE = new UserContext();
	}
	
	/**
	 * 私有构造方法
	 */
	private UserContext() {
		loginUsers = new HashMap<Long, User>();
		emailCode = new HashMap<String, String>();
	}

	/**
	 * 获取UserContext实例对象
	 * @author 李浩然
	 * @return UserContext单例对象
	 */
	public static final UserContext getUserContext() {
		return UserContextHolder.INSTANCE;
	}
	
	/**
	 * 添加一个用户至登录用户集中
	 * @author 李浩然
	 * @param user 待添加的用户
	 * @return 操作是否成功
	 */
	public boolean addUser(User user) {
		if (user != null && user.getUserId() > 0) {
			loginUsers.remove(user.getUserId());
			loginUsers.put(user.getUserId(), user);
			return true;
		}
		return false;
	}
	
	/**
	 * 从登录用户集中删除一个用户
	 * @author 李浩然
	 * @param userId 待删除用户的ID
	 */
	public void removeUser(long userId) {
		loginUsers.remove(userId);
	}
	
	/**
	 * 从登录用户集中获取一个用户
	 * @author 李浩然
	 * @param userId 要获取的用户的ID
	 * @return 用户实体，若不存在相应用户，返回null
	 */
	public User getUser(long userId) {
		return loginUsers.get(userId);
	}
	
	/**
	 * 生成一个随机的邮件验证码
	 * @author 李浩然
	 * @param email 待生成验证码的邮箱地址
	 * @return 生成的邮件验证码
	 */
	public String createEmailCode(String email) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(10);
			sb.append(number);
		}
		String code = sb.toString();
		emailCode.remove(email);
		emailCode.put(email, code);
		return code;
	}
	
	/**
	 * 验证邮件验证码是否正确
	 * @author 李浩然
	 * @param email 邮箱地址
	 * @param code 邮件验证码
	 * @return 若邮件验证码正确，返回true；否则，返回false
	 */
	public boolean verifyEmailCode(String email, String code) {
		if (email != null && code != null && code.equals(emailCode.get(email))) {
			emailCode.remove(email);
			return true;
		}
		return false;
	}
	
	/**
	 * 更新登录用户集中的用户信息
	 * @author 李浩然
	 * @param userId 待更新的用户ID集
	 */
	public void update(Long... userId) {
		for(long id : userId) {
			if (loginUsers.containsKey(id)) {
				Result<User> result = userDao.selectUserByUserId(id);
				if (result.isSuccessful()) {
					loginUsers.remove(id);
					loginUsers.put(result.getResult().getUserId(), result.getResult());
				}
			}
		}
	}
	
	/**
	 * 更新登录用户集中的用户信息
	 * @author 李浩然
	 * @param userId 待更新的用户ID集
	 */
	public void update(List<Long> userId) {
		if (userId != null) {
			update((Long[])userId.toArray());
		}
	}
	
}
