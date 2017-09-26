package com.sheng.process;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shengxingyue on 2017/9/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ActivitiTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void testActiviti() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("check", "123");
        String instanceId = runtimeService.startProcessInstanceByKey("ActivitiTest", "ActivitiTest", map).getId();

        System.out.println("流程开始");

        // 1
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        System.out.println(task.getName());
        taskService.complete(task.getId());

        // 2
        task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        System.out.println(task.getName());
        taskService.complete(task.getId());

        // 3
        task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        System.out.println(task.getName());
        taskService.complete(task.getId());

        System.out.println("流程结束");
    }
}
