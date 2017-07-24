package chinaren.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chinaren.common.AddressContext;
import chinaren.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import chinaren.common.SessionContext;
import chinaren.model.User;
import chinaren.service.UserService;
import chinaren.util.CaptchaUtil;

/**
 * 用户相关请求控制器
 * @ClassName UserController 
 * @author 李浩然
 * @date 2017年7月24日
 * @version 1.0
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(UserController.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	@RequestMapping(value = "/no_login", method = { RequestMethod.GET, RequestMethod.POST })
	public String noLogin() {
		return "no_login";
	}
	
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request, response);
    }

    @RequestMapping(value = "/headImage", method = RequestMethod.GET)
    @ResponseBody
    public void displayHeadImage(HttpServletRequest request, HttpServletResponse response) {
        userService.outputHeadImage(Long.parseLong(
                request.getSession().getAttribute(SessionContext.ATTR_USER_ID).toString()), request, response);
    }

    @RequestMapping(value = "/sendEmail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void sendEmail(@RequestParam("email") String email, HttpServletResponse response) {
        logger.info(dateFormat.format(new Date()) + "send email to " + email);
	    userService.sendEmail(email);
    }

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView modifyPassword(HttpSession session) {
		long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
		User user = userService.getUserInformation(userId).getResult();
		return new ModelAndView("main")
				.addObject("user", user);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        userService.logout(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()), session);
        return "redirect:/login";
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (session.getAttribute(SessionContext.ATTR_USER_ID) != null) {
			userService.logout(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()), session);
		}
		modelAndView.addObject("user", new User());
		modelAndView.addObject("has_error", false);
		modelAndView.addObject("error_message", "");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session,
							  @RequestParam("captcha") String captcha) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("randomString") == null
				|| !session.getAttribute("randomString").toString().toLowerCase().equals(captcha.toLowerCase())) {
			modelAndView.setViewName("login");
			modelAndView.addObject("has_error", true);
			modelAndView.addObject("error_message", "验证码错误！");
			return modelAndView;
		}
		Result<Boolean> result = userService.login(user.getEmail(), user.getPassword(), session);
		if (!result.isSuccessful()) {
			modelAndView.setViewName("login");
			modelAndView.addObject("has_error", true);
			modelAndView.addObject("error_message", result.getMessage());
			return modelAndView;
		} else {
			modelAndView.setViewName("redirect:/main");
			return modelAndView;
		}
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("user") User user) {
        logger.info(dateFormat.format(new Date()) + "register - get");
	    return new ModelAndView("register")
                .addObject("user", (user == null ? new User() : user))
                .addObject("confirmPassword", "")
                .addObject("code", "")
                .addObject("provinces", userService.getAddressContext().getProvinces())
                .addObject("cities", userService.getAddressContext().getCities())
                .addObject("areas", userService.getAddressContext().getAreas())
                .addObject("has_error", false)
                .addObject("error_message", "");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") User user, HttpSession session,
                                 @RequestParam("captcha") String captcha, @RequestParam("code") String code) {
        logger.info(dateFormat.format(new Date()) + "register - post");
	    ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("randomString") == null
                || !session.getAttribute("randomString").toString().toLowerCase().equals(captcha.toLowerCase())) {
            modelAndView.setViewName("register");
            user.setProvince("0");
            user.setCity("0");
            user.setArea("0");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", "验证码错误！")
                    .addObject("user", user)
                    .addObject("confirmPassword", user.getPassword())
                    .addObject("code", code)
                    .addObject("provinces", userService.getAddressContext().getProvinces())
                    .addObject("cities", userService.getAddressContext().getCities())
                    .addObject("areas", userService.getAddressContext().getAreas());
            logger.info(dateFormat.format(new Date()) + "register: captcha failed");
            return modelAndView;
        }
        Result<Boolean> result = userService.register(session.getServletContext(), user, code);
        if (!result.isSuccessful()) {
            modelAndView.setViewName("register");
            user.setProvince("0");
            user.setCity("0");
            user.setArea("0");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", result.getMessage())
                    .addObject("user", user)
                    .addObject("confirmPassword", user.getPassword())
                    .addObject("code", code)
                    .addObject("provinces", userService.getAddressContext().getProvinces())
                    .addObject("cities", userService.getAddressContext().getCities())
                    .addObject("areas", userService.getAddressContext().getAreas());
            logger.info(dateFormat.format(new Date()) + "register: information failed");
            return modelAndView;
        }
        logger.info(dateFormat.format(new Date()) + "register: successful");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @RequestMapping(value = "resetPWD", method = RequestMethod.GET)
    public ModelAndView resetPassword(HttpSession session) {
        logger.info(dateFormat.format(new Date()) + "reset password - get");
        if (session.getAttribute(SessionContext.ATTR_USER_ID) != null) {
            userService.logout(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()), session);
        }
        return new ModelAndView("reset_password")
                .addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("email", "")
                .addObject("code", "")
                .addObject("password", "");
    }

    @RequestMapping(value = "resetPWD", method = RequestMethod.POST)
    public ModelAndView resetPassword(@RequestParam("email") String email,
                                      @RequestParam("code") String code,
                                      @RequestParam("password") String password,
                                      @RequestParam("captcha") String captcha,
                                      @SessionAttribute("randomString") String randomString) {
        logger.info(dateFormat.format(new Date()) + "reset password - get");
        ModelAndView modelAndView = new ModelAndView();
        if (randomString == null || !randomString.toLowerCase().equals(captcha.toLowerCase())) {
            modelAndView.setViewName("reset_password");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", "验证码错误！")
                    .addObject("email", email)
                    .addObject("code", code)
                    .addObject("password", password);
            logger.info(dateFormat.format(new Date()) + "register: captcha failed");
            return modelAndView;
        }
        Result<Boolean> result = userService.modifyPassword(email, code, password);
        if (!result.isSuccessful()) {
            modelAndView.setViewName("reset_password");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", result.getMessage())
                    .addObject("email", email)
                    .addObject("code", code)
                    .addObject("password", password);
            logger.info(dateFormat.format(new Date()) + "register: captcha failed");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }
}
























