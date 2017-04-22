package com.forestnewark;


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

//    public List<ActionItem> getAllActionItems() {
//        return template.query("SELECT * FROM actionitems",
//                new Object[]{},
//                (resultSet, i) -> new ActionItem(
//                        resultSet.getInt("actionitemid"),
//                        resultSet.getString("actionitem"),
//                        resultSet.getString("status"),
//                        resultSet.getString("priority"),
//                        resultSet.getString("comments")
//                )
//        );
//
//    }


    public void updateUser(User user) {

        if(user.getId() != null){


            template.update("UPDATE person SET firstname =?, lastname = ?,rank = ?,permission = ?,email = ?, username =?, password = ? WHERE personid = ?",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRank(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getFirstName() + "-" +user.getLastName(),
                    user.getPassword(),
                    user.getId()
                    );

        }else {
            template.update("INSERT INTO person (firstname, lastname,rank,permission,email,username,password) VALUES (?,?,?,?,?,?,?)",

                    user.getFirstName(), user.getLastName(), user.getRank(), user.getPermission(), user.getEmail(), user.getFirstName() + "-" + user.getLastName(), user.getPassword());
        }


    }

    public void deleteUser(User user){

        template.update("DELETE FROM person WHERE personid = ?",user.getId());
    }


//    public void updateActionItem(ActionItem actionItem){
//
//        if(actionItem.getId() != null){
//
//            template.update("UPDATE actionitems SET actionitem = ?,status = ?,priority = ?, comments = ?",
//                    actionItem.getItem(),
//                    actionItem.getStatus(),
//                    actionItem.getPriority(),
//                    actionItem.getComments()
//                    );
//        }
//
//        else{
//            template.update("INSERT INTO actionitems (actionitem, status, priority, comments) VALUES (?,?,?,?)",
//                    actionItem.getItem(),
//                    actionItem.getStatus(),
//                    actionItem.getPriority(),
//                    actionItem.getComments()
//                    );
//        }
//    }
//
//    public void deleteActionItem(ActionItem item){
//        template.update("DELETE FROM actionitems WHERE actionitemid = ?",
//                item.getId()
//                );
//    }





}





