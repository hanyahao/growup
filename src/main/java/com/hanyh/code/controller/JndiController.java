package com.hanyh.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class JndiController {


    @RequestMapping("/jndi.htm")
    public String http(HttpServletRequest request, HttpServletResponse response) throws SQLException {
//        Connection connection = dataSource.getConnection();
//        String sql = "select * from user";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ResultSet result = ps.executeQuery();
//        while (result.next()) {
//            String id = result.getString("id");
//            String name = result.getString("name");
//            String age = result.getString("age");
//            System.out.println("id="+id+ " name="+name+" age="+age);
//        }
//        connection.close();
//        ps.close();
        return "http";
    }
}
