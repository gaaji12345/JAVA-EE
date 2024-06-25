
$("#btn3").click(function (){
    // $('#tbjson').empty();
    loadAllCustomers();

});


$("#btnsave").click(function (){
 var data=   $("#customerForm").serialize();
 console.log(data)

   $.ajax({
       url: "customer",
       method: "POST",
       data:data,
       success:function (res){
           console.log(res);
           loadAllCustomers();

       }

   })

});


function loadAllCustomers(){
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
}