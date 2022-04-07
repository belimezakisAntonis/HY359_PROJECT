/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";

var x = document.getElementById("parentMap");
var osm=[];
var temp=[];
var temp2=[];
var temp3=[];
var position=[];


function loadOSM(){

    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    var address2=document.getElementsByName("office").value;
    var address=document.getElementById("address").value+","+document.getElementById("city").value+","+document.getElementById("country").value;
    xhr.addEventListener("readystatechange", function () {

        if (this.readyState === this.DONE ) {
            const obj = JSON.parse(xhr.responseText);
            position=obj;
            temp=JSON.stringify(obj[0].display_name);

            if(temp.includes("Heraklion") || temp.includes("Agios Nikolaos")  || temp.includes("Rethimni")  || temp.includes("Chania")){
                var text='"display_name"'+":"+temp;

                document.getElementById("message").innerHTML+=text;
                osm[0]=text[16];
                for(let i=17;text[i]!=",";i++){
                    osm[0]+=text[i];

                }
                document.getElementById("Map").innerHTML+='<button id="button"  style="height:400px;width:800px" ></button> ';
                document.getElementById("button").addEventListener("click",setMarker);


            }
            if(!temp.includes("Heraklion") && !temp.includes("Agios Nikolaos")  && !temp.includes("Rethimni")  && !temp.includes("Chania")){
                var text="This service is only available at Crete";
                document.getElementById("message").innerHTML+=text;
            }

        }

    });
    if(document.getElementById("office")!==null){
        document.getElementById("office").onchange=function(){
            removeDivs();


            document.getElementById("parentMap").innerHTML+='<div id="message"></div>';
            document.getElementById("parentMap").innerHTML+='<div id="Map"></div>';
        };
    }
    if(document.getElementById("address")!==null){
        document.getElementById("address").onchange=function(){
            removeDivs();


            document.getElementById("parentMap").innerHTML+='<div id="message"></div>';
            document.getElementById("parentMap").innerHTML+='<div id="Map"></div>';
        };
    }
    xhr.open("GET", "https://nominatim.openstreetmap.org/search?format=json&polygon=1&addressdetails=1&q="+address);
    xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
    xhr.setRequestHeader("x-rapidapi-key", "dda2b60811mshc159b41dad41f82p128462jsne012f2b080df");

    xhr.send();
}

function setMarker(){
    marker(position[0].lat,position[0].lon,osm);

}

function marker(pos1,pos2,osm){
    var lat=pos1;
    var lon=pos2;
    var message=osm;
    //Orismos Marker
    var map = new OpenLayers.Map("Map");
    var mapnik         = new OpenLayers.Layer.OSM();
    map.addLayer(mapnik);
    //Markers
    var markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);

    //Protos Marker
    var position=setPosition(lat,lon);
    var mar=new OpenLayers.Marker(position);
    markers.addMarker(mar);
    mar.events.register('mousedown', mar, function(evt) {
            handler(position,message,map);
        }
    );
    //Orismos zoom
    const zoom           = 5;
    map.setCenter(position, zoom);

}
//Orismos Thesis
function setPosition(lat, lon){
    var fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
    var toProjection   = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
    var position       = new OpenLayers.LonLat(lon, lat).transform( fromProjection, toProjection);

    return position;
}

//Orismos Handler

function handler(position, message,map){
    var popup = new OpenLayers.Popup.FramedCloud("Popup",
        position, null,
        message, null,
        true // <-- true if we want a close (X) button, false otherwise
    );
    map.addPopup(popup);
    var div = document.getElementById("Map");
    div.innerHTML += 'Energopoitihike o Handler<br>';


}

function removeDivs(){
    let element = document.getElementById("parentMap");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function automatic(){


    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }

}


function showPosition(position) {
    var k=0,l=0,n=0;
    if(document.forms.box.user.value==="Simple"){
        document.forms.box.innerHTML+="<input type="+'"'+"text"+'"'+"  name="+'"'+"lat"+'"'+" id="+'"'+"lat"+'"'+" />";
        document.forms.box.innerHTML+="<input type="+'"'+"text"+'"'+"  name="+'"'+"lon"+'"'+" id="+'"'+"lon"+'"'+" />";

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                const obj=JSON.parse(xhr.responseText);

                temp=JSON.stringify(obj.display_name);
                temp2=JSON.stringify(obj.lat);

                temp3=JSON.stringify(obj.lon);

                var text='"display_name"'+":"+temp;
                var text2='"lat"'+":"+temp2;
                var text3='"lon"'+":"+temp3;
                osm[0]=text[16];
                osm[2]=text2[7];
                osm[3]=text3[7];
                for(let i=17;text[i]!==",";i++){
                    osm[0]+=text[i];
                    k=i;
                }
                for(let o=8;text2[o]!=='"';o++){
                    osm[2]+=text2[o];

                }
                for(let O=8;text3[O]!=='"';O++){
                    osm[3]+=text3[O];

                }
                document.getElementById("address").value=osm[0];
                console.log("address:"+osm[0]);
                document.getElementById("lat").value=osm[2];
                document.getElementById("lon").value=osm[3];
                osm[1]=text[k+2];
                for(let j=k+3; text[j]!==","; j++){
                    osm[1]+=text[j];
                }
                document.getElementById("city").value=osm[1];

                marker(position.coords.latitude,position.coords.longitude,osm[0]);
            }
        });
        xhr.open("GET", "https://nominatim.openstreetmap.org/reverse?format=json&lat="+position.coords.latitude+"&lon="+ position.coords.longitude+"&zoom=17&addressdetails=1");
        xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
        xhr.setRequestHeader("x-rapidapi-key", "dda2b60811mshc159b41dad41f82p128462jsne012f2b080df");

        xhr.send();
    }
    else{
        document.forms.box.innerHTML+="<input type="+'"'+"text"+'"'+"  name="+'"'+"lat"+'"'+" id="+'"'+"lat"+'"'+" />";
        document.forms.box.innerHTML+="<input type="+'"'+"text"+'"'+"  name="+'"'+"lon"+'"'+" id="+'"'+"lon"+'"'+" />";
        var address2=document.getElementById("office").value;

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                const obj=JSON.parse(xhr.responseText);

                temp=JSON.stringify(obj.display_name);
                temp2=JSON.stringify(obj.lat);

                temp3=JSON.stringify(obj.lon);

                var text='"display_name"'+":"+temp;
                var text2='"lat"'+":"+temp2;
                var text3='"lon"'+":"+temp3;
                osm[0]=text[16];
                osm[2]=text2[7];
                osm[3]=text3[7];
                for(let i=17;text[i]!==",";i++){
                    osm[0]+=text[i];
                    k=i;
                }
                for(let o=8;text2[o]!=='"';o++){
                    osm[2]+=text2[o];

                }
                for(let O=8;text3[O]!=='"';O++){
                    osm[3]+=text3[O];

                }
                document.getElementById("office").value=osm[0];
                document.getElementById("lat").value=osm[2];
                document.getElementById("lon").value=osm[3];
                osm[1]=text[k+2];
                for(let j=k+3; text[j]!=","; j++){
                    osm[1]+=text[j];
                }


                marker(position.coords.latitude,position.coords.longitude,osm[0]);
            }
        });
        xhr.open("GET", "https://nominatim.openstreetmap.org/reverse?format=json&lat="+position.coords.latitude+"&lon="+ position.coords.longitude+"&zoom=17&addressdetails=1");
        xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
        xhr.setRequestHeader("x-rapidapi-key", "dda2b60811mshc159b41dad41f82p128462jsne012f2b080df");

        xhr.send();
    }
}



