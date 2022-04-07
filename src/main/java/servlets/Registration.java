package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.tables.*;
import mainClasses.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Registration", value = "/Registration")
public class Registration extends HttpServlet {

    public String jsonToString(BufferedReader json) {
        Gson gson = new Gson();
        String instance = gson.fromJson(json, String.class);
        return instance;
    }

    public String getJSONFromAjax(BufferedReader reader) throws IOException{
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        return data;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        EditDoctorTable edt=new EditDoctorTable();

        EditSimpleUserTable eut=new EditSimpleUserTable();
        HttpSession session= request.getSession(true);

        if(request.getParameter("data")!=null){
            try(PrintWriter out= response.getWriter()) {
                response.setContentType("text/html;charset=utf-8");
                SimpleUser su= eut.databaseToSimpleUsers().get(session.getAttribute("loggedIn").toString());
                response.setStatus(200);
                out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>RESULTS</bold></h1><p>");
                out.println("username:"+su.getUsername()+",amka:"+su.getAmka()+
                        "<form action='Resources' method='get'>"+
                        "<input type='text' name='email' value='"+su.getEmail()+"'/>"+
                        "<input type='text' name='password' value='"+su.getPassword()+"'/>"+
                        "<input type='text' name='firstname' value='"+su.getFirstname()+"'/>"+
                        "<input type='text' name='lastname' value='"+su.getLastname()+"'/><br>"+
                        "<input type='text' name='birthdate' value='"+su.getBirthdate()+"'/>"+
                        "<input type='text' name='gender' value='"+su.getGender()+"'/>"+
                        "<input type='text' name='country' value='"+su.getCountry()+"'/>"+
                        "<input type='text' name='city' value='"+su.getCity()+"'/><br>"+
                        "<input type='text' name='address' value='"+su.getAddress()+"'/>"+
                        "<input type='text' name='lat' value='"+su.getLat()+"'/>"+
                        "<input type='text' name='lon' value='"+su.getLon()+"'/>"+
                        "<input type='text' name='telephone' value='"+su.getTelephone()+"'/><br>"+
                        "<input type ='text' name='height' value='"+su.getHeight()+"'/>"+
                        "<input type='text' name='weight' value='"+su.getWeight()+"'/>"+"blooddonor has values 0 and 1 if you want to be blood donor save 1 if you don't then write 0 on the field."+
                        "<input type='text' name='blooddonor' value='"+su.getBlooddonor()+"'/>"+
                        "<input type='text' name='bloodtype' value='"+su.getBloodtype()+"'/>"+"<br>"+
                        "<input type='submit' value='ENTER'></form>"+"/p></body></html>");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(request.getParameter("doctors")!=null) {
            int i=0;
            try {
                if (edt.records() > 0) {
                    try (PrintWriter out = response.getWriter()) {

                        String json = doctorsList();

                        response.setStatus(200);
                        out.println("<!DOCTYPE HTML><html><head><meta charset="  + "UTF-8"  + "></head><body>");
                        Iterator hmIterator = edt.databaseToDoctors().entrySet().iterator();
                        while (hmIterator.hasNext()) {

                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
                            out.println("<form action='Resources' method='get'>");
                            out.println("<input type='submit'  name='delete' value='"+
                                    "delete "+
                                    edt.databaseToDoctors().get(mapElement.getKey()).getUsername()+"'"+"/> ");
                            out.println("</form>");


                        }
                        out.println("<h1>DOCTORS</h1>");
                        out.println("<p>"+doctorsList()+"</p></body></html>");


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try (PrintWriter out = response.getWriter()) {
                        response.setContentType("text/html;charset=UTF-8");
                        String json = doctorsList();

                        response.setStatus(403);
                        out.println("<!DOCTYPE HTML><html><head><meta charset="  + "UTF-8"  + "></head><body>");
                        out.println("<h1><bold>THERE ARE NO DOCTORS</bold></h1></body></html>");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(request.getParameter("doctorsSu")!=null) {

            try (PrintWriter out = response.getWriter()) {

                String json = doctorsList();

                response.setStatus(200);
                out.println("<!DOCTYPE HTML><html><head><meta charset=" + "UTF-8" + "></head><body>");
                if(edt.records()==0){
                    out.println("<h1><bold>THERE ARE NO DOCTORS!!!!");
                    out.println("</bold></h1></body></html>");
                }
                Iterator hmIterator = edt.databaseToDoctors().entrySet().iterator();
                while (hmIterator.hasNext()) {

                    Map.Entry mapElement = (Map.Entry) hmIterator.next();
                    out.println("<form action='Resources' method='post'>");
                    out.println("<input type='submit'  name='randevouz' value='" +
                            edt.databaseToDoctors().get(mapElement.getKey()).getUsername() + "'" + "/> ");



                }
                out.println("</form>");
                out.println("<h1>DOCTORS</h1>");
                out.println("<p>" + doctorsList() + "</p></body></html>");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(request.getParameter("users")!=null) {
            try {
                if (eut.databaseToSimpleUsers().size() > 0){
                    try(PrintWriter out=response.getWriter()) {
                        String json=registeredUsers();
                        response.setContentType("text/html;charset=UTF-8");
                        response.setStatus(200);
                        out.println("<!DOCTYPE HTML><html><head><meta charset="+'"'+"UTF-8"+'"'+"></head><body>");
                        Iterator hmIterator = eut.databaseToSimpleUsers().entrySet().iterator();
                        while (hmIterator.hasNext()) {

                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
                            if(eut.databaseToSimpleUsers().get(mapElement.getKey()).getUsername().equals("admin")){
                                continue;
                            }
                            out.println("<form action='Resources' method='get'>");
                            out.println("<input type='submit'  name='deleteUsers' value='"+"delete "+
                                    eut.databaseToSimpleUsers().get(mapElement.getKey()).getUsername()+"'"+"/> ");
                            out.println("</form>");


                        }
                        out.println("<h1><bold>REGISTERED USERS</bold></h1><p>"+json+"</p></body></html>");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(request.getParameter("noUsers")!=null){
            EditDoctorNoRegistrationTable ednrt = new EditDoctorNoRegistrationTable();
            try {
                if(ednrt.records()>0){
                    response.setStatus(200);
                    try(PrintWriter out = response.getWriter()) {
                        String json = ednrt.getDoctors();
                        out.println("<!DOCTYPE HTML><html><head><meta charset="+'"'+"UTF-8"+'"'+"></head><body>");
                        out.println("<p>"+json+"</p><form action='Resources' method='post'>");
                        int i=0;
                        for(i=0;i<ednrt.doctors().size();i++) {
                            Doctor doc = (Doctor) ednrt.doctors().get(i);
                            out.println("<input type='submit' name='certify' value='"+doc.getUsername()+"'/>");
                        }
                        out.println("</form></body></html>");
                    }
                }
                else{
                    response.setStatus(403);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if(request.getParameter("blood-test")!=null){
            response.setStatus(200);
            try(PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                out.println("<h1><bold>ΠΑΡΑΚΑΛΩ ΚΑΤΑΧΩΡΗΣΤΕ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΚΑΙ ΣΤΗΝ ΣΥΝΕΧΕΙΑ ΠΑΤΗΣΤΕ ENTER");
                out.println("</bold></h1><form action='Resources' method='post'><br><br><br>");
                out.println("<input type='text' name='amka' placeholder='amka(required)' required />");
                out.println("<input type='text' name='test_date' placeholder='test date(required)' required />");
                out.println("<input type='text' name='medical_center' placeholder='medical center(required)' required />");
                out.println("<input type='text' name='blood_sugar' placeholder='blood sugar(required)' required />");
                out.println("<input type='text' name='blood_sugar_level' placeholder='blood sugar level(required)' required /><br>");
                out.println("<input type='text' name='cholesterol' placeholder='cholesterol(required)' required />");
                out.println("<input type='text' name='cholesterol_level' placeholder='cholesterol level(required)' required />");
                out.println("<input type='text' name='iron' placeholder='iron(required)' required />");
                out.println("<input type='text' name='iron_level' placeholder='iron level(required)' required /><br>");
                out.println("<input type='text' name='vitamin_d3' placeholder='vitamin d3(required)' required />");
                out.println("<input type='text' name='vitamin_d3_level' placeholder='vitamin d3 level(required)' required />");
                out.println("<input type='text' name='vitamin_b12' placeholder='vitamin b12(required)' required />");
                out.println("<input type='text' name='vitamin_b12_level' placeholder='vitamin b12 level(required)' required /><br>");
                out.println("<input type='submit' value='ENTER'></form><body><html>");



            }
        }
        else if(request.getParameter("doctor'sData")!=null){
            response.setStatus(200);
            response.setContentType("text/html;charset=utf-8");
            Doctor doc= null;
            try {
                doc = edt.databaseToDoctors().get(session.getAttribute("loggedIn").toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            try (PrintWriter out = response.getWriter()) {


                out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>RESULTS</bold></h1><p>");
                out.println("certified:1 means is certified,blooddonor:1 means is blood donor"+"username:" + doc.getUsername() + ",amka:" + doc.getAmka() +",certified:"+doc.getCertified()+
                        "<form action='Login' method='get'>" +
                        "<input type='text' name='email' value='" + doc.getEmail() + "'/>" +
                        "<input type='text' name='password' value='" + doc.getPassword() + "'/>" +
                        "<input type='text' name='firstname' value='" + doc.getFirstname() + "'/>" +
                        "<input type='text' name='lastname' value='" + doc.getLastname() + "'/><br>" +
                        "<input type='text' name='birthdate' value='" + doc.getBirthdate() + "'/>" +
                        "<input type='text' name='gender' value='" + doc.getGender() + "'/>" +
                        "<input type='text' name='country' value='" + doc.getCountry() + "'/>" +
                        "<input type='text' name='city' value='" + doc.getCity() + "'/><br>" +
                        "<input type='text' name='address' value='" + doc.getAddress() + "'/>" +
                        "<input type='text' name='lat' value='" + doc.getLat() + "'/>" +
                        "<input type='text' name='lon' value='" + doc.getLon() + "'/>" +
                        "<input type='text' name='telephone' value='" + doc.getTelephone() + "'/><br>" +
                        "<input type ='text' name='height' value='" + doc.getHeight() + "'/>" +
                        "<input type='text' name='weight' value='" + doc.getWeight() + "'/>" + "blooddonor has values 0 and 1 if you want to be blood donor save 1 if you don't then write 0 on the field." +
                        "<input type='text' name='blooddonor' value='" + doc.getBlooddonor() + "'/>" +
                        "<input type='text' name='bloodtype' value='" + doc.getBloodtype() + "'/>" + "<br>" +
                        "<input type='text' name='specialty' value='" + doc.getSpecialty()+"'/>"+
                        "<input type='text' name='doctor_info' value='"+doc.getDoctor_info()+"'/>"+"<br>"+
                        "<input type='submit' value='ENTER'></form>" + "/p></body></html>");

            }
        }
        else if(request.getParameter("patients")!=null) {
            if (session.getAttribute("loggedIn") != null) {
                Doctor doc = null;
                SimpleUser su = null;
                EditRandevouzTable ert = new EditRandevouzTable();
                try {
                    try {
                        doc = edt.databaseToDoctors().get(session.getAttribute("loggedIn").toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    boolean tmp = false;
                    try {
                        tmp = ert.containsDoctorId(doc.getDoctor_id());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (tmp == true) {
                        try (PrintWriter out = response.getWriter()) {
                            response.setStatus(202);
                            int i = 0;
                            if (ert.get_randevouz(doc.getDoctor_id()) != null) {
                                out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>RESULTS</bold></h1>");
                                out.println("<form action='Resources' method='get'>");
                                for (i = 0; i < ert.get_randevouz(doc.getDoctor_id()).size(); i++)

                                    out.println("<button name='patient' value='"+
                                    ert.get_randevouz(doc.getDoctor_id()).get(i).toString() +"'>"+
                                                    ert.get_randevouz(doc.getDoctor_id()).get(i).toString()+
                                            "</button></form></body></html>");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try (PrintWriter out = response.getWriter()) {
                            response.setStatus(202);
                            out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>RESULTS</bold></h1>");
                            out.println("You don't have patients</body></html>");
                        }
                    }

                }catch (Exception e){

                }
                }
            }
            else if(request.getParameter("therapy")!=null){
                response.setStatus(200);
                try(PrintWriter out = response.getWriter()){
                    out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>TREATMENT</bold></h1>");
                    out.println("<form action='Resources' method='post'>doctor id<input type='text' name='doctor_id' required/>");
                    out.println("user id<input type='text' name='user_id' required />");
                    out.println("start date<input type='text' name='start_date' required/>");
                    out.println("end date<input type='text' name='end_date' required />");
                    out.println("treatment<input type='text' name='treatment_text' required />");
                    out.println("blood test id<input type='text' name='blood_test_id' required />");
                    out.println("<input type='submit' value='ENTER'>");
                    out.println("</form></body></html>");
                }
            }
            else if(request.getParameter("doctorsForVisitor")!=null){
            try {
                if(edt.records() == 0){
                    response.setStatus(200);
                    try(PrintWriter out = response.getWriter()){
                        out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>THERE ARE NO DOCTORS</bold></h1>");
                        out.println("</body></html>");
                    }
                }
                else{
                    int i=0;
                    response.setStatus(200);
                    try(PrintWriter out = response.getWriter()){
                        out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>DOCTORS</bold></h1>");
                        out.println("<table border='1'><tr><th>DOCTORS</th></tr>");
                        Iterator hmIterator = edt.databaseToDoctors().entrySet().iterator();
                        while (hmIterator.hasNext() ) {

                            Map.Entry mapElement = (Map.Entry) hmIterator.next();
                            out.println("<tr><td>"+edt.databaseToDoctors().get(mapElement.getKey()).getUsername());
                            out.println("</td></tr>");
                        }
                        out.println("</table><a href=\"https://www.vrisko.gr/efimeries-farmakeion/irakleio\" target=\"_blank\"><img src=\"https://www.vrisko.gr/graphlink/Pharmacies/image/468x60_Banner_n/?Region=irakleio&NativeRegion=%ce%97%cf%81%ce%ac%ce%ba%ce%bb%ce%b5%ce%b9%ce%bf&\" border=\"0\" alt=\"Εφημερεύοντα Φαρμακεία Ηράκλειο\" /></a>\n" +
                                "<a href=\"https://www.vrisko.gr/efimeries-nosokomeion?SelectedCity=hrakleio\" target=\"_blank\"><img src=\"https://www.vrisko.gr/graphlink/Hospitals/image/468x60_Banner_n/?Prefecture=hrakleio&NativePrefecture=%ce%97%ce%a1%ce%91%ce%9a%ce%9b%ce%95%ce%99%ce%9f&\" border=\"0\" alt=\"Εφημερεύοντα Νοσοκομεία ΗΡΑΚΛΕΙΟ\" /></a>\n" +
                                "<a href=\"https://www.ekab.gr/files/entypa/EKAB-protes-voithies.pdf\">AMESH VOITHEIA</a>\n" +
                                "<a href=\"https://www.venizeleio.gr/\">VENIZELEIO</a>\n" +
                                "<a href=\"https://www.pagni.gr/\">PAGNI</a>\n" +
                                "<a href=\"https://covid19.gov.gr/\">INFO COVID</a>");
                        out.println("</body></html>");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(request.getParameter("exams")!=null){
            EditTreatmentTable ett = new EditTreatmentTable();

            try {
                SimpleUser su=eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                Treatment t = null;
                BloodTest bt = null;
                EditBloodTestTable ebt = new EditBloodTestTable();
                if(ett.getTreatments(su.getUser_id())!=null){
                    response.setStatus(200);
                    int i=0;
                    try(PrintWriter out = response.getWriter()){
                        out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>TREATMENTS AND BLOOD TESTS</bold></h1>");
                        out.println("<table border='1'><tr><th>TREATMENTS</th></tr>");
                        out.println("<tr><th>doctor id</th><th>user id</th><th>start date</th><th>end date</th>");
                        out.println("<th>treatment</th><th>blood test id</th></tr>");
                        for(i=0;i<ett.getTreatments(su.getUser_id()).size();i++){
                            t= (Treatment) ett.getTreatments(su.getUser_id()).get(i);
                            out.println("<tr><td>"+ t.getDoctor_id()+"</td>");
                            out.println("<td>"+ t.getUser_id()+"</td>");
                            out.println("<td>"+ t.getStart_date()+"</td>");
                            out.println("<td>"+ t.getEnd_date()+"</td>");
                            out.println("<td>"+ t.getTreatment_text()+"</td>");
                            out.println("<td>"+t.getBloodtest_id()+"</td></tr>");
                        }
                        out.println("</table><table border='1'><tr><th>Blood Tests</th></tr>");
                        out.println("<tr><th>amka</th><th>test date</th><th>medical center</th>");
                        out.println("<th>blood sugar</th><th>blood sugar level</th><th>cholesterol</th>");
                        out.println("<th>cholesterol level</th><th>iron</th><th>iron level</th>");
                        out.println("<th>vitamin d3</th><th>vitamin d3 level</th><th>vitamin b12</th><th>vitamin b12 level</th></tr>");
                        for(i=0;i< ebt.getBloodTests(su.getAmka());i++){
                            bt= (BloodTest) ebt.getBloodTest(su.getAmka()).get(i);
                            out.println("<tr><td>"+bt.getAmka()+"</td><td>"+bt.getTest_date()+"</td><td>"+bt.getMedical_center()+"</td>");
                            out.println("<td>"+bt.getBlood_sugar()+"</td><td>"+bt.getBlood_sugar_level()+"</td>");
                            out.println("<td>"+bt.getCholesterol()+"</td><td>"+bt.getCholesterol_level()+"</td>");
                            out.println("<td>"+bt.getIron()+"</td><td>"+bt.getIron_level()+"</td>");
                            out.println("<td>"+bt.getVitamin_d3()+"</td><td>"+bt.getVitamin_d3_level()+"</td>");
                            out.println("<td>"+bt.getVitamin_b12()+"</td><td>"+bt.getVitamin_b12_level()+"</td></tr>");
                        }
                        out.println("</table><table border='1'><tr><th>DIFFERENCE</th></tr>");
                        out.println("<th>blood sugar</th><th>cholesterol</th>");
                        out.println("<th>iron</th>");
                        out.println("<th>vitamin d3</th><th>vitamin b12</th></tr>");
                        for(i=1;i<ebt.getBloodTests(su.getAmka());i++){
                            bt= (BloodTest) ebt.getBloodTest(su.getAmka()).get(i);
                            BloodTest bt2= (BloodTest) ebt.getBloodTest(su.getAmka()).get(i-1);
                            out.println("<td>"+Double.parseDouble(String.valueOf(bt.getBlood_sugar()- bt2.getBlood_sugar()))+"</td>");
                            out.println("<td>"+Double.parseDouble(String.valueOf(bt.getCholesterol()- bt2.getCholesterol()))+"</td>");
                            out.println("<td>"+Double.parseDouble(String.valueOf(bt.getIron()- bt2.getIron()))+"</td>");
                            out.println("<td>"+Double.parseDouble(String.valueOf(bt.getVitamin_d3()- bt2.getVitamin_d3()))+"</td>");
                            out.println("<td>"+Double.parseDouble(String.valueOf(bt.getVitamin_b12()- bt2.getVitamin_b12()))+"</td></tr>");
                        }
                        out.println("</table>");
                        out.println("</body></html>");
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else {
            response.setStatus(403);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        EditSimpleUserTable eut = new EditSimpleUserTable();
        SimpleUser su=new SimpleUser();
        Gson gson=new Gson();

        Doctor doctor=new Doctor();
        EditDoctorNoRegistrationTable doc2=new EditDoctorNoRegistrationTable();
        EditDoctorTable doc=new EditDoctorTable();
        String username=null;
        String JSON=this.getJSONFromAjax(request.getReader());
        JsonParser parser=new JsonParser();
        JsonObject obj=parser.parse(JSON).getAsJsonObject();
        username=obj.get("username").getAsString();
        if(obj.get("address")!=null) {
            String address = obj.get("address").getAsString();
        }
        if(obj.get("office")!=null) {
            String address = obj.get("office").getAsString();
        }
        //  username=gson.fromJson(JSON.valueOf(username),String.class);

        String email=obj.get("email").getAsString();
        String amka=obj.get("amka").getAsString();
        HttpSession session=request.getSession(true);
        String user=obj.get("user").getAsString();

        /* TODO output your page here. You may use following sample code. */
        try {
            if (doc.databaseUserNameToDoctor(username) != null) {

                String JsonString = "This username already exists at doctors' table";

                response.getWriter().write(JsonString);
                response.setStatus(403);
            } else if (eut.databaseUserNameToSimpleUser(username) != null) {
                response.setStatus(409);

                JsonObject jo = new JsonObject();
                jo.addProperty("error", "Username Already Taken");
                response.getWriter().write(jo.toString());
            } else if (doc.databaseAmkaToDoctor(amka) != null) {

                response.sendError(0, "Error:this amka already exists");
                response.setStatus(403);
            } else if (eut.databaseAmkaToSimpleUser(amka) != null) {
                response.sendError(0, "Error:this amka already exists");
                response.setStatus(403);
            } else if (doc.databaseEmailToDoctor(email) != null) {
                response.sendError(0, "Error:this email already exists");
                response.setStatus(403);
            } else if (eut.databaseMailToSimpleUser(email) != null) {
                response.sendError(0, "Error:this email already exists");
                response.setStatus(403);
            } else {
                if (user.equals("Simple")) {

                    su.setBloodDonor(Integer.parseInt(obj.get("blooddonor").getAsString()));
                    su.setAddress(obj.get("address").getAsString());
                    su.setUsername(username);
                    su.setEmail(email);
                    su.setFirstname(obj.get("firstname").getAsString());
                    su.setLastname(obj.get("lastname").getAsString());
                    su.setAmka(amka);
                    su.setGender(obj.get("gender").getAsString());
                    su.setBirthdate(obj.get("birthdate").getAsString());
                    su.setCity(obj.get("city").getAsString());
                    su.setCountry(obj.get("country").getAsString());
                    su.setPassword(obj.get("password").getAsString());
                    su.setBloodtype(obj.get("bloodtype").getAsString());
                    su.setTelephone(obj.get("telephone").getAsString());
                    if (!obj.get("height").getAsString().isEmpty()) {
                        su.setHeight(Integer.parseInt(obj.get("height").getAsString()));
                    }
                    if (!obj.get("weight").getAsString().isEmpty()) {
                        su.setWeight(Double.parseDouble(obj.get("weight").getAsString()));
                    }


                    su.setLat(Double.parseDouble(obj.get("lat").getAsString()));
                    su.setLon(Double.parseDouble(obj.get("lon").getAsString()));

                    eut.addNewSimpleUser(su);
                    String json= eut.simpleUserToJSON(su);
                    response.getWriter().write(json);
                    response.setStatus(200);

                }
                if (obj.get("user").getAsString().equals("Doctor")) {
                    session.setAttribute("loggedIn", username);
                    doctor.setBloodDonor(Integer.parseInt(obj.get("blooddonor").getAsString()));
                    doctor.setAddress(obj.get("office").getAsString());
                    doctor.setUsername(username);
                    doctor.setEmail(email);
                    doctor.setFirstname(obj.get("firstname").getAsString());
                    doctor.setLastname(obj.get("lastname").getAsString());
                    doctor.setAmka(amka);
                    doctor.setGender(obj.get("gender").getAsString());
                    doctor.setBirthdate(obj.get("birthdate").getAsString());
                    doctor.setCity(obj.get("city").getAsString());
                    doctor.setCountry(obj.get("country").getAsString());
                    doctor.setPassword(obj.get("password").getAsString());
                    doctor.setBloodtype(obj.get("bloodtype").getAsString());
                    doctor.setTelephone(obj.get("telephone").getAsString());
                    if (!obj.get("height").getAsString().isEmpty()) {
                        doctor.setHeight(Integer.parseInt(obj.get("height").getAsString()));
                    }
                    if (!obj.get("weight").getAsString().isEmpty()) {
                        doctor.setWeight(Double.parseDouble(obj.get("weight").getAsString()));
                    }

                    doctor.setLat(Double.parseDouble(obj.get("lat").getAsString()));
                    doctor.setLon(Double.parseDouble(obj.get("lon").getAsString()));
                    doctor.setSpecialty(obj.get("specialty").getAsString());
                    if (!obj.get("textarea").getAsString().isEmpty()) {
                        doctor.setDoctor_info(obj.get("textarea").getAsString());
                    }
                    doctor.setCertified(0);

                    doc2.addNewDoctorNoRegistration(doctor);
                    String JsonString = doc2.doctorToJSON(doctor);

                    response.setStatus(201);
                    response.getWriter().write(doctor.getUsername());

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String doctorsList() throws SQLException, ClassNotFoundException {
        EditDoctorTable edt=new EditDoctorTable();
        int i=0;
        if (edt.records() > 0) {
            Gson gson = new Gson();
            String json = null;

            Iterator hmIterator = edt.databaseToDoctors().entrySet().iterator();
            while (hmIterator.hasNext() ) {

                Map.Entry mapElement = (Map.Entry)hmIterator.next();

                if (json == null) {
                    json = "[{" + '"' + "username" + '"' + ":" +'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getUsername()+'"'+
                            "," + '"' + "first name" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getFirstname()+'"'+
                            "," + '"' + "last name" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getLastname()+'"'+
                            "," + '"' + "birth date" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getBirthdate()+'"'+
                            "}]";
                } else {
                    json += "[{" + '"' + "username" + '"' + ":" +'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getUsername()+'"'+
                            "," + '"' + "first name" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getFirstname()+'"'+
                            "," + '"' + "last name" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getLastname()+'"'+
                            "," + '"' + "birth date" + '"' + ":"+'"'+
                            edt.databaseToDoctors().get(mapElement.getKey()).getBirthdate()+'"'+
                            "}]";
                }
            }
            return json;
        }
        return null;
    }

    public String registeredUsers() throws SQLException, ClassNotFoundException {
        EditSimpleUserTable est=new EditSimpleUserTable();
        if (est.getSimpleUsers() > 0) {
            Gson gson = new Gson();
            String json = null;
            Iterator hmIterator = est.databaseToSimpleUsers().entrySet().iterator();
            while (hmIterator.hasNext()){

                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                if(mapElement.getKey().equals("admin")){
                    continue;
                }
                if (json == null) {
                    json = "[{" + '"' + "username" + '"' + ":" + est.databaseToSimpleUsers().get(mapElement.getKey()).getUsername() +
                            "," + '"' + "first name" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getFirstname()+
                            "," + '"' + "last name" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getLastname() +
                            "," + '"' + "birth date" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getBirthdate() + "}]";
                } else {
                    json += "[{" + '"' + "username" + '"' + ":" + est.databaseToSimpleUsers().get(mapElement.getKey()).getUsername() +
                            "," + '"' + "first name" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getFirstname()+
                            "," + '"' + "last name" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getLastname() +
                            "," + '"' + "birth date" + '"' + ":" +est.databaseToSimpleUsers().get(mapElement.getKey()).getBirthdate() + "}]";
                }
            }
            return json;
        }
        return null;
    }
}
