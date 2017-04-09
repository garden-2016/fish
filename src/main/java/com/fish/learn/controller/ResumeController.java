package com.fish.learn.controller;

import com.fish.learn.dao.ResumeDao;
import com.fish.learn.dto.Paging;
import com.fish.learn.dto.Response;
import com.fish.learn.model.Resume;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @author noName
 * @since
 */
@RestController
@RequestMapping("/resume")
public class ResumeController extends BaseController {

    @Autowired
    private ResumeDao resumeDao;

    @RequestMapping("/paging/{pageNo}/{pageSize}")
    @ResponseBody
    public Response<Paging<Resume>> paging(@PathVariable("pageNo")Integer pageNo, @PathVariable("pageSize")Integer pageSize) throws Exception{
        return Response.ok(resumeDao.paging( pageNo , pageSize ));
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Response<Resume> getById(@PathVariable Integer id) throws Exception {
        return Response.ok(resumeDao.getById(id));
    }

    @RequestMapping("/update")
    @ResponseBody
    public Response<Void> updateById(@RequestBody Resume resume ) throws Exception{
        resumeDao.update( resume );
        return Response.ok();
    }

    @RequestMapping( value =  {"/add"} , method = RequestMethod.POST )
    @ResponseBody
    public Response<Integer> insertById(@RequestBody Resume resume ) throws Exception{
        int id = resumeDao.insert(resume);
        StringBuffer imageUrl = new StringBuffer();
        String fileUrl = this.verifyImgFileUrl(id , imageUrl);
        String showUrl = this.verifyImgShowUrl(id);
        resumeDao.createVerifyImg( id , showUrl , fileUrl );
        resume.setVerifyImg( imageUrl.toString() );
        resumeDao.update( resume );
        return Response.ok(id);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Response<Void> deleteById(@PathVariable Integer id) throws Exception{
        resumeDao.delete(id);
        return Response.ok();
    }

    @PostMapping("/upload")
    @ResponseBody
    public Response<String> fileUpload( @RequestParam("file") MultipartFile file) throws Exception{
        String path = StringUtils.join( ROOT_IMG_PATH , File.separator );
        String hashFileName = StringUtils.join( UUID.randomUUID() , ".jpg" ) ;
        File fPath = new File(path);
        fPath.mkdirs();
        String fileUrl = StringUtils.join( "http://" , DO_MAIN , File.separator , IMG_PATH , File.separator , hashFileName );
        FileOutputStream ops = new FileOutputStream( StringUtils.join( path , File.separator , hashFileName ) );
        IOUtils.copy( file.getInputStream() , ops );
        ops.flush();
        ops.close();
        file.getInputStream().close();
        return Response.ok(fileUrl);
    }

}
