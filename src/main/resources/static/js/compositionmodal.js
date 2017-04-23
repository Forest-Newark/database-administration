/**
 * Created by forestnewark on 4/22/17.
 */
$('#composition-modal').on('show.bs.modal', function (e) {
    //Set Data from clicked item
    var id = ($(e.relatedTarget).attr("data-id"));
    var catagory = ($(e.relatedTarget).attr("data-catagory"));
    var libraryNumber = ($(e.relatedTarget).attr("data-librarynumber"));
    var title = ($(e.relatedTarget).attr("data-title"));
    var composer = ($(e.relatedTarget).attr("data-composer"));
    var arranger = ($(e.relatedTarget).attr("data-arranger"));
    var ensemble = ($(e.relatedTarget).attr("data-ensemble"));
    var copyright = ($(e.relatedTarget).attr("data-copyright"));
    var notes = ($(e.relatedTarget).attr("data-notes"));


    //Populate modal data
    $("#composition-modal").find("input[name='id']").val(id);
    $("#composition-modal").find("input[name='title']").val(title);
    $("#composition-modal").find("input[name='composer']").val(composer);
    $("#composition-modal").find("input[name='arranger']").val(arranger);
    $("#composition-modal").find("input[name='libraryNumber']").val(libraryNumber);
    $("#composition-modal").find("select[name='catagory']").val(catagory);
    $("#composition-modal").find("select[name='ensemble']").val(ensemble);
    $("#composition-modal").find("input[name='copyright']").val(copyright);
    $("#composition-modal").find("input[name='notes']").val(notes);
});