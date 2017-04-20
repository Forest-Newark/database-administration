/**
 * Created by forestnewark on 4/20/17.
 */
$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});




$('#user-modal').on('show.bs.modal', function (e) {
    //Set Data from clicked item
   var id = ($(e.relatedTarget).attr("data-id"));
   var firstName = ($(e.relatedTarget).attr("data-firstName"));
   var lastName  = ($(e.relatedTarget).attr("data-lastName"));
   var rank =  ($(e.relatedTarget).attr("data-rank"));
   var permission = ($(e.relatedTarget).attr("data-permission"));
   var email = ($(e.relatedTarget).attr("data-email"));
   var username = ($(e.relatedTarget).attr("data-username"));
   var password = ($(e.relatedTarget).attr("data-password"));

   //Populate modal data
   $("#user-modal").find("input[name='id']").val(id);
   $("#user-modal").find("input[name='firstName']").val(firstName);
   $("#user-modal").find("input[name='lastName']").val(lastName);
   $("#user-modal").find("select[name='rank']").val(rank);
   $("#user-modal").find("select[name='permission']").val(permission);
   $("#user-modal").find("input[name='email']").val(email);
   $("#user-modal").find("input[name='username']").val(username);
   $("#user-modal").find("input[name='password']").val(password);





});