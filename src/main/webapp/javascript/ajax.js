/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";

function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;

}


function isLoggedIn() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200 ) {
            $("#choices").html("");
            $("#choices").load("simpleUser.html");
            $("#ajaxContent").html("");
            $("#ajaxContent").append("Welcome again "+xhr.responseText);
        }
        else if(xhr.readyState === 4 && xhr.status === 202) {
            $("#choices").html("");
            $("#choices").load("Doctor.html");
            $("#ajaxContent").html("");
            $("#ajaxContent").append("Welcome again "+xhr.responseText);
        }
        else{

        //    alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('GET', 'Login');
    xhr.send();
}

$(document).ready(function () {
    isLoggedIn();
});


function getUser() {
    let myForm = document.getElementById('box');
    let formData = new FormData(myForm);
    const data={};
    formData.forEach((value,key)=>(data[key]=value));
    var jsonData=JSON.stringify(data);
    /*var json='{\n\
            "username":'+'"'+document.forms.box.username.value+'"'+',"email":'+'"'+document.forms.box.email.value+'"'
        +',"password":'+'"'+document.forms.box.password.value+'"'+',"firstname":'+document.forms.box.firstname.value+'"'
        +',"lastname":'+'"'+document.forms.box.lastname.value+'"'+',"birthdate":'+document.forms.box.birthdate.value+'"'
        +',"gender":'+'"'+document.forms.box.gender.value+'"'+',"amka":'+'"'+document.forms.box.amka.value+'"'
        +',"country":'+'"'+document.forms.box.country.value+'"'+',"city":'+document.forms.box.city.value+'"'
        +',"address":'+'"'+document.forms.box.address.value+'"'+',"lat":'+'"'+document.getElementsByName("lat").value+'"'
        +',"lon":'+'"'+document.getElementsByName("lon").value+'"'+',"telephone":'+'"'+document.forms.box.telephone.value+'"'
        +',"height":'+'"'+document.forms.box.height.value+'"'+',"weight":'+'"'+document.forms.box.weight.value+'"'
        +',"blooddonor":'+'"'+document.forms.box.blooddonor.value+'"'+',"bloodtype":'+'"'
        +document.forms.box.bloodtype.value+'\n\"}';
*/
    var xhr = new XMLHttpRequest();
    xhr.onload=function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("Η εγγραφή σας ολοκληρώθηκε επιτυχώς!");
            $('#ajaxContent').append("<h1>Your Data</h1>");
            $('#ajaxContent').append(createTableFromJSON(responseData));

        } else if (xhr.status !== 200 && xhr.status!==201) {
            $('#ajaxContent').append('Request failed. Returned status of ' + xhr.status + "<br>");
            const responseData = JSON.parse(xhr.responseText);
            for (const x in responseData) {
                $('#ajaxContent').append("<p style='color:red'>" + x + "=" + responseData[x] + "</p>");
            }
        }
        else if(xhr.readyState ===4 && xhr.status===201){
            const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("Η εγγραφή σας ολοκληρώθηκε επιτυχώς!Περιμένετε μέχρι να σας πιστοποιήσει ο διαχειριστής.");
            $('#ajaxContent').append("<h1>Your Data</h1>");
            $('#ajaxContent').append(createTableFromJSON(responseData));
        }
    };
    xhr.open('POST', 'Registration');
    xhr.setRequestHeader('Content-type','application/json');
    xhr.send(jsonData);
}

function showLogin() {
    $("#ajaxContent").load("login.html");
}


function showRegistrationForm() {
    $("#ajaxContent").load("Registration.html");
}


function loginPOST() {

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#choices").load("simpleUser.html");
            $("#ajaxContent").html("Successful login");
        }
        else if(xhr.readyState ===4 && xhr.status === 202){
            $("#choices").load("Doctor.html");
            $("#ajaxContent").html("Successful login");
        } else if (xhr.status === 201) {
            $(document).ready(function () {
                $(location).attr("href", "Admin.html");
            });

        } else {
            $("#error").html("Wrong Credentials");
            //('Request failed. Returned status of ' + xhr.status);
        }
    }

    var data = $("#login").serialize();

    xhr.open('POST', 'Login');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function getDataRequest() {

    var xhr = new XMLHttpRequest();

    var json=JSON.stringify(data);

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("<h1>Your Data</h1>");
            $('#ajaxContent').append(createTableFromJSON(responseData));

        } else if ((xhr.status !== 200)) {
            alert('Request failed. Returned status of ' + xhr.status);
        }

    };

    xhr.open('GET', 'Registration');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function getBMI(){

    const xhr=new XMLHttpRequest();
    xhr.withCredentials=true;

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            const user = JSON.parse(xhr.responseText);

            const xhr2 = new XMLHttpRequest();
            xhr2.withCredentials = true;

            xhr2.onload = function () {
                if (xhr2.readyState === 4 && xhr2.status === 200) {
                    var bmiResponse = xhr2.responseText;
                    $("#BMI").html(bmiResponse);
                }
                else{
                    console.log("height should be from 100-250 nad weight from 20.00-300.00");
                }

            };


            var month_diff = Date.now() - new Date(user.birthdate);

            var age_dt = new Date(month_diff);

            var year = age_dt.getUTCFullYear();

            var age = Math.abs(year - 1970);
            xhr2.open("GET", "https://fitness-calculator.p.rapidapi.com/bmi?age="+age+"&weight="+user.weight+"&height="+user.height);
            xhr2.setRequestHeader("x-rapidapi-host", "fitness-calculator.p.rapidapi.com");
            xhr2.setRequestHeader("x-rapidapi-key", "dda2b60811mshc159b41dad41f82p128462jsne012f2b080df");

            xhr2.send();

        } else if (xhr2.status !== 200) {
            alert('Request failed. Returned status of ' + xhr2.status);
        }
    };

    xhr.open("GET","Registration");
    xhr.setRequestHeader("Content-type","application/json");
    xhr.send();
}

/*function setChoicesForLoggedUser() {
    $("#choices").html("");
    $("#choices").append("<h1>choices</h1>");

    $("#choices").append("<button  onclick='getDataRequest();return false;' name='data'  class='button' >DATA</button><br>");

    $("#choices").append(
        " <button name='doctorsSu' onclick='getDoctorsList();return false;' " +
        "class='button'>DOCTORS<br></button>");
    $("#choices").append("<button onclick='logout();return false;'  class='button' >LOGOUT</button><br>");
}*/

function getUsers(){
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status===200) {

            const responseData = JSON.parse(xhr.responseText);
            $('#Users').html("");

            $('#Users').html("<h1>Registered Users</h1>");
            $('#Users').append(responseData);

        } else if (xhr.status !== 200 ) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('GET', 'Registration');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function getDoctors(){
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200 ) {

            const responseData = JSON.parse(xhr.responseText);
            $('#Doctors').html("");

            $('#Doctors').html("<h1>Doctors</h1>");
            $('#Doctors').append(responseData);

        } else if (xhr.status !== 200 ) {
        //    alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('GET', 'Registration');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function getDoctorsList(){
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status===200) {

         /*   const responseData = JSON.parse(xhr.responseText);
            $('#choices').html("");

            $("#choices").append("<button  onclick='getDataRequest();return false;' name='data'  class='button' >DATA</button><br>");
            $("#choices").append("<form action='Resources' method='get'>" +
                "<button name='doctorsSu' onclick='getDoctorsList();return false;'" +
                " class='button'>DOCTORS</button><br></form>");
            $('#choices').append("<button onclick='logout()'"+" class='button'>logout</button>");
            $('#ajaxContent').append("<h1>DOCTORS</h1>")
            $('#ajaxContent').append(responseData);*/



        } else if (xhr.status !== 200) {
            alert('Request failed. There are no doctors.');
        }
    };

    xhr.open('GET', 'http://localhost:8080/HY359_PROJECT_war_exploded/health/admin/doctors');
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send();
}

function setChoicesForVisitor(){
    $("#choices").html("");
    $("#choices").load("Visitor.html");

}

function certificate() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#Notifications").html("");
            $("#Notifications").append("Doctors " + xhr.responseText + " wait for certification");
        } else {
            $("#Notifications").html("");
            $("#Notifications").append("No doctors wait for certification");
        }
    };
    xhr.open('get', 'Registration');

    xhr.send();
}

function logout(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status===201)) {

            $("#ajaxContent").html("Successful Logout");
            $("#choices").html("");
            $('#choices').append("<button onclick='showRegistrationForm()'"+" class='button'>Register</button>");
            $('#choices').append("<button onclick='showLogin()'"+" class='button'>Login</button>");
            $("#choices").append("<button onclick='setChoicesForVisitor()'"+" class='button'>Login as visitor</button>");
        } else {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function Logout(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status===201)) {

            $(document).ready(function () {
                $(location).attr("href", "index.html");
            });
        } else {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}


function deleteDoctor(username) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

        } else {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    data = username;
    xhr.open('DELETE', 'Registration?' + data);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}