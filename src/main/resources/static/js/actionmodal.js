/**
 * Created by forestnewark on 4/21/17.
 */
$('#action-modal').on('show.bs.modal', function (e) {
    //Set Data from clicked item
    var id = ($(e.relatedTarget).attr("data-id"));
    var item = ($(e.relatedTarget).attr("data-item"));
    var status = ($(e.relatedTarget).attr("data-status"));
    var priority = ($(e.relatedTarget).attr("data-priority"));
    var comments = ($(e.relatedTarget).attr("data-comments"));


    //Populate modal data
    $("#action-modal").find("input[name='id']").val(id);
    $("#action-modal").find("input[name='item']").val(item);
    $("#action-modal").find("textarea[name='comments']").val(comments);
    $("#action-modal").find("select[name='status']").val(status);
    $("#action-modal").find("select[name='priority']").val(priority);

});
