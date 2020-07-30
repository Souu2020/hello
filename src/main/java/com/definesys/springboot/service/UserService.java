package com.definesys.springboot.service;

import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.query.session.MpaasSession;
import com.definesys.springboot.dao.UserDao;
import com.definesys.springboot.mpaas.Verifytoken;
import com.definesys.springboot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserService {
    @Autowired(required = false)
    private UserDao udao;

    UserProfile userProfile = MpaasSession.getUserProfile();

//    public User service(String uid) {
//        return udao.getUserByUid(uid);
//    }

    public boolean validateToken(String token) {
        boolean bool = false;
        if (token.equals("definesys")) {
            bool = true;
        }
        return bool;
    }

    public List<Employee> getUsers() {
        return udao.getAllEmployees();
    }

    //用户注册
    public MdbUser registNewUser(MdbUser mdbUser) {
        if (udao.isExistByAccount(mdbUser.getAccount())) {
            throw new MpaasBusinessException("该账号已被注册");
        }
        if (udao.isExistByNickName(mdbUser.getNickname())) {
            throw new MpaasBusinessException("该昵称已被使用");
        }
        if (udao.isExistByPhone(mdbUser.getPhone())) {
            throw new MpaasBusinessException("该手机号已被注册");
        }

        return udao.registNewUser(mdbUser);
    }

    //用户登录
    public UserProfile validateByAccountAndPwd(String account, String password) {
        MdbUser mdbUser = udao.validateByAccountAndPwd(account, password);
        if (mdbUser != null) {
            userProfile.setAnonymous(false);
            userProfile.setToken("definesys");
            userProfile.setUid(mdbUser.getAccount());
            userProfile.setUserName("上海得帆信息技术有限公司");
        }
        return userProfile;
    }

    //获取用户信息
    public MdbUser getUserInfo(String account) {
        MdbUser mdbUser = (MdbUser)udao.getUserInfoByAccount(account);
        if(mdbUser == null) {
            throw new MpaasBusinessException("该昵称不存在");
        }
        mdbUser.setPassword("");
        mdbUser.setRowId("");
        return mdbUser;

    }

    //新增演员
    public MdbActor insertNewActor(String name, String pinyin, Date birthday, String town, String sex, String description, String avatar) {
        MdbActor mdbActor = new MdbActor();
        mdbActor.setName(name);
        mdbActor.setPinyin(pinyin);
        mdbActor.setBirthday(birthday);
        mdbActor.setTown(town);
        mdbActor.setSex(sex);
        mdbActor.setDescription(description);
        mdbActor.setAvatar(avatar);
        return udao.insertNewActor(mdbActor);
    }

    //按照id删除演员
    public Integer deleteActorById(String rowId) {
        return udao.deleteActorById(rowId);
    }

    //修改演员信息
    public Integer updateActor(String rowId, String name, String pinyin, Date birthday, String town, String sex, String description, String avatar) {
        MdbActor mdbActor = new MdbActor();
        mdbActor.setId(rowId);
        mdbActor.setName(name);
        mdbActor.setPinyin(pinyin);
        mdbActor.setBirthday(birthday);
        mdbActor.setTown(town);
        mdbActor.setSex(sex);
        mdbActor.setDescription(description);
        mdbActor.setAvatar(avatar);
        return udao.updateActor(mdbActor);
    }

    //新增电影标签
    public MdbMovieTag insertMVtag(String tagName, String status) {
        MdbMovieTag mdbMovieTag = new MdbMovieTag();
        mdbMovieTag.setTagName(tagName);
        if(status == null) {
            mdbMovieTag.setStatus("ENABLE");
        } else {
            mdbMovieTag.setStatus(status);
        }
        return udao.insertMVtag(mdbMovieTag);
    }

    //删除电影标签
    public Integer deleteMVTagById(String rowId) {
        return udao.deleteMVTagById(rowId);
    }

    //修改电影标签
    public MdbMovieTag updateMVTag(String rowId, String tagName, String status) {
        MdbMovieTag mdbMovieTag = new MdbMovieTag();
        mdbMovieTag.setStatus(status);
        mdbMovieTag.setTagName(tagName);
        return udao.updateMVTag(rowId,mdbMovieTag);
    }

    //插入电影信息，以及职员，标签与电影的关系维护
    public MdbMovie insertMV(MdbMovie mdbMovie, String tagList, String actorList,String editorList,String directorList) {
        String rowId = udao.insertMV(mdbMovie);

        String[] tagIds = tagList.split(";");
        String[] actorIds = actorList.split(";");
        String[] editorIds = editorList.split(";");
        String[] directorIds = directorList.split(";");

        //影片职员关系(添加关系) 维护
        //1.演员
        for(String aId : actorIds) {
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(aId);
            movieActorRel.setRole("actor");
            udao.addMVActorRel(movieActorRel);
        }
        //2.导演
        for(String aId : directorIds) {
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(aId);
            movieActorRel.setRole("director");
            udao.addMVActorRel(movieActorRel);
        }
        //3.编剧
        for(String aId : editorIds) {
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(aId);
            movieActorRel.setRole("editor");
            udao.addMVActorRel(movieActorRel);
        }
        //影片标签关系(添加关系)
        for(String tId : tagIds) {
            MdbMovieTagRel movieTagRel = new MdbMovieTagRel();
            movieTagRel.setMovieId(rowId);
            movieTagRel.setTagId(tId);
            udao.addMVTagRel(movieTagRel);
        }
        return mdbMovie;
    }

    //删除电影信息，并维护影片标签关系，影片职员关系
    public Integer deleteMV(String rowId) {
        udao.deleteMARelByMid(rowId);
        udao.deleteMTRelByMid(rowId);
        return udao.deleteMVById(rowId);
    }

    //更新电影信息，并维护影片标签关系，影片职员关系
    public MdbMovie updateMV(MdbMovie mdbMovie, String tagList, String actorList, String editorList, String directorList) {
        //

        String[] tagIds = tagList.split(";");
        String[] actorIds = actorList.split(";");
        String[] editorIds = editorList.split(";");
        String[] directorIds = directorList.split(";");
        String rowId = mdbMovie.getId();
        String role = "";
        //影片职员关系(更新关系) 维护
        //1.演员
        for(String aId : actorIds) {
            if(aId.equals("")) {
                break;
            }
            role = "actor";
            udao.deleteMARelByMidAndRole(rowId,role);
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(aId);
            movieActorRel.setRole(role);
            udao.addMVActorRel(movieActorRel);
        }
        //2.导演
        for(String dId : directorIds) {
            if(dId.equals("")) {
                break;
            }
            role = "director";
            udao.deleteMARelByMidAndRole(rowId,role);
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(dId);
            movieActorRel.setRole(role);
            udao.addMVActorRel(movieActorRel);
        }
        //3.编剧
        for(String eId : editorIds) {
            if(eId.equals("")) {
                break;
            }
            role = "editor";
            udao.deleteMARelByMidAndRole(rowId,role);
            MdbMovieActorRel movieActorRel = new MdbMovieActorRel();
            movieActorRel.setMovieId(rowId);
            movieActorRel.setActorId(eId);
            movieActorRel.setRole(role);
            udao.addMVActorRel(movieActorRel);
        }
        //影片标签关系(更新关系)
        for(String tId : tagIds) {
            if(tId.equals("")) {
                break;
            }
            udao.deleteMTRelByMid(rowId);
            MdbMovieTagRel movieTagRel = new MdbMovieTagRel();
            movieTagRel.setMovieId(rowId);
            movieTagRel.setTagId(tId);
            udao.addMVTagRel(movieTagRel);
        }

        return udao.updateMV(mdbMovie);
    }

    //查询并返回演员及其影片列表
    public Map<MdbActor,String> queryActor(String name,String mode) {
        Map<MdbActor,String> map = new HashMap<MdbActor,String>();

        List<MdbActor> actorList = udao.queryActor(name,mode);
        for (MdbActor actor : actorList) {
            String mvs = udao.queryMVsOfActor(actor.getId());
            map.put(actor,mvs);
        }
        return map;
    }


    //用户头像上传
    public String uploadImage(MultipartFile file, String account,String tag,String docId) {
        String fPath = udao.uploadImage(file);

        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;


        MdbAttachment mdbAttachment = new MdbAttachment();
        mdbAttachment.setUuid(fPath);
        mdbAttachment.setAttachmentName(dFileName);
        mdbAttachment.setTag(tag);
        mdbAttachment.setDocId(docId);

        //插入到附件表
        String attId = udao.addAttachment(mdbAttachment);
        //插入到用户表
        udao.updateAttToUser(account,attId);

        return fPath;
    }

    //电影海报上传
    public String uploadPoster(MultipartFile file, String id, String tag, String docId) {
        String fPath = udao.uploadImage(file);

        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;

        MdbAttachment mdbAttachment = new MdbAttachment();
        mdbAttachment.setUuid(fPath);
        mdbAttachment.setAttachmentName(dFileName);
        mdbAttachment.setTag(tag);
        mdbAttachment.setDocId(docId);
        //插入到附件表
        String attId = udao.addAttachment(mdbAttachment);
        udao.updateAttToMovie(id,attId);

        return fPath;
    }

    public String addComment(String userId, String movieId, String content, String star) {
        //计算并更新电影评分
        udao.updateMVStar(movieId,star);


        MdbComment comment = new MdbComment();
        comment.setUserId(userId);
        comment.setMovieId(movieId);
        comment.setContent(content);
        comment.setStar(star);
        //插入评论表
        return udao.addComment(comment);
    }

    public Map<String,String> queryMVStar(String mvId) {
        return udao.queryMVStar(mvId);
    }
}
