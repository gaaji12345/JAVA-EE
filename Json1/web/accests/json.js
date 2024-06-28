
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
    var formdata= $("#cusID").val();
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
    var formdata=   $("#customerForm").serialize();

    $.ajax({
        url: "customer?"+formdata,
        method: "PUT",
        // data: formdata,
        success: function (res) {
            console.log(res)

            // alert(res);
            loadAllCustomers();

        }
    })
});


function loadAllCustomers(){
    $("#tbjson").empty();
    $.ajax({
        url:"customer",
        method:"GET",
       // dataType:"json",//please convert the response into jason
        success:function (r){
            for (const customer of r.data){
                // $("#tbjson").empty();
                console.log( typeof r);
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
        let name= $(this).children().eq(1).text();
        let address= $(this).children().eq(2).text();
        let salary= $(this).children().eq(3).text();

        $("#cusID").val(id);
        $("#cusName").val(name);
        $("#cusAddress").val(address);
        $("#cusSalary").val(salary);



    })


}