/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.app;

import com.sg.flooring.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matthewswanberg
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller
                = ctx.getBean("controller", FlooringController.class);

        controller.run();
    }
}
