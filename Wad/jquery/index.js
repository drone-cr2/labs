$(document).ready(function(){
    $("#color").on("input change",function(){
        $("body").css("background-color",$("#color").val())
        if($("#color").val()<"#242424"){
            $("h1").css("color","#ffffff")
        }
        else{$("h1").css("color","#000000")}
    })
})