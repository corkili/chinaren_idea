package chinaren.model;

/**
 * 操作结果的封装类
 * @ClassName Result
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public class Result<T> {
	
	/**
	 * 操作是否成功
	 */
	private boolean successful;
	
	/**
	 * 操作结果信息
	 */
	private String message;
	
	/**
	 * 操作结果
	 */
	private T result;
	
	/**
	 * 构造函数
	 * @param successful 操作结果是否成功
	 * @param message 操作结果信息
	 * @param result 操作结果
	 */
	public Result(boolean successful, String message, T result) {
		this.successful = successful;
		this.message = message;
		this.result = result;
	}

	/**
	 * @return the successful
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
}
