package com.forestnewark;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by forestnewark on 4/18/17.
 */
@Component
public class UserRepository {

 @Autowired
 JdbcTemplate template;

    public List<User> getAllUsers(){

        return template.query("SELECT personid,firstname,lastname,rank,permission FROM person ",
                new Object[]{},
                ((resultSet, i) -> new User(
                        resultSet.getInt("personid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("rank"),
                        resultSet.getString("permission")

                ))
                );

    }





}
