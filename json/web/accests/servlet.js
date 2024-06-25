
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
          alert(res);
           loadAllCustomers();

       }

   })

});


$("#btndelete").click(function (){
    var formdata=   $("#cusID").val();
    $.ajax({
        url: "customer?cusID="+formdata,
        method: "DELETE",
        // data:data,
        success:function (res){
            alert(res);
            loadAllCustomers();

        }

    })
})


$("#btnUpdate").click(function (){

});


function loadAllCustomers(){
    $("#tbjson").empty();
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
     rowBack();
        }
    })
}


function rowBack(){
    $("#tbjson>tr").click(function (){
        let id= $(this).children().eq(0).text();
        let name= $(this).children().eq(0).text();
        let address= $(this).children().eq(0).text();
        let salary= $(this).children().eq(0).text();

        $("#cusID").val(id);
        $("#cusName").val(name);
        $("#cusAddress").val(address);
        $("#cusSalary").val(salary);



    })


}