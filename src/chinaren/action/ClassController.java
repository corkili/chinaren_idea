package chinaren.action;

import chinaren.common.SessionContext;
import chinaren.model.Class;
import chinaren.model.Result;
import chinaren.model.User;
import chinaren.service.ClassService;
import chinaren.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                .addObject("clazz", clazz);
        return modelAndView;
    }

    @RequestMapping(value = "/createClass", method = RequestMethod.POST)
    public ModelAndView createClass(HttpSession session, @ModelAttribute("clazz") Class clazz,
                                    @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView();
        clazz.setManagerId(Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString()));
        clazz.setManagerName(displayName);
        Result<Boolean> result = classService.createClass(clazz);
        if (!result.isSuccessful()) {
            modelAndView.setViewName("create_class");
            modelAndView.addObject("has_error", true)
                    .addObject("error_message", result.getMessage())
                    .addObject("display_name", displayName)
                    .addObject("provinces", userService.getAddressContext().getProvinces())
                    .addObject("cities", userService.getAddressContext().getCities())
                    .addObject("areas", userService.getAddressContext().getAreas())
                    .addObject("clazz", clazz);
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/manageClass");
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
    public ModelAndView exitClass(@RequestParam("class_id") long classId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/myClass");
        long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        classService.exitClass(userId, classId);
        return  modelAndView;
    }

    @RequestMapping(value = "/manageClass", method = RequestMethod.GET)
    public ModelAndView getManageClassList(HttpSession session,
                                       @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView("manage_class");
        long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        Result<List<Class>> manageClassesResult = classService.getManageClasses(userId);
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", userId)
                .addObject("manageClasses", manageClassesResult.getResult());
        return modelAndView;
    }

    @RequestMapping(value = "/manageClass", method = RequestMethod.POST)
    public ModelAndView getManageClassmateList(HttpSession session,
                                               @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName,
                                               @RequestParam("class_id") long classId) {
        ModelAndView modelAndView = new ModelAndView("manage_classmates");
        long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", userId)
                .addObject("clazz", classService.getClassInformation(classId).getResult())
                .addObject("classmates", classService.getUsersByClassId(classId, true).getResult())
                .addObject("applyUsers", classService.getUsersByClassId(classId, false).getResult());
        return modelAndView;
    }

    @RequestMapping(value = "/removeClass", method = RequestMethod.POST)
    public ModelAndView removeClass(HttpSession session,
                                    @RequestParam("class_id") long classId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/manageClass");
        long userId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        logger.info(dateFormat.format(new Date()) + classService.removeClass(userId, classId).getMessage());
        return  modelAndView;
    }

    @RequestMapping(value = "/removeClassmate", method = RequestMethod.POST)
    public ModelAndView removeClassmate(@RequestParam("class_id") long classId,
                                      @RequestParam("user_id") long userId, HttpSession session,
                                      @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView("manage_classmates");
        long managerId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        classService.removeClassmate(managerId, userId, classId);
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", managerId)
                .addObject("clazz", classService.getClassInformation(classId).getResult())
                .addObject("classmates", classService.getUsersByClassId(classId, true).getResult())
                .addObject("applyUsers", classService.getUsersByClassId(classId, false).getResult());
        return  modelAndView;
    }

    @RequestMapping(value = "/refuseJoin", method = RequestMethod.POST)
    public ModelAndView refuseJoining(@RequestParam("class_id") long classId,
                                      @RequestParam("user_id") long userId, HttpSession session,
                                      @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView("manage_classmates");
        long managerId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        classService.refuseJoinClass(managerId, userId, classId);
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", managerId)
                .addObject("clazz", classService.getClassInformation(classId).getResult())
                .addObject("classmates", classService.getUsersByClassId(classId, true).getResult())
                .addObject("applyUsers", classService.getUsersByClassId(classId, false).getResult());
        return  modelAndView;
    }

    @RequestMapping(value = "/allowJoin", method = RequestMethod.POST)
    public ModelAndView allowJoining(@RequestParam("class_id") long classId,
                                      @RequestParam("user_id") long userId, HttpSession session,
                                      @SessionAttribute(SessionContext.ATTR_USER_NAME) String displayName) {
        ModelAndView modelAndView = new ModelAndView("manage_classmates");
        long managerId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        classService.allowJoinClass(managerId, userId, classId);
        modelAndView.addObject("has_error", false)
                .addObject("error_message", "")
                .addObject("display_name", displayName)
                .addObject("user_id", managerId)
                .addObject("clazz", classService.getClassInformation(classId).getResult())
                .addObject("classmates", classService.getUsersByClassId(classId, true).getResult())
                .addObject("applyUsers", classService.getUsersByClassId(classId, false).getResult());
        return  modelAndView;
    }

    @RequestMapping(value = "/modifyDescription", method = RequestMethod.POST)
    @ResponseBody
    public void modifyDescription(@RequestParam("class_id") long classId, HttpSession session,
                                  @RequestParam("description") String description,
                                  HttpServletResponse response) throws IOException {
        long managerId = Long.parseLong(session.getAttribute(SessionContext.ATTR_USER_ID).toString());
        PrintWriter out = response.getWriter();
        if (classService.modifyDescription(managerId, classId, description).isSuccessful()) {
            out.append("successful");
            out.flush();
        } else {
            out.append("failed");
            out.flush();
        }
    }
}
