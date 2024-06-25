$("#btn3").click(function (){
    // $('#tbjson').empty();
    $.ajax({
        url:"customer",
        method:"GET",
        dataType:"json",//please convert the response into jason
        success:function (resp){
            for (const customer of resp){
                // $("#tbjson").empty();
                console.log( typeof resp);
                let row=`<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.salary}</td></tr>`
                $("#tbjson").append(row);
            }

        }
    })
});

$("#btnItemJson").click(function (){
    $.ajax({
        url:"item",
        method:"GET",
        // dataType:"json",//please convert the response into jason
        success:function (resp){
            for (const item of resp){
                // $("#tbjson").empty();
                console.log( typeof resp);
                let row=`<tr><td>${item.code}</td><td>${item.description}</td><td>${item.price }</td><td>${item.qty}</td></tr>`
                $("#tbItemjson").append(row);
            }

        }
    })
})