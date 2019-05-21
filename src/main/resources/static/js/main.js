function loadComments () {

    this.source = null;

    this.start = function () {
        console.log("Starting source");
        var uuid = $("#uuid").val();

        this.source = new EventSource("/chat?uuid=" + uuid);
        this.source.addEventListener("message", function (event) {

            var obj = jQuery.parseJSON(event.data);
            console.log(obj.id);
            var table = $("#comments");
            table.find("tr:gt(0)").remove();

            $.each( obj.messages, function(key, value){
                console.log(value);
                table.append("<tr>" +
                        "<td>" + value.sender + "</td>" +
                        "<td>" + value.content + "</td>" +
                        "<td>" + value.time + "</td>" +
                    "</tr>");
            });

        });

        this.source.onerror = function () {
            this.close();
        };

    };

    this.stop = function() {
        console.log("Stopping source");
        this.source.close();
    }
}

comment = new loadComments();

/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    comment.start();
};
window.onbeforeunload = function() {
    comment.stop();
};
$("#send").click(function(){
    var id = $("#uuid").val();
    var sender = $("#name").val();
    var message = $("#message").val();

    $.ajax({
       url: "/message?id=" + id + "&sender=" +sender + "&message=" + message,
        async: true,
        method: "GET",
        success: function(result){
           $("#message").val("");
        }
    });
});