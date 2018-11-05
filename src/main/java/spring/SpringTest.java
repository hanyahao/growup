package spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import pattern.factory.Factory;

import java.util.ResourceBundle;

public class SpringTest {
    public static void main(String[] args) {
        String path = "classpath:applicationContent.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(path);
        User user = (User)ac.getBean("user");
        user.setName("sdfdsfsf");
        System.out.println(user.getName());

        BeanFactory factoryBean = new FileSystemXmlApplicationContext();

    }
}
