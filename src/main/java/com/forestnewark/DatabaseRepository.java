package com.forestnewark;


import com.forestnewark.beans.ActionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by forestnewark on 4/18/17.
 */
@Component
public class DatabaseRepository {

    final
    JdbcTemplate template;

    @Autowired
    public DatabaseRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<User> getAllUsers() {

        return template.query("SELECT personid,firstname,lastname,rank,permission,email,username,password FROM person ",
                new Object[]{},
                ((resultSet, i) -> new User(
                        resultSet.getInt("personid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("rank"),
                        resultSet.getString("permission"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")

                ))
        );

    }

    public List<ActionItem> getAllActionItems() {
        return template.query("SELECT * FROM actionitems",
                new Object[]{},
                (resultSet, i) -> new ActionItem(
                        resultSet.getInt("actionitemid"),
                        resultSet.getString("item"),
                        resultSet.getString("status"),
                        resultSet.getString("addedby")
                )
        );

    }


    public void addUser(User user) {


        template.update("INSERT INTO person (firstname, lastname,rank,permission,email,username,password) VALUES (?,?,?,?,?,?,?)",

                user.getFirstName(), user.getLastName(), user.getRank(), user.getPermission(), user.getEmail(), user.getFirstName() + "-" + user.getLastName(), user.getPassword());
    }


}





