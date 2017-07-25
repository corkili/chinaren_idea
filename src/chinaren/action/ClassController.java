package chinaren.action;

import chinaren.common.SessionContext;
import chinaren.model.Class;
import chinaren.model.Result;
import chinaren.service.ClassService;
import chinaren.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 李浩然 on 2017/7/25.
 */
@Controller
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(ClassController.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");

    @RequestMapping(value = "/createClass", method = RequestMethod.GET)
    public ModelAndView createClass(HttpSession session,
                                    @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        Class clazz = new Class();
        clazz.setManagerId(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()));
        clazz.setManagerName(displayName);
        ModelAndView modelAndView = new ModelAndView("create_class");
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("provinces", userService.getAddressContext().getProvinces())
                .addObject("cities", userService.getAddressContext().getCities())
                .addObject("areas", userService.getAddressContext().getAreas())
                .addObject("class", clazz);
        return modelAndView;
    }

    @RequestMapping(value = "/createClass", method = RequestMethod.POST)
    public ModelAndView createClass(HttpSession session, @ModelAttribute("class") Class clazz,
                                    @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView();
        clazz.setManagerId(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()));
        Result<Boolean> result = classService.createClass(clazz);
        if (!result.isSuccessful()) {
            modelAndView.setViewName("create_class");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", result.getMessage())
                    .addObject("display_name", displayName)
                    .addObject("provinces", userService.getAddressContext().getProvinces())
                    .addObject("cities", userService.getAddressContext().getCities())
                    .addObject("areas", userService.getAddressContext().getAreas())
                    .addObject("class", clazz);
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/main");
        return modelAndView;
    }

    @RequestMapping(value = "/myClass", method = RequestMethod.GET)
    public ModelAndView getMyClassList(HttpSession session,
                                       @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView("my_class");
        long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        Result<List<Class>> myClassResult = classService.getClasses(userId, true);
        Result<List<Class>> applyClassResult = classService.getClasses(userId, false);
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", userId)
                .addObject("myClasses", myClassResult.getResult())
                .addObject("applyClasses", applyClassResult.getResult());
        return modelAndView;
    }

    @RequestMapping(value = "/exitClass", method = RequestMethod.POST)
    public ModelAndView exitClassList(HttpSession session,
                                      @RequestParam("class_id") long classId,
                                      @SessionAttribute(SessionContext.ATTR_USER_ID) long userId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/myClass");
        classService.exitClass(userId, classId);
        return  modelAndView;
    }
}
