$("#btn1").click(function () {

    console.log("Start")

    $.ajax({
       url:"db/db.txt",
        async:false,//default=true(async) //false =sync(blocking)
        success:function (resp){
           $("#p1").text(resp);
        },

        error:function (xhr){
        console.log(xhr)
    }


    });

    console.log("Ended");
});

$("#btn2").click(function (){
    $.ajax({
        url:"db/db.xml",

        success:function (r){
            console.log(r);
            let  ob=$(r);
            console.log(ob.children().children().length)

            for (let i=0;i<ob.children().children().length;i++){
               let id=  ob.children().children().eq(i).find("id").text();
             let name=  ob.children().children().eq(i).find("name").text();
            let address=   ob.children().children().eq(i).find("address").text();
            let salary=   ob.children().children().eq(i).find("salary").text();

                let row=`<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${salary}</td></tr>`
                $("#tb").append(row);
            }
        },
        error:function (e){
            console.log(e)
        }

    })
})

$("#btn3").click(function (){
    $("#tbjson").empty();
    $.ajax({
        url:"db/db.json",
        success:function (ob){
            // console.log(js);
            // let  ob=$(js);
            // console.log(ob[js])

            for (let i in ob){
                let id=  ob[i].id;
                let name=  ob[i].name;
                let address= ob[i].address;
                let salary=  ob[i].salary;

                let rows=`<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${salary}</td></tr>`
                $("#tbjson").append(rows);
            }
        }
    })
});