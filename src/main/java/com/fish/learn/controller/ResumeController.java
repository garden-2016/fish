package com.fish.learn.controller;

import com.fish.learn.dao.ResumeDao;
import com.fish.learn.dto.Response;
import com.fish.learn.model.Resume;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author noName
 * @since
 */
@RestController
@RequestMapping("/resume")
public class ResumeController extends BaseController {

    @Autowired
    private ResumeDao resumeDao;

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

    @RequestMapping("/add")
    @ResponseBody
    public Response<Integer> insertById(@RequestBody Resume resume ) throws Exception{
        return Response.ok(resumeDao.insert(resume));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Response<Void> deleteById(@PathVariable Integer id) throws Exception{
        resumeDao.delete(id);
        return Response.ok();
    }

    @PostMapping("/upload/{id}")
    @ResponseBody
    public Response<String> fileUpload(@PathVariable("id") String id ,@RequestParam("file") MultipartFile file) throws Exception{
        String path = StringUtils.join( ROOT_IMG_PATH , File.separator , id );
        File fPath = new File(path);
        fPath.mkdirs();
        String fileUrl = StringUtils.join( DO_MAIN , File.separator , IMG_PATH , File.separator , id , File.separator , file.getOriginalFilename() );
        FileOutputStream ops = new FileOutputStream( StringUtils.join( path , File.separator , file.getOriginalFilename() ) );
        IOUtils.copy( file.getInputStream() , ops );
        ops.flush();
        ops.close();
        file.getInputStream().close();
        return Response.ok(fileUrl);
    }


}
