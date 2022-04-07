/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
function validate(){
    if(validSSN()===true){

        var form=document.forms.box;
        var password1=form.password.value;
        var password2=form.pass2.value;
        console.log("password1:"+password1);
        console.log("password2:"+password2);
        if(password1!==password2){
            document.getElementById("message").innerHTML='Passwords must be the same';
            return false;

        }
        else{
            document.getElementById('message4').style.display='none';
            document.getElementById("message").innerHTML='Correct!';
            return true;
        }
    }
    document.getElementById("message4").innerHTML='You do not type valid social security number.The form for the first 6  digits should be:DDMMYY';
    return false;

}

function showPass(){
    var form=document.forms.box;
    if(form.password.type==="text" && form.pass2.type==="text"){
        form.password.type="password";
        form.pass2.type="password";
        document.getElementById("myBut").value="Show Password";
    }
    else{
        form.password.type="text";
        form.pass2.type="text";
        document.getElementById("myBut").value="Hide Password";
    }
}


function calculatePasswordStrength2(){
    var form=document.forms.box;
    var password2=form.pass2.value;
    const matches = password2.match(/(.)\1/g) || []
    if (matches.length >= 0.5*password2.length || password2.length<8 || password2.match(/[0-9]/g).length>=0.5*password2.length) {
        document.getElementById("message2").innerHTML='Weak';
    }
    else if(matches.length<=0.2*password2.length && matches.length < 0.5*password2.length && password2.length>=8
        && password2.match(/[0-9]/g).length<0.5*password2.length){
        document.getElementById("message2").innerHTML='Strong';
    }
    else{
        document.getElementById("message2").innerHTML='Medium';
    }
}


function calculatePasswordStrength1() {
    var form=document.forms.box;
    const  password1=form.password.value;

    if(password1!==null){
        const matches = password1.match(/(.)\1/g) || []
        if (matches.length >= 0.5*password1.length || password1.length<8 || password1.match(/[0-9]/g).length>=0.5*password1.length) {
            document.getElementById("message1").innerHTML='Weak'
        }
        else if(matches.length<=0.2*password1.length){
            document.getElementById("message1").innerHTML='Strong'
        }
        else{
            document.getElementById("message1").innerHTML='Medium'
        }
    }



}

function radioButtonDefault(){
    document.getElementById('general').style.display='none';
    document.getElementById('internist').style.display='none';
    document. getElementById('text-area'). style.display = 'none';
    document.getElementById('office').style.display='none';
    document.forms.box.innerHTML+="<input type='"+"text'"+" id='"+"address'"+"minlngth='"+"5'"+"maxlength='"+"50'"+" placeholder='"+"addresss'"+"></>";
}

function radioButtonDoctor(){
    var elem = document.getElementById('address');
    elem.parentNode.removeChild(elem);


    var form=document.forms.box;
    form.innerHTML+="<input type="+"'"+"text"+"'"+" id="+"'"+"office"+"'"+" name="+"'"+"office"+"'"+" minlength="+"'"+"5"+"'"+" maxlength="+"'"+"50"+"'"+ " placeholder="+"'"+"doctor office address"+"'"+"/>";
    form.innerHTML+="<input type="+"'"+"radio"+"'"+" id="+"'"+"general"+"'"+" name="+"'"+"specialty"+"'"+ " value="+"'"+"general"+"'"+"/>"+"General";
    form.innerHTML+="<input type="+"'"+"radio"+"'"+" name="+"'"+"specialty"+"'"+" id="+"'"+"internist"+"'"+ " value="+"'"+"internist"+"'"+"/>"+"Internist";
    form.innerHTML+="<textarea id="+"'"+"text-area"+"'"+" name="+"'"+"textarea"+"'"+" cols="+"'"+"50"+"'"+" rows="+"'"+"3"+"'"+">Informations about the doctor</textarea>";
}

function validSSN(){


    const value=document.getElementById("start").value;
    var ssn=value;
    const input=document.getElementById("ssn").value;
    ssn=value.replace(ssn,value[8]+value[9]+value[5]+value[6]+value[2]+value[3]);


    for(let i=0;i<6;i++){
        if(ssn[i]!=input[i]){

            return false;
        }
    }

    return true;
}

