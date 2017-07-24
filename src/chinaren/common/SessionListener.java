package chinaren.common;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义Session监听器
 * @ClassName SessionListener
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public class SessionListener implements HttpSessionListener{
    public static SessionContext sessionContext = SessionContext.getInstance();

    /**
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionContext.addSession(httpSessionEvent.getSession());
    }

    /**
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessionContext.delSession(httpSessionEvent.getSession());
    }
}
