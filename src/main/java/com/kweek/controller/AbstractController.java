package com.kweek.controller;

import com.kweek.service.ServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Harrison on 2017-01-24.
 */

public abstract class AbstractController<T> {

    private ServiceInterface<T> serviceInterface;

    public AbstractController(ServiceInterface<T> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Boolean create(@RequestBody T t) {
        return serviceInterface.save(t) != null;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<T> findAll() {
        return serviceInterface.findAll();
    }

    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    @ResponseBody
    public Page<T> findAll(@RequestParam("page") int page,
                           @RequestParam("pageSize") int pageSize) {
        PageRequest pageRequest = new PageRequest(page, pageSize);
        return serviceInterface.findAll(pageRequest);
    }

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    @ResponseBody
    public Page<T> search(@PathVariable("param") String param,
                          @RequestParam("page") int page,
                          @RequestParam("pageSize") int pageSize) {
        PageRequest pageRequest = new PageRequest(page, pageSize);
        return serviceInterface.findByParam(param, pageRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T find(@PathVariable("id") long id) {
        return serviceInterface.find(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean update(@RequestBody T t) {
        return serviceInterface.update(t);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestParam("id") long id) {
        serviceInterface.delete(id);
    }

    /*@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean delete(@RequestBody T t) {
        return serviceInterface.delete(t);
    }*/
}
