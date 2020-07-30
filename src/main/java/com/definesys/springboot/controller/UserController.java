package com.definesys.springboot.controller;

import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.query.session.MpaasSession;
import com.definesys.springboot.pojo.*;
import com.definesys.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author xiangyu.zeng 465
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    UserProfile userProfile = MpaasSession.getUserProfile();
    //localhost:8080/t-springboot/user/query?uid=100
//    @RequestMapping(value = "/t-springboot/user/query", method = RequestMethod.GET)
//    public User showUser1(@RequestParam("uid") String uid) {
//        return userService.service(uid);
//    }

//    @RequestMapping(value = "/t-springboot/user/query/{uid}", method = RequestMethod.GET)
//    public User showUser2(@PathVariable(name = "uid") String uid) {
//        return userService.service(uid);
//    }

    @RequestMapping(value = "/t-springboot/user/query", method = RequestMethod.POST)
    public User showUser3(@RequestBody User user) {
        return user;
    }

    @GetMapping("/headers")
    public boolean checkToken(@RequestHeader(value = "token", required = false) String token) {
        return userService.validateToken(token);
    }


//    @GetMapping("/employees"
//    public List<Map<String, Object>> queryUser() {
//        System.out.println("queryUser()");
//        List<Map<String, Object>> employees = sw.buildQuery()
//                .table("employees")
//                .doQuery();
//        return employees;
//    }

    @GetMapping("/employees/all")
    public List<Employee> queryUsers() {
        return userService.getUsers();
    }

//    @RequestMapping(value = "/employees",method = RequestMethod.GET)
//    public List<Employee> queryUsers(@RequestParam String employeeId) {
//        return userService.getUserById(employeeId);
//    }

    //用户注册
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public Response regist(@RequestBody MdbUser mdbUser) {
        return Response.ok().data(userService.registNewUser(mdbUser));
    }

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response login(@RequestParam String account, @RequestParam String password) {
        return Response.ok().data(userService.validateByAccountAndPwd(account, password));
    }

    //用户头像上传
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Response uploadImage(@RequestParam MultipartFile file,
                                @RequestParam String account,
                                @RequestParam String tag,
                                @RequestParam String docId) {
        if (userProfile.getToken().equals("definesys") && false==userProfile.isAnonymous()) {
            return Response.ok().data(userService.uploadImage(file,account,tag,docId));
        } else {
            return Response.error("请登录后操作");
        }

    }

    //获取用户信息
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Response getUserInfo(@RequestParam String account) {
        if (userProfile.getToken().equals("definesys") && false==userProfile.isAnonymous()) {
            return Response.ok().data(userService.getUserInfo(account));
        } else {
            return Response.error("请登录后操作");
        }
    }
    //新增演员
    @RequestMapping(value = "/insertActor", method = RequestMethod.GET)
    public Response insertActor(@RequestParam String name,
                                   @RequestParam String pinyin,
                                   @RequestParam Date birthday,
                                   @RequestParam String town,
                                   @RequestParam String sex,
                                   @RequestParam String description,
                                   @RequestParam String avatar) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.insertNewActor(name,pinyin,birthday,town,sex,description,avatar));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //按照id删除演员
    @RequestMapping(value = "/deleteActor", method = RequestMethod.GET)
    public Response deleteActor(@RequestParam(value = "rowId") String rowId) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.deleteActorById(rowId));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //修改演员信息
    @RequestMapping(value = "/updateActor", method = RequestMethod.GET)
    public Response updateActor(@RequestParam(value = "rowId") String rowId,
                                @RequestParam String name,
                                @RequestParam String pinyin,
                                @RequestParam Date birthday,
                                @RequestParam String town,
                                @RequestParam String sex,
                                @RequestParam String description,
                                @RequestParam String avatar) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.updateActor(rowId,name,pinyin,birthday,town,sex,description,avatar));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //新增电影标签
    @RequestMapping(value = "/insertMVtag", method = RequestMethod.GET)
    public Response insertMVtag(@RequestParam String tagName,
                                @RequestParam String status) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.insertMVtag(tagName,status));
        } else {
            return Response.error("请登录后操作");
        }
    }
    //删除电影标签
    @RequestMapping(value = "/deleteMVTag", method = RequestMethod.GET)
    public Response deleteMVTag(@RequestParam(value = "rowId") String rowId) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.deleteMVTagById(rowId));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //修改电影标签信息
    @RequestMapping(value = "/updateMVTag", method = RequestMethod.GET)
    public Response updateMVTag(@RequestParam(value = "rowId") String rowId,
                                @RequestParam String tagName,
                                @RequestParam String status) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.updateMVTag(rowId,tagName,status));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //新建电影信息
    @RequestMapping(value = "/insertMV", method = RequestMethod.GET)
    public Response insertMV(@RequestParam String name,
                             @RequestParam Date showDate,
                             @RequestParam String type,
                             @RequestParam String national,
                             @RequestParam String language,
                             @RequestParam String avatar,
                             @RequestParam String actorList ,//规定用该String传输 影片演员（演员id;演员id;...） 列表
                             @RequestParam String tagList,//规定用该String传输 影片标签（标签id;标签id;...） 列表
                             @RequestParam String editerList ,//规定用该String传输 影片编剧（标签id;标签id;...） 列表
                             @RequestParam String directorList//规定用该String传输 影片导演（标签id;标签id;...） 列表
                                ) {
        if (request.getHeader("token").equals("definesys")) {
            MdbMovie mdbMovie = new MdbMovie();
            mdbMovie.setName(name);
            mdbMovie.setShowDate(showDate);
            mdbMovie.setType(type);
            mdbMovie.setNational(national);
            mdbMovie.setLanguage(language);
            mdbMovie.setAvatar(avatar);
            return Response.ok().data(userService.insertMV(mdbMovie,tagList,actorList,editerList,directorList));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //删除电影信息
    @RequestMapping(value = "/deleteMV", method = RequestMethod.GET)
    public Response deleteMV(@RequestParam(value = "rowId") String rowId) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.deleteMV(rowId));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //修改电影信息
    @RequestMapping(value = "/updateMV", method = RequestMethod.GET)
    public Response updateMV(@RequestParam(value = "rowId") String rowId,
                             @RequestParam String name,
                             @RequestParam Date showDate,
                             @RequestParam String type,
                             @RequestParam String national,
                             @RequestParam String language,
                             @RequestParam String avatar,
                             @RequestParam String actorList ,//规定用该String传输 影片演员（演员id;演员id;...） 列表
                             @RequestParam String tagList,//规定用该String传输 影片标签（标签id;标签id;...） 列表
                             @RequestParam String editorList ,//规定用该String传输 影片编剧（标签id;标签id;...） 列表
                             @RequestParam String directorList//规定用该String传输 影片导演（标签id;标签id;...） 列表
    ) {
        if (request.getHeader("token").equals("definesys")) {
            MdbMovie mdbMovie = new MdbMovie();
            mdbMovie.setId(rowId);
            mdbMovie.setName(name);
            mdbMovie.setShowDate(showDate);
            mdbMovie.setType(type);
            mdbMovie.setNational(national);
            mdbMovie.setLanguage(language);
            mdbMovie.setAvatar(avatar);
            return Response.ok().data(userService.updateMV(mdbMovie,tagList,actorList,editorList,directorList));
        } else {
            return Response.error("请登录后操作");
        }
    }

    //电影海报上传
    @RequestMapping(value = "/uploadPoster", method = RequestMethod.POST)
    public Response uploadPoster(@RequestParam MultipartFile file,
                                @RequestParam String id,
                                @RequestParam String tag,
                                @RequestParam String docId) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.uploadPoster(file,id,tag,docId));
        } else {
            return Response.error("请登录后操作");
        }

    }

    //用户评论
    @RequestMapping(value = "/addComment")
    public Response addComment(@RequestParam String userId,
                               @RequestParam String movieId,
                               @RequestParam String content,
                               @RequestParam String star) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.addComment(userId,movieId,content,star));
        } else {
            return Response.error("请登录后操作");
        }

    }

    //演员信息模糊查询(姓名:mode->1)(拼音:mode->2)
    @RequestMapping(value = "/queryActor", method = RequestMethod.GET)
    public Response queryActor(@RequestParam String name,
                               @RequestParam String mode) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.queryActor(name,mode));
        } else {
            return Response.error("请登录后操作");
        }
    }

//    //电影信息模糊查询MV
//    @RequestMapping(value = "/queryMV", method = RequestMethod.GET)
//    public Response queryMV(@RequestParam String name,
//                               @RequestParam String tags) {
//        if (request.getHeader("token").equals("definesys")) {
//            return Response.ok().data(userService.queryMV(name,tags));
//        } else {
//            return Response.error("请登录后操作");
//        }
//    }


    //电影评分信息查询
    @RequestMapping(value = "/queryMVStar", method = RequestMethod.GET)
    public Response queryMV(@RequestParam String mvId) {
        if (request.getHeader("token").equals("definesys")) {
            return Response.ok().data(userService.queryMVStar(mvId));
        } else {
            return Response.error("请登录后操作");
        }
    }

}
