package com.definesys.springboot.dao;

import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.springboot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class UserDao {
    @Autowired
    private MpaasQueryFactory sw;

//    public User getUserByUid(String uid) {
//        User u = new User();
//        u.setUid("s001");
//        u.setUserName("zengxiangyu");
//        u.setEmail("xiangyu.zeng@definesys.com");
//        return u;
//    }

    public List<Employee> getAllEmployees() {
        return sw.buildQuery()
                .table("employees")
                .doQuery(Employee.class);
    }

    //查询用户账号是否存在
    public boolean isExistById(String id) {
        boolean bool = false;
        List<MdbUser> mdbUser = sw.buildQuery()
                .table("MDB_USER")
                .eq("ID", id)
                .doQuery(MdbUser.class);
        if (mdbUser.size() > 0) {
            bool = true;
        }
        return bool;
    }

//    //MD5加密方法
//    private String toMD5(String str) {
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//            md.update(str.getBytes());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return new BigInteger(1, md.digest()).toString(16);
//    }

    //查询用户昵称是否存在
    public boolean isExistByNickName(String nickname) {
        boolean bool = false;
        List<MdbUser> mdbUser = sw.buildQuery()
                .table("MDB_USER")
                .eq("NICKNAME", nickname)
                .doQuery(MdbUser.class);
        if (mdbUser.size() > 0) {
            bool = true;
        }
        return bool;
    }

    //查询用户昵称是否存在
    public boolean isExistByPhone(String phone) {
        boolean bool = false;
        List<MdbUser> mdbUser = sw.buildQuery()
                .table("MDB_USER")
                .eq("PHONE", phone)
                .doQuery(MdbUser.class);
        if (mdbUser.size() > 0) {
            bool = true;
        }
        return bool;
    }

    //查询用户账号是否存在
    public boolean isExistByAccount(String account) {
        boolean bool = false;
        List<MdbUser> mdbUser = sw.buildQuery()
                .table("MDB_USER")
                .eq("ACCOUNT", account)
                .doQuery(MdbUser.class);
        if (mdbUser.size() > 0) {
            bool = true;
        }
        return bool;
    }

    //插入新用户到数据库（用户注册）
    public MdbUser registNewUser(MdbUser mdbUser) {
        //先加密，再插入密文存入数据库
        mdbUser.setPassword(DigestUtils.md5DigestAsHex(mdbUser.getPassword().getBytes()));
        sw.buildQuery()
                .table("MDB_USER")
                .doInsert(mdbUser);
        return mdbUser;
    }

    //校验账号与密码
    public MdbUser validateByAccountAndPwd(String account, String password) {
        MdbUser mdbUser = new MdbUser();
        List<MdbUser> mdbUsers = sw.buildQuery()
                .table("MDB_USER")
                .eq("ACCOUNT", account)
                .and()
                .table("MDB_USER")
                .eq("PASSWORD", DigestUtils.md5DigestAsHex(password.getBytes()))
                .doQuery(MdbUser.class);
        if (mdbUsers.size() > 0) {
            mdbUser = mdbUsers.get(0);
        }
        return mdbUser;
    }

    //通过昵称获取用户信息（不包括密码）
    public MdbUser getUserInfoByAccount(String account) {
        MdbUser mdbUser = new MdbUser();
        List<MdbUser> mdbUsers = sw.buildQuery()
                .table("MDB_USER")
                .eq("ACCOUNT", account)
                .doQuery(MdbUser.class);
        if (mdbUsers.size() > 0) {
            mdbUser = mdbUsers.get(0);
        }
        return mdbUser;
    }

    //插入新演员信息
    public MdbActor insertNewActor(MdbActor mdbActor) {
        sw.buildQuery()
                .table("MDB_ACTOR")
                .doInsert(mdbActor);
        return mdbActor;
    }

    //按照id删除演员
    public Integer deleteActorById(String rowId) {
        //***************
        /**
         * 解除与该演员有关的电影
         */
        Integer result = sw.buildQuery()
                .rowid("id",rowId)
                .table("MDB_ACTOR")
                .doDelete(MdbActor.class);
        return result;
    }

    //修改职员（演员）信息
    public Integer updateActor(MdbActor mdbActor) {
        Integer result = sw.buildQuery()
                .table("MDB_ACTOR")
                .rowid("id", mdbActor.getId())
                .doUpdate(mdbActor);
        return result;
    }

    //新增电影标签
    public MdbMovieTag insertMVtag(MdbMovieTag mdbMovieTag) {
        sw.buildQuery()
                .table("MDB_MOVIE_TAG")
                .doInsert(mdbMovieTag);
        return mdbMovieTag;
    }

    //删除电影标签
    public Integer deleteMVTagById(String rowId) {
        Integer result = sw.buildQuery()
                .rowid("id",rowId)
                .table("MDB_MOVIE_TAG")
                .update("STATUS","DISABLE")
                .doUpdate();
        return result;
    }

    //修改电影标签
    public MdbMovieTag updateMVTag(String rowId, MdbMovieTag mdbMovieTag) {
        sw.buildQuery()
                .rowid("id",rowId)
                .doUpdate(mdbMovieTag);
        return mdbMovieTag;
    }

    //insert电影信息表
    public String insertMV(MdbMovie mdbMovie) {
        sw.buildQuery()
                .table("MDB_MOVIE")
                .doInsert(mdbMovie);
        return mdbMovie.getId();
    }

    //维护影片职员关系
    public MdbMovieActorRel addMVActorRel(MdbMovieActorRel movieActorRel) {
            sw.buildQuery()
                    .table("MDB_MOVIE_ACTOR_REL")
                    .doInsert(movieActorRel);
        return movieActorRel;
    }

    //维护影片标签关系
    public MdbMovieTagRel addMVTagRel(MdbMovieTagRel movieTagRelel) {
            sw.buildQuery()
                    .table("MDB_MOVIE_TAG_REL")
                    .doInsert(movieTagRelel);
        return movieTagRelel;
    }

    //按照id删除电影信息
    public Integer deleteMVById(String rowId) {
        Integer result = sw.buildQuery()
                .table("MDB_MOVIE")
                .eq("ID", rowId)
                .doDelete();
        return result;
    }

    //按照电影编号删除相应的 影片、职员关系
    public Integer deleteMARelByMid(String rowId) {
        Integer result = sw.buildQuery()
                .table("MDB_MOVIE_ACTOR_REL")
                .eq("MOVIE_ID", rowId)
                .doDelete();
        return result;

    }

    //按照电影编号删除相应的 影片、标签（Tag）关系
    public Integer deleteMTRelByMid(String rowId) {
        Integer result = sw.buildQuery()
                .table("MDB_MOVIE_TAG_REL")
                .eq("MOVIE_ID", rowId)
                .doDelete();
        return result;
    }

    //更新电影信息
    public MdbMovie updateMV(MdbMovie mdbMovie) {
        sw.buildQuery()
                .table("MDB_MOVIE")
                .eq("ID",mdbMovie.getId())
                .doUpdate(mdbMovie);
        return mdbMovie;
    }

    public Integer deleteMARelByMidAndRole(String rowId,String role) {
        Integer result = sw.buildQuery()
                .table("MDB_MOVIE_ACTOR_REL")
                .eq("MOVIE_ID", rowId)
                .and()
                .eq("ROLE",role)
                .doDelete();
        return result;
    }

    public List<MdbActor> queryActor(String name,String mode) {
        List<MdbActor> actorList = new ArrayList<MdbActor>();
        if(mode.equals("1")) {
            actorList = sw.buildQuery()
                    .table("MDB_ACTOR")
                    .likeNocase("NAME", name)
                    .doQuery(MdbActor.class);
        } else if(mode.equals("2")) {
            actorList = sw.buildQuery()
                    .table("MDB_ACTOR")
                    .likeNocase("PINYIN", name)
                    .doQuery(MdbActor.class);
        } else {
            throw new MpaasBusinessException("Wrong mode");
        }
        return actorList;
    }

    public String queryMVsOfActor(String id) {
        List<String> mvIds = sw.buildQuery()
                .table("MDB_MOVIE_ACTOR_REL")
                .select("MOVIE_ID")
                .eq("ACTOR_ID", id)
                .doQuery(String.class);
        String mvs = "";
        int i = 0;
        for(String mvId : mvIds) {
            if(i != mvIds.size()) {
                mvs = mvs + mvId + ";";
            } else {
                mvs = mvs + mvId;
            }
            i++;
        }
        return mvs;
    }

    //保存上传的文件
    public String uploadImage(MultipartFile file) {
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件
        String path = "D:/code/t-springboot/";
        //生成保存文件
        File uploadFile = new File(path+dFileName);
        System.out.println(uploadFile);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            throw new MpaasBusinessException("网络异常，请联系管理员");
        }
        return path+dFileName;
    }

    //插入到附件表
    public String addAttachment(MdbAttachment attachment) {
        sw.buildQuery()
                .table("MDB_ATTACHMENT")
                .doInsert(attachment);
        return attachment.getId();
    }

    //将附件id更新到user表
    public String updateAttToUser(String account, String attId) {
        sw.buildQuery()
                .table("MDB_USER")
                .eq("ACCOUNT",account)
                .update("AVATAR",attId)
                .doUpdate();
        return attId;
    }

    //将附件id更新到movie表
    public String updateAttToMovie(String id, String attId) {
        sw.buildQuery()
                .table("MDB_MOVIE")
                .eq("ID",id)
                .update("AVATAR",attId)
                .doUpdate();
        return attId;
    }

    public String addComment(MdbComment comment) {
        sw.buildQuery()
                .table("MDB_COMMENT")
                .doInsert(comment);
        return comment.getId();
    }

    public Float updateMVStar(String movieId, String star) {
        Float result = 0F;
        List<String> oldStar = sw.buildQuery()
                .table("MDB_MOVIE")
                .eq("ID",movieId)
                .select("STAR")
                .doQuery(String.class);
        if(oldStar.size() == 0 || oldStar.get(0).equals("") || oldStar.get(0) == null) {
            sw.buildQuery()
                    .table("MDB_MOVIE")
                    .eq("ID",movieId)
                    .update("STAR",star)
                    .doUpdate();
        } else {
            Float oStar = Float.valueOf(oldStar.get(0));
            Float nStar = Float.valueOf(star);
            result = (oStar+nStar)/2;
            sw.buildQuery()
                    .table("MDB_MOVIE")
                    .eq("ID",movieId)
                    .update("STAR",result)
                    .doUpdate();
        }
        return result;
    }

    public Map<String, String> queryMVStar(String mvId) {
        Map<String, String> map = new HashMap<String, String>();
        List<String> oldStar = sw.buildQuery()
                .table("MDB_MOVIE")
                .eq("ID",mvId)
                .select("STAR")
                .doQuery(String.class);
        if(oldStar.size() == 0 || oldStar.get(0).equals("") || oldStar.get(0) == null) {
            map.put("star","暂无评分");
        } else {
            List<String> star1 = sw.buildQuery()
                    .table("MDB_COMMENT")
                    .eq("MOVIE_ID",mvId)
                    .gteq("STAR",0)
                    .lt("STAR",1)
                    .select("STAR")
                    .doQuery(String.class);
            List<String> star2 = sw.buildQuery()
                    .table("MDB_COMMENT")
                    .eq("MOVIE_ID",mvId)
                    .gteq("STAR",1)
                    .lt("STAR",2)
                    .select("STAR")
                    .doQuery(String.class);
            List<String> star3 = sw.buildQuery()
                    .table("MDB_COMMENT")
                    .eq("MOVIE_ID",mvId)
                    .gteq("STAR",2)
                    .lt("STAR",3)
                    .select("STAR")
                    .doQuery(String.class);
            List<String> star4 = sw.buildQuery()
                    .table("MDB_COMMENT")
                    .eq("MOVIE_ID",mvId)
                    .gteq("STAR",3)
                    .lt("STAR",4)
                    .select("STAR")
                    .doQuery(String.class);
            List<String> star5 = sw.buildQuery()
                    .table("MDB_COMMENT")
                    .eq("MOVIE_ID",mvId)
                    .eq("STAR",5)
                    .select("STAR")
                    .doQuery(String.class);
            Integer count1 = star1.size();
            Integer count2 = star1.size();
            Integer count3 = star1.size();
            Integer count4 = star1.size();
            Integer count5 = star1.size();
            Integer sum = count1+count2+count3+count4+count5;
            map.put("star1",(count1/sum)+"");
            map.put("star2",(count2/sum)+"");
            map.put("star3",(count3/sum)+"");
            map.put("star4",(count4/sum)+"");
            map.put("star5",(count5/sum)+"");
        }
        return map;
    }
}
