package com.fish.learn.controller;

import com.fish.learn.dao.ResumeDao;
import com.fish.learn.dto.Response;
import com.fish.learn.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response<Void> deleteByid(@PathVariable Integer id) throws Exception{
        resumeDao.delete(id);
        return Response.ok();
    }

}
