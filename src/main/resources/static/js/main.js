$(".my.cup").click(function(){
    sowFunction(this);
});

function Session () {
    this.source = null;
    this.start = function () {
        console.log("Starting source");
        var uuid = $("#uuid").val();
        this.source = new EventSource("/session?uuid=" + uuid);
        this.source.addEventListener("message", function (event) {

            var obj = jQuery.parseJSON(event.data);

            var myPlayer = $("#myPlayer").val();
            console.log(myPlayer + " " + obj.mancala.isGameOver + " " + obj.mancala.winner);
            if (obj.mancala.isGameOver){
                if (obj.mancala.winner === myPlayer){
                    $("#winnerAlert").show();
                } else {
                    $("#loserAlert").show()
                }
                $(".my.cup").off("click");

            }

            $("#activePlayer").val(obj.mancala.activePlayer);
            var mine;
            $.each(obj.mancala.cups, function(key, value){
                mine = (value.owner === myPlayer) ? "my" : "opponent";
                $("." + mine + "." + value.type.toLowerCase() +  "." + key % 7).html(value.stones).attr("game.html", key);
            });

            this.activePlayer = obj.mancala.activePlayer;
            if (obj.mancala.activePlayer === myPlayer){
                $(".my.player").show();
                $(".opponent.player").hide();
            } else {
                $(".my.player").hide();
                $(".opponent.player").show();
            }
        });

        this.source.onerror = function () {
            this.close();
        };
    };

    this.stop = function() {
        console.log("Stopping source");
        this.source.close();
    };
}

session = new Session();

/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    session.start();
};
window.onbeforeunload = function() {
    session.stop();
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

var sowFunction = function sow(control){
    var id = $("#uuid").val();
    var myPlayer = $("#myPlayer").val();
    var activePlayer = $("#activePlayer").val();
    var index = $(control).attr("game.html");

    if (activePlayer != myPlayer){
        $("#notYourTurnAlert").show();
        return;
    }

    $.ajax({
        url: "/sow?id=" + id + "&index=" + index,
        async: true,
        method: "GET",
        success: function(result){
            $("#message").val("");
        }
    });
}

